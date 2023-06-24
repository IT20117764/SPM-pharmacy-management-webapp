package sliit.ecarebackend.dao.dto;

import lombok.Data;


@Data
public class ProcessRequestDto {
	private Integer userId;
	private Integer pharmacyId;
	private Integer quotationId;
	private Integer medicineId;
	private String username;
	private AppUserDto userDto;
	private PharmacyDto pharmacyDto;
	private ItemDto itemDto;
	private QuotationDto quotationDto;
	private Integer itemId;
	private MedicineDto medicineDto;
	private OrderDto orderDto;
	
	//Shashika
	private String searchName;
}
