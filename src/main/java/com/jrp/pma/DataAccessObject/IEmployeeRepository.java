package com.jrp.pma.DataAccessObject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jrp.pma.DataTransferObject.IEmployeeProject;
import com.jrp.pma.entities.Employee;

@RepositoryRestResource(collectionResourceRel= "apiemployees", path= "apiemployees") //this shows the data from EmployeeApiController
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	
//	@Override
//	public List<Employee> findAll();
	
	//the following lines are for defining custom queries
	@Query(nativeQuery=true, value="SELECT e.first_name AS firstName, e.last_name AS lastName, COUNT(pe.employee_id) AS projectCount "
			+ "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id "
			+ "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC") //specifies a sql query statement to execute 
	public List<IEmployeeProject> employeeProjects(); //returns a list of something. connects to employee project interface in the data transfer object package/folder. What ever is in the angle bracket is created as a label/variable/definition. the angle brackets will determine what the interface will be named in the data transfer object

//	public Employee findEmployeeByEmail(String value); you can type it this way or in the next line
	public Employee findByEmail(String value);

//	public Employee findEmployeeByEmployeeId(Long id); //samething as the next line
	public Employee findByEmployeeId(Long theId);
		
}
