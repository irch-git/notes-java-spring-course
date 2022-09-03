/*
 * this file is to add an additional layer to the repo so that if you had to migrate the database to a non-relational database it wouldnt mess with the controllers, so the only change we would need to make is in the service files
 */
package com.jrp.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jrp.pma.DataAccessObject.IEmployeeRepository;
import com.jrp.pma.DataTransferObject.IEmployeeProject;
import com.jrp.pma.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	IEmployeeRepository empRepo;
	
	public Employee save(Employee employee) { //needed for employeeController
		return empRepo.save(employee);
	}
	
//	public List<Employee> getAll() { //needed for employeeController
//		return empRepo.findAll();
//	}

	public Iterable<Employee> getAll() { //needed for employeeController
		return empRepo.findAll();
	}
	
	public Iterable<IEmployeeProject> employeeProjects() { //needed for homeController
		return empRepo.employeeProjects();
	}
	
	public Employee findById(Long id) {
		// TODO Auto-generated method stub
		return empRepo.findById(id).get();
	}

	public void deleteById(Long id) {
		empRepo.deleteById(id);
		// TODO Auto-generated method stub
	}

	public Iterable<Employee> findAll(PageRequest pageAndSize) {
		// TODO Auto-generated method stub
		return empRepo.findAll(pageAndSize);
	}

	public Employee findByEmployeeId(long theId) {
		// TODO Auto-generated method stub
		return empRepo.findByEmployeeId(theId);
	}

	public void delete(Employee theEmp) {
		// TODO Auto-generated method stub
		empRepo.delete(theEmp);
	}
}
