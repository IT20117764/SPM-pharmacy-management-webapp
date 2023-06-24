package sliit.ecarebackend.dao.dto;

import java.util.List;

import lombok.Data;


@Data
public class ProcessResponseDto {
	private boolean isSuccess;
	private List<ItemDto> itemDtoList;
	private List<QuotationDto> quotationDtoList;
	private AppUserDto user;
	private AppUserDto owner;
	private PharmacyDto pharmacydto;
	
	//Shashika
	private List<MedicineDto> medicineDtoList;
	private List<PharmacyDto> pharmacyDtoList;
	private List<String> citylist;
	
	//Bhashitha
	private MedicineDto medicineDto;
	
	//punsisi
	private List<OrderDto> orderDtoList;
}
