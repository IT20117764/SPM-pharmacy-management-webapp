package sliit.ecarebackend.service.i;

import sliit.ecarebackend.dao.dto.ProcessRequestDto;
import sliit.ecarebackend.dao.dto.ProcessResponseDto;

public interface EcareService {
	
	//------------------------ Tharuka ---------------------------------//
	public ProcessResponseDto saveQuotation(ProcessRequestDto requestDto);
	public ProcessResponseDto findQuotationsByPharamacyId(ProcessRequestDto requestDto);
	public ProcessResponseDto getQuotationUserById(ProcessRequestDto requestDto);
	public ProcessResponseDto deleteQuotationsById(ProcessRequestDto requestDto);
	public ProcessResponseDto findUserOrdersByUserId(ProcessRequestDto requestDto);
	public ProcessResponseDto approveOrders(ProcessRequestDto requestDto);
	public ProcessResponseDto changeOrderStatusById(ProcessRequestDto requestDto);
	
	//------------------------ Shashika --------------------------------//
	public ProcessResponseDto deleteItemById(ProcessRequestDto requestDto);
	public ProcessResponseDto findInventoryByUsernameAndId (ProcessRequestDto requestDto);
	public ProcessResponseDto findInventoryByUserId (ProcessRequestDto requestDto);
	public ProcessResponseDto addInventoryItem(ProcessRequestDto requestDto);
	public ProcessResponseDto updateInventoryItem(ProcessRequestDto requestDto);
	public ProcessResponseDto getAllPharmaciesAndMedicines(ProcessRequestDto requestDto);
	
	
	//------------------------ Bhashitha ------------------------------//
	public ProcessResponseDto userRegistration (ProcessRequestDto requestDto);
	public ProcessResponseDto findMedicineById(ProcessRequestDto requestDto);
	public ProcessResponseDto addMedicine(ProcessRequestDto requestDto);
	public ProcessResponseDto getPharmacyList(ProcessRequestDto requestDto);
	public ProcessResponseDto getMedicineList(ProcessRequestDto requestDto);
	public ProcessResponseDto updateMedicine(ProcessRequestDto requestDto);
	public ProcessResponseDto deleteMedicine(ProcessRequestDto requestDto);
	public ProcessResponseDto updateAppUser(ProcessRequestDto requestDto);
	

	//-------------------------- Punsisi -------------------------------//
	public ProcessResponseDto getUserProfile(ProcessRequestDto requestDto); 
	public ProcessResponseDto saveOrder(ProcessRequestDto requestDto);
	public ProcessResponseDto findOrdersByPharmacyId(ProcessRequestDto requestDto);
	public ProcessResponseDto savePharmacy(ProcessRequestDto requestDto);
	public ProcessResponseDto findPharmacyByPharmacyId(ProcessRequestDto requestDto);
	public ProcessResponseDto getAllRequestsByPharmacyId(ProcessRequestDto requestDto);
	public ProcessResponseDto updatePharmacyProfile(ProcessRequestDto requestDto);
	public ProcessResponseDto deletePharmacyProfile(ProcessRequestDto requestDto);
}
