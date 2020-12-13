package com.boss.bes.exam.controller;

import boss.xtrain.util.cdn.CdnUtil;
import boss.xtrain.util.file.FileUtil;
import com.boss.bes.exam.util.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {
    //测试Cdn工具类
    @Test
    public void testCdnUtil(){
        //首先调用二维码生成工具类生成二维码
//        String classPath = getClass().getResource("/").getPath();
//        File file = QRCodeUtil.qrCodeCreate("https://www.baidu.com/",
//                classPath, null, "testQR3", false);
//        CdnUtil.upload(file,"qrcode/"+file.getName());
        String url = CdnUtil.getUrl("testQR3");
//        FileUtil.deleteFile(file.getAbsolutePath());
//        log.info("file:{}{}",file.getAbsolutePath(),file.getName());
        log.info("url:{}",url);
    }
}