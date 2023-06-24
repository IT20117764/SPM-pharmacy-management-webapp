package sliit.spm.ecare.backend.dao.dto;

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
	private List<ItemDto> itemDtoList;
}
