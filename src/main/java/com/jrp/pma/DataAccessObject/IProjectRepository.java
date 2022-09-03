package com.jrp.pma.DataAccessObject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jrp.pma.DataTransferObject.IChartData;
import com.jrp.pma.DataTransferObject.ITimeChartData;
import com.jrp.pma.entities.Project;

@RepositoryRestResource(collectionResourceRel= "apiprojects", path= "apiprojects") //this shows the data from EmployeeApiController
public interface IProjectRepository extends PagingAndSortingRepository<Project, Long> { // Allows us to access the database. We use Project for the model and Long for the projectId's data type from the Project model file. By extending to the CrudRepository, we inherit methods from CrudRepository to be able to query data(create, read, update, delete). What ever is inside the angle brackets must be capitalized 
	
	@Override
	public List<Project> findAll();

	public Project findByProjectId(long theId); 
	
	@Query(nativeQuery=true, value="SELECT stage as label, COUNT(*) as value " //WARNING!!!make sure there is a space at the end of each query that is in quotes or you will get an error
			+ "FROM project "//WARNING!!!make sure there is a space at the end of each query that is in quotes or you will get an error
			+ "GROUP BY stage ")//WARNING!!!make sure there is a space at the end of each query that is in quotes or you will get an error
	public List<IChartData> getProjectStatus();
	
	@Query(nativeQuery=true, value="SELECT name as projectName, start_date as startDate, end_date as endDate " //WARNING!!!make sure there is a space at the end of each query that is in quotes or you will get an error
			+ "FROM project WHERE start_date is not null ")//WARNING!!!make sure there is a space at the end of each query that is in quotes or you will get an error
	public List<ITimeChartData> getTimeData();
}
