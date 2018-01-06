package com.eficode.buggywebservice.repository;

import com.eficode.buggywebservice.domain.LoginInformation;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LoginRepository extends MongoRepository<LoginInformation,String> {

    /*
   1) Are there major problems with this method?
   2) Are there some minor things that you could make this better?
     */
	
	/*
	 * Answer:
	 * 1) The <code>findLogin</code> query method only returns one document even though the database may have many documents (with different IDs).
	 * Fix proposal: change the result type e.g. to <code>List<LoginInformation></code>
	 * 
	 * 2) Proposals: 
	 * - more flexibility for the query can be obtained by using the regex patter (<code>findLoginRegexQuery</code>)
	 * - query method could be used instead of the query annotation (<code>findByUsernameAndPassword</code>)
	 */

    @Query(value = "{ username: ?0, password: ?1}")
    LoginInformation findLogin(String username, String password);
    
    @Query("{username : {$regex : ?0}, password : {$regex : ?1}}")
    public List<LoginInformation> findLoginRegexQuery(final String username, final String password);
    
    public List<LoginInformation> findByUsernameAndPassword(final String username, final String password);

}
