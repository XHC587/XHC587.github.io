package GamePlay;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;





    public class BackgroundGenerator {

        // 登录窗口尺寸
        private static final int WIDTH = 488;
        private static final int HEIGHT = 430;
        // 图片保存路径
        private static final String SAVE_PATH = "D:\\develop\\idea-daima\\bishe\\day02\\tupian\\";

        public static void main(String[] args) {
            // 生成第一张背景图（蓝紫渐变）
            generateBluePurpleBackground();
            // 生成第二张背景图（绿色渐变）
            generateGreenBackground();
            System.out.println("背景图片生成完成！");
        }

        /**
         * 生成蓝紫渐变背景图
         */
        private static void generateBluePurpleBackground() {
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            // 创建蓝紫渐变背景
            for (int y = 0; y < HEIGHT; y++) {
                int r = (int) (50 + (y / (double) HEIGHT) * 100);
                int g = (int) (50 + (y / (double) HEIGHT) * 80);
                int b = (int) (150 + (y / (double) HEIGHT) * 105);
                g2d.setColor(new Color(r, g, b));
                g2d.drawLine(0, y, WIDTH, y);
            }

            // 添加装饰性圆形
            g2d.setColor(new Color(255, 255, 255, 100));
            g2d.setStroke(new BasicStroke(2));
            for (int i = 0; i < 5; i++) {
                int x = 50 + i * 100;
                int y = 100 + (i % 3) * 100;
                int radius = 30 + i * 5;
                g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
                g2d.setColor(Color.WHITE);
                g2d.drawOval(x - radius, y - radius, radius * 2, radius * 2);
                g2d.setColor(new Color(255, 255, 255, 100));
            }

            // 保存图片
            saveImage(image, "login_background1.png");
        }

        /**
         * 生成绿色渐变背景图
         */
        private static void generateGreenBackground() {
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            // 创建绿色渐变背景
            for (int y = 0; y < HEIGHT; y++) {
                int r = (int) (50 + (y / (double) HEIGHT) * 100);
                int g = (int) (150 + (y / (double) HEIGHT) * 100);
                int b = (int) (50 + (y / (double) HEIGHT) * 80);
                g2d.setColor(new Color(r, g, b));
                g2d.drawLine(0, y, WIDTH, y);
            }

            // 添加装饰性几何图形
            g2d.setColor(new Color(255, 255, 255, 100));
            g2d.setStroke(new BasicStroke(2));
            for (int i = 0; i < 4; i++) {
                int x = 80 + i * 120;
                int y = 120 + (i % 2) * 150;
                int size = 60 + i * 10;
                g2d.fillRect(x - size / 2, y - size / 2, size, size);
                g2d.setColor(Color.WHITE);
                g2d.drawRect(x - size / 2, y - size / 2, size, size);
                g2d.setColor(new Color(255, 255, 255, 100));
            }

            // 保存图片
            saveImage(image, "login_background2.png");
        }

        /**
         * 保存图片到指定路径
         */
        private static void saveImage(BufferedImage image, String fileName) {
            try {
                File output = new File(SAVE_PATH + fileName);
                ImageIO.write(image, "PNG", output);
                System.out.println("已生成：" + fileName);
            } catch (IOException e) {
                System.err.println("保存图片失败：" + e.getMessage());
            }
        }
    }


