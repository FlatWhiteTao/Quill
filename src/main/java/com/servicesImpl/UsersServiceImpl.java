package com.servicesImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.repository.UsersRepository;
import com.entities.Role;
import com.entities.Users;
import com.services.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	UsersRepository userRepo;
	
	public boolean saveOrUpdate(Users users) {
		return userRepo.saveOrUpdate(users);
	}

	public List<Users> list() {
		// TODO Auto-generated method stub
		return userRepo.list();
	}

	public boolean delete(Users users) {
		// TODO Auto-generated method stub
		return userRepo.delete(users);
	}

	public Users findUserById(Integer user_id) {
		return userRepo.findUser(user_id);
	}

	public Users findUserByName(String user_name) {
		return userRepo.findUserByName(user_name);
	}	
	
	
	
}