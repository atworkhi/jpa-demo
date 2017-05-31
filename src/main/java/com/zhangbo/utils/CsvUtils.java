package com.zhangbo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CsvUtils {

    private static final Logger logger = LoggerFactory.getLogger(CsvUtils.class);

    /**
     * 读取csv数据
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static List<String[]> readCSV(InputStream in) throws Exception {
        if (in == null) {
            return new ArrayList<String[]>();
        }
        List<String[]> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            reader.readLine();
            String str;
            while ((str = reader.readLine()) != null) {
                result.add(str.split(","));
            }
        } catch (Exception e) {
            logger.error("读取csv文件异常");
            throw new Exception("读取csv文件异常", e);
        }
        return result;
    }

    /**
     * 写入csv文件
     *
     * @param strArrlist
     * @param outputStream
     * @throws Exception
     */
    public static void writeCSV(String[] header, List<String[]> strArrlist, OutputStream outputStream) throws Exception {
        if (outputStream == null) {
            throw new NullPointerException();
        }
        if (strArrlist == null) {
            strArrlist = new ArrayList();
        }
        strArrlist.add(0, header);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {

            StringBuilder strBuilder = new StringBuilder();
            for (String[] line : strArrlist) {
                if (line == null || line.length == 0) {
                    continue;
                }
                for (String str : line) {
                    str = str == null ? "" : str;
                    strBuilder.append(str).append(",");
                }
                strBuilder.deleteCharAt(strBuilder.lastIndexOf(","))
                        .append("\n");
            }
            writer.write(strBuilder.toString());
            writer.flush();
        } catch (Exception e) {
            logger.error("写入cvs异常");
            throw new Exception("写入cvs异常", e);
        }
    }
}
