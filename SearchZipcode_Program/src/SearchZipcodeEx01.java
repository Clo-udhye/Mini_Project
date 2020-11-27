import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SearchZipcodeEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*문제 - 우편번호 검색
			파일을 읽어서 우편번호 검색 프로그램 
			입력 - 동이름 일부 
			출력 - 
			신사1 
			...
			신사2 
			...

		Data : zipcode_seoul_utf8_type2.csv 
			우편번호,시도,구군,동,리,번지,번호
		*/
		
		BufferedReader br = null;
		Scanner scan = new Scanner(System.in);
		boolean isExist = false;
		
		System.out.println("*********************************");
		System.out.println("*\t우편번호 검색 프로그램\t\t*");
		System.out.println("*********************************");
		
		System.out.print("검색어를 입력해주세요. > ");
		String search = scan.nextLine();	//찾을 동이름 입력
		System.out.println("결과 : ");
	
		System.out.println("\t우편번호\t\t주소");
		try {
			br = new BufferedReader(new FileReader("./zipcode_seoul_utf8_type2.csv"));
			
			String data = null;
			while((data = br.readLine()) != null) {	// 파일의 끝까지 읽음
				if(data.indexOf(search)!=-1) {		// 현재 line에 찾고싶은 동이름이 포함되어있는지 확인
					//System.out.println("\t" + data);
					
					StringTokenizer st = new StringTokenizer(data, ",");
					System.out.print("\t" + st.nextToken() + "\t\t");
					while(st.hasMoreTokens()) {
						System.out.print(st.nextToken() + " ");
					}
					
					System.out.println();
					isExist = true;
				}
			}
			if(!isExist) {
				System.out.println( "\t검색어 '" + search + "'을 찾을 수 없습니다.");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(br != null) try {br.close();} catch(IOException e) {}
		}
		
		scan.close();
	}

}
