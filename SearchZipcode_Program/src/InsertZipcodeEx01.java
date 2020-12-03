import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class InsertZipcodeEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 우편번호 데이터파일(csv) -> zipcode 테이블
		// Statement 이용
		
		String url = "jdbc:mysql://localhost:3306/sample";
		String user = "root";
		String password = "!123456";
		
		BufferedReader br = null;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			br = new BufferedReader(new FileReader("./zipcode_seoul_utf8_type2.csv"));
			Class.forName("org.mariadb.jdbc.Driver");	// 드라이버 로딩 완료
			conn = DriverManager.getConnection(url, user, password);	// 연결 완료
			
			String address = null;
			while((address=br.readLine())!=null) {
				String[] addresses = address.split(",");
				
				String sql = String.format("insert into zipcode values ('%s', '%s', '%s', '%s', '%s', '%s', %s)"
						,addresses[0], addresses[1], addresses[2], addresses[3],addresses[4],addresses[5],addresses[6]);
			
				//System.out.println(sql);
				
				stmt = conn.createStatement();
				
				int result = stmt.executeUpdate(sql);
				System.out.println("result : " + result);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("[에러] " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[에러] " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("[에러] " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
			if(br!=null) try {br.close();} catch(IOException e) {}
		}
		
	}

}
