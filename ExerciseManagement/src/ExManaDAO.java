import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExManaDAO{
	Connection conn = null;

	public ExManaDAO() {
		// TODO Auto-generated constructor stub
		String url = "jdbc:mysql://localhost:3306/exmana";
		String user = "root";
		String password = "!123456";

		try {
			Class.forName("org.mariadb.jdbc.Driver"); 
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		}

	}

	public ArrayList<UserTO> login(String id, String password){
		ArrayList<UserTO> user = new ArrayList<UserTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select id, password, type from user where id = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				UserTO to = new UserTO();
				to.setId(rs.getString("id"));
				to.setPassword(rs.getString("password"));
				to.setType(rs.getString("type"));

				user.add(to);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		}

		//System.out.println(user.get(0).getId());
		return user;
	}

	public ArrayList<MemberTO> searchInfo(String id){
		ArrayList<MemberTO> memberInfo = new ArrayList<MemberTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {			
			String sql = "select mserial, id, mname, tserial, mcontact, height, weight, gender from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				MemberTO to = new MemberTO();
				to.setMserial(rs.getString("mserial"));
				to.setId(rs.getString("id"));
				to.setMname(rs.getString("mname"));
				to.setTserial(rs.getString("tserial"));
				to.setMcontact(rs.getString("mcontact"));
				to.setHeight(rs.getString("height"));
				to.setWeight(rs.getString("weight"));
				to.setGender(rs.getString("gender"));

				memberInfo.add(to);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		}

		return memberInfo;
	}

	public ArrayList<TScheduleTO> searchTSchedule(String userId, Calendar sCal, Calendar eCal){
		ArrayList<TScheduleTO> tschedule = new ArrayList<TScheduleTO>();
		ArrayList<TScheduleTO> scheduled = new ArrayList<TScheduleTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sdate = String.format("%d-%d-%d 00:00:00", sCal.get(Calendar.YEAR), sCal.get(Calendar.MONTH)+1, sCal.get(Calendar.DATE));
		String edate = String.format("%d-%d-%d 00:00:00", eCal.get(Calendar.YEAR), eCal.get(Calendar.MONTH)+1, eCal.get(Calendar.DATE));
		//System.out.println(sdate +" "+ edate);
		try {			
			String sql = "select ts.tserial, date, ts.mserial from tschedule ts inner join member using (tserial) "
					+ "where id = ? and date between ? and ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, sdate);
			pstmt.setString(3, edate);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				TScheduleTO to = new TScheduleTO();
				to.setTserial(rs.getString("tserial"));
				int index =rs.getString("date").lastIndexOf(":");
				to.setDate(rs.getString("date").substring(0, index));
				to.setMserial(rs.getString("mserial"));

				scheduled.add(to);
			}
			
			for (Date date = sCal.getTime(); sCal.before(eCal); sCal.add(Calendar.DATE, 1), date = sCal.getTime()) {
				//System.out.println(date);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				String strDate = String.format("%02d-%02d-%02d",c.get(Calendar.YEAR), (c.get(Calendar.MONTH)+1), c.get(Calendar.DATE));
				for(int time = 10; time<=18; time+=2) {
					TScheduleTO to = new TScheduleTO();		
					to.setDate(strDate + " " + time + ":00");
					tschedule.add(to);
				}

			}		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		}

		return tschedule;
	}

}
