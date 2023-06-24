package sliit.ecarebackend.dao.dto;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final AppUserDto userDto; 

	public JwtResponse(String jwttoken, AppUserDto userDto) {
		this.jwttoken = jwttoken;
		this.userDto = userDto;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public AppUserDto getUserDto() {
		return userDto;
	}
	
	
}