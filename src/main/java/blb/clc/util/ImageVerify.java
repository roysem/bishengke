package blb.clc.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ImageVerify {
    private int width=160;//图片的宽度
    private int hight=40;//图片的高度
    private int codeCount=5;//验证码个数;
    private int lineCount=150;//干扰线数
    private String code=null;//验证码
    private BufferedImage buffImg=null;//图片
    private char[] codeSequence={ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N',  'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z',  '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public ImageVerify() {
    }

    public ImageVerify(int width, int hight, int codeCount, int lineCount) {
        this.width = width;
        this.hight = hight;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
    }

    public ImageVerify(int width, int hight, int codeCount, int lineCount, String code, BufferedImage buffImg) {
        this.width = width;
        this.hight = hight;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.code = code;
        this.buffImg = buffImg;
    }


    //创建验证码
    public void createCode() {
        int x = 0;//水平分区的个数
        int fontHeight = 0;//字体高度
        int codeY = 0;

        x = width / (codeCount + 2);
        fontHeight = hight /2;
        codeY = hight/2+7;

        //创建图层
        buffImg = new BufferedImage(width, hight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        //生成随机数
        Random random = new Random();

        //为图层填充白色
        g.setColor(Color.white);
        //设置边框宽度
        g.fillRect(0, 0, width, hight);

        //设置字体样式
        g.setFont(new Font(Font.DIALOG, Font.PLAIN, fontHeight));

        //画验证码
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            String s = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawString(s, (i + 1) * x, codeY);
            buffer.append(s);
        }
        code = buffer.toString();

        //画干扰线
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(width);//起点横坐标
            int ys = random.nextInt(hight);//起点纵坐标
            int xe = xs + random.nextInt(width / 8);//终点横坐标
            int ye = ys + random.nextInt(hight / 8);//终点横坐标
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawLine(xs, ys, xe, ye);
        }
    }

    public void write(String path) throws IOException {
        OutputStream sos = new FileOutputStream(path);
        this.write(sos);
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }
    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code;
    }
}
