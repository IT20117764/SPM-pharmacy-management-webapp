package sliit.spm.ecare.backend.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.spm.ecare.backend.dao.domain.AppUser;
import sliit.spm.ecare.backend.dao.domain.InventoryItem;
import sliit.spm.ecare.backend.dao.domain.Medicine;
import sliit.spm.ecare.backend.dao.domain.Pharmacy;
import sliit.spm.ecare.backend.dao.domain.Quotation;
import sliit.spm.ecare.backend.dao.dto.AppUserDto;
import sliit.spm.ecare.backend.dao.dto.ItemDto;
import sliit.spm.ecare.backend.dao.dto.PharmacyDto;
import sliit.spm.ecare.backend.dao.dto.ProcessRequestDto;
import sliit.spm.ecare.backend.dao.dto.ProcessResponseDto;
import sliit.spm.ecare.backend.dao.dto.QuotationDto;
import sliit.spm.ecare.backend.dao.repository.AppUserRepository;
import sliit.spm.ecare.backend.dao.repository.InventoryItemRepository;
import sliit.spm.ecare.backend.dao.repository.MedicineRepository;
import sliit.spm.ecare.backend.dao.repository.PharmacyRepository;
import sliit.spm.ecare.backend.dao.repository.QuotationRepository;
import sliit.spm.ecare.backend.service.i.EcareService;
import sliit.spm.ecare.backend.util.enums.RestApiStatus;

@Service
public class EcareServiceImpl implements EcareService{
	
	private static final String Date = null;

	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	InventoryItemRepository itemRepository;
	
	@Autowired
	QuotationRepository quotationRepository;
	
	@Autowired
	MedicineRepository medicineRepository;
	
	//Find Quotations by Pharmacy ID
	@Autowired
	PharmacyRepository pharmacyRepository;
	
	public ProcessResponseDto userRegistration (ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		try {
			if(requestDto.getUserDto() != null) {
				AppUser app = new AppUser();
				app.setUsername(requestDto.getUserDto().getUsername());
				app.setFirstName(requestDto.getUserDto().getFirstName());
				app.setLastName(requestDto.getUserDto().getLastName());
				app.setEmail(requestDto.getUserDto().getEmail());
				app.setMobile(requestDto.getUserDto().getMobile());
				app.setUserRole(requestDto.getUserDto().getUserRole());
				app.setPassword(requestDto.getUserDto().getPassword());
				
				AppUser result = appUserRepository.save(app);
				
				if(result != null) {
					responseDto.setSuccess(true);
				} else {
					responseDto.setSuccess(false);
				}
			} else {
				responseDto.setSuccess(false);
			} 
			
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("userRegistration : " + e);
		}		
		return responseDto;
		
	}

	
	public ProcessResponseDto findInventoryByUsernameAndId(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {
			if(requestDto.getUsername() != null && requestDto.getUserId() != null) {
				String username = requestDto.getUsername();
				Integer userId = requestDto.getUserId();
				
				AppUser user = appUserRepository.findByUsername(username);
				
				if(user != null) {
					responseDto.setSuccess(true);
					
				}
			}
		} catch (Exception e) {
			System.out.println("findUserByUsernameAndId : " + e);
		}
		
		
		return null;
	}
	
	
	
