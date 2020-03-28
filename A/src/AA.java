

import java.awt.event.*;
import java.net.URL;

//Label表示标签
import javax.swing.*;
import java.awt.*;

public class AA extends JFrame{
	
	 int xOld = 0;
	 int yOld = 0;
	public AA(){ 
		setType(JFrame.Type.UTILITY);
		this.setSize(200, 200);
		this.getContentPane().setLayout(null);//流式布局
		this.setTitle("测试动画");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocation(1600,600 );

		// 动画的承载体
		JLabel jLabel = new JLabel();
		// 初始化第一张图
		this.cgJLabelImg(jLabel,"a1.png");

		this.add(jLabel);
		// 框体透明
		        this.setUndecorated(true); // 取消窗口标题栏
		        this.setBackground(new Color(0,0,0,0));// 背景透明

				this.setVisible(true);
		
				this.addMouseListener(new MouseAdapter() {
					  @Override
					  public void mousePressed(MouseEvent e) {
					    xOld = e.getX();//记录鼠标按下时的坐标
					    yOld = e.getY();
					   }
					  });
					  
					    this.addMouseMotionListener(new MouseMotionAdapter() {
					    @Override
					    public void mouseDragged(MouseEvent e) {
					    int xOnScreen = e.getXOnScreen();
					    int yOnScreen = e.getYOnScreen();
					    int xx = xOnScreen - xOld;
					    int yy = yOnScreen - yOld;
					    AA.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
					    }
					   });

		// 动画线程
		new Thread(() -> {
			int i=1;
			try{
				while (true){
					Thread.sleep(250);
					cgJLabelImg(jLabel,"a"+ i++ +".png");
					if(i>55)i=1;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}).start();
		
		
		if(SystemTray.isSupported()){// 判断系统是否支持系统托盘
			SystemTray tray = SystemTray.getSystemTray(); // 获取当前系统的托盘
			
			// 为托盘添加一个右键弹出菜单
			PopupMenu popMenu = new PopupMenu();
			
			MenuItem itemOpen = new MenuItem("打开");
			itemOpen.addActionListener(e -> System.out.println("打开"));
			
			MenuItem itemExit = new MenuItem("退出");
			itemExit.addActionListener(e -> System.exit(0));
			
			popMenu.add(itemOpen);
			popMenu.add(itemExit);
 
			// 设置托盘图标
			ImageIcon icon = new ImageIcon("src/images/trayIcon.png");
			Image image = icon.getImage().getScaledInstance(icon.getIconWidth(),icon.getIconHeight(), Image.SCALE_DEFAULT);
			
			TrayIcon trayIcon = new TrayIcon(image,"桌面宠物",popMenu);
			trayIcon.setImageAutoSize(true); // 自适应尺寸，这个属性至关重要
			
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	private void cgJLabelImg(JLabel jLabel,String imgUrl){
		ImageIcon icon = new ImageIcon(imgUrl);
		int picWidth = icon.getIconWidth(),pinHeight = icon.getIconHeight();
		icon.setImage(icon.getImage().getScaledInstance(picWidth,pinHeight, Image.SCALE_DEFAULT));
		jLabel.setBounds(0,0,picWidth,pinHeight);
		jLabel.setIcon(icon);
		}
	
		
	public static void main(String[] args){
		new AA();
	}
}

