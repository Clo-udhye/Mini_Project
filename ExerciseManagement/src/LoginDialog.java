import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField idField;
	private JPasswordField passwordField;
	private boolean login = false;
	private String login_type;
	private String userId;
	
	public boolean succressLogin() {
		return login;
	}

	public String getLoginType() {
		return login_type;
	}
	
	public String getUserId() {
		return userId;
	}
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */
	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		setTitle("로그인");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			idField = new JTextField();
			idField.setColumns(10);
			idField.setBounds(70, 69, 166, 27);
			contentPanel.add(idField);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(69, 107, 166, 27);
			contentPanel.add(passwordField);
		}
		{
			JButton loginbtn = new JButton("로그인");
			loginbtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ExManaDAO dao = new ExManaDAO();
					String id = idField.getText();
					String password = new String(passwordField.getPassword());
					if(id==null||password==null) {
						JOptionPane.showMessageDialog(LoginDialog.this, "메시지", "타이틀", JOptionPane.WARNING_MESSAGE);
					}
					if(id!=null&&password!=null) {
						ArrayList<UserTO> to = dao.login(id, password);
						if(to.isEmpty()) {
							JOptionPane.showMessageDialog(LoginDialog.this, "아이디와 비밀번호를 확인해주세요.", "Error", JOptionPane.WARNING_MESSAGE);
						} else {
							login = true;	// 로그인 성공
							login_type = to.get(0).getType();
							userId = to.get(0).getId();
							dispose();
						}
					}
				}
			});
			loginbtn.setBounds(247, 70, 129, 69);
			contentPanel.add(loginbtn);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("취소");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
