package com.badou.start;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import net.sf.json.JSONObject;

import com.badou.start.bean.ReturnBean;
import com.badou.start.utils.HttpRequest;
import com.badou.start.utils.RButton;

public class LoginDialog extends JFrame{
	
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
    private static int height = 180;

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
    
    public LoginDialog(){
    	Display.getDefault().getActiveShell().setEnabled(false);
    	Shell parentShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    	parentShell.setEnabled(false);
    	
    	setUIFont();
    	Font font =new Font("宋体", Font.PLAIN, 15);//设置字体  
        jf_1=new JFrame("登陆界面");  
        jf_1.setSize(width, height); 
        jf_1.setResizable(false);
        //界面居中
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getSize().x/2-width/2; 
        int y = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getSize().y/2-height/2;
       /* int x = (int)(toolkit.getScreenSize().getWidth()-width)/2;
        int y = (int)(toolkit.getScreenSize().getHeight()-height)/2;*/
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
        //bt1.setFont(font1);  
        //bt1.setBackground(new Color(245,244,243));
       // bt1.setBorder(BorderFactory.createLineBorder(new Color(206,203,201)));//设置面板边框颜色 
        // bt1.setBorderPainted(false); 
        //bt1.setBorder(BorderFactory.createRaisedBevelBorder()); 
        bt1.addActionListener(new LoginButtonListener());
        
        
        bt2=new RButton("取消");  
        bt2.setBounds(200, 100, 100, 30);  
       //bt2.setFont(font1);  
        //bt2.setBackground(new Color(245,244,243));
        //bt2.setBorder(BorderFactory.createLineBorder(new Color(206,203,201)));//设置面板边框颜色 
        // bt1.setBorderPainted(false); 
       // bt2.setBorder(BorderFactory.createRaisedBevelBorder()); 
       bt2.addActionListener(new LoginOutButtonListener());
       
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
    //登录监听
    private class LoginButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String username = jtext1.getText();
			String password = jtext2.getText();
			//
			String message = null;
			if(username==null || username.length()<1){
				message="请输入用户名!";
			}else if(password==null || password.length()<1){
				message="请输入密码!";
			}else{
				String parameter = "username="+username+"&password="+password+"&appId=www.badousoft.com&appKey=IOS";
				ReturnBean bean = new ReturnBean();
				 //发送 POST 请求
		        String str=HttpRequest.sendPost(url, parameter);
		        String subStr = str.substring(str.indexOf("{"), str.lastIndexOf("}")+1);
		        JSONObject jsonObject = JSONObject.fromObject(subStr);
				bean = (ReturnBean) JSONObject.toBean(jsonObject, ReturnBean.class); 
				if(bean.getHasOk()){
					JOptionPane.showMessageDialog(jf_1, "登录成功", "成功信息", JOptionPane.QUESTION_MESSAGE);
					//关闭窗口锁定
					PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {  
		                public void run() {  
		                    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();  
		                    window.getShell().setEnabled(true);
		                }  
		            }); 
					jf_1.dispose();
				}else{
					message = bean.getMessage();
				}
			}
			if(message!=null)
				JOptionPane.showMessageDialog(jf_1, message, "错误信息", JOptionPane.ERROR_MESSAGE);
		}
    	
    }
    //取消监听
    private class LoginOutButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			jtext1.setText("");
			jtext2.setText("");
		}
    }
    
    public static void main(String[] args) {
    	 try {  
             // select Look and Feel  
             UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");  
             // start application  
             new LoginDialog();  
         }  
         catch (Exception ex) {  
             ex.printStackTrace();  
         }  
    	//LoginDialog login = new LoginDialog();
	}
}
