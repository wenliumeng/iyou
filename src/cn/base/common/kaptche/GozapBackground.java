package cn.base.common.kaptche;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.util.Configurable;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * 验证码图片
 * Created by seyMour on 2016/4/9.
 * ☞120465271@qq.com☜
 */
public class GozapBackground extends Configurable implements BackgroundProducer{

    private static final Color color = new Color(0,true);

    @Override
    public BufferedImage addBackground(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        BufferedImage bufferedWithBackground = new BufferedImage(width + 1, height + 1,13);

        //提供更为复杂的图像控制
        Graphics2D graphics2D =(Graphics2D)bufferedWithBackground.getGraphics();

        //实体设备配置，返回一个支持透明度和颜色模型的图像，3表示透明度模式
        bufferedWithBackground = graphics2D.getDeviceConfiguration().createCompatibleImage(width+1,height+1,3);
        graphics2D.dispose();
        graphics2D = (Graphics2D)bufferedWithBackground.getGraphics();

        //提供图像的着色微调，为图像类提供算法
        //抗锯齿：不使用抗锯齿
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
        hints.add(new RenderingHints(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY));
        hints.add(new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY));
        hints.add(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));

        graphics2D.setRenderingHints(hints);

        //渐变填充
        GradientPaint paint = new GradientPaint(0.0F,0.0F,color,width + 1,height + 1,color);

        graphics2D.setPaint(paint);

        //绘制圆角边框，8为圆角
        graphics2D.drawRoundRect(0,0,width-1,height-1,8,8);

        //定义圆角边框
        RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(0.0D,0.0D,width,height,8.0D,8.0D);
        graphics2D.fill(roundRectangle2D);

        graphics2D.drawImage(bufferedImage,0,0,null);
        //释放图像
        graphics2D.dispose();

        return bufferedWithBackground;
    }
}
