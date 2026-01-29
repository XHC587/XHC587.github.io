package GamePlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements ActionListener {
    static ArrayList<User>list=new ArrayList<>();
    static {
        list.add(new User("zhangsan","123"));
        list.add(new User("lisi","1234"));
    }

    //设置 验证码
    //从test中获取 方法
    test t=new test();
    String coadSrr=t.Getcoad();
    //69 行 定义验证码为按钮 方便添加动作监听
    JButton rightCoad=new JButton();
    //76 定义登录为按钮 方便添加动作监听
    JButton loginText=new JButton("登录");
    // 80 定义注册为按钮 方便添加动作监听
    JButton zhuceText=new JButton("注册");
    //验证码输入框
    JTextField coad=new JTextField();

    //加入用户名输入框
    JTextField username=new JTextField();
    //加入密码输入框
    JPasswordField mima=new JPasswordField();



    //登录页面
    public LoginJFrame(){
        //初始化界面
       initJFrame();
       //添加内容
        initView();
        



        //是否展示
        this.setVisible(true);


    }
//用户名 密码 登录 注册 等
    private void initView() {
//        // 创建背景标签
//        JLabel background = new JLabel(new ImageIcon("D:\\develop\\idea-daima\\bishe\\day02\\tupian\\login_background1.png"));
//        background.setSize(488, 430);
//        this.add(background);


        //用户名
        JLabel usernameText=new JLabel("用户名");
        usernameText.setBounds(125,100,100,40);
        //usernameText.setForeground(Color.WHITE); // 设置用户名标签字体颜色为白色
        this.getContentPane().add(usernameText);
        //加入用户名输入框
       // JTextField username=new JTextField();
        username.setBounds(175,100,168,30);
        this.getContentPane().add(username);
        //密码

        JLabel mimaText=new JLabel("密码");
        mimaText.setBounds(125,160,200,30);
        //mimaText.setForeground(Color.WHITE); // 设置密码标签字体颜色为白色
        this.getContentPane().add(mimaText);
        //加入密码输入框
        //JPasswordField mima=new JPasswordField();
        mima.setBounds(175,160,168,30);
        this.getContentPane().add(mima);
        //验证码
        JLabel coadText=new JLabel("验证码");
        coadText.setBounds(125,220,200,30);
        //coadText.setForeground(Color.WHITE); // 设置验证码标签字体颜色为白色
        this.getContentPane().add(coadText);
        //验证码输入框
        //JTextField coad=new JTextField();
        coad.setBounds(175,220,70,30);
        this.getContentPane().add(coad);
        //设置 验证码
        //从test中获取 方法
       // test t=new test();
        //String coadSrr=t.Getcoad();

        //JButton rightCoad=new JButton();
        //设置内容
        rightCoad.setText(coadSrr);
        rightCoad.setBounds(270,220,100,30);
        this.getContentPane().add(rightCoad);
        //登录
        //JButton loginText=new JButton("登录");
        loginText.setBounds(100,280,100,30);
        this.getContentPane().add(loginText);
        //注册
        //JButton zhuceText=new JButton("注册");
        zhuceText.setBounds(300,280,100,30);
        this.getContentPane().add(zhuceText);

        // 创建背景标签
        JLabel background = new JLabel(new ImageIcon("D:\\develop\\idea-daima\\bishe\\day02\\tupian\\login_background2.png"));
        background.setSize(488, 430);
        this.add(background);




        //加上动作监听
        rightCoad.addActionListener(this);
        loginText.addActionListener(this);
        zhuceText.addActionListener(this);




    }
//初始界面 标题 高度等
    private void initJFrame() {
        //展示初始的，比如多高多宽
        this.setSize(488,430);
        //设置标题
        this.setTitle("拼图 登录");
        //界面置顶
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //取消默认的剧中方式
        this.setLayout(null);


    }

    //弹窗 如 showJDialog（“用户名或密码错误”）
    public  void showJDialog(String centent){
        //创建一个弹窗对象
        JDialog jDialog=new JDialog();
        //设置大小 位置
        jDialog.setSize(400,400);
        //弹框置顶
        jDialog.setAlwaysOnTop(true);
        //弹框居中
        jDialog.setLocationRelativeTo(null);
        //不取消弹框 无法进行操作
        jDialog.setModal(true);
        // 用流式布局让内容自然排列
        jDialog.setLayout(new FlowLayout(FlowLayout.LEFT));

        // 用JTextArea实现自动换行
        JTextArea warningArea = new JTextArea(centent,10,20);
        warningArea.setLineWrap(true);
        warningArea.setWrapStyleWord(true);
        warningArea.setEditable(false);
        warningArea.setBackground(jDialog.getBackground());
        warningArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // 2. 核心：设置字体大小（这里以16号微软雅黑为例）
        // 参数说明：Font(字体名称, 字体样式, 字体大小)
        Font dialogFont = new Font("微软雅黑", Font.PLAIN, 20);
        warningArea.setFont(dialogFont);

        // 3. 添加到弹窗
        jDialog.getContentPane().add(warningArea);
        jDialog.setVisible(true);

        //创建JLable对象管理文字 并添加到弹窗中
        /*JLabel warning=new JLabel(centent);
        warning.setBounds(0,0 ,200,150);*/
        jDialog.getContentPane().add(warningArea);
        //展示弹窗
        jDialog.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj==rightCoad){
            // 重新生成验证码
            coadSrr = t.Getcoad();
            rightCoad.setText(coadSrr);
//            test t=new test();
//            String  coadsrr=t.Getcoad();
//            rightCoad.setText(coadsrr);
        }
        //String coadtext = coad.getText();

        else if (obj==loginText){
            // 获取用户输入的验证码
            String coadtext = coad.getText();
            // 验证验证码是否正确


            if (!coadtext.equalsIgnoreCase(coadSrr)){
               showJDialog("验证码错误");
               return;

            }// 获取用户名和密码
            String usernameText = username.getText();
            String mimaText = mima.getText();
            // 验证用户名和密码
            boolean isLoginSuccess = false;
            for (User user : list) {
                if (usernameText.equalsIgnoreCase(user.getName()) && mimaText.equals(user.getMima())) {
                    isLoginSuccess = true;
                    break;
                }
            }
            if (isLoginSuccess) {
                // 登录成功，创建并显示主界面
                new GameJFrame();
                // 关闭当前的登录窗口
                this.dispose();
            } else {
                showJDialog("用户名或密码错误");
            }




//             if (coadtext.equalsIgnoreCase(coadSrr)){
//
//                for (int i = 0; i < list.size(); i++) {
//                    User user = list.get(i);
//                    if(!usernameText.equalsIgnoreCase(user.getName())&&mimaText.equals(user.getMima())){
//                        showJDialog("用户名或密码错误");
//                        return;
//                    }else {
//                        // 1. 创建并显示主界面GameJFrame
//                        new GameJFrame();
//                        // 可选：让主界面居中显示
//                       // gameFrame.setLocationRelativeTo(null);
//
//                        // 2. 关闭当前的登录窗口
//                        this.dispose(); // dispose()关闭当前窗口，释放资源
//                    }
//                }
//            }






        }




    }
}
