package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.ExManaDAO;
import data.MemberTO;
import model.RsvTableModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class MemberUI extends JFrame {

	private final JPanel contentPanel = new JPanel();
	JTable reserve_table = new JTable();

	/**
	 * Create the dialog.
	 */
	public MemberUI(String userId) {
		setBounds(400, 250, 810, 650);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 794, 616);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		ExManaDAO dao = new ExManaDAO();
		ArrayList<MemberTO> memInfo = new ArrayList<MemberTO>();
		memInfo = dao.searchMInfo(userId);

		JPanel panel = new JPanel();
		panel.setBounds(17, 49, 750, 542);
		contentPanel.add(panel);
		panel.setLayout(new CardLayout(0, 0));

		JPanel info_panel = new JPanel();
		info_panel.setBackground(Color.WHITE);
		panel.add(info_panel, "info_panel");
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

		JLabel lbl_mcontact1 = new JLabel(memInfo.get(0).getMcontact());
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
		panel.add(checkEx_panel, "checkEx_panel");

		JLabel lbl_date1 = new JLabel("yyyy-mm-dd");
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
		panel.add(checkLog_panel, "checkLog_panel");

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
		searchLogbtn1.setBounds(408, 15, 68, 29);
		checkLog_panel.add(searchLogbtn1);

		JList log_list1 = new JList();
		log_list1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		log_list1.setEnabled(false);
		log_list1.setBounds(0, 68, 750, 474);
		checkLog_panel.add(log_list1);

		JPanel reserve_panel = new JPanel();
		reserve_panel.setBounds(17, 49, 750, 542);
		panel.add(reserve_panel, "reserve_panel");
		reserve_panel.setLayout(null);

		UtilDateModel datepick_model1 = new UtilDateModel();
		JDatePanelImpl datepanel = new JDatePanelImpl(datepick_model1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datepanel);
		datePicker1.getJFormattedTextField().setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		datePicker1.setBounds(17, 16, 172, 27);
		reserve_panel.add(datePicker1);

		UtilDateModel datepick_model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(datepick_model2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.getJFormattedTextField().setFont(new Font("함초롬돋움", Font.PLAIN, 16));
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

				Calendar chkDate1 = Calendar.getInstance();
				// 현재날짜의 시분초 초기화
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				Date date = chkDate1.getTime();
				String today = new SimpleDateFormat("yyyyMMdd").format(date);
				try {
					chkDate1.setTime(formatter.parse(today));
				} catch (ParseException e1) {
					e1.printStackTrace();
				} 

				Calendar chkDate2 = Calendar.getInstance();
				chkDate2.setTime(sCal.getTime());
				chkDate2.add(Calendar.DATE, 7);

				//System.out.println(chkDate1.getTime() +"" );
				if(!chkDate1.before(sCal)) {
					JOptionPane.showMessageDialog(MemberUI.this, "검색 시작일이 과거일 수 없습니다.", "Error", JOptionPane.WARNING_MESSAGE);
				}else if(!sCal.before(eCal)) {
					JOptionPane.showMessageDialog(MemberUI.this, "검색 시작일이 마지막일보다 클 수 없습니다.", "Error", JOptionPane.WARNING_MESSAGE);
				}else if(chkDate2.before(eCal)) { 
					JOptionPane.showMessageDialog(MemberUI.this, "검색은 최대 7일까지 가능합니다.", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					reserve_table.setModel(new RsvTableModel(userId, sCal, eCal));
				}
				reserve_table.getColumnModel().getColumn(0).setResizable(false);
				reserve_table.getColumnModel().getColumn(0).setPreferredWidth(250);
				reserve_table.getColumnModel().getColumn(1).setResizable(false);
				reserve_table.getColumnModel().getColumn(1).setPreferredWidth(250);
				reserve_table.getColumnModel().getColumn(2).setResizable(false);
				reserve_table.getColumnModel().getColumn(2).setPreferredWidth(250);
				scrollPane.setViewportView(reserve_table);
			}
		});
		searchRsvbtn1.setBounds(408, 15, 68, 29);
		reserve_panel.add(searchRsvbtn1);

		reserve_table = new JTable();
		reserve_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectRow = reserve_table.getSelectedRow();
				String date = reserve_table.getValueAt(selectRow,0) + " "+ reserve_table.getValueAt(selectRow,1);

				if(reserve_table.getValueAt(selectRow,2).equals("예약 불가능")) {

					if(true) { // 내 예약 일 때 

					} else {

					}
				} else {
					int result = JOptionPane.showConfirmDialog(MemberUI.this, date+"로 예약할까요?", "예약", JOptionPane.YES_NO_OPTION);
					//System.out.println(result);

					if(result==0) {
						ExManaDAO dao = new ExManaDAO();
						int isSuccess = dao.insertRsv(date, userId);
						if(isSuccess==1) {
							JOptionPane.showMessageDialog(MemberUI.this, "예약되었습니다.", "예약", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(MemberUI.this, "예약을 다시 진행해주세요.", "Error", JOptionPane.WARNING_MESSAGE);
						}

					}
				}
			}
		});
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
		reserve_table.getColumnModel().getColumn(0).setPreferredWidth(250);
		reserve_table.getColumnModel().getColumn(1).setResizable(false);
		reserve_table.getColumnModel().getColumn(1).setPreferredWidth(250);
		reserve_table.getColumnModel().getColumn(2).setResizable(false);
		reserve_table.getColumnModel().getColumn(2).setPreferredWidth(250);
		scrollPane.setViewportView(reserve_table);

		// mem_pane버튼
		JButton btn_reserve = new JButton("예약하기");
		btn_reserve.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_reserve.setBounds(153, 5, 105, 29);
		btn_reserve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel.getLayout()).show(panel,"reserve_panel");
			}
		});
		contentPanel.add(btn_reserve);

		JButton btn_checkEx = new JButton("프로그램 확인");
		btn_checkEx.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_checkEx.setBounds(263, 5, 147, 29);
		btn_checkEx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel.getLayout()).show(panel,"checkEx_panel");
			}
		});
		contentPanel.add(btn_checkEx);

		JButton btn_checkLog = new JButton("기록 확인");
		btn_checkLog.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_checkLog.setBounds(415, 5, 111, 29);
		btn_checkLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel.getLayout()).show(panel,"checkLog_panel");
			}
		});
		contentPanel.add(btn_checkLog);

		JButton btn_info = new JButton("회원정보");
		btn_info.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_info.setBounds(531, 5, 105, 29);
		btn_info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel.getLayout()).show(panel,"info_panel");
			}
		});
		contentPanel.add(btn_info);

	}

}
