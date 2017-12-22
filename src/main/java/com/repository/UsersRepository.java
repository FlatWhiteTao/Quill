package com.repository;

import java.util.List;

import com.entities.Users;

public interface UsersRepository {
	public boolean saveOrUpdate(Users users);
	public List<Users> list();
	public boolean delete(Users users);
	Users findUser(Integer user_id);
	Users findUserByName(String user_name);
}