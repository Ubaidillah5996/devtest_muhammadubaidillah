package com.muhammadubaidillah.devtest.service;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.muhammadubaidillah.devtest.dto.UserReq;
import com.muhammadubaidillah.devtest.dto.UserResp;
import com.muhammadubaidillah.devtest.model.User;
import com.muhammadubaidillah.devtest.repository.UserRepository;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class UserService {
	
	@Inject
	UserRepository userRepository;
	
	@Transactional
	public UserResp updateUser(UserReq req) {
		UserResp joResponse = new UserResp();
		joResponse.status = "DATA CHANGE";
		
		if(req.email != null) {
			//UPDATE
			User devUpdate = userRepository.findByEmailUser(req.email);
			devUpdate.name = req.name;
			devUpdate.phone = req.phone;
			devUpdate.email = req.email;
			devUpdate.updatedDate = new Date();
			devUpdate.updatedBy = "admin";
			userRepository.persist(devUpdate);
			joResponse.data = devUpdate.id;
		}
		
		return joResponse;
		
	}
	
	@Transactional
	public UserResp delete(UserReq req) {
		UserResp joResponse = new UserResp();
		joResponse.status = "SUCCESS DELETE";
		
		if(req.email != null || !req.email.isEmpty()) {
			//DELETE
			User devUpdate = userRepository.findByEmailUser(req.email);
			userRepository.delete(devUpdate);
		} 
		
		return joResponse;
		
	}
	
	@Transactional
	public Object getUser(UserReq request) {
		UserResp response = new UserResp();
		response.status = "SUCCESS GET LIST DATA";
		
		User listUser = null; 
		
		if (!request.email.isEmpty() || request.email != null) {
			listUser = userRepository.findByEmailUser(request.email);
		} 
		
		response.data = listUser;
		
		return response;
		
	}
	
	@Transactional
	public Object registerUser(UserReq req) {
		JsonObject joResponse = new JsonObject();
		joResponse.put("status", "SUCCESS REGISTER");
		
		User dev = new User();
		dev.name = req.name;
		dev.phone = req.phone;
		dev.email = req.email;
		dev.createdDate = new Date();
		dev.createdBy = "admin";
		userRepository.persist(dev);
		
		JsonObject jo = new JsonObject();
		jo.put("id", dev.id);
		jo.put("name", dev.name);
		jo.put("phone", dev.phone);
		jo.put("email", dev.email);
		joResponse.put("data", jo);
		
		return joResponse;
		
	}

}
