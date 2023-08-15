package steps;

import pages.*;

public class PageInitializer {

    public static loginPage loginPage;
    public static DashboardPage dashboardPage;
    public static AddEmployeePage addEmployeePage;
    public static EmployeeSearchPage employeeSearchPage;
    public static JobPage jobPage;
      public static void initializePageObject(){
          loginPage=new loginPage();
          dashboardPage=new DashboardPage();
          addEmployeePage=new AddEmployeePage();
          employeeSearchPage=new EmployeeSearchPage();
          jobPage=new JobPage();
      }

}
