package com.zhangbo.web;

import com.zhangbo.model.BSTableEntity;
import com.zhangbo.model.SmsDown;
import com.zhangbo.service.SmsDownService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


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
            @RequestParam("limit") int limit,
            @RequestParam("offset") int offset,
            @RequestParam(value = "order", defaultValue = "", required = false) String order,
            @RequestParam(value = "other", defaultValue = "", required = false) String other) {
        Pageable pageable = new PageRequest(offset / limit, limit);
        BSTableEntity<SmsDown> entity = new BSTableEntity<SmsDown>();
        Page<SmsDown> page = smsDownService.findAll(pageable);
        entity.setRows(page.getContent());
        entity.setTotal(page.getTotalElements());
        return entity;
    }

    @RequestMapping("/index")
    public String index() {
        return "smsdown/list2";
    }
}
