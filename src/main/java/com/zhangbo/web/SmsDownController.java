package com.zhangbo.web;

import com.zhangbo.model.BSTableEntity;
import com.zhangbo.model.ResultInfo;
import com.zhangbo.model.SmsDown;
import com.zhangbo.service.SmsDownService;
import com.zhangbo.utils.CsvUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by zhangbo on 2017/5/9.
 */
@Controller
@RequestMapping("/sms-down")
public class SmsDownController {

    private static Logger logger = Logger.getLogger(SmsDownController.class);

    @Autowired
    private SmsDownService smsDownService;

    @RequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<SmsDown> list(
            @RequestParam(value = "inTime", required = false) String inTime,
            @RequestParam(value = "md5", required = false) String md5) {
        SmsDown smsDown = new SmsDown();
        if (inTime != null && inTime.length() > 0) {
            smsDown.setInTime(inTime);
        }
        if (md5 != null && md5.length() > 0) {
            smsDown.setMd5(md5);
        }
        Example<SmsDown> example = Example.of(smsDown);
        List<SmsDown> smsDownList = new ArrayList<>();
        try {
            smsDownList = smsDownService.findAll(example);
        } catch (Exception e) {
            logger.error("查询SmsDown列表异常", e);
        }
        return smsDownList;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delete(@RequestParam(value = "id", required = true) Integer[] id) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            List<SmsDown> smsDownList = smsDownService.findAll(Arrays.asList(id));
            smsDownService.delete(smsDownList);
            resultInfo.setMessage("删除成功");
            resultInfo.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            resultInfo.setMessage("删除失败");
            resultInfo.setSuccess(Boolean.FALSE);
            logger.error("删除smsdown失败");
        }
        return resultInfo;
    }


    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(@RequestParam(value = "id", required = true) Integer[] idArr,
                       HttpServletResponse response) {
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + System.currentTimeMillis() + ".csv");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            List<SmsDown> smsDownList = smsDownService.findAll(Arrays.asList(idArr));
            List<String[]> strList = parseSmsDown2Csv(smsDownList);
            CsvUtils.writeCSV(SmsDown.EXPORT_HEADERS, strList, outputStream);
        } catch (Exception e) {
            logger.error("导出smsdown异常", e);
        }
    }


    @RequestMapping(value = "templet", method = RequestMethod.GET)
    public void templet(HttpServletRequest request, HttpServletResponse response) {
        InputStream in = null;
        OutputStream out = null;
        try {
            String filePath = request.getServletContext().getRealPath(
                    "templet" + File.separator + "smsdown.csv");
            File file = new File(filePath);
            if (!file.isFile()) {
                return;
            }
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=smsdown.csv");
            in = new FileInputStream(file);
            out = response.getOutputStream();
            byte[] buff = new byte[in.available()];
            in.read(buff);
            out.write(buff);
            out.flush();
        } catch (Exception e) {
            logger.error("下载模板错误");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "import", method = RequestMethod.POST)
    public ResultInfo csvImport(MultipartFile uploadFile) {
        ResultInfo resultInfo = new ResultInfo();
        try (InputStream inputStream = uploadFile.getInputStream()) {
            List<String[]> list = CsvUtils.readCSV(inputStream);
            if (list != null) {
                List<SmsDown> smsDownList = parseCsv2SmsDown(list);
                smsDownService.save(smsDownList);
                resultInfo.setSuccess(Boolean.TRUE);
                resultInfo.setMessage("导入成功");
            }
        } catch (Exception e) {
            resultInfo.setSuccess(Boolean.FALSE);
            resultInfo.setMessage("导入失败");
            logger.error("导入smsdown失败", e);
        }
        return resultInfo;
    }


    @RequestMapping("/index")
    public String index() {
        return "smsdown/list";
    }


    /**
     * 将smsdown转换为csv
     *
     * @param smsDownList
     * @return
     * @throws Exception
     */
    private static List<String[]> parseSmsDown2Csv(List<SmsDown> smsDownList) throws Exception {
        List<String[]> resultList = new ArrayList<>();
        if (smsDownList == null) {
            return resultList;
        }
        for (SmsDown smsDown : smsDownList) {
            StringBuilder sb = new StringBuilder();
            Class clazz = smsDown.getClass();
            for (String field : SmsDown.EXPORT_HEADERS_FIELDS) {
                String getMethod = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
                Method method = clazz.getDeclaredMethod(getMethod);
                Object obj = method.invoke(smsDown);
                String value = "";
                if (obj != null) {
                    value = obj.toString();
                }
                sb.append(value + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            resultList.add(sb.toString().split(","));
        }
        return resultList;
    }


    /**
     * 将csv转换为smsdown
     *
     * @param list
     * @return
     */
    private static List<SmsDown> parseCsv2SmsDown(List<String[]> list) throws Exception {
        List<SmsDown> smsDownList = new ArrayList<>();
        String[] fields = SmsDown.EXPORT_HEADERS_FIELDS;
        if (list == null && list.size() == 0) {
            for (String[] line : list) {
                if (line == null || line.length == 0) {
                    continue;
                }
                SmsDown smsDown = new SmsDown();
                Class clazz = smsDown.getClass();
                for (int i = 1; i < fields.length; i++) {
                    Field field = clazz.getDeclaredField(fields[i]);
                    field.setAccessible(true);
                    String type = field.getType().toString();
                    if (line[i] != null && line[i] != "") {
                        if (type.endsWith("String")) {
                            field.set(smsDown, String.valueOf(line[i]));
                        } else if (type.endsWith("Integer")) {
                            field.set(smsDown, Integer.parseInt(String.valueOf(line[i])));
                        }
                    }
                }
                smsDownList.add(smsDown);
            }
        }
        return smsDownList;
    }


}
