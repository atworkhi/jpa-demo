package com.zhangbo.web;

import com.zhangbo.model.ResultInfo;
import com.zhangbo.model.SmsMD5;
import com.zhangbo.service.SmsMD5Service;
import com.zhangbo.utils.CsvUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sms-md5")
public class SmsMD5Controller {

    private static Logger logger = Logger.getLogger(SmsMD5Controller.class);

    @Autowired
    private SmsMD5Service smsMD5Service;

    @RequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<SmsMD5> list(@RequestParam(value = "md5", required = false) String md5) {
        SmsMD5 smsMD5 = new SmsMD5();
        if (md5 != null && md5.length() > 0) {
            smsMD5.setMd5(md5);
        }
        Example<SmsMD5> example = Example.of(smsMD5);
        List<SmsMD5> smsDownList = new ArrayList<>();
        try {
            smsDownList = smsMD5Service.findAll(example);
        } catch (Exception e) {
            logger.error("查询SmsMD5列表异常", e);
        }
        return smsDownList;
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delete(@RequestParam(value = "id[]") Integer[] ids) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            List<SmsMD5> smsMD5List = smsMD5Service.findAll(Arrays.asList(ids));
            if (smsMD5List == null || smsMD5List.size() == 0) {
                resultInfo.setMessage("参数有误");
                resultInfo.setSuccess(false);
                return resultInfo;
            }
            smsMD5Service.delete(smsMD5List);
            resultInfo.setMessage("删除成功");
            resultInfo.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            resultInfo.setMessage("删除失败");
            resultInfo.setSuccess(Boolean.FALSE);
            logger.error("删除SmsMD5失败", e);
        }
        return resultInfo;
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(@RequestParam(value = "id") Integer[] idArr,
                       HttpServletResponse response) {
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + System.currentTimeMillis() + ".csv");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            List<SmsMD5> smsDownList = smsMD5Service.findAll(Arrays.asList(idArr));
            List<String[]> strList = parseSmsMD52Csv(smsDownList);
            CsvUtils.writeCSV(SmsMD5.EXPORT_HEADERS, strList, outputStream);
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
                    "templet" + File.separator + "smsmd5.csv");
            File file = new File(filePath);
            if (!file.isFile()) {
                return;
            }
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=SmsMD5.csv");
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
    @ResponseBody
    public ResultInfo csvImport(@RequestParam("uploadFile") MultipartFile uploadFile) {
        ResultInfo resultInfo = new ResultInfo();
        try (InputStream inputStream = uploadFile.getInputStream()) {
            List<String[]> list = CsvUtils.readCSV(inputStream);
            if (list.size() > 0) {
                //比较模版头是否一直
                if (compare(list.get(0), SmsMD5.EXPORT_HEADERS)) {
                    resultInfo.setSuccess(false);
                    resultInfo.setMessage("当前数据与模版数据不一致");
                    return resultInfo;
                }
            }
            List<SmsMD5> smsDownList = parseCsv2SmsMD5(list);
            smsMD5Service.save(smsDownList);
            resultInfo.setSuccess(Boolean.TRUE);
            resultInfo.setMessage("导入成功");
        } catch (Exception e) {
            resultInfo.setSuccess(Boolean.FALSE);
            resultInfo.setMessage("导入失败");
            logger.error("导入smsdown失败", e);
        }
        return resultInfo;
    }


    @RequestMapping("/index")
    public String index() {
        return "smsmd5/list";
    }


    /**
     * 将smsdown转换为csv
     *
     * @param smsMD5List
     * @return csv数据
     * @throws Exception
     */
    private static List<String[]> parseSmsMD52Csv(List<SmsMD5> smsMD5List) throws Exception {
        List<String[]> resultList = new ArrayList<>();
        for (SmsMD5 SmsMD5 : smsMD5List) {
            StringBuilder sb = new StringBuilder();
            Class clazz = SmsMD5.getClass();
            for (String field : SmsMD5.EXPORT_HEADERS_FIELDS) {
                String getMethod = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
                Method method = clazz.getDeclaredMethod(getMethod);
                Object obj = method.invoke(SmsMD5);
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
    private static List<SmsMD5> parseCsv2SmsMD5(List<String[]> list) throws Exception {
        List<SmsMD5> smsDownList = new ArrayList<>();
        String[] fields = SmsMD5.EXPORT_HEADERS_FIELDS;
        //从第二行取数据
        if (list != null && list.size() > 1) {
            for (int a = 1; a < list.size(); a++) {
                String[] line = list.get(a);
                if (line == null || line.length == 0) {
                    continue;
                }
                SmsMD5 SmsMD5 = new SmsMD5();
                Class clazz = SmsMD5.getClass();
                for (int i = 0; i < line.length; i++) {
                    Field field = clazz.getDeclaredField(fields[i]);
                    field.setAccessible(true);
                    String type = field.getType().toString();
                    if (line[i] != null && line[i] != "") {
                        if (type.endsWith("String")) {
                            field.set(SmsMD5, String.valueOf(line[i]));
                        } else if (type.endsWith("Integer")) {
                            field.set(SmsMD5, Integer.parseInt(String.valueOf(line[i])));
                        }
                    }
                }
                smsDownList.add(SmsMD5);
            }
        }
        return smsDownList;
    }


    private boolean compare(String[] arr1, String[] arr2) {
        if (arr1 == null || arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2)) {
                return false;
            }
        }
        return true;
    }

}
