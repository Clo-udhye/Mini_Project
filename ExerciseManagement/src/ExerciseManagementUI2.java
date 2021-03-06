import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.TitledBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ExerciseManagementUI2 extends JFrame {
	private String userId;
	
	private JPanel contentPane;
	private JLabel lbl_date1;
	private JFormattedTextField sdate_field1;
	private JFormattedTextField edate_field1;
	private JLabel lbl_mcontact1;
	private JTable reserve_table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExerciseManagementUI2 frame = new ExerciseManagementUI2();
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
	public ExerciseManagementUI2() {
		setTitle("헬스장 운동관리 시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 672);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		DateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd");
		/*
		// 로그인 다이어로그
		LoginDialog dialog = new LoginDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setVisible(true);

		//System.out.println(dialog.succressLogin());
		String login_type = "MEM";
		if(dialog.succressLogin()) {
			login_type = dialog.getLoginType();
			setVisible(true);
		} else {
			dispose(); // 로그인 하지 않으면 프로그램 종료
		}
		 */
		setVisible(true);

		contentPane.setLayout(new CardLayout(0, 0));

		JPanel mem_panel = new JPanel();
		contentPane.add(mem_panel, "mem_panel");

		JPanel tra_panel = new JPanel();
		contentPane.add(tra_panel, "tra_panel");
		tra_panel.setLayout(null);

		ArrayList<MemberTO> memInfo = new ArrayList<MemberTO>(); // 멤버 정보를 저장

		String login_type = "MEM";	// 테스트용

		// user 타입에 따라 화면 다르게 보이기
		if(login_type.equals("TRA")) {
			((CardLayout)contentPane.getLayout()).show(contentPane,"tra_panel");
		} else {
			((CardLayout)contentPane.getLayout()).show(contentPane,"mem_panel");
			ExManaDAO dao = new ExManaDAO();
			//userId = dialog.getUserId();
			//memInfo = dao.searchInfo(userId);
			
			userId = "user3";
			memInfo = dao.searchInfo(userId);	//테스트용
		}

		JPanel panel2 = new JPanel();
		panel2.setBounds(17, 49, 750, 542);
		tra_panel.add(panel2);
		panel2.setLayout(new CardLayout(0, 0));

		JPanel info_panel2 = new JPanel();
		info_panel2.setLayout(null);
		info_panel2.setBackground(Color.WHITE);
		panel2.add(info_panel2, "info_panel2");

		JList mem_list = new JList();
		mem_list.setBounds(0, 0, 750, 542);
		info_panel2.add(mem_list);

		JPanel checkEx_panel2 = new JPanel();
		checkEx_panel2.setLayout(null);
		panel2.add(checkEx_panel2, "checkEx_panel2");

		JList reserve_list2 = new JList();
		reserve_list2.setEnabled(false);
		reserve_list2.setBounds(0, 0, 750, 542);
		checkEx_panel2.add(reserve_list2);

		JPanel checkLog_panel2 = new JPanel();
		checkLog_panel2.setLayout(null);
		panel2.add(checkLog_panel2, "checkLog_panel2");

		JFormattedTextField sdate_field2_1 = new JFormattedTextField();
		sdate_field2_1.setBounds(17, 16, 136, 27);
		checkLog_panel2.add(sdate_field2_1);

		JFormattedTextField edate_field2_1 = new JFormattedTextField();
		edate_field2_1.setBounds(186, 16, 136, 27);
		checkLog_panel2.add(edate_field2_1);

		JLabel lblNewLabel_2 = new JLabel("~");
		lblNewLabel_2.setBounds(160, 19, 20, 21);
		checkLog_panel2.add(lblNewLabel_2);

		JButton searchLogbtn2 = new JButton("걷색");
		searchLogbtn2.setBounds(332, 15, 68, 29);
		checkLog_panel2.add(searchLogbtn2);

		JList logList2 = new JList();
		logList2.setEnabled(false);
		logList2.setBounds(0, 68, 750, 474);
		checkLog_panel2.add(logList2);

		JPanel reserve_panel2 = new JPanel();
		reserve_panel2.setLayout(null);
		panel2.add(reserve_panel2, "reserve_panel2");

		JFormattedTextField sdate_field2_2 = new JFormattedTextField();
		sdate_field2_2.setBounds(17, 16, 136, 27);
		reserve_panel2.add(sdate_field2_2);

		JFormattedTextField edate_field2_2 = new JFormattedTextField();
		edate_field2_2.setBounds(186, 16, 136, 27);
		reserve_panel2.add(edate_field2_2);

		JLabel lblNewLabel_9 = new JLabel("~");
		lblNewLabel_9.setBounds(160, 19, 20, 21);
		reserve_panel2.add(lblNewLabel_9);

		JButton searchRsvbtn2 = new JButton("걷색");
		searchRsvbtn2.setBounds(332, 15, 68, 29);
		reserve_panel2.add(searchRsvbtn2);

		JList reserve_list3 = new JList();
		reserve_list3.setEnabled(false);
		reserve_list3.setBounds(0, 68, 750, 474);
		reserve_panel2.add(reserve_list3);

		// tra_panel 버튼
		JButton btn_reserve2 = new JButton("예약확인");
		btn_reserve2.setBounds(153, 5, 105, 29);
		btn_reserve2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel2.getLayout()).show(panel2,"reserve_panel2");
			}
		});
		tra_panel.add(btn_reserve2);

		JButton btn_checkEx2 = new JButton("프로그램 계획");
		btn_checkEx2.setBounds(263, 5, 147, 29);
		btn_checkEx2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel2.getLayout()).show(panel2,"checkEx_panel2");
			}
		});
		tra_panel.add(btn_checkEx2);

		JButton btn_checkLog2 = new JButton("기록 확인");
		btn_checkLog2.setBounds(415, 5, 111, 29);
		btn_checkLog2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel2.getLayout()).show(panel2,"checkLog_panel2");
			}
		});
		tra_panel.add(btn_checkLog2);

		JButton btn_info2 = new JButton("회원정보");
		btn_info2.setBounds(531, 5, 105, 29);
		btn_info2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel2.getLayout()).show(panel2,"info_panel2");
			}
		});
		tra_panel.add(btn_info2);
		mem_panel.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBounds(17, 49, 750, 542);
		mem_panel.add(panel1);
		panel1.setLayout(new CardLayout(0, 0));

		JPanel info_panel = new JPanel();
		info_panel.setBackground(Color.WHITE);
		panel1.add(info_panel, "info_panel");
		info_panel.setLayout(null);

		JLabel lblNewLabel1_1 = new JLabel("이름 : ");
		lblNewLabel1_1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lblNewLabel1_1.setBounds(36, 27, 82, 21);
		info_panel.add(lblNewLabel1_1);

		JLabel lblNewLabel1_2 = new JLabel("연락처 : ");
		lblNewLabel1_2.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lblNewLabel1_2.setBounds(22, 63, 82, 21);
		info_panel.add(lblNewLabel1_2);

		JLabel lblNewLabel1_3 = new JLabel("성별 : ");
		lblNewLabel1_3.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lblNewLabel1_3.setBounds(36, 99, 82, 21);
		info_panel.add(lblNewLabel1_3);

		JLabel lblNewLabel1_4 = new JLabel("키 :");
		lblNewLabel1_4.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		lblNewLabel1_4.setBounds(50, 135, 82, 21);
		info_panel.add(lblNewLabel1_4);

		JLabel lblNewLabel1_5 = new JLabel("몸무게 : ");
		lblNewLabel1_5.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lblNewLabel1_5.setBounds(22, 172, 82, 21);
		info_panel.add(lblNewLabel1_5);

		JLabel lbl_mname1 = new JLabel(memInfo.get(0).getMname());
		lbl_mname1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lbl_mname1.setBounds(105, 27, 82, 21);
		info_panel.add(lbl_mname1);

		lbl_mcontact1 = new JLabel(memInfo.get(0).getMcontact());
		lbl_mcontact1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lbl_mcontact1.setBounds(105, 63, 156, 21);
		info_panel.add(lbl_mcontact1);

		JLabel lbl_gender1 = new JLabel(memInfo.get(0).getGender());
		lbl_gender1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lbl_gender1.setBounds(105, 99, 82, 21);
		info_panel.add(lbl_gender1);

		JLabel lbl_height1 = new JLabel(memInfo.get(0).getHeight());
		lbl_height1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lbl_height1.setBounds(105, 135, 82, 21);
		info_panel.add(lbl_height1);

		JLabel lbl_weight1 = new JLabel(memInfo.get(0).getWeight());
		lbl_weight1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lbl_weight1.setBounds(105, 171, 82, 21);
		info_panel.add(lbl_weight1);

		JLabel lblNewLabel1_6 = new JLabel("cm");
		lblNewLabel1_6.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lblNewLabel1_6.setBounds(169, 135, 82, 21);
		info_panel.add(lblNewLabel1_6);

		JLabel lblNewLabel1_7 = new JLabel("kg");
		lblNewLabel1_7.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lblNewLabel1_7.setBounds(169, 172, 82, 21);
		info_panel.add(lblNewLabel1_7);

		JPanel checkEx_panel = new JPanel();
		checkEx_panel.setLayout(null);
		checkEx_panel.setBounds(17, 49, 750, 542);
		panel1.add(checkEx_panel, "checkEx_panel");

		lbl_date1 = new JLabel("yyyy-mm-dd");
		lbl_date1.setFont(new Font("함초롬돋움", Font.PLAIN, 17));
		lbl_date1.setBounds(34, 32, 119, 21);
		checkEx_panel.add(lbl_date1);

		JList exList1 = new JList();
		exList1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		exList1.setEnabled(false);
		exList1.setBounds(0, 68, 750, 474);
		checkEx_panel.add(exList1);

		JPanel checkLog_panel = new JPanel();
		checkLog_panel.setLayout(null);
		checkLog_panel.setBounds(17, 49, 750, 542);
		panel1.add(checkLog_panel, "checkLog_panel");

		//jdatepicker-1.3.2.jar
		JFormattedTextField sdate_field1_1 = new JFormattedTextField();
		sdate_field1_1.setBounds(17, 16, 136, 27);
		checkLog_panel.add(sdate_field1_1);

		JFormattedTextField edate_field1_1 = new JFormattedTextField();
		edate_field1_1.setBounds(186, 16, 136, 27);
		checkLog_panel.add(edate_field1_1);

		JLabel lblNewLabel_1 = new JLabel("~");
		lblNewLabel_1.setBounds(160, 19, 20, 21);
		checkLog_panel.add(lblNewLabel_1);

		JButton searchLogbtn1 = new JButton("걷색");
		searchLogbtn1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		searchLogbtn1.setBounds(332, 15, 68, 29);
		checkLog_panel.add(searchLogbtn1);

		JList log_list1 = new JList();
		log_list1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		log_list1.setEnabled(false);
		log_list1.setBounds(0, 68, 750, 474);
		checkLog_panel.add(log_list1);

		JPanel reserve_panel = new JPanel();
		reserve_panel.setBounds(17, 49, 750, 542);
		panel1.add(reserve_panel, "reserve_panel");
		reserve_panel.setLayout(null);
		
		UtilDateModel datepick_model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(datepick_model1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		datePicker1.setBounds(17, 16, 172, 27);
		reserve_panel.add(datePicker1);
		
		UtilDateModel datepick_model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(datepick_model2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.setBounds(220, 17, 172, 27);
		reserve_panel.add(datePicker2);

		JLabel lblNewLabel = new JLabel("~");
		lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		lblNewLabel.setBounds(200, 20, 20, 21);
		reserve_panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 68, 750, 474);
		reserve_panel.add(scrollPane);

		JButton searchRsvbtn1 = new JButton("걷색");
		searchRsvbtn1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		searchRsvbtn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Calendar sCal = Calendar.getInstance();
				sCal.setTime(datepick_model1.getValue());
				
				Calendar eCal = Calendar.getInstance();
				eCal.setTime(datepick_model2.getValue());
				eCal.add(Calendar.DATE, 1); 	
				
				reserve_table.setModel(new RsvTableModel(userId, sCal, eCal));
				
				reserve_table.getColumnModel().getColumn(0).setResizable(false);
				reserve_table.getColumnModel().getColumn(0).setPreferredWidth(150);
				reserve_table.getColumnModel().getColumn(1).setResizable(false);
				reserve_table.getColumnModel().getColumn(1).setPreferredWidth(450);
				reserve_table.getColumnModel().getColumn(2).setResizable(false);
				reserve_table.getColumnModel().getColumn(2).setPreferredWidth(150);
				scrollPane.setViewportView(reserve_table);
			}
		});
		searchRsvbtn1.setBounds(408, 15, 68, 29);
		reserve_panel.add(searchRsvbtn1);
		
		reserve_table = new JTable();
		reserve_table.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		reserve_table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
					"날짜", "시간", "예약상황"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		reserve_table.getColumnModel().getColumn(0).setResizable(false);
		reserve_table.getColumnModel().getColumn(0).setPreferredWidth(150);
		reserve_table.getColumnModel().getColumn(1).setResizable(false);
		reserve_table.getColumnModel().getColumn(1).setPreferredWidth(450);
		reserve_table.getColumnModel().getColumn(2).setResizable(false);
		reserve_table.getColumnModel().getColumn(2).setPreferredWidth(150);
		scrollPane.setViewportView(reserve_table);

		// mem_pane버튼
		JButton btn_reserve1 = new JButton("예약하기");
		btn_reserve1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_reserve1.setBounds(153, 5, 105, 29);
		btn_reserve1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel1.getLayout()).show(panel1,"reserve_panel");
			}
		});
		mem_panel.add(btn_reserve1);

		JButton btn_checkEx1 = new JButton("프로그램 확인");
		btn_checkEx1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_checkEx1.setBounds(263, 5, 147, 29);
		btn_checkEx1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel1.getLayout()).show(panel1,"checkEx_panel");
			}
		});
		mem_panel.add(btn_checkEx1);

		JButton btn_checkLog1 = new JButton("기록 확인");
		btn_checkLog1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_checkLog1.setBounds(415, 5, 111, 29);
		btn_checkLog1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel1.getLayout()).show(panel1,"checkLog_panel");
			}
		});
		mem_panel.add(btn_checkLog1);

		JButton btn_info1 = new JButton("회원정보");
		btn_info1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_info1.setBounds(531, 5, 105, 29);
		btn_info1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel1.getLayout()).show(panel1,"info_panel");
			}
		});
		mem_panel.add(btn_info1);

	}
}
