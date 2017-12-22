package com.services;

import java.util.List;

import com.entities.Users;

public interface UsersService {
	public boolean saveOrUpdate(Users users);
	public List<Users> list();
	public boolean delete(Users users);
	public Users findUserByName(String user_name);
	public Users findUserById(Integer user_id);
}
