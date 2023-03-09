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

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;

import com.muhammadubaidillah.devtest.common.UserLoginInfo;
import com.muhammadubaidillah.devtest.dto.DevtestReq;
import com.muhammadubaidillah.devtest.dto.DevtestResp;
import com.muhammadubaidillah.devtest.model.Devtest;
import com.muhammadubaidillah.devtest.model.User;
import com.muhammadubaidillah.devtest.repository.UserRepository;
import com.muhammadubaidillah.devtest.service.DevtestService;

@Path("/v1/devtest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"User"})
@RequestScoped

public class DevtestController {
	
//	@Inject
//    JsonWebToken jwt;
//	
//	@Inject
//    @Claim(standard = Claims.zoneinfo)
//    Optional<JsonString> userInfo;
	
	@Inject
	DevtestService devtestService;
	
	@Inject
	UserRepository userRepository;
	
	@Path("/save")
    @POST
    public DevtestResp SaveDevtest(DevtestReq request) throws Exception {
        return devtestService.save(request);
    }
	
	@Path("/delete")
    @DELETE
    public Object deleteDevtest(DevtestReq request) throws Exception {
		User user = userRepository.findById(request.userId);
        return devtestService.delete(request, user.id);
    }
	
	@Path("/getDevtest")
    @GET
    public List<Devtest> getDevtest(DevtestReq request) {
        return devtestService.getDevtest(request);
    }
	
	@Path("/update")
    @POST
    public Object UpdateDevtest(DevtestReq request) throws Exception {
		User user = userRepository.findById(request.userId);
		System.out.println("user id " + user);
        return devtestService.update(request, user.id);
    }

}
