/**
 * 
 */
package com.jrp.pma.DataTransferObject;

/**
 * @author My PC
 *
 *
 * this file is created from the employee repository file in the angle brackets from the code that defines the custom sql query
 * the custom queries will be stored here. the custom queries originate from the project repository file
 */
public interface IEmployeeProject {
	
	//this file is for custom queries
	// Need to have the property names begin with get
	public String getFirstName();
	public String getLastName();
	public int getProjectCount();
}
