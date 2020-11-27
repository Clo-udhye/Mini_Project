import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
//import java.util.Set;

public class BaseballGame01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int NUMBER_SIZE = 3;

		// 컴퓨터의 랜덤 숫자 뽑기
		/*// set으로 할 경우 중복된 숫자는 뽑지 않지만 정렬되어 있다  
		Set<Integer> answerNumbers = new HashSet<Integer>();
		while(answerNumbers.size()!=NUMBER_SIZE) {
			answerNumbers.add((int)(Math.random()*10));	// 0 ~ 9 까지의 숫자 뽑기
		}*/

		List<Integer> answerNumbers = new ArrayList<Integer>();
		while(answerNumbers.size()!=NUMBER_SIZE) {
			int number = (int)(Math.random()*10);
			//System.out.println(answerNumbers.toString() + " : " + number);

			if(answerNumbers.contains((Integer)number)) {	
				continue;	// 같은 숫자가 이미 존재하면 다시 뽑기 
			} else {
				answerNumbers.add(number);
			}
		}		
		//System.out.println(answerNumbers.toString());

		/*//test용 숫자 
		List<Integer> answerNumbers = new ArrayList<Integer>();
		answerNumbers.add(4);
		answerNumbers.add(1);
		answerNumbers.add(2);
		System.out.println(answerNumbers.toString());
		*/
		
		System.out.println("**********************************");
		System.out.println("*            숫자 야구 게임                      *");
		System.out.println("**********************************");
		
		Loop:
		while(true) {
			// 사용자의 숫자 입력
			System.out.print("0 부터 9사이의 숫자" + NUMBER_SIZE+"개를 입력하세요. > ");
			Scanner scanner = new Scanner(System.in);			
			List<Integer> inputNumbers = new ArrayList<Integer>();

			for(int i=0; i<NUMBER_SIZE; i++) {
				try {
					int number = scanner.nextInt();
					if(inputNumbers.contains((Integer)number)) {		// 입력검사
						System.out.println("중복된 숫자는 입력할 수 없습니다.");
						continue Loop;
					} else if(number<0 || number>9){
						System.out.println("잘못된 숫자 입력입니다.");
						continue Loop;
					}else {
						inputNumbers.add(number);
					}
				} catch (Exception e) {
					System.out.println("잘못된 입력입니다.");
					continue Loop;
				}
			}
			//System.out.println(inputNumbers.toString());

			Map<String, Integer> scoreBoard = new HashMap<String, Integer>();
			scoreBoard.put("Strike",0);
			scoreBoard.put("Ball",0);
			scoreBoard.put("Out",0);

			// 스트라이크 검사
			int strikeCnt = 0;
			for(int i=0; i<NUMBER_SIZE; i++) {
				if(answerNumbers.get(i)==inputNumbers.get(i)) {
					strikeCnt++;
				}
			}

			scoreBoard.put("Strike", strikeCnt);
			if(strikeCnt == NUMBER_SIZE) {
				System.out.println();
				System.out.print(">" + inputNumbers.toString());
				System.out.println("\t| S | B | O |");
				System.out.println("\t\t+---+---+---+");
				System.out.println("\t\t| 3 | 0 | 0 |");
				System.out.println();
				System.out.println("[************정답 입니다.************]");
				scanner.close();
				break;
			}

			// 볼 검사
			int ballCnt = 0;
			for(int i=0; i<NUMBER_SIZE; i++) {
				if(answerNumbers.contains(inputNumbers.get(i))) {	// 스트라이크까지 같이 검사
					ballCnt++;
				}
			}

			int outCnt = NUMBER_SIZE - ballCnt;
			scoreBoard.put("Out", outCnt);

			ballCnt = ballCnt-strikeCnt;
			scoreBoard.put("Ball", ballCnt);

			//System.out.println(scoreBoard.toString());
			
			System.out.println();
			System.out.print(">" + inputNumbers.toString());
			System.out.println("\t| S | B | O |");
			System.out.println("\t\t+---+---+---+");
			System.out.println("\t\t| " + scoreBoard.get("Strike") + " | " + scoreBoard.get("Ball") + " | " + scoreBoard.get("Out") + " |");
			System.out.println();
			
			// 점수판 리셋
			scoreBoard.put("Strike",0);
			scoreBoard.put("Ball",0);
			scoreBoard.put("Out",0);
		}
		
	}

}
