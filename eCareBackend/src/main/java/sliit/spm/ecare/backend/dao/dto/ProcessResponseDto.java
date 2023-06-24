package sliit.spm.ecare.backend.dao.dto;

import java.util.List;

import lombok.Data;
import sliit.spm.ecare.backend.util.enums.RestApiStatus;

@Data
public class ProcessResponseDto {
	private boolean isSuccess;
	private List<ItemDto> itemDtoList;
	private List<QuotationDto> quotationDtoList;
	private AppUserDto owner;
	private PharmacyDto pharmacydto;
}
