import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseballGame02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* 숫자야구
			java Baseball
			0 -9  XXX(보이지 않음)
			1회 > 4 5 6
			strike : 1 / ball : 1 
					= strike : 3 => 종료
			...
			6회 > 4 5 6
		 */

		final int NUMBER_SIZE = 3;		// 몇개의 숫자로 게임을 할지 정하는 상수
		final int LAST_ROUND = 6;		// 게임의 최종 라운드를 정하는 상수
		int currentRound = 0;			// 현재 라운드

		List<Integer> answerNumbers = new ArrayList<Integer>();	// 정답 숫자		
		List<Integer> inputNumbers = new ArrayList<Integer>();	// 사용자 입력 숫자

		Scanner scanner = new Scanner(System.in);	// 입력을 받을 스캐너 

		int strikeCount = 0;
		int ballCount = 0;

		// 정답 숫자 뽑기
		while(answerNumbers.size() != NUMBER_SIZE) {
			int number = (int)(Math.random() * 10);
			if(!answerNumbers.contains(number)) {
				answerNumbers.add(number);
			}
		}
		//System.out.println(answerNumbers.toString());

		System.out.println("숫자야구 게임시작!\n");
		while(currentRound != LAST_ROUND) {
			//초기화
			inputNumbers.clear();
			ballCount = 0;
			strikeCount = 0;
			currentRound++;

			try {
				// 사용자 입력
				System.out.print(currentRound + "회 > ");
				String strNumbers = scanner.nextLine();
				String[] numbers = strNumbers.split(" ");
				
				for(int i=0; i<NUMBER_SIZE; i++) {
					// 입력검사
					// 1. 숫자가 아닌것 
					// 2. 중복된 숫자
					// 3. 범위 밖의 숫자
					int number = Integer.parseInt(numbers[i]);
					
					if(number>9 || number<0) {
						throw new Exception("범위내의 숫자를 입력해주세요.(0-9)");
					}
					if(inputNumbers.contains(number)) {
						throw new Exception("중복되지 않는 숫자를 입력해주세요");
					} 
					inputNumbers.add(number);
				}
			} catch(NumberFormatException e) {
				System.out.println("[입력오류] 숫자를 입력해주세요.");
				currentRound--;
				continue;
			} catch (Exception e) {
				System.out.println("[입력오류]" + e.getMessage());
				currentRound--;
				continue;
			}
			//System.out.println(inputNumbers.toString());


			// 점수 계산 			
			for(int i=0; i<NUMBER_SIZE; i++) {
				if(answerNumbers.contains(inputNumbers.get(i))){
					if(answerNumbers.get(i) == inputNumbers.get(i)) {
						strikeCount++;
					} else {
						ballCount++;
					}
				}
			}
			System.out.println("Strike : " + strikeCount + " / Ball : " + ballCount);

			// 정답처리
			if(strikeCount == 3) {
				break;
			}
		
		}
		
		if(strikeCount==3) {
			System.out.println("   [Win!!!]");
		} else {
			System.out.println("   [Lose...]");
			System.out.println("정답 : " + answerNumbers.toString());
		}
		scanner.close();
	}
}
