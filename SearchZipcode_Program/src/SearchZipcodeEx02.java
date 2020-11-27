import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SearchZipcodeEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* 문제 - 우편번호 검색
			파일을 읽어서 우편번호 검색 프로그램 
			입력 - 동이름 일부 
			출력 - 
			신사1 
			...
			신사2 
			...

			Data : zipcode_seoul_utf8_type2.csv 
				우편번호,시도,구군,동,리,번지,번호
			
			입력값 검사
			1. 한글자 입력 -> 두 자이상 입력 오류메세지
			2. 한글만 입력 가능 (구글링)	
		 */

		// 검색할 동이름 입력받기
		Scanner scan = new Scanner(System.in);
		System.out.print("검색할 동이름을 입력해주세요 > ");
		String strDong = scan.next();
		/*
			자바 정규표현식참고 : http://blog.daum.net/question0921/419
		*/
		boolean isEnglish = !(Pattern.matches("^[가-힣0-9]+$", strDong));	// strDong이 한글과 숫자만 포함하는가  
		
		BufferedReader br = null;
		if(strDong.length()==1) {
			System.out.println("검색어는 한글자일 수 없습니다. 두글자 이상 입력해주세요. ");
			System.exit(0);
		}else if(isEnglish){
			System.out.println("검색어는 영어가 포함될수 없습니다. 한글로 입력해주세요");
			System.exit(0);
		}
		
		try {
			br = new BufferedReader(new FileReader("./zipcode_seoul_utf8_type2.csv"));
			String address = null;
			while((address = br.readLine()) != null) {
				//전체 데이터 읽어서 출력
				//System.out.println(address);

				// 동이름만 추출
				String[] addresses = address.split(",");
				//System.out.println(addresses[3]);

				//if(addresses[3] == strDong)				// 참조비교 X!
				//if(addresses[3].equals(strDong))			// 같은지 비교
				//if(addresses[3].startsWith(strDong)) {	// strDong으로 시작하는지 비교
				if(addresses[3].contains(strDong)) {		// strDong을 포함하는지 비교
					//System.out.println( addresses[3] );
					System.out.println(address);
				}
			}
			System.out.println("출력 완료");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(br != null) try {br.close();} catch(IOException e) {}
		}

	}

}
