package sliit.ecarebackend.dao.dto;

import lombok.Data;

@Data
public class AppUserDto {
	private Integer userId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private Integer userRole;
	private String password;
}