	//find items
	public ProcessResponseDto findInventoryByPid(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {
			
			List<InventoryItem> itemList = itemRepository.findByPharmacyId(3);
			System.out.println(1);
			if(requestDto.getUsername() != null && requestDto.getUserId() != null) {
//				String username = requestDto.getUsername();
//				Integer userId = requestDto.getUserId();
				
				
//				AppUser user = appUserRepository.findByUsernameAndId(username, userId);
//				
//				if(user != null) {
//					responseDto.setSuccess(true);
//					responseDto.setStatus(RestApiStatus.FETCH_SUCCESS);
//					
//				}
			}
		} catch (Exception e) {
			System.out.println("findUserByUsernameAndId : " + e);
		}
		
		
		return null;
	}

	
	public ProcessResponseDto findInventoryByUserId(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();		
		try {
			if(requestDto.getUserId() != null) {
				List<InventoryItem> itemList = itemRepository.findByPharmacyId(requestDto.getUserId());
				List<ItemDto> itemDtoList = new ArrayList<ItemDto>();
				
				if(itemList != null && !itemList.isEmpty()) {
					for(InventoryItem i : itemList) {
						ItemDto item = new ItemDto();
						item.setItemId(i.getItemId());
						item.setItemName(i.getItemName());
						item.setType(i.getType());
						item.setPricePerItem(i.getPricePerItem());
						item.setQty(i.getQty());
						item.setDescription(i.getDescription());
						
						itemDtoList.add(item);
					}
				} 
				responseDto.setSuccess(true);
				responseDto.setItemDtoList(itemDtoList);
			} else {
				responseDto.setSuccess(false);
			}
					
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("findInventoryByUserId : " + e);
		}
		
		return responseDto;
	}

	//save Quotation
	public ProcessResponseDto saveQuotation(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		
		try {
			if(requestDto.getQuotationDto() != null) {
				
				Quotation quotation = new Quotation();
				
				quotation.setPrescription(requestDto.getQuotationDto().getPrescription());
				quotation.setUserId(requestDto.getQuotationDto().getUserId());
				quotation.setPharmacyId(requestDto.getQuotationDto().getPharmacyId());
				quotation.setStatus(0);
				quotation.setDate(new Date());
				
				//save
			 	Quotation result = quotationRepository.save(quotation);
			 	
			 	if(result != null) {
			 		responseDto.setSuccess(true);
			 	} else {
			 		responseDto.setSuccess(false);
			 	}
			 	
			} else {
				responseDto.setSuccess(false);
			}
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("findInventoryByUserId : " + e);
		}
		
		return responseDto;
	}


	//get quotation list for pharanacest
	public ProcessResponseDto findQuotationsByPharamacyId(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {
			if(requestDto.getUserId() != null) {
				List<Quotation> quotationList = quotationRepository.findQuotationById(requestDto.getUserId());
				List<QuotationDto> quotationDtoList = new ArrayList<QuotationDto>();
				
				if(quotationList != null && !quotationList.isEmpty()) {
					for(Quotation q : quotationList) {
						
						AppUser app = appUserRepository.findUserById(q.getUserId());
						AppUserDto userDto = new AppUserDto();
						userDto.setEmail(app.getEmail());
						userDto.setFirstName(app.getFirstName());
						userDto.setLastName(app.getLastName());
						userDto.setMobile(app.getMobile());
						
						
						QuotationDto quotationDto = new QuotationDto();
						quotationDto.setId(q.getId());
						quotationDto.setPharmacyId(q.getPharmacyId());
						quotationDto.setUserId(q.getUserId());
						quotationDto.setPrescription(q.getPrescription());
						quotationDto.setUserDto(userDto);
						
						quotationDtoList.add(quotationDto);
				
					}
					
					responseDto.setSuccess(true);
					responseDto.setQuotationDtoList(quotationDtoList);
				} else {
					responseDto.setSuccess(false);
				}
				
			} else {
				responseDto.setSuccess(false);
			}
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("findQuotationsByPharamacyId : " + e);
		}
		
		
		return responseDto;
	}
	
	//get quotation list for user
	public ProcessResponseDto getQuotationUserById(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		try {
			if(requestDto.getUserId() != null) {
				List<Quotation> quotationList = quotationRepository.getQuotationById(requestDto.getUserId());
				List<QuotationDto> quotationDtoList = new ArrayList<QuotationDto>();
				
				if(quotationList != null && !quotationList.isEmpty()) {
					for(Quotation q : quotationList) {
						
						QuotationDto quotationDto = new QuotationDto();
						quotationDto.setId(q.getId());
						quotationDto.setPharmacyId(q.getPharmacyId());
						quotationDto.setUserId(q.getUserId());
						quotationDto.setStatus(q.getStatus());;
						quotationDto.setDate(q.getDate());
						quotationDtoList.add(quotationDto);
				
					}
					
					responseDto.setSuccess(true);
					responseDto.setQuotationDtoList(quotationDtoList);
					
				} else {
					responseDto.setSuccess(false);
				}
				
			} else {
				responseDto.setSuccess(false);
			}
			
			
		}catch(Exception e) {
			responseDto.setSuccess(false);
			System.out.println("findQuotationsByPharamacyId : " + e);
		}
		return responseDto;
	}


