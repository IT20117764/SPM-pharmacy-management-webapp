package sliit.spm.ecare.backend.service.i;

import sliit.spm.ecare.backend.dao.dto.ProcessRequestDto;
import sliit.spm.ecare.backend.dao.dto.ProcessResponseDto;

public interface EcareService {
	public ProcessResponseDto userRegistration (ProcessRequestDto requestDto);
	public ProcessResponseDto findInventoryByUserId (ProcessRequestDto requestDto);
	public ProcessResponseDto findInventoryByUsernameAndId (ProcessRequestDto requestDto);
	public ProcessResponseDto saveQuotation(ProcessRequestDto requestDto);
	public ProcessResponseDto findQuotationsByPharamacyId(ProcessRequestDto requestDto);
	public ProcessResponseDto getQuotationUserById(ProcessRequestDto requestDto);
	public ProcessResponseDto deleteItemById(ProcessRequestDto requestDto);
	public ProcessResponseDto addMedicine(ProcessRequestDto requestDto);
	public ProcessResponseDto savePharmacy(ProcessRequestDto requestDto);
	public ProcessResponseDto findPharmacyByPharmacyId(ProcessRequestDto requestDto);
}
