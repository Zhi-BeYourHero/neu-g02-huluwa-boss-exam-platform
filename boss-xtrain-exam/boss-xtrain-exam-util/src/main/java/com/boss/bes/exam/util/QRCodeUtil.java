package com.boss.bes.exam.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-07 18:03
 * @since 1.0
 */
@Slf4j
public final class QRCodeUtil {
    /**
     *  二维码编码字符集
     */
    private static final String CHARSET = "UTF-8";
    /**
     * 二维码输出格式
     */
    private static final String FORMAT = "jpg";
    /**
     * 二维码尺寸
     */
    private static final int QRCODE_SIZE = 300;
    /**
     *  LOGO高度
     */
    private static final int LOGO_HEIGHT = 60;
    /**
     * LOGO宽度
     */
    private static final int LOGO_WIDTH = 60;

    private QRCodeUtil(){}

    /**
     * zxing方式生成二维码
     * 注意：
     * 1,文本生成二维码的方法独立出来,返回image流的形式,可以输出到页面
     * 2,设置容错率为最高,一般容错率越高,图片越不清晰, 但是只有将容错率设置高一点才能兼容logo图片
     * 3,logo图片默认占二维码图片的20%,设置太大会导致无法解析
     *
     * @param content  二维码包含的内容，文本或网址
     * @param path     生成的二维码图片存放位置
     * @param logoPath logo的存放位置
     */
    public static File qrCodeCreate(String content, String path, String logoPath, String QRName, boolean needCompressed) {
        try {
            //获取二维码流的形式，写入到目录文件中
            BufferedImage image = createImage(content, logoPath, needCompressed);
            // 生成二维码存放文件
            // File file = new File(path + random.nextInt(1000) + ".jpg");      // 随机命名
            File file = new File(path + QRName + '.' + FORMAT);
            ImageIO.write(image, FORMAT, file);
            return file;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * @param path 二维码所在位置
     * @return 二维码包含的信息
     */
    public static String decode(String path) throws NotFoundException, IOException {
        File file = new File(path);

        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        Result result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

    /**
     * @param content        二维码内容
     * @param logoImagePath  logo图标的路径
     * @param needCompressed 是否需要压缩logo
     * @return 生成的二维码
     */
    private static BufferedImage createImage(String content, String logoImagePath, boolean needCompressed)
            throws IOException, WriterException {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        // 纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置编码
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        // 二维码两边空白区域大小
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        //如果不存在log照片
        if (logoImagePath == null || "".equals(logoImagePath)) {
            return image;
        }
        // 如果有logo，则插入logo图片
        QRCodeUtil.insertImage(image, logoImagePath, needCompressed);
        return image;
    }

    /**
     * @param sourceImage    原图片
     * @param logoImagePath  logo图片所在的路径
     * @param needCompressed 是否需要压缩
     */
    private static void insertImage(BufferedImage sourceImage, String logoImagePath, boolean needCompressed) throws IOException {
        File file = new File(logoImagePath);
        if (!file.exists()) {
            log.info("二维码图片不存在");
            return;
        }

        Image src = ImageIO.read(file);
        int width = src.getWidth(null);
        int height = src.getHeight(null);

        // 压缩二维码图片
        if (needCompressed) {
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();  // 释放占有的资源
            src = image;
            // 直观的理解：Graphics2D 就相当于画笔，而BufferedImage 就是画笔绘制的结果。
        }

        // 插入logo
        Graphics2D graph = sourceImage.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }
}