	public ProcessResponseDto deleteItemById(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		try {
			if(requestDto.getItemId() != null) {
				itemRepository.deleteById(requestDto.getItemId());
				responseDto.setSuccess(true);
			} else {
				responseDto.setSuccess(false);
			}
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("deleteItemById : " + e);
		}	
		return responseDto;
	}
	
	
	public ProcessResponseDto addMedicine (ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		
		try {
			if(requestDto.getMedicineDto() != null) {
				Medicine medicine = new Medicine();
				
				medicine.setmName(requestDto.getMedicineDto().getmName());
				medicine.setPharmacyName(requestDto.getMedicineDto().getPharmacyName());
				medicine.setmPrice(requestDto.getMedicineDto().getmPrice());
				medicine.setmDescription(requestDto.getMedicineDto().getmDescription());
				
				Medicine result = medicineRepository.save(medicine);
				
				if(result != null) {
					responseDto.setSuccess(true);
				} else {
					responseDto.setSuccess(false);
				}
			} else {
				responseDto.setSuccess(false);
			} 
			
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println(" Medicine : " + e);
		}		
		return responseDto;
		
	}
	
	//Punsisi - Pharmacy Management.
		//Save Pharmacy Details.
		public ProcessResponseDto savePharmacy(ProcessRequestDto requestDto) {
			ProcessResponseDto responseDto = new ProcessResponseDto();
			
			try {
				if(requestDto.getPharmacyDto() != null) {
					AppUser owner = appUserRepository.findUserById(requestDto.getUserId());
					Pharmacy pharmacy = new Pharmacy();
					
					pharmacy.setOwner(owner);
					pharmacy.setPharmacyName(requestDto.getPharmacyDto().getPharmacyName());
					pharmacy.setRegisteredId(requestDto.getPharmacyDto().getRegisteredId());
					pharmacy.setAddress(requestDto.getPharmacyDto().getAddress());
					pharmacy.setAbout(requestDto.getPharmacyDto().getAbout());
					
					//Save
					Pharmacy result = pharmacyRepository.save(pharmacy);
					
					if(result != null) {
						responseDto.setSuccess(true);
					}else {
						responseDto.setSuccess(false);
					}
					
						
				}else {
					responseDto.setSuccess(false);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				responseDto.setSuccess(false);
				System.out.println(e);
				
			}
			
			return responseDto;
		}
		
		//View Pharmacy Details.
		public ProcessResponseDto findPharmacyByPharmacyId(ProcessRequestDto requestDto) {
			ProcessResponseDto responseDto = new ProcessResponseDto();	
			
			try {
				
				if(requestDto.getPharmacyId() != null) {
					Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyId(requestDto.getPharmacyId());
					PharmacyDto pharmacyDto = new PharmacyDto();
					
					if(pharmacy != null) {
						pharmacyDto.setOwner(pharmacy.getOwner().getFirstName()+" "+pharmacy.getOwner().getLastName());
						pharmacyDto.setPharmacyName(pharmacy.getPharmacyName());
						pharmacyDto.setRegisteredId(pharmacy.getRegisteredId());
						pharmacyDto.setEmail(pharmacy.getOwner().getEmail());
						pharmacyDto.setContactNo(pharmacy.getOwner().getMobile());
						pharmacyDto.setAddress(pharmacy.getAddress());
						pharmacyDto.setAbout(pharmacy.getAbout());
							
					}
					responseDto.setSuccess(true);
					responseDto.setPharmacydto(pharmacyDto);	
				}else {
					responseDto.setSuccess(false);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				responseDto.setSuccess(false);
				System.out.println("findPharmacyByPharmacyId : " + e);
			}
			
			return responseDto;
		}
	
}
