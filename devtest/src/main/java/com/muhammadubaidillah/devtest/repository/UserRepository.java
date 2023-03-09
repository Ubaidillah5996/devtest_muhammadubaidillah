package com.muhammadubaidillah.devtest.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.muhammadubaidillah.devtest.model.Devtest;
import com.muhammadubaidillah.devtest.model.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
	
	public User findById(String id) {
		return find("id=?1", id).firstResult();
	}
	
	public List<User> findByName(String name) {
		return find("lower(name) like ?1", name.trim().toLowerCase()).list();
	}
	
	public User findByNameUser(String name) {
		return find("lower(name) like ?1", name.trim().toLowerCase()).firstResult();
	}
	
	public List<User> findByNameAndEmail(String name, String email) {
		return find("lower(name) like ?1 and email =?2", name.trim().toLowerCase(), email).list();
	}
	
	public List<User> findByEmail(String email) {
		return find("email =?1", email).list();
	}
	
	public User findByEmailUser(String email) {
		return find("email =?1", email).firstResult();
	}
	
	public User findByEmailAndPhone(String email, String phone) {
		return find("email =?1 and phone =?2", email, phone).firstResult();
	}

}
