import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SearchZipcodeEx04 {

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
		//Statement 
		String url = "jdbc:mysql://localhost:3306/sample";
		String user = "root";
		String password = "!123456";
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("동이름 입력 > ");
		String searchDong = scanner.nextLine();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = String.format("select zipcode, sido, gugun, dong, ri, bunji, seq from zipcode where dong like '%s%%' or ri like '%%%s%%'"
					,searchDong,searchDong); 
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.printf("%-10s %s %s %s %s %s %s\n"
						, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) );
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
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
			
	}

}
