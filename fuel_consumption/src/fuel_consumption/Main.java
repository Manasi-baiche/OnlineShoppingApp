package fuel_consumption;
import java.util.*;
import java.text.*;
class Main {
	public static void main (String[] args){
		DecimalFormat df2= new DecimalFormat("0.00");
		Scanner sc= new Scanner(System.in);
		
		System.out.println("enter the no liters to fill the tank ");
		int ltt=sc.nextInt();
		double lt=(ltt*1.00);
		
		if(ltt<1) {
		System.out.println("Invalid input");
		System.exit(0);
		}
		
		System.out.println("enter the distance covered");
		int dis= sc.nextInt();
		double diss=(dis*1.00);
		
		if(dis<0) {
			System.out.println("Invalid distance");
			System.exit(0);
		}
		
		double hundred=((lt/dis)*100);
		System.out.println("Litres/100km");
		System.out.println(df2.format(hundred));
		
		double miles=(dis*0.6214);
		double gallons=(lt*0.2642);
		double mg=(miles/gallons);
		System.out.println(miles/gallons);
		System.out.println(df2.format(mg));
	}
		
	}


