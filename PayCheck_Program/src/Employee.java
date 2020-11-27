
public class Employee {	//직원 정보 클래스
	String name;
	int age;
	int workTime;
	int pay;
	int total;
	int tax;
	
	Employee(String name, int age, int workTime, int pay){
		this.name = name;
		this.age = age;
		this.workTime = workTime;
		this.pay = pay;
		this.total = calculateTotalPay(workTime, pay);
		this.tax = calculateTax(this.total);	
	}
	
	@Override
	//객체 정보출력을 위한 오버라이딩
	public String toString() { 
		return "이름 : " + name + "   나이 : " + age + "   주당 근무시간수: " + workTime + "   시간당 급여액: " + pay;
	}

	//총수입을 계산하는 메서드 
	static int calculateTotalPay( int workTime, int pay ) {
		int total;
		if(workTime<=40) {
			total = pay * workTime;
		} else {
			total = (pay * 40) + (int)(pay * (workTime-40) * 1.5);
		}
		return total;  
	}
	
	//세금을 계산하는 메서드
	static int calculateTax(int total) {
		int tax;
		if(total<=100000) {
			tax = (int)(total * 0.1);
		}else {
			tax = 10000 + (int)((total-100000)*0.2);
		}
		return tax;
	}
	
	//가장 많은 급여를 받은 직원이 누구인지 알려주는 메서드
	static Employee maxPayEmployee(Employee[] e, int countEm) {
		Employee maxPayEm = e[0];
		for(int i=1; i<countEm; i++) {
			if(maxPayEm.pay<e[i].pay) {
				maxPayEm = e[i];
			}
		}
		return maxPayEm;
	}
}
