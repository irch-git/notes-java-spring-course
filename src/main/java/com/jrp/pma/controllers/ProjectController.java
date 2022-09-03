/*
 * This file controls the projects routes.
 */

package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.DataTransferObject.ITimeChartData;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller //Turns class into controller
@RequestMapping("/projects") //Creates a top level route for browser to connect to. Plural bc this is where all projects will be located, everything else will be singular bc user will create only one at a time. So everything is singular except for url route within this file.
public class ProjectController {
	
//	@Autowired // This will auto-create an instance of ProjectRepository. Since ProjectRepository is not a class but an interface, this will create an anonymous class and inject/inserted into proRepo in the next line without having to manually create an instance. This is just a shortcut. 
//	ProjectRepository proRepo; // Making ProjectRepository accessible. We will use proRepo to save the project instance that the user have created
//
//	@Autowired
//	EmployeeRepository empRepo;
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	/*********************************************************************************
	 * READ
	 *********************************************************************************/
	
	// Lists ALL data
	@GetMapping
	public String displayProjects(Model model) {
		Iterable<Project> projects = proService.getAll(); // This gets a list of all the projects
		model.addAttribute("projects", projects); // This declares the value projects to a key called "projects" to use in the HTML file.
		
		return "projects/list-projects";
	}
	
	
	/*********************************************************************************
	 * CREATE
	 *********************************************************************************/
	
	// Shows the create form for new data
	@GetMapping("/new") //Creates a sub-level route for browser to connect to within /projects.
	public String displayProjectForm(Model model) { // This method gets executed when the browser accesses /projects/new.
	// The Model is a data type that will help connect the view data(from the forms input in the HTML) to the controller here
		
		Project aProject = new Project(); // This creates a new instance(with the keyword 'new') using the empty constructor(Default Constructor with NO Value) from the Project.java file. that is why it is important to have an empty constructor. We connect an empty project to the form so that the user will fill in the empty project from the form.
		Iterable<Employee> employees = empService.getAll(); //accesses all employees data
		model.addAttribute("project", aProject); //This accesses the Model type variable created in the parameter. This is a key/value pair where "project" is the key and aProject(which is the instance that was created in the previous line with new Project()) is the value. But, since aProject is a new empty instance of Project() from the previous line it is, in other words "project" = new Project(). The key is the one in quotes which will be the key you'll use in the html file
		model.addAttribute("allEmployees", employees);  //use employee data to display in HTML
		
		return "projects/new-project"; // This is the HTMl file that gets rendered onto the browser when the method gets executed
	}
	
	@PostMapping("/save") // This creates a /project/save route for the form's action attribute in the HTML file.
	public String createProject(Project project, Model model) { 
			
		proService.save(project); // Saves to the database using the CRUD repository
			
		return "redirect:/projects"; // This will redirect the page after the form is submitted. We do this to prevent duplicate submissions if the user clicks the submit button multiple times. Always redirect after saving data
	}
	
	// The commented code in the following lines are for one to many.
//	@PostMapping("/save") // This creates a /project/save route for the form's action attribute in the HTML file.
//	public String createProject(Project project, @RequestParam List<Long> employees, Model model) { // Since this is a POST request, this line will help post the project and the model into the database. The @RequestParam accepts the employee chosen on the form
//		
//		proRepo.save(project); // Saves to the database using the CRUD repository
//		
//		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees); //this is a type Iterable<Employee> with the variable chosenEmployees. this line will find the employees ID that the user has chosen on the form.
//		
//		for(Employee emp : chosenEmployees) { //This is a forEach loop
//			emp.setTheProject(project); // We use the setter project from the Employee.java model file. The "project" inside the parenthesis comes from the parameters of this parent method (createProject method)
//			empRepo.save(emp);
//		}
//		
//		return "redirect:/projects/new"; // This will redirect the page after the form is submitted. We do this to prevent duplicate submissions if the user clicks the submit button multiple times. Always redirect after saving data
//	}
	
	@GetMapping("/timelines") //url location
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		
		List<ITimeChartData> timelineData = proService.getTimeData();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);

		System.out.println("---------- project timelines ----------");
		System.out.println(jsonTimelineString);
		
		model.addAttribute("projectTimeList", jsonTimelineString);
		
		return "projects/project-timelines"; //file location
	}
}







