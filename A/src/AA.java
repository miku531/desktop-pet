

import java.awt.event.*;
import java.net.URL;

//Label��ʾ��ǩ
import javax.swing.*;
import java.awt.*;

public class AA extends JFrame{
	
	 int xOld = 0;
	 int yOld = 0;
	public AA(){ 
		setType(JFrame.Type.UTILITY);
		this.setSize(200, 200);
		this.getContentPane().setLayout(null);//��ʽ����
		this.setTitle("���Զ���");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocation(1600,600 );

		// �����ĳ�����
		JLabel jLabel = new JLabel();
		// ��ʼ����һ��ͼ
		this.cgJLabelImg(jLabel,"a1.png");

		this.add(jLabel);
		// ����͸��
		        this.setUndecorated(true); // ȡ�����ڱ�����
		        this.setBackground(new Color(0,0,0,0));// ����͸��

				this.setVisible(true);
		
				this.addMouseListener(new MouseAdapter() {
					  @Override
					  public void mousePressed(MouseEvent e) {
					    xOld = e.getX();//��¼��갴��ʱ������
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
					    AA.this.setLocation(xx, yy);//������ק�󣬴��ڵ�λ��
					    }
					   });

		// �����߳�
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
		
		
		if(SystemTray.isSupported()){// �ж�ϵͳ�Ƿ�֧��ϵͳ����
			SystemTray tray = SystemTray.getSystemTray(); // ��ȡ��ǰϵͳ������
			
			// Ϊ�������һ���Ҽ������˵�
			PopupMenu popMenu = new PopupMenu();
			
			MenuItem itemOpen = new MenuItem("��");
			itemOpen.addActionListener(e -> System.out.println("��"));
			
			MenuItem itemExit = new MenuItem("�˳�");
			itemExit.addActionListener(e -> System.exit(0));
			
			popMenu.add(itemOpen);
			popMenu.add(itemExit);
 
			// ��������ͼ��
			ImageIcon icon = new ImageIcon("src/images/trayIcon.png");
			Image image = icon.getImage().getScaledInstance(icon.getIconWidth(),icon.getIconHeight(), Image.SCALE_DEFAULT);
			
			TrayIcon trayIcon = new TrayIcon(image,"�������",popMenu);
			trayIcon.setImageAutoSize(true); // ����Ӧ�ߴ磬�������������Ҫ
			
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

