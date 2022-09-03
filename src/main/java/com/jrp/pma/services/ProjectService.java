package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jrp.pma.DataAccessObject.IProjectRepository;
import com.jrp.pma.DataTransferObject.IChartData;
import com.jrp.pma.DataTransferObject.ITimeChartData;
import com.jrp.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	IProjectRepository proRepo;
	
	public Project save(Project project) { //needed for projectController
		return proRepo.save(project);
	}
	
	public Iterable<Project> getAll() { //needed for projectController
		return proRepo.findAll();
	}
	
	public Iterable<IChartData> getProjectStatus() { //needed for homeController
		return proRepo.getProjectStatus();
	}

	public Project findById(Long id) {
		// TODO Auto-generated method stub
		return proRepo.findById(id).get();
	}

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		proRepo.deleteById(id);
	}
	
	public Iterable<Project> findAll(PageRequest pageAndSize) {
		// TODO Auto-generated method stub
		return proRepo.findAll(pageAndSize);
	}

	public Project findByProjectId(Long theId) {
		// TODO Auto-generated method stub
		return proRepo.findByProjectId(theId);
	}

	public void delete(Project thePro) {
		// TODO Auto-generated method stub
		proRepo.delete(thePro);
	}
	
	public List<ITimeChartData> getTimeData() {
		return proRepo.getTimeData();
	}
}
