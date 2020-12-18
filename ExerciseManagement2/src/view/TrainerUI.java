package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import model.MyMemberListModel;
import model.RsvListModel;
import model.RsvTableModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class TrainerUI extends JFrame {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public TrainerUI(String userId) {
		setBounds(400, 250, 810, 650);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 794, 616);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(17, 49, 760, 542);
		contentPanel.add(panel);
		panel.setLayout(new CardLayout(0, 0));

		// 프로그램 계획 panel
		JPanel planEx_panel = new JPanel();
		planEx_panel.setLayout(null);
		panel.add(planEx_panel, "planEx_panel");

		JList expo_list = new JList();
		expo_list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 프로그램 계획 다이아로그
				String mserial = ((String)expo_list.getSelectedValue()).substring(((String)expo_list.getSelectedValue()).length()-4, ((String)expo_list.getSelectedValue()).length());
						
				PlanExDialog planExDialog = new PlanExDialog(userId, mserial);
				planExDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				planExDialog.setModal(true);
				planExDialog.setVisible(true);
			}
		});
		expo_list.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		expo_list.setBounds(0, 0, 760, 542);
		expo_list.setModel(new MyMemberListModel(userId));
		planEx_panel.add(expo_list);

		// 회원 정보 panel
		JPanel info_panel = new JPanel();
		info_panel.setLayout(null);
		info_panel.setBackground(Color.WHITE);
		panel.add(info_panel, "info_panel");

		JList mem_list = new JList();
		mem_list.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		mem_list.setBounds(0, 0, 760, 542);
		mem_list.setModel(new MyMemberListModel(userId));
		info_panel.add(mem_list);

		// 로그확인 panel
		JPanel checkLog_panel = new JPanel();
		checkLog_panel.setLayout(null);
		panel.add(checkLog_panel, "checkLog_panel");

		UtilDateModel datepick_model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(datepick_model1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		datePicker1.getJFormattedTextField().setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		datePicker1.setBounds(17, 16, 172, 27);
		checkLog_panel.add(datePicker1);

		UtilDateModel datepick_model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(datepick_model2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.getJFormattedTextField().setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		datePicker2.setBounds(220, 17, 172, 27);
		checkLog_panel.add(datePicker2);

		JLabel lblNewLabel_2 = new JLabel("~");
		lblNewLabel_2.setBounds(200, 20, 20, 21);
		lblNewLabel_2.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		checkLog_panel.add(lblNewLabel_2);

		JButton searchLogbtn2 = new JButton("걷색");
		searchLogbtn2.setBounds(408, 15, 68, 29);
		checkLog_panel.add(searchLogbtn2);

		JList logList2 = new JList();
		logList2.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		logList2.setEnabled(false);
		logList2.setBounds(0, 68, 760, 474);
		checkLog_panel.add(logList2);

		// 예약확인 panel
		JPanel reserve_panel = new JPanel();
		reserve_panel.setLayout(null);
		panel.add(reserve_panel, "reserve_panel");

		UtilDateModel datepick_model3 = new UtilDateModel();
		JDatePanelImpl datePanel3 = new JDatePanelImpl(datepick_model3);
		JDatePickerImpl datePicker3 = new JDatePickerImpl(datePanel3);
		datePicker3.getJFormattedTextField().setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		datePicker3.setBounds(17, 16, 172, 27);
		reserve_panel.add(datePicker3);

		UtilDateModel datepick_model4 = new UtilDateModel();
		JDatePanelImpl datePanel4 = new JDatePanelImpl(datepick_model4);
		JDatePickerImpl datePicker4 = new JDatePickerImpl(datePanel4);
		datePicker4.getJFormattedTextField().setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		datePicker4.setBounds(220, 17, 172, 27);
		reserve_panel.add(datePicker4);

		JLabel lblNewLabel_9 = new JLabel("~");
		lblNewLabel_9.setBounds(200, 20, 20, 21);
		lblNewLabel_9.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		reserve_panel.add(lblNewLabel_9);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(750, 474);
		scrollPane.setLocation(0, 68);
		reserve_panel.add(scrollPane);

		JList reserve_list = new JList();
		reserve_list.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		scrollPane.setViewportView(reserve_list);

		JButton searchRsvbtn2 = new JButton("걷색");
		searchRsvbtn2.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		searchRsvbtn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Calendar sCal = Calendar.getInstance();
				sCal.setTime(datepick_model3.getValue());

				Calendar eCal = Calendar.getInstance();
				eCal.setTime(datepick_model4.getValue());

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
					JOptionPane.showMessageDialog(TrainerUI.this, "검색 시작일이 과거일 수 없습니다.", "Error", JOptionPane.WARNING_MESSAGE);
				}else if(!sCal.before(eCal)) {
					JOptionPane.showMessageDialog(TrainerUI.this, "검색 시작일이 마지막일보다 클 수 없습니다.", "Error", JOptionPane.WARNING_MESSAGE);
				}else if(chkDate2.before(eCal)) { 
					JOptionPane.showMessageDialog(TrainerUI.this, "검색은 최대 7일까지 가능합니다.", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					reserve_list.setModel(new RsvListModel(userId, sCal, eCal));
				}
				scrollPane.setViewportView(reserve_list);
			}
		});                                                    
		searchRsvbtn2.setBounds(408, 15, 68, 29);
		reserve_panel.add(searchRsvbtn2);

		// panel 버튼
		JButton btn_reserve = new JButton("예약확인");
		btn_reserve.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_reserve.setBounds(153, 11, 105, 29);
		btn_reserve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel.getLayout()).show(panel,"reserve_panel");
			}
		});
		contentPanel.add(btn_reserve);

		JButton btn_checkEx = new JButton("프로그램 계획");
		btn_checkEx.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_checkEx.setBounds(263, 11, 147, 29);
		btn_checkEx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel.getLayout()).show(panel,"planEx_panel");
			}
		});
		contentPanel.add(btn_checkEx);

		JButton btn_checkLog = new JButton("기록 확인");
		btn_checkLog.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_checkLog.setBounds(415, 11, 111, 29);
		btn_checkLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel.getLayout()).show(panel,"checkLog_panel");
			}
		});
		contentPanel.add(btn_checkLog);

		JButton btn_info = new JButton("회원정보");
		btn_info.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btn_info.setBounds(531, 11, 105, 29);
		btn_info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout)panel.getLayout()).show(panel,"info_panel");
			}
		});
		contentPanel.add(btn_info);

	}
}
