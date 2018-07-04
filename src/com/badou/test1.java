package com.badou;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.badou.start.utils.RButton;

public class test1 {
	
	  private static int count=0;  
	    private static JButton bt1;//登陆按钮  
	    private static JButton bt2;//忘记密码按钮  
	    private static JLabel jl_1;//登录的版面  
	    private static JFrame jf_1;//登陆的框架  
	    private static JTextField jtext1;//用户名  
	    private static JPasswordField jtext2;//密码  
	    private static JLabel jl_admin;  
	    private static JLabel jl_password;  
	    private static String url="http://api.mdzplus.com/api/app/logon/userlogon/login.do";
	    private static int  width = 350;
	    private static int height = 150;

		public static void setUIFont()
	    {
	    	Font f = new Font("宋体",Font.PLAIN,18);
	    	String names[]={ "Label", "CheckBox", "PopupMenu","MenuItem", "CheckBoxMenuItem",
	    			"JRadioButtonMenuItem","ComboBox", "Button", "Tree", "ScrollPane",
	    			"TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
	    			"OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
	    			"ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
	    			"PasswordField","TextField", "Table", "Label", "Viewport",
	    			"RadioButtonMenuItem","RadioButton", "DesktopPane", "InternalFrame"
	    	}; 
	    	for (String item : names) {
	    		 UIManager.put(item+ ".font",f); 
	    	}
	    }
	    
	
	private void mian() {
		Font font =new Font("宋体", Font.PLAIN, 15);//设置字体  
        jf_1=new JFrame("登陆界面");  
        jf_1.setSize(width, height); 
        jf_1.setResizable(false);
        //界面居中
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth()-width)/2;
        int y = (int)(toolkit.getScreenSize().getHeight()-height)/2;
        jf_1.setLocation(x, y);

        jl_1=new JLabel();  
       
        jl_admin=new JLabel("用户名:");  
        jl_admin.setBounds(10, 10, 60, 30);  
        jl_admin.setFont(font);  
          
        jl_password=new JLabel("  密码:");  
        jl_password.setBounds(10, 45, 60, 30);  
        jl_password.setFont(font);  
         
        Font font1 =new Font("宋体", Font.PLAIN, 15);//设置字体
        bt1=new RButton("登陆");         //更改成loginButton  
        bt1.setBounds(70, 100, 100, 30);  
        bt1.setFont(font1);  
        bt1.setBackground(new Color(245,244,243));
        bt1.setBorder(BorderFactory.createLineBorder(new Color(206,203,201)));//设置面板边框颜色 
        // bt1.setBorderPainted(false); 
        bt1.setBorder(BorderFactory.createRaisedBevelBorder()); 
        //bt1.addActionListener(new LoginButtonListener());
        
        
        bt2=new RButton("取消");  
        bt2.setBounds(200, 100, 100, 30);  
       bt2.setFont(font1);  
        bt2.setBackground(new Color(245,244,243));
        bt2.setBorder(BorderFactory.createLineBorder(new Color(206,203,201)));//设置面板边框颜色 
        // bt1.setBorderPainted(false); 
        bt2.setBorder(BorderFactory.createRaisedBevelBorder()); 
     //  bt2.addActionListener(new LoginOutButtonListener());
       
        //加入文本框  
        jtext1=new JTextField("daikemian@badousoft.com");  
        jtext1.setBounds(70, 10, 260, 30);  
        jtext1.setFont(font);  
          
        jtext2=new JPasswordField("111111");//密码输入框  
        jtext2.setBounds(70, 45, 260, 30);  
        jtext2.setFont(font);  
          
        jl_1.add(jtext1);  
        jl_1.add(jtext2);  
          
        jl_1.add(jl_admin);  
        jl_1.add(jl_password);  
        jl_1.add(bt1);  
        jl_1.add(bt2);  
          
        jf_1.add(jl_1);  
        jf_1.setVisible(true);  
        jf_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        jf_1.setAlwaysOnTop(true);
        jf_1.setVisible(true);

	}
}
