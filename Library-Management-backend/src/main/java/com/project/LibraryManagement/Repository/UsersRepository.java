package com.project.LibraryManagement.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.project.LibraryManagement.entity.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users,Long> {

	Users findOneByEmailIgnoreCaseAndPassword(String email, String password);
	
	
	

}