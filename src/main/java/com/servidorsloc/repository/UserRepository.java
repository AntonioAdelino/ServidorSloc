package com.servidorsloc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.servidorsloc.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	User findByUsername(String username);

}