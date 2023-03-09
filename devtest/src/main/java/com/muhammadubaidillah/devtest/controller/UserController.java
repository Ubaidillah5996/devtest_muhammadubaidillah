package com.muhammadubaidillah.devtest.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonString;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;

import com.muhammadubaidillah.devtest.dto.DevtestReq;
import com.muhammadubaidillah.devtest.dto.DevtestResp;
import com.muhammadubaidillah.devtest.dto.UserReq;
import com.muhammadubaidillah.devtest.dto.UserResp;
import com.muhammadubaidillah.devtest.model.Devtest;
import com.muhammadubaidillah.devtest.model.User;
import com.muhammadubaidillah.devtest.repository.UserRepository;
import com.muhammadubaidillah.devtest.service.DevtestService;
import com.muhammadubaidillah.devtest.service.UserService;

import oracle.jdbc.driver.JsonWebToken;

@Path("/v1/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped

public class UserController {
	
	@Inject
	UserService userService;
	
	@Inject
	UserRepository userRepository;
	
	@Path("/update")
    @POST
    public UserResp updateUser(UserReq request) throws Exception {
        return userService.updateUser(request);
    }
	
	@Path("/save")
    @POST
    public Object saveUser(UserReq request) throws Exception {
		User user = userRepository.findByEmailUser(request.email);
		if (user != null) {
			return userService.getUser(request);
		} else {
			return userService.registerUser(request);
		}
    }
	
	@Path("/delete")
    @DELETE
    public Object deleteDevtest(UserReq request) throws Exception {
        return userService.delete(request);
    }
	
}
