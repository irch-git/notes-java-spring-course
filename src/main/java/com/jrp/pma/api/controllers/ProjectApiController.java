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

import com.jrp.pma.entities.Project;
import com.jrp.pma.services.ProjectService;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	
	@Autowired
	ProjectService proService;
	
	@GetMapping
	public Iterable<Project> getprojects() {
		return proService.getAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id) {
		return proService.findById(id);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody @Valid Project project) {
		return proService.save(project);
	}
	
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody @Valid Project project) {
		return proService.save(project);
	}
	
	@PatchMapping(path= "/{id}", consumes= "application/json")
	public Project partialUpdate(@PathVariable("id") Long id, @RequestBody @Valid Project patchProject) {
		Project pro = proService.findById(id);
		
		if(patchProject.getName() != null) {
			pro.setName(patchProject.getName()); 
		}
		
		if(patchProject.getStage() != null) {
			pro.setStage(patchProject.getStage()); 
		}
		
		if(patchProject.getDescription() != null) {
			pro.setDescription(patchProject.getDescription()); 
		}
		
		return proService.save(pro);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		try {
			proService.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {}
	}
	
	@GetMapping(params= {"page", "size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Project> findPaginatedProjects(@RequestParam("page") int page, 
													@RequestParam("size") int size) {
		
		PageRequest pageAndSize = PageRequest.of(page, size);
		
		return proService.findAll(pageAndSize);
	}
}








