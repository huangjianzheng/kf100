package com.medhead.kf100.common.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成器工具类
 *
 * 1.可生成数字、大写、小写字母及三者混合类型的验证码
 * 2.支持自定义验证码字符数量,支持自定义验证码图片的大小,支持自定义需排除的特殊字符,支持自定义干扰线的数量,支持自定义验证码图文颜色
 *
 */
public class VerifyCodeUtil {


    /**
     * 验证码类型为仅数字,即0~9
     */
    public static final int TYPE_NUM_ONLY = 0;

    /**
     * 验证码类型为仅字母,即大小写字母混合
     */
    public static final int TYPE_LETTER_ONLY = 1;

    /**
     * 验证码类型为数字和大小写字母混合
     */
    public static final int TYPE_ALL_MIXED = 2;

    /**
     * 验证码类型为数字和大写字母混合
     */
    public static final int TYPE_NUM_UPPER = 3;

    /**
     * 验证码类型为数字和小写字母混合
     */
    public static final int TYPE_NUM_LOWER = 4;

    /**
     * 验证码类型为仅大写字母
     */
    public static final int TYPE_UPPER_ONLY = 5;

    /**
     * 验证码类型为仅小写字母
     */
    public static final int TYPE_LOWER_ONLY = 6;

    private VerifyCodeUtil() {
    }

    /**
     * 生成随机颜色
     */
    private static Color generateRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255),
                random.nextInt(255));
    }

    /**
     * 生成图片验证码
     *
     * @param type           验证码类型,参见本类的静态属性
     * @param length         验证码字符长度,要求大于0的整数
     * @param excludeString  需排除的特殊字符
     * @param width          图片宽度(注意此宽度若过小,容易造成验证码文本显示不全,如4个字符的文本可使用85到90的宽度)
     * @param height         图片高度
     * @param interLine      图片中干扰线的条数
     * @param randomLocation 每个字符的高低位置是否随机
     * @param backColor      图片颜色,若为null则表示采用随机颜色
     * @param foreColor      字体颜色,若为null则表示采用随机颜色
     * @param lineColor      干扰线颜色,若为null则表示采用随机颜色
     * @return 图片缓存对象
     */
    public static BufferedImage generateImageCode(int type, int length,
                                                  String excludeString, int width, int height, int interLine,
                                                  boolean randomLocation, Color backColor, Color foreColor,
                                                  Color lineColor) {
        String textCode = generateTextCode(type, length, excludeString);
        return generateImageCode(textCode, width, height, interLine,
                randomLocation, backColor, foreColor, lineColor);
    }

    /**
     * 生成验证码字符串
     *
     * @param type          验证码类型,参见本类的静态属性
     * @param length        验证码长度,要求大于0的整数
     * @param excludeString 需排除的特殊字符（无需排除则为null）
     * @return 验证码字符串
     */
    private static String generateTextCode(int type, int length,
                                           String excludeString) {
        if(length <= 0) {
            return "";
        }
        StringBuilder verifyCode = new StringBuilder();
        int i = 0;
        Random random = new Random();
        switch (type) {
            case TYPE_NUM_ONLY:
                while (i < length) {
                    int t = random.nextInt(10);
                    // 排除特殊字符
                    if(null == excludeString || !excludeString.contains(t + "")) {
                        verifyCode.append(t);
                        i++;
                    }
                }
                break;
            case TYPE_LETTER_ONLY:
                while (i < length) {
                    int t = random.nextInt(123);
                    if((t >= 97 || (t >= 65 && t <= 90))
                            && (null == excludeString || excludeString
                            .indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_ALL_MIXED:
                while (i < length) {
                    int t = random.nextInt(123);
                    if((t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57))
                            && (null == excludeString || excludeString
                            .indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_NUM_UPPER:
                while (i < length) {
                    int t = random.nextInt(91);
                    if((t >= 65 || (t >= 48 && t <= 57))
                            && (null == excludeString || excludeString
                            .indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_NUM_LOWER:
                while (i < length) {
                    int t = random.nextInt(123);
                    if((t >= 97 || (t >= 48 && t <= 57))
                            && (null == excludeString || excludeString
                            .indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_UPPER_ONLY:
                while (i < length) {
                    int t = random.nextInt(91);
                    if((t >= 65)
                            && (null == excludeString || excludeString
                            .indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_LOWER_ONLY:
                while (i < length) {
                    int t = random.nextInt(123);
                    if((t >= 97)
                            && (null == excludeString || excludeString
                            .indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
        }
        return verifyCode.toString();
    }

    /**
     * 已有验证码,生成验证码图片
     *
     * @param textCode       文本验证码
     * @param width          图片宽度(注意此宽度若过小,容易造成验证码文本显示不全,如4个字符的文本可使用85到90的宽度)
     * @param height         图片高度
     * @param interLine      图片中干扰线的条数
     * @param randomLocation 每个字符的高低位置是否随机
     * @param backColor      图片颜色,若为null则表示采用随机颜色
     * @param foreColor      字体颜色,若为null则表示采用随机颜色
     * @param lineColor      干扰线颜色,若为null则表示采用随机颜色
     * @return 图片缓存对象
     */
    private static BufferedImage generateImageCode(String textCode, int width,
                                                   int height, int interLine, boolean randomLocation, Color backColor,
                                                   Color foreColor, Color lineColor) {
        // 创建内存图像
        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics graphics = bufferedImage.getGraphics();
        // 画背景图
        graphics.setColor(null == backColor ? generateRandomColor() : backColor);
        graphics.fillRect(0, 0, width, height);
        // 画干扰线
        Random random = new Random();
        if(interLine > 0) {
            int x = 0, y = 0, y1 = 0;
            for (int i = 0; i < interLine; i++) {
                graphics.setColor(null == lineColor ? generateRandomColor()
                        : lineColor);
                y = random.nextInt(height);
                y1 = random.nextInt(height);
                graphics.drawLine(x, y, width, y1);
            }
        }
        // 字体大小为图片高度的80%
        int fsize = (int) (height * 0.8);
        int fx = height - fsize;
        int fy = fsize;
        // 设定字体
        graphics.setFont(new Font("Default", Font.PLAIN, fsize));
        // 写验证码字符
        for (int i = 0; i < textCode.length(); i++) {
            fy = randomLocation ? (int) ((Math.random() * 0.3 + 0.6) * height)
                    : fy;
            graphics.setColor(null == foreColor ? generateRandomColor()
                    : foreColor);
            // 将验证码字符显示到图象中
            graphics.drawString(textCode.charAt(i) + "", fx, fy);
            fx += fsize * 0.9;
        }
        graphics.dispose();
        return bufferedImage;
    }

    //方法1：length为产生的位数
    public static String getRandomString(int length) {
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //由Random生成随机数
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        //长度为几就循环几次
        for (int i = 0; i < length; ++i) {
            //产生0-61的数字
            int number = random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    public static String getRandomNumeric(int length) {
        return createData(length);
    }

    //根据指定长度生成纯数字的随机数
    private static String createData(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
}
