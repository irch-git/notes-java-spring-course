	/*
	 * ==================================================================================================================
	 * |RESTful Routes																								|
	 * |    Name    | Path            | HTTP Verb | Purpose                                                             |
	 * ------------------------------------------------------------------------------------------------------------------   
	 * | 1. Index   | /datas          | GET       | Lists all data visually                                             |
	 * | 																										        |
	 * | 2. New     | /datas/new      | GET       | Shows new data form with empty fields                               |
	 * | 2. Create  | /datas          | POST      | Creates by sending/saving data to database, then redirect somewhere |
	 * |																										        |
	 * | 3. Show    | /datas/:id      | GET       | Shows info about one specific data visually                         |
	 * |																										        |
	 * | 4. Edit    | /datas/:id/edit | GET       | Shows edit form for one data with pre-filled fields                 |
	 * | 4. Update  | /datas/:id      | PUT       | Updates by sending/saving data to database, then redirect somewhere |
	 * |																										        |
	 * | 5. Destroy | /datas/:id      | DELETE    | Delete a particular data, then redirect somewhere                   |
	 * ==================================================================================================================
	 */

package com.jrp.pma.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	
	/*********************************************************************************
	 * READ
	 *********************************************************************************/
	
	// Lists ALL data
	@GetMapping
	public String displayEmployees(Model model) {
//		List<Employee> employees = empService.getAll();
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("employees", employees);
		
		return "employees/list-employees";
	}
	
	
	/*********************************************************************************
	 * CREATE
	 *********************************************************************************/
	
	// Shows the create form for new data
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		Employee anEmployee = new Employee();
		model.addAttribute("employee", anEmployee);
		
		return "employees/new-employee";
	}
	
	// Excecutes and confirms new data
	@PostMapping("/save")
	public String createEmployee(Model model, @Valid Employee employee, Errors errors) { //The order in the parenthesis matters. model should be first
		
		if(errors.hasErrors())
			return "employees/new-employee";
			
		//save to the database using an employee CRUD repository
		empService.save(employee);
		
		return "redirect:/employees";
	}

	
	/*********************************************************************************
	 * UPDATE
	 *********************************************************************************/
	
	// You can either use @GetMapping or @PutMapping/@PatchMapping
	// @PutMapping/@PatchMapping is prefered to stay consistent but you will have to add extra work on the html pages and add a form with pre-filled fields
	// we use @GetMapping for a quick demonstration 
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) { // requestparam has to be the same as the html in the href attribute
		
		Employee theEmp = empService.findByEmployeeId(theId);
		model.addAttribute("employee", theEmp);
		
		return "employees/new-employee";
	}
	
	
	/*********************************************************************************
	 * DELETE
	 *********************************************************************************/
	
	@GetMapping("delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		Employee theEmp = empService.findByEmployeeId(theId);
		empService.delete(theEmp);
		return "redirect:/employees";
	}
}








