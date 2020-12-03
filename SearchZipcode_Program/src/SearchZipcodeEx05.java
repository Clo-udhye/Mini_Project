import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchZipcodeEx05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 데이터베이스를 활용해서 우편번호 검색
		동이름 넣고 검색...

		java SearchZipcode
		동이름 입력 : 개포

		결과출력
		결과출력
		결과출력
		*/
		//PreparedStatement 
		
		String url = "jdbc:mysql://localhost:3306/sample";
		String user = "root";
		String password = "!123456";
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("동이름 입력 > ");
		String searchDong = scanner.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			// ? - 값
			//String sql = "select zipcode, sido, gugun, dong, ri, bunji, seq from zipcode where dong like '?%%' or ri like '%%?%%'";
			String sql = "select zipcode, sido, gugun, dong, ri, bunji, seq from zipcode where dong like ? or ri like ?";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, searchDong);
			//pstmt.setString(2, searchDong);
			pstmt.setString(1, searchDong + "%");
			pstmt.setString(2, "%" + searchDong + "%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.printf("%s %s %s %s %s %s %s\n"
						, rs.getString("zipcode"), rs.getString("sido"), rs.getString("gugun")
						, rs.getString("dong"), rs.getString("ri"), rs.getString("bunji"), rs.getString("seq") );
			}
			scanner.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[에러] " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
			
	}

}
