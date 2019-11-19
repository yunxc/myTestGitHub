/*
package com.yun.myecharts;

import com.github.abel533.echarts.Option;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;

*/
/**
 * @author Administrator
 * @date 2019/9/18 16:36
 *//*

public class ImageRenderParams {
    //echart的Option
    public Option opt;
    //echart图片的宽度
    public int width = 600;
    //echart图片的高度
    public int height = 400;
    //echart图片保存地址
    public String outfile = "d:/dsafdf.png";
    //file 或者  PNG
    public String type = "file";

    */
/**
     * 创建Echart图
     *
     * @param imagePath 图片保存地址，例如：d:/demo.png
     * @throws Exception
     *//*

    public ImageResult createEchartImage(Option option) throws Exception {
        if (option == null) {
            return new ImageResult();
        }
        String imgPath = getRandomImagePath(option);
        ImageRenderParams params = new ImageRenderParams();
        params.opt = option;
        params.outfile = imgPath;
        // 实例化
        Gson gson = new Gson();
        // 将map转成JSON
        String paramsJsonStr = gson.toJson(params);
        Base64 base64 = new Base64();
        //转换为base64编码是为了防止执行指令的时候出问题
        String base64Str = base64.encodeToString(paramsJsonStr.getBytes("UTF-8"));
        //调用的JS文件路径
        File jsFile = ResourceUtils.getFile("classpath:static/js/echarts-convert.js");
        // 生成的图片名称
        String jsFilePath = jsFile.getAbsolutePath();
        String[] args = new String[3];
        args[0] = jsFilePath;
        args[1] = base64Str;
        // 执行脚本
        try {
            CommandExecUtil.phantomjsExec(args);
        } catch (Exception e) {
            throw e;
        }
        return new ImageResult(imgPath);
    }

public static class ImageResult {
    private String imagePath;
    private byte[] imageData;

    public ImageResult(String imagePath) {
        this.imagePath = imagePath;
    }

    public ImageResult() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public byte[] getImageData() throws Exception {
        if (StringUtils.isBlank(imagePath)) {
            return null;
        }
        if (imageData != null) {
            return imageData;
        }
        File file = ResourceUtils.getFile(imagePath);
        if (file == null) {
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        imageData = new byte[fi.available()];
        fi.read(imageData);
        fi.close();
        return imageData;
    }
    }
}*/
