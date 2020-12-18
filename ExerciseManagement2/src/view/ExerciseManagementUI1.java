package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;

public class ExerciseManagementUI1 extends JFrame {

	private JPanel contentPane;
	private JLabel lbl_date1;
	private JFormattedTextField sdate_field1;
	private JFormattedTextField edate_field1;
	private JLabel lbl_mcontact1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExerciseManagementUI1 frame = new ExerciseManagementUI1();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExerciseManagementUI1() {
		setTitle("헬스장 운동관리 시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 672);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		// 로그인 다이어로그
		LoginDialog loginDialog = new LoginDialog();
		loginDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		loginDialog.setModal(true);
		loginDialog.setVisible(true);
		
		
		//System.out.println(dialog.succressLogin());
		String login_type = "MEM";
		String userId = "";
		if(loginDialog.succressLogin()) {
			login_type = loginDialog.getLoginType();
			userId = loginDialog.getUserId();
		} else {
			//System.out.println("종료");
			dispose(); // 로그인 하지 않으면 프로그램 종료
			System.exit(0);
		}
		
		//테스트용
		//String login_type = "TRA";
		//String userId = "user1";
		
		
		if(login_type.equals("MEM")) {
			MemberUI memFrame = new MemberUI(userId);
			memFrame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
			memFrame.setVisible(true);
			
			dispose();
		} else {
			TrainerUI traFrame = new TrainerUI(userId);
			traFrame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
			traFrame.setVisible(true);
			
			dispose();
		}
	}
}
