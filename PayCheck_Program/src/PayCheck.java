import java.text.DecimalFormat;
import java.util.Scanner;

public class PayCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean run = true;	
		Scanner scan = new Scanner(System.in);
		DecimalFormat fomatter = new DecimalFormat("###,###");	//숫자를 천단위씩 끊어서 표현하기 위한 포맷
		final int MAX_EMPLOYEE = 5;	//배열을 만들기위한 최대 직원수 
		Employee[] eArr = new Employee[MAX_EMPLOYEE];
		int countEm = 0;	//현재 직원수 

		System.out.println("*******************************");
		System.out.println("*      직원 급여 계산 프로그램               *");
		System.out.println("*******************************");

		while(run) {
			System.out.print("직원의 급여 계산을 원하면 'y' 아니면 'n'을 입력해주세요. : ");
			String input = scan.next();
			System.out.println();

			if(input.equals("n")) {
				if(eArr.length>0) {	// 직원이 한명도 없을 때는 출력하지 않는다
					Employee e = Employee.maxPayEmployee(eArr,countEm);	//가장 많은 급여를 받은 직원
					int cntUnder50 = 0, cntUp50 = 0; 	//50세이상, 50세미만의 직원 수를 세기위한 변수
					int sumUnder = 0, sumUp = 0;		//50세이상, 50세미만의 직원들의 총수입을 더하기위한 변수

					for(int i=0; i<countEm; i++) {
						if(eArr[i].age<50) {
							cntUnder50++;
							sumUnder += eArr[i].total;

						} else {
							cntUp50++;
							sumUp += eArr[i].total;
						}
					}
					if(cntUp50>=1) {	//50세 이상의 직원이 있을 때만 출력 
						System.out.println("\t50세 이상의 직원들의 평균 수입 : " + fomatter.format(sumUp/cntUp50) + "원");
					}
					if(cntUnder50>=1) {	//50세 미만의 직원이 있을 때만 출력
						System.out.println("\t50세 미만의 직원들의 평균 수입 : " + fomatter.format(sumUnder/cntUnder50) + "원");
					}
					System.out.println("\t\t총직원들의 평균 수입 : " + fomatter.format((sumUp+sumUnder)/countEm) + "원");
					System.out.println("\n가장 많은 급여를 받은 직원의 이름 및 급여액 : " + e.name +"  "+fomatter.format(e.total) + "원");

				}
				System.out.println("\n직원 급여 프로그램을 종료합니다~ ");
				run = false;

			} else if(input.equals("y")) {

				System.out.println("이름, 나이, 주당 근무시간수, 시간당 급여액을 입력하시오 : ");
				//next()로 하나씩 받을 경우
				/*String name = scan.next();
				int age = scan.nextInt();
				int workTime = scan.nextInt();
				int pay = scan.nextInt();
				eArr[countEm] = new Employee(name,age,workTime,pay);*/

				//nextLine()으로 한줄을 받는경우
				scan.nextLine();				// nextLine()하기전에 next()를 사용했다면 이전의 입력된 Enter값을  
				String info = scan.nextLine();	// 읽어오기때문에 nextLine()을 한줄더 추가한다.
				String[] infoArr = info.split(" ");
				
				try {	
					//입력이 잘 된 경우 직원 객체 생성
					eArr[countEm] = new Employee(infoArr[0],Integer.parseInt(infoArr[1]),Integer.parseInt(infoArr[2]),Integer.parseInt(infoArr[3]));
				}catch(NumberFormatException ex) {
					// 입력이 잘못된 경우 예외 처리
					System.out.println("잘못된 입력입니다.");
					continue;
				}
				
				// 객체 정보 출력
				System.out.println("\n" + eArr[countEm].toString());	
				System.out.println("총수입 : " + fomatter.format(eArr[countEm].total)+"원\t세금  : " +fomatter.format(eArr[countEm].tax)+"원\n");

				countEm++;	//직원의 수 증가
			}
		}

		scan.close();
	}

}
