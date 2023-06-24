package sliit.ecarebackend.dao.dto;

import java.util.List;
import lombok.Data;


@Data
public class PharmacyDto {
	private Integer pid;
	private String owner;
	private String pharmacyName;
	private String email;
	private String contactNo;
	private String registeredId;
	private String address;
	private String about;
	private String city;
	private List<ItemDto> itemDtoList;
}
