package sliit.spm.ecare.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sliit.spm.ecare.backend.dao.domain.AppUser;
import sliit.spm.ecare.backend.dao.dto.ProcessRequestDto;
import sliit.spm.ecare.backend.dao.dto.ProcessResponseDto;
import sliit.spm.ecare.backend.service.impl.EcareServiceImpl;
import sliit.spm.ecare.backend.util.common.REST_CONTROLLER_URL;

@RestController
@CrossOrigin
public class EcareController {
	
	@Autowired
	EcareServiceImpl ecareService;
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.USER_REGISTRATION, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto uploadDocument(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		responseDto = ecareService.userRegistration(processRequestDto);
//		JwtUser user = getAuthenticatedUser(request);
//		HasPermissionDto permission = validationControllerImpl.hasPermission(user,0, 0,request);
//		if(permission.isValidity()){
//			processRequestDto.setPermissionDto(permission);
//			responseDto = docProcessControllerImpl.uploadDocument(processRequestDto);
//		}else{
//			responseDto.setErrorDetailsList(permission.getErrorDetailsList());
//		}
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.FIND_INVENTORY_BU_USER_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto findInventoryByUserId(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.findInventoryByUserId(processRequestDto);
		return responseDto;
	}
	
	@RequestMapping(value = REST_CONTROLLER_URL.SAVE_QUOTATION, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto saveQuotation(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.saveQuotation(processRequestDto);
		return responseDto;
	}
	
	@RequestMapping(value = REST_CONTROLLER_URL.FIND_QUOTATIONS_BY_PHARMACY_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto findQuotationsByPharamacyId(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.findQuotationsByPharamacyId(processRequestDto);
		return responseDto;
	}
	
	@RequestMapping(value = REST_CONTROLLER_URL.GET_QUOTATIONS_BY_USER_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto getQuotationUserById(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.getQuotationUserById(processRequestDto);
		return responseDto;
	}
	
	@RequestMapping(value = REST_CONTROLLER_URL.DELETE_ITEM_BY_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto deleteItemById(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.deleteItemById(processRequestDto);
		return responseDto;
	}
	
	@RequestMapping(value = REST_CONTROLLER_URL.ADD_MEDICINE, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto addMedicine(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.addMedicine(processRequestDto);
		return responseDto;
	}
	
	@RequestMapping(value = REST_CONTROLLER_URL.SAVE_PHARMACY, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto savePharmacy(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.savePharmacy(processRequestDto);
		return responseDto;
	}
	
	@RequestMapping(value = REST_CONTROLLER_URL.FIND_PHARMACY_BY_PHARMACY_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto findPharmacyByPharmacyId(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.findPharmacyByPharmacyId(processRequestDto);
		return responseDto;
	}
	
	
}
