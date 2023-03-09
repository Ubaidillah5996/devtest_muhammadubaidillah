package com.muhammadubaidillah.devtest.service;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.muhammadubaidillah.devtest.dto.DevtestReq;
import com.muhammadubaidillah.devtest.dto.DevtestResp;
import com.muhammadubaidillah.devtest.model.Devtest;
import com.muhammadubaidillah.devtest.repository.DevtestRepository;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class DevtestService {
	
	@Inject
	DevtestRepository devtestRepository;
	
	@Transactional
	public DevtestResp save(DevtestReq req) {
		DevtestResp joResponse = new DevtestResp();
		joResponse.status = "SUCCESS";
		
		//INSERT
		Devtest dev = new Devtest();
		dev.name = req.name;
		dev.phone = req.phone;
		dev.email = req.email;
		dev.userId = req.userId; //Jika token bisa, userId ini akan melalui tokennya 
		dev.createdDate = new Date();
		dev.createdBy = "admin";
		devtestRepository.persist(dev);
		joResponse.data = dev.id;
		
		return joResponse;
		
	}
	
	@Transactional
	public Object delete(DevtestReq req, String userId) {
		JsonObject joResponse = new JsonObject();
		joResponse.put("status", "SUCCESS DELETE");
		
		Devtest devUp = devtestRepository.findByUserId(userId);
		if (devUp != null) {
			if(req.id != null || !req.id.isEmpty()) {
				//DELETE
				Devtest devUpdate = devtestRepository.findById(req.id);
				devtestRepository.delete(devUpdate);
			}
		} else {
			joResponse.put("status", "SORRY, YOU CAN'T DELETE THIS DATA");
		}
		 
		
		return joResponse;
		
	}
	
	@Transactional
	public List<Devtest> getDevtest(DevtestReq request) {
		DevtestResp response = new DevtestResp();
		response.status = "SUCCESS GET DATA";
		
		List<Devtest> listDevtest = null; 
		
		if (request.name.isEmpty() && request.name.isEmpty()) {
			listDevtest = devtestRepository.findAll().list();
		} else if (!request.name.isEmpty() && !request.phone.isEmpty()){
			listDevtest = devtestRepository.findByNameAndPhone(request.name.trim().toLowerCase(), request.phone);
		} else if (!request.name.isEmpty() && request.phone.isEmpty()){
			listDevtest= devtestRepository.findByName(request.name.trim().toLowerCase());
		} else if (request.name.isEmpty() && !request.phone.isEmpty()){
			listDevtest= devtestRepository.findByPhone(request.phone);
		}
		
		return listDevtest;
		
	}
	
	@Transactional
	public Object update(DevtestReq req, String userId) {
		JsonObject joResponse = new JsonObject();
		joResponse.put("status", "SUCCESS CHANGE");
		
		Devtest devUp = devtestRepository.findByUserId(userId);
		if (devUp != null) {
			if(req.id != null) {
				//UPDATE
				Devtest devUpdate = devtestRepository.findById(req.id);
				devUpdate.name = req.name;
				devUpdate.phone = req.phone;
				devUpdate.email = req.email;
				devUpdate.userId = req.userId; //Jika token bisa, userId ini akan melalui tokennya
				devUpdate.updatedDate = new Date();
				devUpdate.updatedBy = "admin";
				devtestRepository.persist(devUpdate);
				joResponse.put("data", devUpdate.id);
			}
		} else {
			joResponse.put("status", "SORRY, YOU CAN'T CHANGE THIS DATA");
		}
		
		
		return joResponse;
		
	}

}
