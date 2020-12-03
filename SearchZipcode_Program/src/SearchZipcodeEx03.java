
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class SearchZipcodeEx03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* Excel 이용
		 * 문제 - 우편번호 검색
			파일을 읽어서 우편번호 검색 프로그램 
			입력 - 동이름 일부 
			출력 - 
			신사1 
			...
			신사2 
			...

			Data : zipcode_seoul_utf8_type2.xls 
				우편번호,시도,구군,동,리,번지,번호
			
			입력값 검사
			1. 한글자 입력 -> 두 자이상 입력 오류메세지
			2. 한글만 입력 가능 (구글링)	
		 */

		// 검색할 동이름 입력받기
		BufferedReader br = null;
		String strDong = "";
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("검색할 동이름을 입력해주세요 > ");
			strDong = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if(br!=null) try {br.close();} catch(IOException e) {}
		}
		
		// 입력 검사 
		boolean isEnglish = !(Pattern.matches("^[가-힣0-9]+$", strDong)); 
		if(strDong.length()<=1 ) {
			System.out.println("검색어를 두글자 이상 입력해주세요. ");
			System.exit(0);
		}else if(isEnglish){
			System.out.println("검색어는 영어가 포함될수 없습니다. 한글로 입력해주세요");
			System.exit(0);
		}
		
		// 데이터 처리
		Workbook worksheet = null;
		try {
			worksheet = Workbook.getWorkbook(new File("./zipcode_seoul_euckr_type2.xls"));
			Sheet sheet = worksheet.getSheet(0);
			
			//System.out.println(sheet.getRows());
			//System.out.println(sheet.getColumns());
			System.out.println("출력 결과 : ");
			for(int rows=0; rows<sheet.getRows(); rows++) {
				Cell cell = sheet.getCell(3,rows);
				if(cell.getContents().contains(strDong)){
					Cell zipcode = sheet.getCell(0, rows);
					Cell sido = sheet.getCell(1, rows);
					Cell gugun = sheet.getCell(2, rows);
					Cell dong = sheet.getCell(3, rows);
					Cell ri = sheet.getCell(4, rows);
					Cell bunji = sheet.getCell(5, rows);
					System.out.println("\t" + zipcode.getContents() +" "+ sido.getContents() +" "
									+ gugun.getContents() +" "+ dong.getContents() +" "+ ri.getContents()
									+" "+ ri.getContents() +" "+ bunji.getContents());
				}
			}
			
			System.out.println("출력 완료");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(worksheet != null) worksheet.close();
		}

	}

}
