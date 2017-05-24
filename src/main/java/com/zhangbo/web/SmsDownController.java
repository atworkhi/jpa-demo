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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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
    public BSTableEntity<SmsDown> list(
            @RequestParam(value = "order", defaultValue = "", required = false) String order,
            @RequestParam(value = "other", defaultValue = "", required = false) String other,
            @RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        BSTableEntity<SmsDown> entity = new BSTableEntity<SmsDown>();
        Pageable pageable = new PageRequest(offset / limit, limit);
        Example<SmsDown> example = Example.of(null);
        try {
            Page<SmsDown> page = smsDownService.findAll(example, pageable);
            entity.setRows(page.getContent());
            entity.setTotal(page.getTotalElements());
        } catch (Exception e) {
            logger.error("查询SmsDown列表异常", e);
        }
        return entity;
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(@RequestParam(value = "id", required = false) Integer[] idArr,
                       HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.addHeader("Content-Disposition", "attachment;fileName=" + System.currentTimeMillis() + ".csv");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            List<Integer> idList = Arrays.asList(idArr);
            List<SmsDown> smsDownList = smsDownService.findAll(idList);
            List<String[]> strList = new ArrayList<>();
            for (SmsDown smsDown : smsDownList) {
                strList.add(smsDown.getfieldValues());
            }
            CsvUtils.writeCSV(SmsDown.EXPORT_HEADERS, strList, outputStream);
        } catch (Exception e) {
            logger.error("导出smsdown异常", e);
        }
    }


    @RequestMapping(value = "delete", method = RequestMethod.GET)
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

    @RequestMapping("/index")
    public String index() {
        return "smsdown/list2";
    }
}
