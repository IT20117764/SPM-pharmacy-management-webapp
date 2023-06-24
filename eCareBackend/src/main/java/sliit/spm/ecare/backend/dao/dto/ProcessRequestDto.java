package sliit.spm.ecare.backend.dao.dto;

import lombok.Data;


@Data
public class ProcessRequestDto {
	private Integer userId;
	private Integer pharmacyId;
	private String username;
	private AppUserDto userDto;
	private PharmacyDto pharmacyDto;
	private ItemDto itemDto;
	private QuotationDto quotationDto;
	private Integer itemId;
	private MedicineDto medicineDto;
}
