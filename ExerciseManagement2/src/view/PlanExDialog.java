package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.ExManaDAO;
import model.ExListModel;
import model.PlanExListModel;
import model.RsvDateComboBoxModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;

public class PlanExDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList planList;
	ArrayList<String> pExData = new ArrayList<String>();

	/**
	 * Create the dialog.
	 */
	public PlanExDialog(String userId, String mserial) {
		getContentPane().setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		setBounds(400, 150, 634, 705);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// 날짜 선택
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(327, 15, 200, 27);
		comboBox.setModel(new RsvDateComboBoxModel(mserial));
		contentPanel.add(comboBox);

		//운동 목록 리스트 
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		tabbedPane.setBounds(17, 15, 293, 593);
		contentPanel.add(tabbedPane);

		// 추가한 운동 목록
		JScrollPane planScrollPane = new JScrollPane();
		planScrollPane.setBounds(327, 53, 268, 354);
		contentPanel.add(planScrollPane);

		planList = new JList();
		planList.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		planScrollPane.setViewportView(planList);


		// 부위별 운동 목록
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(17, 53, 293, 555);
			JList list = new JList();
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String ex = (String)list.getSelectedValue();
					if(pExData.contains(ex)) {
						JOptionPane.showMessageDialog(PlanExDialog.this, "이미 추가된 운동입니다.", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						pExData.add(ex);
						planList.setModel(new PlanExListModel(pExData));
					}
				}
			});
			list.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
			list.setModel(new ExListModel("복근"));
			scrollPane.setViewportView(list);
			tabbedPane.addTab("복근", scrollPane);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(17, 53, 293, 555);
			JList list = new JList();
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String ex = (String)list.getSelectedValue();
					if(pExData.contains(ex)) {
						JOptionPane.showMessageDialog(PlanExDialog.this, "이미 추가된 운동입니다.", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						pExData.add(ex);
						planList.setModel(new PlanExListModel(pExData));
					}
				}
			});
			list.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
			list.setModel(new ExListModel("가슴"));
			scrollPane.setViewportView(list);
			tabbedPane.addTab("가슴", scrollPane);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(17, 53, 293, 555);
			JList list = new JList();
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String ex = (String)list.getSelectedValue();
					if(pExData.contains(ex)) {
						JOptionPane.showMessageDialog(PlanExDialog.this, "이미 추가된 운동입니다.", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						pExData.add(ex);
						planList.setModel(new PlanExListModel(pExData));
					}
				}
			});
			list.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
			list.setModel(new ExListModel("어깨"));
			scrollPane.setViewportView(list);
			tabbedPane.addTab("어깨", scrollPane);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(17, 53, 293, 555);
			JList list = new JList();
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String ex = (String)list.getSelectedValue();
					if(pExData.contains(ex)) {
						JOptionPane.showMessageDialog(PlanExDialog.this, "이미 추가된 운동입니다.", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						pExData.add(ex);
						planList.setModel(new PlanExListModel(pExData));
					}
				}
			});
			list.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
			list.setModel(new ExListModel("등"));
			scrollPane.setViewportView(list);
			tabbedPane.addTab("등", scrollPane);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(17, 53, 293, 555);
			JList list = new JList();
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String ex = (String)list.getSelectedValue();
					if(pExData.contains(ex)) {
						JOptionPane.showMessageDialog(PlanExDialog.this, "이미 추가된 운동입니다.", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						pExData.add(ex);
						planList.setModel(new PlanExListModel(pExData));
					}
				}
			});
			list.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
			list.setModel(new ExListModel("기타"));
			scrollPane.setViewportView(list);
			tabbedPane.addTab("기타", scrollPane);
		}

		JButton btnNewButton = new JButton("삭제");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!planList.isSelectionEmpty()) {
					//String selected = (String)planList.getSelectedValue();
					pExData.remove((String)planList.getSelectedValue());
					planList.setModel(new PlanExListModel(pExData));
				}
			}
		});
		btnNewButton.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btnNewButton.setBounds(503, 422, 92, 29);
		contentPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("완료");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 계획한 리스트를 데이터베이스에 삽입
				if(comboBox.getSelectedItem()!=null) {
					String selectedDate = (String)comboBox.getSelectedItem();

					ExManaDAO dao = new ExManaDAO();
					int result = dao.insertExprogram(userId, pExData, selectedDate, mserial);

					if(result == 1) {
						JOptionPane.showMessageDialog(PlanExDialog.this, "저장되었습니다.", "프로그램 계획", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(PlanExDialog.this, "성공적으로 저장하지 못했습니다.", "Error", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(PlanExDialog.this, "날짜를 입력해주세요.", "Error", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnNewButton_1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		btnNewButton_1.setBounds(394, 422, 92, 29);
		contentPanel.add(btnNewButton_1);

		// 버튼 패널
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("확인");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int result = JOptionPane.showConfirmDialog(PlanExDialog.this, "완료하지 않은 목록은 사라집니다. 프로그램 계획을 종료할까요?", "프로그램 계획", JOptionPane.YES_NO_OPTION);
						if(result == 0) {
							dispose();
						}
					}
				});
				okButton.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
