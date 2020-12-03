import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class InsertZipcodeEx03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 우편번호 데이터파일(.xls) -> zipcode 테이블

		String url = "jdbc:mysql://localhost:3306/sample";
		String user = "root";
		String password = "!123456";

		Connection conn = null;
		PreparedStatement pstmt = null;

		Workbook workbook = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);

			String sql = "insert into zipcode values (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			workbook = Workbook.getWorkbook(new File("./zipcode_seoul_euckr_type2.xls"));
			Sheet sheet = workbook.getSheet(0);
			int result = 0;
			
			for(int rows=0; rows<sheet.getRows(); rows++) {
				/*
				Cell zipcode = sheet.getCell(0,rows);
				Cell sido = sheet.getCell(1,rows);
				Cell gugun = sheet.getCell(2,rows);
				Cell dong = sheet.getCell(3,rows);
				Cell ri = sheet.getCell(4,rows);
				Cell bunji = sheet.getCell(5,rows);
				Cell seq = sheet.getCell(6,rows);
				*/
				
				//System.out.println("\t" + zipcode.getContents() +" "+ sido.getContents() +" "
				//	+ gugun.getContents() +" "+ dong.getContents() +" "+ ri.getContents()
				//	+" "+ ri.getContents() +" "+ bunji.getContents());

				pstmt.setString(1, sheet.getCell(0,rows).getContents());
				pstmt.setString(2, sheet.getCell(1,rows).getContents());
				pstmt.setString(3, sheet.getCell(2,rows).getContents());
				pstmt.setString(4, sheet.getCell(3,rows).getContents());
				pstmt.setString(5, sheet.getCell(4,rows).getContents());
				pstmt.setString(6, sheet.getCell(5,rows).getContents());
				pstmt.setString(7, sheet.getCell(6,rows).getContents());
				
				result += pstmt.executeUpdate();
			}
			System.out.println("result : " + result);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[에러] " + e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[에러] " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[에러] " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[에러] " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[에러] " + e.getMessage());
		} finally{
			if(workbook != null) workbook.close();
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}

	}

}
