package GamePlay;

import javax.swing.*;
//import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
public class GameJFrame extends JFrame implements KeyListener , ActionListener {

    //定义一个变量 方便切换图片
    String path = "D:\\develop\\idea-daima\\bishe\\day02\\tupian\\";


    //定义一个二维数组 用于处理图片
    int[][] date = new int[4][4];
    //记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    //定义一个二维数组 用于判断 是否完成
    int[][] win = {
            {0, 1, 2, 3},
            {4, 5, 6, 7},
            {8, 9, 10, 11},
            {12, 13, 14, 15}
    };
    //计算走了多少步数
    int step = 0;

    //创建选项下面模块选项
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem TP2 = new JMenuItem("图片2");
    JMenuItem TP3 = new JMenuItem("图片3");
    JMenuItem TP4 = new JMenuItem("图片4");
    JMenuItem TP5 = new JMenuItem("图片5");
    JMenuItem TP6 = new JMenuItem("图片6");
    JMenuItem TP7 = new JMenuItem("图片7");
    JMenuItem TP8 = new JMenuItem("图片8");
    JMenuItem TP9 = new JMenuItem("图片9");

    JMenuItem accountItem = new JMenuItem("公众号");


    //这是 彩蛋按钮
    JButton caidan1 = new JButton("我");


    //游戏的主界面

    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱图片）
        initdate();

        //初始化方法(根据打乱之后的顺序展示图片）
        imageicom();


