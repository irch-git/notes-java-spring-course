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
/*
 * to see the data from this file, you'll have to add an annotation in IEmployeeRepository file. @RepositoryRestResource
 */

package com.jrp.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	EmployeeService empService;
	
//	@Autowired
//	IEmployeeRepository empRepo;
	

	/*********************************************************************************
	 * READ
	 *********************************************************************************/
	
	// Lists ALL data
	@GetMapping
	public Iterable<Employee> getEmployees() { //gets a list of all employees
		return empService.getAll();
	}
	
	// Shows SINGLE data
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) { //gets individual employee
		return empService.findById(id);
	}
	
	
	/*********************************************************************************
	 * CREATE
	 *********************************************************************************/
	
	//WARNING: you will have to disable csrf in the SecurityConfiguration.java file to be able to test the http requests for POST and PUT
	//WARNING: you will have to disable csrf in the SecurityConfiguration.java file to be able to test the http requests for POST and PUT
	//WARNING: you will have to disable csrf in the SecurityConfiguration.java file to be able to test the http requests for POST and PUT
	// Creates NEW data
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody @Valid Employee employee) {
		return empService.save(employee);
	}
	
	
	/*********************************************************************************
	 * UPDATE
	 *********************************************************************************/
	
	//WARNING: you will have to disable csrf in the SecurityConfiguration.java file to be able to test the http requests for POST and PUT
	//WARNING: you will have to disable csrf in the SecurityConfiguration.java file to be able to test the http requests for POST and PUT
	//WARNING: you will have to disable csrf in the SecurityConfiguration.java file to be able to test the http requests for POST and PUT
	// Updates by deleting then creating new data
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@RequestBody @Valid Employee employee) {
		return empService.save(employee);
	}
	
	// Updates by editing data
	@PatchMapping(path="/{id}", consumes= "application/json") //patch is a partial update
	public Employee partialUpdate(@PathVariable("id") long id, @RequestBody @Valid Employee patchEmployee) {
		Employee emp = empService.findById(id);
		
		if(patchEmployee.getEmail() != null) {
			emp.setEmail(patchEmployee.getEmail());
		}
		
		if(patchEmployee.getFirstName() != null) {
			emp.setFirstName(patchEmployee.getFirstName());
		}

		if(patchEmployee.getLastName() != null) {
			emp.setLastName(patchEmployee.getLastName());
		}
		
		return empService.save(emp);
	}
	
	
	/*********************************************************************************
	 * DELETE
	 *********************************************************************************/
	
	// Deletes SINGLE data
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		try {
			empService.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {}
	}
	
	
	/*********************************************************************************
	 * READ
	 *********************************************************************************/
	
	// Custom lists of data
	@GetMapping(params= {"page", "size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page, 
													@RequestParam("size") int size) {
		
		PageRequest pageAndSize = PageRequest.of(page, size);
		
		return empService.findAll(pageAndSize);
	}
}












