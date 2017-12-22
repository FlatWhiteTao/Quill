package com.repositoryImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repository.UsersRepository;
import com.entities.Users;


@Repository
@Transactional
public class UsersRepositoryImpl implements UsersRepository{

	@Autowired
	SessionFactory session;
	
	public boolean saveOrUpdate(Users users) {
		// TODO Auto-generated method stub
		session.getCurrentSession().saveOrUpdate(users);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Users> list() {
		return session.getCurrentSession().createQuery("from Users").list();
	}

	public boolean delete(Users users) {
		try{
			
			System.out.println("DeletingUserId"+users.getUser_name());
			session.getCurrentSession().delete(users);
			
		}catch(Exception ex){
			return false;
		}
		
		return true;
	}

	public Users findUser(Integer user_id) {
		return (Users) session.getCurrentSession().get(Users.class,user_id);
	}

	public Users findUserByName(String user_name) {
		Criteria criteria = session.getCurrentSession().createCriteria(Users.class);
		criteria.add(Restrictions.eq("user_name", user_name));		
		return (Users) criteria.uniqueResult();
	}
	
	
}
