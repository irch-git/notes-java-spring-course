/*
 * This file is basically the model for Projects. 
 * It helps with how data will be labeled and organized.
 * Entities are the databases organization
 */

package com.jrp.pma.entities;

import java.util.ArrayList;
import java.util.Date;
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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // Turns a Class into an Entity Class. An Entity represents a table in the database
public class Project {
	
	/*
	 * Variable Section
	 * 
	 * These Variable will have there own Columns in the Database.
	 * projectId Column, name Column, stage Column, 
	 * and description Column
	 */
	
	//Generate Id numbers from Database, not from code 
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="project_generator") // This will auto-increment, giving a unique id number for each project
	@SequenceGenerator(name="project_generator",sequenceName="project_seq", allocationSize = 1)
	private long projectId;
	private String name;
	
	private String stage; //NOTSTARTED, COMPLETED, INPROGRESS
	
	private String description;
	
	@NotBlank(message="date cannot be empty")
	private Date startDate; //make sure to import utils.date not sql.date //if setter and getter option doesn't appear when hovering over, first comment out or delete the annotation for this line first then add it back after adding setter and getter //comment out authorization first if issues come up
	
	@NotBlank(message="date cannot be empty")
	private Date endDate; //make sure to import utils.date not sql.date //if setter and getter option doesn't appear when hovering over, first comment out or delete the annotation for this line first then add it back after adding setter and getter //comment out authorization first if issues come up
	
	// @OneToMany(mappedBy="theProject") // This annotation activates the ability for the next line to use the one to many relationship feature. Without the parenthesis the database will create another table that connects the two tables. With the parenthesis and the value inside it, will create another column for the employee table to connect the tables. You will have to add additional code(variable) to connect this line to the other entity with the opposite annotation of this one which is @ManyToOne..
	// private List<Employee> employees; // This will represent a one to many(one project to many employees) relationship
	//
	//	public List<Employee> getEmployees() {
	//		return employees;
	//	}
	//
	//	public void setEmployees(List<Employee> employees) {
	//		this.employees = employees;
	//	}
	
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", // This is the name for the new table for many to many
				joinColumns= @JoinColumn(name="project_id"), // This is one of the column/foreign key for the new table that connects the project table
				inverseJoinColumns= @JoinColumn(name="employee_id") // This is the other column/foreign for the new table that connects to the employee table
	)
	@JsonIgnore
	private List<Employee> employees; // This will represent a one to many(one project to many employees) relationship 

	
	/*
	 *  Constructor Section
	 */

	public Project() { // This is a Default Constructor with NO Value. We only need to create an empty constructor if we plan to use a form for the user to fill
		
	}
	
	
	public Project(String name, String stage, String description) { //This is a constructor. We do not need the projectId for this part because the user will not be input an id
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}

	/*
	 * Getters and Setter Section
	 * create getters and setters for all fields/variables including the projectId
	 * *** NOTE that setters and getters MUST have the same name as the parameters. example: if perameter is "exampleProject" then the getter is suppose to be "getExampleProject()" and the setter should be "setExampleProject()". if the names are not the same, there will be an error
	 */
	
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	 
	
	  /*
	  * The following lines are for the seed database
	  */
	//convenience method:
	public void addEmployee(Employee emp) {
		if(employees==null) { // checking to see if employee is null. if it is then we will create an new array list in the next line 
			employees = new ArrayList<>();
		}
		employees.add(emp);
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}




}
