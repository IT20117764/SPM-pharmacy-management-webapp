package sliit.spm.ecare.backend.dao.dto;



import java.util.Date;

import lombok.Data;

@Data
public class QuotationDto {
	private Integer id;
	private Integer userId;
	private Integer pharmacyId;
	private Integer status;
	private Date date;
	private byte[] prescription;
	private AppUserDto userDto;
}
