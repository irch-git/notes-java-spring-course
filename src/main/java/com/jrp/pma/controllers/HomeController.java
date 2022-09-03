package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.DataAccessObject.IEmployeeRepository;
import com.jrp.pma.DataAccessObject.IProjectRepository;
import com.jrp.pma.DataTransferObject.IChartData;
import com.jrp.pma.DataTransferObject.IEmployeeProject;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {

	/*
	 *	An example if you want to do this manually without @Autowired annotation
	 */
//	ProjectRepository proRepo = new ProjectRepository() {
//
//		@Override
//		public <S extends Project> S save(S entity) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public <S extends Project> Iterable<S> saveAll(Iterable<S> entities) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public Optional<Project> findById(Long id) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public boolean existsById(Long id) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public Iterable<Project> findAllById(Iterable<Long> ids) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public long count() {
//			// TODO Auto-generated method stub
//			return 0;
//		}
//
//		@Override
//		public void deleteById(Long id) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void delete(Project entity) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void deleteAll(Iterable<? extends Project> entities) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void deleteAll() {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public List<Project> findAll() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public List<ChartData> getProjectStatus() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//	};
	
	@Autowired
	IProjectRepository proRepo;
	
	@Autowired
	IEmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException { // JsonProcessingException is used for the line that involves object mapper. this exception will crash the program if there is an issue when converting data for projectData 
		
		@SuppressWarnings("unused")
		Map<String, Object> map = new HashMap<>(); // converts ChartData to json structure. this data structure is declared as "map". String is for column name. Object is for the data. 

		// we are querying the database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		List<IChartData> projectData = proRepo.getProjectStatus(); //proRepo is the label given for the ProjectRepository file from the declaration given above. getProjectStatus is the method we named in the project repository file. ChartData connects this file to the ChartData.java file in the data transfer object package and we named the access projectData to use as a key/value pair in HTML

		// Lets convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper(); //creates a new object
		String jsonString = objectMapper.writeValueAsString(projectData); //converts data from projectData into string. result is stored in the variable jsonString
		// example of what to expect for the conversion to json. pairs of [label, value]
		//[["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]
		model.addAttribute("projectStatusCnt", jsonString); // this will send the converted data to HTML file
		
		List<IEmployeeProject> employeesProjectCnt = empRepo.employeeProjects(); //this connects to the employee project file in the data transfer object
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt); // this is the key used in the home HTML file
		
		
		return "main/home";
	}
}
