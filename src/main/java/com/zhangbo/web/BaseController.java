package com.zhangbo.web;

import com.zhangbo.model.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by zhangbo on 2017/6/7.
 */
@Controller
public class BaseController {


    private static Logger logger = LoggerFactory.getLogger(BaseController.class);


    @RequestMapping(value = "/download/{filePath}/{fileName:.+}", method = RequestMethod.GET)
    public void fileDownLoad(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable("filePath") String filePath,
            @PathVariable("fileName") String fileName) {

        String realPath = request.getServletContext().getRealPath(filePath);
        File file = new File(realPath + File.separator + fileName);
        if (!file.exists() || !file.isFile()) {
            logger.info("下载文件不存在");
            return;
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream()) {

            byte[] buff = new byte[inputStream.available()];
            inputStream.read(buff);
            outputStream.write(buff);
            outputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("下载文件不存在", e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("下载文件异常", e);
        }

    }


    @RequestMapping(value = "/delete/{filePath}/{fileName:.+}", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo delete(
            @PathVariable("filePath") String filePath,
            @PathVariable("fileName") String fileName,
            HttpServletRequest request) {

        ResultInfo resultInfo = new ResultInfo();

        String realPath = request.getServletContext().getRealPath(filePath);
        File file = new File(realPath + File.separator + fileName);
        if (!file.exists() || file.isDirectory()) {
            resultInfo.setMessage("文件不存在");
            resultInfo.setSuccess(Boolean.FALSE);
            return resultInfo;
        }
        try {
            file.delete();
            resultInfo.setSuccess(Boolean.TRUE);
            resultInfo.setMessage("删除成功");
        } catch (Exception e) {
            resultInfo.setSuccess(Boolean.FALSE);
            resultInfo.setMessage("删除文件异常");
            logger.error("文件删除失败", e);
        }

        return resultInfo;

    }

}
