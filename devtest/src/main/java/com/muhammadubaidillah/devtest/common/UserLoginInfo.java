package com.muhammadubaidillah.devtest.common;

import java.util.Optional;

import javax.json.JsonString;

import io.vertx.core.json.JsonObject;

public final class UserLoginInfo {
	
	public static String getUserId(Optional<JsonString> userInfo){
		JsonObject userData = new JsonObject(userInfo.get().getString());
		String name = userData.getString("name", "");
		System.out.println("================ NAME : " + name + " =================");
		return name;
	}

}
