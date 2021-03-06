import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

interface Computewage{
	public void salary(Company C) ;
	public void disp(Company C);
	public void add(String name,int totalWage);
	public int getTotalWage(String name);
	
}
class Company{
	private String compName;
	private int ratePerHr;
	private int maxWorkingDays;
	private int maxWorkingHrs;
	private int totalDays=0;
	private ArrayList<Integer> salary=new ArrayList<>();
	private ArrayList<Integer> totalSalary=new ArrayList<>();
	public Company(String compName,int ratePerHr,int maxWorkingDays,int maxWorkingHrs) {
		this.compName=compName;
		this.ratePerHr=ratePerHr;
		this.maxWorkingDays=maxWorkingDays;
		this.maxWorkingHrs=maxWorkingHrs;
	}
	public Company(String compName,ArrayList salary,ArrayList totalSalary,int totalDays) {
		this.salary=salary;
		this.totalSalary=totalSalary;
		this.totalDays=totalDays;
		this.compName=compName;
	}
	public String getCompName() {
		return compName;
	}
	public int getRatePerHr() {
		return ratePerHr;
	}
	public int getMaxWorkingDays() {
		return maxWorkingDays;
	}
	public int getMaxWorkingHrs() {
		return maxWorkingHrs;
	}
	public int getTotalDays() {
		return totalDays;
	}
	public ArrayList<Integer> getSalary() {
		return salary;
	}
	public ArrayList<Integer> getTotalSalary() {
		return totalSalary;
	}
	
}
class Employeesalary implements Computewage{
	private static HashMap<String,Integer> empWageMap=new HashMap<String,Integer>();
	public void salary(Company C) {
		int empHr=0,totalHrs=0,dailyWage=0,diff=0,sum=0,totalDays=0;
		ArrayList<Integer> salary=new ArrayList<>();
		ArrayList<Integer> totalSalary=new ArrayList<>();
		Random rand=new Random();
		while(totalDays<=C.getMaxWorkingDays() && totalHrs<=C.getMaxWorkingHrs()) {
			totalDays++;
			int empCheck=rand.nextInt(3);
			switch(empCheck) {
				case 1: empHr=8;
						break;
				case 2: empHr=4;
						break;
				default: empHr=0;
			}
			totalHrs+=empHr;
			if(totalHrs>C.getMaxWorkingHrs()) {
				diff=totalHrs-C.getMaxWorkingHrs();
				empHr-=diff;
			}
		dailyWage=C.getRatePerHr()*empHr;
		salary.add(dailyWage);
		sum+=dailyWage;
		totalSalary.add(sum);
		}
		Company comp=new Company(C.getCompName(),salary,totalSalary,totalDays);
		Employeesalary emp=new Employeesalary();
		emp.disp(comp);
		emp.add(C.getCompName(),sum);
	}
	public void disp(Company C) {
		System.out.println(C.getCompName()+"'s salary details for the month are as below:");
		System.out.println("Day\tDaily Wage\tTotal Salary");
		for(int i=1;i<C.getTotalDays();i++) {
			System.out.println(i+"\t"+C.getSalary().get(i)+"\t\t"+C.getTotalSalary().get(i));
			}
	}
	public void add(String name,int totalWage) {
		empWageMap.put(name, totalWage);
	}
	public int getTotalWage(String name) {
		return empWageMap.get(name);
	}
	
}
public class EmpWage {
	public static void main(String[] args) {
	int n=0;
	Employeesalary empsal=new Employeesalary();
	Scanner sc=new Scanner(System.in);
	do{
	System.out.println("Welcome to Employee Wage Computation");
	System.out.println("Enter Company name");
	String name=sc.next();
	System.out.println("Enter wage per hour");
	int ratePerHr=sc.nextInt();
	System.out.println("Enter maximum working days of a month");
	int workingDays=sc.nextInt();
	System.out.println("Enter maximum working hours of a month");
	int workingHrs=sc.nextInt();
	Company company=new Company(name,ratePerHr,workingDays,workingHrs);
	empsal.salary(company);
	System.out.println("Enter 1 to enter new company, 0 to stop");
	n=sc.nextInt();
		}while(n!=0);
	System.out.println("Enter the name of company you want total wage for");
	String name=sc.next();
	System.out.println("Total Wage for "+name+" is:"+empsal.getTotalWage(name));
	}
}
