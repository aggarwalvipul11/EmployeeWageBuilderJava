import java.util.*;
public class EmpWageBuilder implements IComputeEmpWage {
	public static final int isPartTime = 1;
	public static final int isFullTime = 2;
	
	private int numOfCompany = 0;
	private LinkedList<CompanyEmpWage> companyEmpWageList ;
	private Map<String,CompanyEmpWage> companyToEmpWageMap;
	
	public EmpWageBuilder() {
		companyEmpWageList = new LinkedList<>();
		companyToEmpWageMap = new HashMap<>();
	}

	public void addCompanyEmpWage(String company, int empRatePerHour, int numOfWorkingDays, int maxHoursPerMonth) {
		CompanyEmpWage companyEmpWage = new CompanyEmpWage(company, empRatePerHour, numOfWorkingDays, maxHoursPerMonth);
		companyEmpWageList.add(companyEmpWage);
		companyToEmpWageMap.put(company, companyEmpWage);
	}
		
	public void computeEmpWage() {
		for (int i = 0; i < companyEmpWageList.size(); i++) {
			CompanyEmpWage companyEmpWage = companyEmpWageList.get(i);
			companyEmpWage.setTotalEmpWage(computeEmpWage(companyEmpWage));
			System.out.println(companyEmpWage);
		}
	}

	@Override
	public int getTotalWage(String company) {
		return companyToEmpWageMap.get(company).totalEmpWage;
	}

	public int computeEmpWage(CompanyEmpWage companyEmpWage) {
		// Variables
		int empHrs = 0, totalEmpHrs = 0, totalWorkingDays = 0; 
		// Computation
		while (totalEmpHrs <= companyEmpWage.maxHoursPerMonth && totalWorkingDays < companyEmpWage.numOfWorkingDays) {
			totalWorkingDays++;
			int empCheck = (int) Math.floor(Math.random() * 10) % 3;
			switch (empCheck) {
				case isPartTime:
					empHrs = 4;
					break;
				case isFullTime:
					empHrs = 8;
					break;
				default:
					empHrs = 0;
			}
			totalEmpHrs += empHrs;
			System.out.println("Days#: " + totalWorkingDays + " Emp Hr: " +empHrs);
		}
		return totalEmpHrs * companyEmpWage.empRatePerHour;
	}

	public static void main(String args[]) {
		IComputeEmpWage empWageBuilder = new EmpWageBuilder();
		empWageBuilder.addCompanyEmpWage("Microsoft", 20, 2, 10);
		empWageBuilder.addCompanyEmpWage("Google", 10, 4, 20);
		empWageBuilder.computeEmpWage();
		System.out.println("Total Wage for Microsoft Company: "+empWageBuilder.getTotalWage("Microsoft"));
	}
}
