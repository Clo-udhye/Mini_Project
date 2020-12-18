package data;
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
import java.util.Map;

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

	//사용자 아이디를 입력받아 로그인
	public ArrayList<UserTO> login(String id, String password){
		ArrayList<UserTO> user = new ArrayList<UserTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select id, password, type from user where id = ? and password = ?";
			pstmt = this.conn.prepareStatement(sql);
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
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(this.conn!=null) try {this.conn.close();} catch(SQLException e) {}
		}

		//System.out.println(user.get(0).getId());
		return user;
	}

	// 트레이너 사용자의 아이디를 입력받아 자신의 정보를 리턴
	public ArrayList<TrainerTO> searchTInfo(String userId){
		ArrayList<TrainerTO> tInfo = new ArrayList<TrainerTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {			
			String sql = "select tserial, id, tname, tcontact from trainer where id = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				TrainerTO to = new TrainerTO();
				to.setTserial(rs.getString("tserial"));
				to.setId(rs.getString("id"));
				to.setTname(rs.getString("tname"));
				to.setTcontact(rs.getString("tcontact"));

				tInfo.add(to);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(this.conn!=null) try {this.conn.close();} catch(SQLException e) {}
		}
		return tInfo;
	}

	// 트레이너인 사용자의 아이디를 입력받아 담당하는 회원들의 리스트를 리턴
	public ArrayList<MemberTO> searchMyMember(String userId){
		ArrayList<MemberTO> memList = new ArrayList<MemberTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {			
			String sql = "select mserial, m.id, mname, m.tserial, mcontact, height, weight, gender from member m inner join trainer t using (tserial) where t.id = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, userId);
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

				memList.add(to);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(this.conn!=null) try {this.conn.close();} catch(SQLException e) {}
		}
		return memList;
	}

	// 멤버인 사용자아이디를 입력받아 자신의 회원정보를 리턴
	public ArrayList<MemberTO> searchMInfo(String userId){
		ArrayList<MemberTO> mInfo = new ArrayList<MemberTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {			
			String sql = "select mserial, id, mname, tserial, mcontact, height, weight, gender from member where id = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, userId);
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

				mInfo.add(to);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(this.conn!=null) try {this.conn.close();} catch(SQLException e) {}
		}

		return mInfo;
	}

	// 사용자의 담당 트레이너의  예약되어 있는 tschedule을 리턴
	public ArrayList<TScheduleTO> searchTSchedule(String userId, Calendar sCal, Calendar eCal){
		ArrayList<TScheduleTO> scheduled = new ArrayList<TScheduleTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		eCal.add(Calendar.DATE, 1);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return scheduled;
	}

	// 트레이너의 아이디를 입력받아 자신의 스케줄 확인
	public ArrayList<ArrayList> searchTSchedule_tra(String userId, Calendar sCal, Calendar eCal){
		ArrayList<ArrayList> scheduled = new ArrayList<ArrayList>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		eCal.add(Calendar.DATE, 1);
		String sdate = String.format("%d-%d-%d 00:00:00", sCal.get(Calendar.YEAR), sCal.get(Calendar.MONTH)+1, sCal.get(Calendar.DATE));
		String edate = String.format("%d-%d-%d 00:00:00", eCal.get(Calendar.YEAR), eCal.get(Calendar.MONTH)+1, eCal.get(Calendar.DATE));
		//System.out.println(sdate +" "+ edate);

		try {						
			String sql = "select ts.mserial, ts.tserial, date, mname, mcontact, height, weight, gender "
					+ "from tschedule ts inner join trainer t using (tserial) "
					+ "inner join member m using (mserial)  "
					+ "where t.id = ? and date between ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, sdate);
			pstmt.setString(3, edate);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				TScheduleTO tsto = new TScheduleTO();
				MemberTO mto = new MemberTO();

				tsto.setTserial(rs.getString("tserial"));
				int index =rs.getString("date").lastIndexOf(":");
				tsto.setDate(rs.getString("date").substring(0, index));
				tsto.setMserial(rs.getString("mserial"));

				mto.setMserial(rs.getString("mserial"));
				mto.setMname(rs.getString("mname"));
				mto.setTserial(rs.getString("tserial"));
				mto.setMcontact(rs.getString("mcontact"));
				mto.setHeight(rs.getString("height"));
				mto.setWeight(rs.getString("weight"));
				mto.setGender(rs.getString("gender"));

				ArrayList tsMemList = new ArrayList();
				tsMemList.add(tsto);
				tsMemList.add(mto);

				//System.out.println(tsto.getDate() + " " +mto.getMname());
				scheduled.add(tsMemList);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return scheduled;
	}


	// 날짜와 아이디를 입력 받아 예약
	public int insertRsv(String date, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			ExManaDAO dao = new ExManaDAO();
			ArrayList<MemberTO> to = dao.searchMInfo(userId);

			String sql = "insert into tschedule values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.get(0).getTserial());
			pstmt.setString(2, date);
			pstmt.setString(3, to.get(0).getMserial());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}

		return result;
	}

	//날짜와 아이디를 입력받아 예약을 삭제
	public int deleteRsv(String date, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			ExManaDAO dao = new ExManaDAO();
			ArrayList<MemberTO> to = dao.searchMInfo(userId); // 아이디로 회원정보 가져오기

			String sql = "delete from tschedule where mserial = ? and date = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.get(0).getMserial());
			pstmt.setString(2, date);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}

		return result;
	}

	// 사용자 아이디와 기간을 입력받아 해당 기간의  ArrayList<Exprogram>를 반환
	public ArrayList<ExprogramTO> searchExprogram(String userId, Calendar sCal, Calendar eCal){
		ArrayList<ExprogramTO> expro = new ArrayList<ExprogramTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		eCal.add(Calendar.DATE, 1);
		String sdate = String.format("%d-%d-%d 00:00:00", sCal.get(Calendar.YEAR), sCal.get(Calendar.MONTH)+1, sCal.get(Calendar.DATE));
		String edate = String.format("%d-%d-%d 00:00:00", eCal.get(Calendar.YEAR), eCal.get(Calendar.MONTH)+1, eCal.get(Calendar.DATE));
		//System.out.println(sdate +" "+ edate);

		try {						
			String sql = "select exp.mserial, date, exp.tserial, exname from exprogram exp inner join member m "
					+ "using (mserial) where id = ? and date between ? and ?"; 

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, sdate);
			pstmt.setString(3, edate);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				ExprogramTO to = new ExprogramTO();
				to.setMserial(rs.getString("mserial"));
				int index =rs.getString("date").lastIndexOf(":");
				to.setDate(rs.getString("date").substring(0, index));
				to.setTserial(rs.getString("tserial"));
				to.setExname(rs.getString("exname"));

				expro.add(to);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}

		return expro;

	}

	// 프로그램 리스트를 exprogram에 삽입
	public int insertExprogram(String userId, ArrayList<String> datas, String date, String mserial){
		PreparedStatement pstmt = null;
		int result = 0;
		
		ExManaDAO dao = new ExManaDAO();
		ArrayList<TrainerTO> to = dao.searchTInfo(userId);
		String tserial = to.get(0).getTserial();
		
		try {
			for(String data : datas) {
				String sql = "insert into exprogram values (?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mserial);
				pstmt.setString(2, date);
				pstmt.setString(3, tserial);
				pstmt.setString(4, data);

				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] 1" + e.getMessage());
		} finally {
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		
		return result;
	}

	// 프로그램 리스트를 exprogram에 수정 

	// 프로그램 리스트를 exprogram에 삭제
	
	// 회원의 예약 리스트 받기
	public ArrayList<TScheduleTO> searchMyRsvList(String mserial){
		ArrayList<TScheduleTO> myRsv = new ArrayList<TScheduleTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select tserial, date, mserial from tschedule where mserial = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, mserial);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				TScheduleTO to = new TScheduleTO();
				to.setTserial(rs.getString("tserial"));
				to.setDate(rs.getString("date"));
				to.setMserial(rs.getString("mserial"));
				myRsv.add(to);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(this.conn!=null) try {this.conn.close();} catch(SQLException e) {}
		}

		return myRsv;
	}
	
	// 운동 리스트를 리턴
	public ArrayList<ExerciseTO> searchExList(String expart){
		ArrayList<ExerciseTO> exList = new ArrayList<ExerciseTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select exname, expart from exercise where expart = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, expart);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				ExerciseTO to = new ExerciseTO();
				to.setExname(rs.getString("exname"));
				to.setExpart(rs.getString("expart"));
				exList.add(to);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(this.conn!=null) try {this.conn.close();} catch(SQLException e) {}
		}

		return exList;
	}

	// Mserial을 입력받아 사용자정보를 리턴
	public MemberTO searchMInfo_mserial(String mserial) {
		MemberTO to = new MemberTO();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select mserial, id, mname, tserial, mcontact, height, weight, gender from member = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, mserial);
			rs = pstmt.executeQuery();


			to.setMserial(rs.getString("mserial"));
			to.setId(rs.getString("id"));
			to.setMname(rs.getString("mname"));
			to.setTserial(rs.getString("tserial"));
			to.setMcontact(rs.getString("mcontact"));
			to.setHeight(rs.getString("height"));
			to.setWeight(rs.getString("weight"));
			to.setGender(rs.getString("gender"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(this.conn!=null) try {this.conn.close();} catch(SQLException e) {}
		}

		return to;
	}
}
