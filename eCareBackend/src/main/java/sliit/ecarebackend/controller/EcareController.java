package sliit.ecarebackend.controller;

import java.util.Base64;
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

import sliit.ecarebackend.dao.domain.AppUser;
import sliit.ecarebackend.dao.dto.ProcessRequestDto;
import sliit.ecarebackend.dao.dto.ProcessResponseDto;
import sliit.ecarebackend.service.impl.EcareServiceImpl;
import sliit.ecarebackend.util.common.REST_CONTROLLER_URL;

@RestController
@CrossOrigin
public class EcareController {
	
	@Autowired
	EcareServiceImpl ecareService;
	
	
	//----------------------------------- Tharuka ---------------------------------//
	
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
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.DELETE_QUOTATION_BY_USER_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto deleteQuotationsById(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.deleteQuotationsById(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.GET_ORDERS_BY_USER_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto findUserOrdersByUserId(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.findUserOrdersByUserId(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.APPROVE_ORDER, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto approveOrders(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.approveOrders(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.CHANGE_ORDER_STATUS, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto changeOrderStatusById(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.changeOrderStatusById(processRequestDto);
		return responseDto;
	}
	
	
	
	//----------------------------------- Shashika ---------------------------------//
	
	@RequestMapping(value = REST_CONTROLLER_URL.DELETE_ITEM_BY_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto deleteItemById(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.deleteItemById(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.FIND_INVENTORY_BU_USER_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto findInventoryByUserId(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.findInventoryByUserId(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.SAVE_INVENTORY_ITEM, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto addInventoryItem(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();
		responseDto = ecareService.addInventoryItem(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.UPDATE_INVENTORY_ITEM, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto updateInventoryItem(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.updateInventoryItem(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.GET_PHARAMACIES_AND_MEDICINES, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto getAllPharmaciesAndMedicines(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.getAllPharmaciesAndMedicines(processRequestDto);
		return responseDto;
	}
	
	
	
	//----------------------------------- Bhashitha ---------------------------------//
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.USER_REGISTRATION, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto uploadDocument(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.userRegistration(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.GET_MEDICINE_BY_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto findMedicineById(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.findMedicineById(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.ADD_MEDICINE, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto addMedicine(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.addMedicine(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.GET_PHARMACY_LIST, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto getPharmacyList(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.getPharmacyList(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.GET_MEDICINE_LIST, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto getMedicineList(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.getMedicineList(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.UPDATE_MEDICINE, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto updateMedicine(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.updateMedicine(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.DELETE_MEDICINE, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto deleteMedicine(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.deleteMedicine(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.UPDATE_USER, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto updateAppUser(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.updateAppUser(processRequestDto);
		return responseDto;
	}
	
	
	
	
	//----------------------------------- Punsisi ---------------------------------//
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.SAVE_ORDER, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto saveOrder(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.saveOrder(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.GET_ORDERS_PHARMACY_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto findOrdersByPharmacyId(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.findOrdersByPharmacyId(processRequestDto);
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
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.GET_REQUESTS_BY_PHARMACY_ID, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto getAllRequestsByPharmacyId(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.getAllRequestsByPharmacyId(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.GET_PROFILE, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto getUserProfile(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.getUserProfile(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.UPDATE_PROFILE, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto updatePharmacyProfile(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.updatePharmacyProfile(processRequestDto);
		return responseDto;
	}
	
	
	@RequestMapping(value = REST_CONTROLLER_URL.DELETE_PROFILE, method = RequestMethod.POST)
	public @ResponseBody ProcessResponseDto deletePharmacyProfile(@RequestBody ProcessRequestDto processRequestDto, HttpServletRequest request){
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		responseDto = ecareService.deletePharmacyProfile(processRequestDto);
		return responseDto;
	}

		
}
