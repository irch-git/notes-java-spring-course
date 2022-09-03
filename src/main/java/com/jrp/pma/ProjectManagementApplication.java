package com.jrp.pma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jrp.pma.DataAccessObject.IEmployeeRepository;
import com.jrp.pma.DataAccessObject.IProjectRepository;

@SpringBootApplication //This is an Annotation. It is like metadata that helps you be more specific as to what you want the following code to do.
public class ProjectManagementApplication {
	
	@Autowired
	IEmployeeRepository empRepo;
	
	@Autowired
	IProjectRepository projRepo;
	
	public static void main(String[] args) { //This line triggers the starting point to execute the application. It is only used once at the main file of the application.
	// public allows access to the following code globally. This is called an Access Modifier
	// private allows access only within its parent location. So if a method inside a class is private, its content is only accessible within the class/file. If a class is private, its content is only accessible within the package its in. This is called an Access Modifier. If there is no Access Modifier, the default is private
	// void means that this block of code will not return anything. If there is a return, you will have to replace void with a the data type that it's returning.
	// String[] is the data type string array
	// args is a variable, also known as a Parameter, also known as the argument that it accept
	// main is the name given for this method
	// static makes a method accessible through the class it is in. Without the static key word the method will only be accessible through its INSTANCE, and not directly from its class. In other words, you cannot access the non-static method through the class it's in when calling it. You will have to research further to find out how to call a "non-static method" through instances. 
		SpringApplication.run(ProjectManagementApplication.class, args);
	}
	
	
	
	/*
	 * the following codes are used to build a seed database
	 */
//	@Autowired
//	EmployeeRepository empRepo;
//	
//	@Autowired
//	ProjectRepository projRepo;
//	
//	@Bean
//	CommandLineRunner runner() {
//		
//		return args -> {
//			
//			Employee emp1 = new Employee("John", "Warton", "warton@gmail.com");
//			Employee emp2 = new Employee("Mike", "Lanister", "lanister@gmail.com");
//			Employee emp3 = new Employee("Steve", "Reeves", "Reeves@gmail.com");
//
//			Employee emp4 = new Employee("Ronald", "Connor", "connor@gmail.com");
//			Employee emp5 = new Employee("Jim", "Salvator", "Sal@gmail.com");
//			Employee emp6 = new Employee("Peter", "Henley", "henley@gmail.com");
//
//			Employee emp7 = new Employee("Richard", "Carson", "carson@gmail.com");
//			Employee emp8 = new Employee("Honor", "Miles", "miles@gmail.com");
//			Employee emp9 = new Employee("Tony", "Roggers", "roggers@gmail.com");
//			
//			Project pro1 = new Project("Large Production Deploy", "NOTSTARTED", "This requires all hands on deck for"
//					+ "the final deployment of the software into production");
//			Project pro2 = new Project("New Employee Budget",  "COMPLETED", "Decide on a new employee bonus budget"
//					+ "for the year and figureout who will be promoted");
//			Project pro3 = new Project("Office Reconstruction", "INPROGRESS", "The office building in Monroe has "
//					+ "been damaged due to hurricane in the region. This needs to be reconstructed");
//			Project pro4 = new Project("Improve Intranet Security", "INPROGRESS", "With the recent data hack, the office"
//					+ "security needs to be improved and proper security team needs to be hired for "
//					+ "implementation");
//			
//			// need to set both sides of the relationship manually
//			pro1.addEmployee(emp1);
//			pro1.addEmployee(emp2);
//			pro2.addEmployee(emp3);
//			pro3.addEmployee(emp1);
//			pro4.addEmployee(emp1);
//			pro4.addEmployee(emp3);
//			
//			// need to set both sides of the relationship manually
//			emp1.setTheProjects(Arrays.asList(pro1, pro3, pro4));
//			emp2.setTheProjects(Arrays.asList(pro1));
//			emp3.setTheProjects(Arrays.asList(pro2, pro4));
//			
//			// save employees in database
//			empRepo.save(emp1);
//			empRepo.save(emp2); 
//			empRepo.save(emp3); 
//			empRepo.save(emp4);
//			empRepo.save(emp5); 
//			empRepo.save(emp6); 
//			empRepo.save(emp7); 
//			empRepo.save(emp8); 
//			empRepo.save(emp9);
//			
//			// save projects in database
//			projRepo.save(pro1);
//			projRepo.save(pro2); 
//			projRepo.save(pro3); 
//			projRepo.save(pro4);
//			
//		};
//		
//	}

}
