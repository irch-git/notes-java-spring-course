/*
 * this file is a repo for security.html inside of the security folder from the resource/template
 */

package com.jrp.pma.DataAccessObject;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jrp.pma.entities.UserAccount;

public interface IUserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {
	
}
