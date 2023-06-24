package sliit.ecarebackend.dao.dto;



import java.util.Date;

import lombok.Data;

@Data
public class QuotationDto {
	private Integer id;
	private Integer userId;
	private Integer pharmacyId;
	private Integer status;
	private Date date;
	private String prescription;
	private String pharmacyName;
	private AppUserDto userDto;
}
