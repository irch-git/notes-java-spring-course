package com.jrp.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jrp.pma.validators.UniqueValue;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_generator")
	@SequenceGenerator(name="employee_generator",sequenceName="employee_seq", allocationSize = 1)
	private long employeeId;
	
	@NotBlank(message= "*Must give a first name") //prevents users from submitting empty fields in the form.// this will only work if you add the validation dependancy. right click pom.xml>Spring>AddStarters>Validations
	@Size(min=2, max=50) //limits amount of text to between 2 and 50 //this will only work if you add the validation dependancy. right click pom.xml>Spring>AddStarters>Validations
	private String firstName;
	
	@NotBlank(message= "*Must give a last name")
	@Size(min=1, max=50, message= "*Only between 1 to 50 bruh -_-")
	private String lastName;
	
	@NotBlank
	@Email(message= "*Must give a valid email address") //makes sure that it is in email format //this will only work if you add the validation dependancy. right click pom.xml>Spring>AddStarters>Validations
	@UniqueValue
	private String email;
	
	// @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, // This line will also effect the associated/related data. The action depends on what is chosen in the parameters of this line. The "Many" side is considered to be the child side, therefore, the cascading rules need to be configured on this side.
	//			fetch = FetchType.LAZY)   // This line helps to get the associated child records. There are two options for this, lazy or eager. Eager makes the app slower, so always use lazy.
	// @JoinColumn(name="project_id") // This will be the foreign key that connects the project table and employee table. It will create a new column. Basically, the "Many" side of the table/relationship will have the extra column(join column).
	// private Project theProject;
	//
	//	public Project getTheProject() {
	//		return theProject;
	//	}
	//
	//	public void setTheProject(Project theProject) {
	//		this.theProject = theProject;
	//	}
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
				fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", // This is the name for the new table for many to many
	joinColumns= @JoinColumn(name="employee_id"), // This is one of the column/foreign key for the new table that connects the project table
	inverseJoinColumns= @JoinColumn(name="project_id") // This is the other column/foreign for the new table that connects to the employee table
	)
	@JsonIgnore
	private List<Project> theProjects;
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
		
	public List<Project> getTheProjects() {
		return theProjects;
	}
	
	public void setTheProjects(List<Project> theProjects) {
		this.theProjects = theProjects;
	}

	public long getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