        //是否展示
        this.setVisible(true);

    }

    //定义数组 使其对应图片
    private void initdate() {
        //定义一个一维数组 用来对应图片
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //打乱数组中的顺序 用随机索引和任意索引 互换
        Random r = new Random();

        for (int i = 0; i < tempArr.length; i++) {
            //找到随机索引
            int index = r.nextInt(tempArr.length);
            //拿着遍历得到的数据 和随机索引数据 互换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //给二维数组 传递一维数组数据
        //方法一
        for (int i = 0; i < tempArr.length; i++) {
            //如果有空白的图片 即拼图时需要移动的空白图 则需要记录其所在位置 以便移动
            //if（tempArr[i]）==0）{
            //  x=i/4;  记录空白图在二维数组中的位置
            //  y=i%4;   x y 默认是在 （0，0）位置
            //  }

            date[i / 4][i % 4] = tempArr[i];

        }
        //方法二
        /*int index=0;
        for (int i = 0; i < date.length; i++) {
            for (int j = 0; j < date[i].length; j++) {
                date[i][j]=tempArr[index];
                index++;
            }
        }*/

    }

    //初始化图片
    private void imageicom() {
        //删除已经出现的所有图片
        this.getContentPane().removeAll();
        if (victory()) {
            //如果正确了 则......
            //创建一个弹窗
            JDialog jDialog2 = new JDialog();
            // 设置JDialog的布局管理器为null，这样setBounds()才能正常工作
            jDialog2.setLayout(null);
            //加入话术
            JLabel jLabel1 = new JLabel("恭喜你 成功完成 点" + "     " + "有彩蛋");
            jLabel1.setBounds(0, 300, 500, 500);
            Font customFont = new Font("宋体", Font.BOLD, 28); // 宋体、加粗、18号字
            jLabel1.setFont(customFont);
            jLabel1.setForeground(Color.red);

            jDialog2.getContentPane().add(jLabel1);


            //添加按钮
            //JButton caidan1=new JButton("我");
            caidan1.setBounds(250, 535, 100, 30);
            //移除按钮边框
            caidan1.setBorder(null);
            // 如果同时想让按钮在点击时也没有边框和背景变化
            caidan1.setFocusPainted(false); // 移除焦点边框
            caidan1.setContentAreaFilled(false); // 移除背景填充
            //给按钮设置字体
            caidan1.setFont(customFont);
            //给按钮设置颜色
            caidan1.setForeground(Color.red);

            jDialog2.getContentPane().add(caidan1);


            //加入背景图
            JLabel jLabel = new JLabel(new ImageIcon(path + "\\1-1.png"));
            jLabel.setBounds(0, 0, 500, 664);
            jDialog2.getContentPane().add(jLabel);

            jDialog2.setSize(500, 664);

            //弹窗 置顶
            jDialog2.setAlwaysOnTop(true);
            //弹窗 居中
            jDialog2.setLocationRelativeTo(null);
            //弹窗不关闭 无法进行其他的操作
            jDialog2.setModal(true);
            //显示弹窗
            jDialog2.setVisible(true);

        }
        //把步数 放起来
        JLabel stepJ = new JLabel("步数；" + step);
        stepJ.setBounds(0, 20, 100, 20);
        this.getContentPane().add(stepJ);


        //外循环
        for (int i = 0; i < 4; i++) {
            //内循环
            for (int j = 0; j < 4; j++) {
                //获取加载图片的序号
                int nuw = date[i][j];
                //创建一个ImageIcom对象
                ImageIcon icon = new ImageIcon(path + "chunk_" + nuw + ".png");
                //创建一个JLable的对象（容器）
                JLabel jLabel = new JLabel(icon);
                //指定图片位置
                jLabel.setBounds(50 + 125 * j, 166 * i, 125, 166);
                //给图片添加边框
                // jLabel.setBounds(new BevelBorder(BevelBorder.LOWERED));
                //将容器 插入到界面中
                this.getContentPane().add(jLabel);
            }
        }
        //添加背景图
        /*JLabel background=new JLabel(new ImageIcon());
        background.setBounds();//位置
        this.getContentPane().add(background);*/
        //给彩蛋加上动作监听
        caidan1.addActionListener(this);
        //刷新界面
        this.getContentPane().repaint();

    }

    //创建整个菜单
    private void initJMenuBar() {
        //创建整个菜单
        JMenuBar jMenuBar = new JMenuBar();
        //创建小模块选项，比如 （功能，关于我们）
        JMenu changeImage = new JMenu("更换图片");
        JMenu FucutionJMwnu = new JMenu("功能");
        JMenu AboutJMwnu = new JMenu("关于我们");

        //创建选项下面模块选项
       /* JMenuItem replayItem=new JMenuItem("重新游戏");
        JMenuItem reLoginItem=new JMenuItem("重新登录");
        JMenuItem closeItem=new JMenuItem("关闭游戏");
        JMenuItem TP1=new JMenuItem("图片1");

        JMenuItem accountItem=new JMenuItem("公众号");*/
        //将模块添加进来
        FucutionJMwnu.add(changeImage);
        FucutionJMwnu.add(replayItem);
        FucutionJMwnu.add(reLoginItem);
        FucutionJMwnu.add(closeItem);
        AboutJMwnu.add(accountItem);

        changeImage.add(TP2);
        changeImage.add(TP3);
        changeImage.add(TP4);
        changeImage.add(TP5);
        changeImage.add(TP6);
        changeImage.add(TP7);
        changeImage.add(TP8);
        changeImage.add(TP9);

        //给模块菜单 绑定动作监听事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        TP2.addActionListener(this);
        TP3.addActionListener(this);
        TP4.addActionListener(this);
        TP5.addActionListener(this);
        TP6.addActionListener(this);
        TP7.addActionListener(this);
        TP8.addActionListener(this);
        TP9.addActionListener(this);


        //添加进大的
        jMenuBar.add(FucutionJMwnu);
        jMenuBar.add(AboutJMwnu);
        //给整个界面 设置菜单
        this.setJMenuBar(jMenuBar);
    }

    //创建界面大小设定
    private void initJFrame() {
        //展示初始的，比如多高多宽
        this.setSize(603, 680);
        //设置标题
        this.setTitle("拼图单机小游戏.1,0");
        //界面置顶
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //取消默认的剧中方式
        this.setLayout(null);
        //给整个界面 添加 键盘监听
        this.addKeyListener(this);

    }


    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println();

    }


    //按住不松开 执行此代码 （显示图片完整 以用来方便拼图）
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //65-a  32-空格 18-alt
        if (code == 32) {
            //先把当前图片删除
            this.getContentPane().removeAll();
            //加载全图
            ImageIcon img = new ImageIcon(path + "1-1.png");
            JLabel all = new JLabel(img);
            all.setBounds(50, 0, 500, 664);
            //加到主界面去
            this.getContentPane().add(all);
            //刷新界面
            this.getContentPane().repaint();
        }
    }


    //松开后 发生
    @Override
    public void keyReleased(KeyEvent e) {

        //如果赢了 则不能再移动
       /* if(victory()){
            return;
        }*/
        // 左：37 上：38  右：39  下：40
        int code = e.getKeyCode();
        if (code == 40) {
            System.out.println("向下移动");
            if (x == 3) {
                return;
            }

            date[x][y] = date[x + 1][y];
            date[x + 1][y] = 0;
            x++;
            step++;
            imageicom();
        } else if (code == 38) {
            System.out.println("向上移动");
            if (x == 0) {
                return;
            }
            date[x][y] = date[x - 1][y];
            date[x - 1][y] = 0;
            x--;
            step++;
            imageicom();
        } else if (code == 39) {
            System.out.println("向右移动");
            if (y == 3) {
                return;
            }
            date[x][y] = date[x][y + 1];
            date[x][y + 1] = 0;
            y++;
            step++;
            imageicom();
        } else if (code == 37) {
            System.out.println("向左移动");
            if (y == 0) {
                return;
            }
            date[x][y] = date[x][y - 1];
            date[x][y - 1] = 0;
            y--;
            step++;
            imageicom();

            //18-alt 查看完成图 32-kongge
        }
        if (code == 32) {
            System.out.println("查看完成图 32-kongge");
            imageicom();
            //17-ctrl 一键完成 18-alt
        }
        if (code == 18) {
            System.out.println("一键完成 18-alt");

            for (int i = 0; i < date.length; i++) {
                for (int j = 0; j < date[i].length; j++) {
                    date[i][j] = win[i][j];

                }
            }

            imageicom();
        }


    }

    //游戏胜利 方法 如果和date数组中数据 相同 返回true
    public boolean victory() {
        for (int i = 0; i < date.length; i++) {
            for (int j = 0; j < date[i].length; j++) {
                if (date[i][j] != win[i][j]) {
                    return false;

                }
            }
        }
        return true;

    }

    //动作监听
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        //重新游戏
        if (obj == replayItem) {
            System.out.println("重新游戏");
            //计步器 清零
            step = 0;
            //打乱图片（数组中数据打乱）
            initdate();
            //重新加载图片
            imageicom();

        }
        //重新登录
        else if (obj == reLoginItem) {
            //关闭当前游戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        }
        //退出游戏
        else if (obj == closeItem) {
            //直接关闭虚拟机
            System.exit(0);
        }

        //公众号
        else if (obj == accountItem) {
            //创建一个弹窗
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("D:\\develop\\idea-daima" +
                    "\\bishe\\day02\\er\\1.png"));
            jLabel.setBounds(0, 0, 577, 590);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(577, 590);
            //弹窗 置顶
            jDialog.setAlwaysOnTop(true);
            //弹窗 居中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭 无法进行其他的操作
            jDialog.setModal(true);
            //显示弹窗
            jDialog.setVisible(true);

            //如果是图2
        } else if (obj == TP2) {
            path = "D:\\develop\\idea-daima\\bishe\\day02\\tupian-2\\";
            //计步器 清零
            step = 0;
            //打乱图片（数组中数据打乱）
            initdate();
            //重新加载图片
            imageicom();
//            if (victory()){
//                JDialog jDialog2=new JDialog();
//                JLabel jLabel=new JLabel("new ImageIcon()");
//                jLabel.setBounds(0,0,258,258);
//                jDialog2.getContentPane().add(jLabel);
//                jDialog2.setSize(344,344);
//                //弹窗 置顶
//                jDialog2.setAlwaysOnTop(true);
//                //弹窗 居中
//                jDialog2.setLocationRelativeTo(null);
//                //弹窗不关闭 无法进行其他的操作
//                jDialog2.setModal(true);
//                //显示弹窗
//                jDialog2.setVisible(true);
//            }


        } else if (obj == TP3) {
            path = "D:\\develop\\idea-daima\\bishe\\day02\\tupian-3\\";
            //计步器 清零
            step = 0;
            //打乱图片（数组中数据打乱）
            initdate();
            //重新加载图片
            imageicom();


        } else if (obj == TP4) {
            path = "D:\\develop\\idea-daima\\bishe\\day02\\tupian-4\\";
            //计步器 清零
            step = 0;
            //打乱图片（数组中数据打乱）
            initdate();
            //重新加载图片
            imageicom();


        } else if (obj == TP5) {
            path = "D:\\develop\\idea-daima\\bishe\\day02\\tupian-5\\";
            //计步器 清零
            step = 0;
            //打乱图片（数组中数据打乱）
            initdate();
            //重新加载图片
            imageicom();


        } else if (obj == TP6) {
            path = "D:\\develop\\idea-daima\\bishe\\day02\\tupian-6\\";
            //计步器 清零
            step = 0;
            //打乱图片（数组中数据打乱）
            initdate();
            //重新加载图片
            imageicom();


        } else if (obj == TP7) {
            path = "D:\\develop\\idea-daima\\bishe\\day02\\tupian-7\\";
            //计步器 清零
            step = 0;
            //打乱图片（数组中数据打乱）
            initdate();
            //重新加载图片
            imageicom();

        } else if (obj == TP8) {
            path = "D:\\develop\\idea-daima\\bishe\\day02\\tupian-8\\";
            //计步器 清零
            step = 0;
            //打乱图片（数组中数据打乱）
            initdate();
            //重新加载图片
            imageicom();
        } else if (obj == TP9) {
            path = "D:\\develop\\idea-daima\\bishe\\day02\\tupian-9\\";
            //计步器 清零
            step = 0;
            //打乱图片（数组中数据打乱）
            initdate();
            //重新加载图片
            imageicom();
        } else if (obj == caidan1) {
            System.out.println("点击了彩蛋");
            if (path == "D:\\develop\\idea-daima\\bishe\\day02\\tupian\\") {
                //创建一个弹窗
                JDialog jDialog11 = new JDialog();
                JLabel jLabel11 = new JLabel(new ImageIcon("D:\\develop\\idea-daima\\" +
                        "bishe\\day02\\TTPZ\\1.png"));
                jLabel11.setBounds(0, 0, 1459, 599);
                jDialog11.getContentPane().add(jLabel11);
                jDialog11.setSize(1459, 599);
                //弹窗 置顶
                jDialog11.setAlwaysOnTop(true);
                //弹窗 居中
                jDialog11.setLocationRelativeTo(null);
                //弹窗不关闭 无法进行其他的操作
                jDialog11.setModal(true);
                //显示弹窗
                jDialog11.setVisible(true);
            } else if (path == "D:\\develop\\idea-daima\\bishe\\day02\\tupian-2\\") {
                //创建一个弹窗
                JDialog jDialog11 = new JDialog();
                JLabel jLabel11 = new JLabel(new ImageIcon("D:\\develop\\idea-daima\\" +
                        "bishe\\day02\\TTPZ\\2.png"));
                jLabel11.setBounds(0, 0, 1168, 656);
                jDialog11.getContentPane().add(jLabel11);
                jDialog11.setSize(1168, 656);
                //弹窗 置顶
                jDialog11.setAlwaysOnTop(true);
                //弹窗 居中
                jDialog11.setLocationRelativeTo(null);
                //弹窗不关闭 无法进行其他的操作
                jDialog11.setModal(true);
                //显示弹窗
                jDialog11.setVisible(true);
            } else if (path == "D:\\develop\\idea-daima\\bishe\\day02\\tupian-3\\") {
                //创建一个弹窗
                JDialog jDialog11 = new JDialog();
                JLabel jLabel11 = new JLabel(new ImageIcon("D:\\develop\\idea-daima\\" +
                        "bishe\\day02\\TTPZ\\3.png"));
                jLabel11.setBounds(0, 0, 1583, 595);
                jDialog11.getContentPane().add(jLabel11);
                jDialog11.setSize(1583, 595);
                //弹窗 置顶
                jDialog11.setAlwaysOnTop(true);
                //弹窗 居中
                jDialog11.setLocationRelativeTo(null);
                //弹窗不关闭 无法进行其他的操作
                jDialog11.setModal(true);
                //显示弹窗
                jDialog11.setVisible(true);
            } else if (path == "D:\\develop\\idea-daima\\bishe\\day02\\tupian-4\\") {
                //创建一个弹窗
                JDialog jDialog11 = new JDialog();
                JLabel jLabel11 = new JLabel(new ImageIcon("D:\\develop\\idea-daima\\" +
                        "bishe\\day02\\TTPZ\\4.png"));
                jLabel11.setBounds(0, 0, 1334, 483);
                jDialog11.getContentPane().add(jLabel11);
                jDialog11.setSize(1334, 483);
                //弹窗 置顶
                jDialog11.setAlwaysOnTop(true);
                //弹窗 居中
                jDialog11.setLocationRelativeTo(null);
                //弹窗不关闭 无法进行其他的操作
                jDialog11.setModal(true);
                //显示弹窗
                jDialog11.setVisible(true);
            }else if (path == "D:\\develop\\idea-daima\\bishe\\day02\\tupian-5\\") {
                //创建一个弹窗
                JDialog jDialog11 = new JDialog();
                JLabel jLabel11 = new JLabel(new ImageIcon("D:\\develop\\idea-daima\\" +
                        "bishe\\day02\\TTPZ\\5.png"));
                jLabel11.setBounds(0, 0, 1112, 630);
                jDialog11.getContentPane().add(jLabel11);
                jDialog11.setSize(1112, 630);
                //弹窗 置顶
                jDialog11.setAlwaysOnTop(true);
                //弹窗 居中
                jDialog11.setLocationRelativeTo(null);
                //弹窗不关闭 无法进行其他的操作
                jDialog11.setModal(true);
                //显示弹窗
                jDialog11.setVisible(true);
            }else if (path == "D:\\develop\\idea-daima\\bishe\\day02\\tupian-6\\") {
                //创建一个弹窗
                JDialog jDialog11 = new JDialog();
                JLabel jLabel11 = new JLabel(new ImageIcon("D:\\develop\\idea-daima\\bishe" +
                        "\\day02\\TTPZ\\6.png"));
                jLabel11.setBounds(0, 0, 1329, 583);
                jDialog11.getContentPane().add(jLabel11);
                jDialog11.setSize(1329, 583);
                //弹窗 置顶
                jDialog11.setAlwaysOnTop(true);
                //弹窗 居中
                jDialog11.setLocationRelativeTo(null);
                //弹窗不关闭 无法进行其他的操作
                jDialog11.setModal(true);
                //显示弹窗
                jDialog11.setVisible(true);
            }else if (path == "D:\\develop\\idea-daima\\bishe\\day02\\tupian-7\\") {
                //创建一个弹窗
                JDialog jDialog11 = new JDialog();
                JLabel jLabel11 = new JLabel(new ImageIcon("D:\\develop\\idea-daima" +
                        "\\bishe\\day02\\TTPZ\\5.png"));
                jLabel11.setBounds(0, 0, 1112, 630);
                jDialog11.getContentPane().add(jLabel11);
                jDialog11.setSize(1112, 630);
                //弹窗 置顶
                jDialog11.setAlwaysOnTop(true);
                //弹窗 居中
                jDialog11.setLocationRelativeTo(null);
                //弹窗不关闭 无法进行其他的操作
                jDialog11.setModal(true);
                //显示弹窗
                jDialog11.setVisible(true);
            }else if (path == "D:\\develop\\idea-daima\\bishe\\day02\\tupian-8\\") {
                //创建一个弹窗
                JDialog jDialog11 = new JDialog();
                JLabel jLabel11 = new JLabel(new ImageIcon("D:\\develop\\idea-daima" +
                        "\\bishe\\day02\\TTPZ\\1.png"));
                jLabel11.setBounds(0, 0, 1459, 599);
                jDialog11.getContentPane().add(jLabel11);
                jDialog11.setSize(1459, 599);
                //弹窗 置顶
                jDialog11.setAlwaysOnTop(true);
                //弹窗 居中
                jDialog11.setLocationRelativeTo(null);
                //弹窗不关闭 无法进行其他的操作
                jDialog11.setModal(true);
                //显示弹窗
                jDialog11.setVisible(true);
            }else if (path == "D:\\develop\\idea-daima\\bishe\\day02\\tupian-9\\") {
                //创建一个弹窗
                JDialog jDialog11 = new JDialog();
                JLabel jLabel11 = new JLabel(new ImageIcon("D:\\develop\\idea-daima" +
                        "\\bishe\\day02\\TTPZ\\9.png"));
                jLabel11.setBounds(0, 0, 1690, 681);
                jDialog11.getContentPane().add(jLabel11);
                jDialog11.setSize(1690, 681);
                //弹窗 置顶
                jDialog11.setAlwaysOnTop(true);
                //弹窗 居中
                jDialog11.setLocationRelativeTo(null);
                //弹窗不关闭 无法进行其他的操作
                jDialog11.setModal(true);
                //显示弹窗
                jDialog11.setVisible(true);
            }






        }
    }
}
   /* @Override
    public void keyReleased(KeyEvent e) {
        // 左：37 上：38  右：39  下：40
        int code = e.getKeyCode();
        if (code == 38) {
            System.out.println("向上移动");
            if(x==3){

                return;
            }

            date[x][y] = date[x + 1][y];
            date[x + 1][y] = 0;
            x++;
            imageicom();
        } else if (code == 40) {
            System.out.println("向下移动");
            date[x][y] = date[x - 1][y];
            date[x - 1][y] = 0;
            x--;
            imageicom();
        } else if (code == 37) {
            System.out.println("向左移动");
            date[x][y] = date[x][y + 1];
            date[x][y + 1] = 0;
            y++;
            imageicom();
        } else if (code == 39) {
            System.out.println("向右移动");
            date[x][y] = date[x][y - 1];
            date[x][y - 1] = 0;
            y--;
            imageicom();
        }
    }*/