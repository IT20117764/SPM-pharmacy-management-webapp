package sliit.ecarebackend.service.impl;


import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sliit.ecarebackend.dao.domain.AppUser;
import sliit.ecarebackend.dao.domain.InventoryItem;
import sliit.ecarebackend.dao.domain.Medicine;
import sliit.ecarebackend.dao.domain.Order;
import sliit.ecarebackend.dao.domain.OrderItem;
import sliit.ecarebackend.dao.domain.Pharmacy;
import sliit.ecarebackend.dao.domain.Quotation;
import sliit.ecarebackend.dao.dto.AppUserDto;
import sliit.ecarebackend.dao.dto.ItemDto;
import sliit.ecarebackend.dao.dto.MedicineDto;
import sliit.ecarebackend.dao.dto.OrderDto;
import sliit.ecarebackend.dao.dto.PharmacyDto;
import sliit.ecarebackend.dao.dto.ProcessRequestDto;
import sliit.ecarebackend.dao.dto.ProcessResponseDto;
import sliit.ecarebackend.dao.dto.QuotationDto;
import sliit.ecarebackend.repository.AppUserRepository;
import sliit.ecarebackend.repository.InventoryItemRepository;
import sliit.ecarebackend.repository.JwtUserRepository;
import sliit.ecarebackend.repository.MedicineRepository;
import sliit.ecarebackend.repository.OrderItemRepository;
import sliit.ecarebackend.repository.OrderRepository;
import sliit.ecarebackend.repository.PharmacyRepository;
import sliit.ecarebackend.repository.QuotationRepository;
import sliit.ecarebackend.service.i.EcareService;


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
	
	@Autowired
	PharmacyRepository pharmacyRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	private JwtUserRepository jwtUserRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	
	
	//----------------------------------- Tharuka ---------------------------------//
	
	public ProcessResponseDto saveQuotation(ProcessRequestDto requestDto ) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		try {
			if(requestDto.getQuotationDto() != null) {
				
				Quotation quotation = new Quotation();

				quotation.setUserId(requestDto.getQuotationDto().getUserId());
				quotation.setPharmacyId(requestDto.getQuotationDto().getPharmacyId());
				quotation.setStatus(0);
				quotation.setDate(new Date());
				quotation.setPrescription(requestDto.getQuotationDto().getPrescription().getBytes());
							
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
						quotationDto.setPrescription(new String(q.getPrescription()));
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
	
	
	public ProcessResponseDto getQuotationUserById(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		try {
			if(requestDto.getUserId() != null) {
				List<Quotation> quotationList = quotationRepository.getQuotationById(requestDto.getUserId());
				List<QuotationDto> quotationDtoList = new ArrayList<QuotationDto>();
				
				if(quotationList != null && !quotationList.isEmpty()) {
					for(Quotation q : quotationList) {
						
						AppUser appUser = appUserRepository.findById(q.getUserId()).get();
						Pharmacy pharmacy = pharmacyRepository.findById(q.getPharmacyId()).get();
						
						
						
						
						QuotationDto quotationDto = new QuotationDto();
						
						quotationDto.setPharmacyName(pharmacy.getPharmacyName());
						quotationDto.setPharmacyId(q.getPharmacyId());
						quotationDto.setUserId(q.getUserId());
						
						AppUserDto appUserDto = new AppUserDto();
						appUserDto.setUserId(appUser.getUserId());
						appUserDto.setFirstName(appUser.getFirstName());
						appUserDto.setLastName(appUser.getLastName());
		
						quotationDto.setStatus(q.getStatus());
						quotationDto.setId(q.getId());
						quotationDto.setPrescription(new String(q.getPrescription()));
						quotationDto.setUserDto(appUserDto);
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
	
	
	public ProcessResponseDto deleteQuotationsById(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {
			
			if(requestDto.getQuotationId() != null) {
				quotationRepository.deleteById(requestDto.getQuotationId());
				responseDto.setSuccess(true);
			} else {
				responseDto.setSuccess(false);
			}
						
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("deleteQuotationsById : " + e);
		}
		
		return responseDto;
	}


	public ProcessResponseDto findUserOrdersByUserId(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {
			
			if(requestDto.getUserId() != null) {
				List<Order> orders = orderRepository.findOrdersByCid(requestDto.getUserId());
				List<OrderDto> orderDtolist = new ArrayList<OrderDto>();
				if(orders != null) {
					for(Order o : orders) {
						
						AppUser app = appUserRepository.findById(o.getCid()).get();
						
						OrderDto orderDto = new OrderDto();
						orderDto.setOid(o.getOid());
						orderDto.setPid(o.getPid());
						orderDto.setCid(o.getCid());
						orderDto.setTotal(o.getTotal());
						orderDto.setDate(o.getDate());
						orderDto.setStatus(o.getStatus());
						orderDto.setAddress(o.getAddress());
						orderDto.setMobile(o.getMobile());
						orderDto.setDelivery(o.getDelivery());
						orderDto.setPayment(o.getPayment());
						orderDto.setCusName(app.getFirstName() + " " + app.getLastName());
						
						List<ItemDto> itemList = new ArrayList<ItemDto>();
						
						for(OrderItem i : o.getOrderItems()) {
							ItemDto itemDto = new ItemDto();					
							itemDto.setItemId(i.getItemId());
							itemDto.setItemName(i.getItemName());
							itemDto.setPricePerItem(i.getPrice());
							itemDto.setQty(i.getQty());
							itemList.add(itemDto);							
						}
						
						orderDto.setOrderItems(itemList);
						
						orderDtolist.add(orderDto);
						
					}
					responseDto.setSuccess(true);
					responseDto.setOrderDtoList(orderDtolist);
				} else {
					responseDto.setSuccess(false);
				}
			}else {
				responseDto.setSuccess(false);
			}
				
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("findUserOrdersByUserId : " + e);
		}
		
		return responseDto;
	}


	public ProcessResponseDto approveOrders(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {			
			if(requestDto.getOrderDto() != null) {
				
				int result = orderRepository.updateOrderDetailsById(requestDto.getOrderDto().getOid(), requestDto.getOrderDto().getStatus(), requestDto.getOrderDto().getAddress(), requestDto.getOrderDto().getMobile(), requestDto.getOrderDto().getDelivery(), requestDto.getOrderDto().getPayment());
				if(result > 0) {
					responseDto.setSuccess(true);
				} else {
					responseDto.setSuccess(false);
				}
			} else {
				responseDto.setSuccess(false);
			}
			
			responseDto.setSuccess(true);
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("approveOrders : " + e);
		}
		
		return responseDto;
	}


	public ProcessResponseDto changeOrderStatusById(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		try {			
			if(requestDto.getOrderDto() != null) {
				
				int result = orderRepository.changeOrderStatus(requestDto.getOrderDto().getOid(), requestDto.getOrderDto().getStatus());
				if(result > 0) {
					responseDto.setSuccess(true);
				} else {
					responseDto.setSuccess(false);
				}
			} else {
				responseDto.setSuccess(false);
			}
			
			responseDto.setSuccess(true);
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("approveOrders : " + e);
		}
		
		return responseDto;
	}

	
	
	
	//----------------------------------- Shashika ---------------------------------//
	
	public ProcessResponseDto getAllPharmaciesAndMedicines(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {
			
			List<Pharmacy> pharmacyList = pharmacyRepository.findAll();
			List<PharmacyDto> pharmacyDtoList = new ArrayList<PharmacyDto>();
			
			List<String> cities = pharmacyRepository.findCities();
			responseDto.setCitylist(cities);
			
			for(Pharmacy p : pharmacyList) {
				PharmacyDto pharmacyDto = new PharmacyDto();
				pharmacyDto.setPid(p.getPid());
				pharmacyDto.setOwner(p.getOwner().getFirstName() + ' ' + p.getOwner().getLastName());
				pharmacyDto.setPharmacyName(p.getPharmacyName());
				pharmacyDto.setEmail(p.getOwner().getEmail());
				pharmacyDto.setContactNo(p.getOwner().getMobile());
				pharmacyDto.setAddress(p.getAddress());
				pharmacyDto.setAbout(p.getAbout());
				pharmacyDto.setCity(p.getCity());
				
				pharmacyDtoList.add(pharmacyDto);
			}
					
			List<Medicine> medicineList = medicineRepository.findAll();
			List<MedicineDto> medicineDtoList = new ArrayList<MedicineDto>();
			
			for(Medicine m : medicineList) {
				MedicineDto medicineDto = new MedicineDto();
				
				medicineDto.setId(m.getId());
				medicineDto.setName(m.getName());
				medicineDto.setBrand(m.getBrand());
				medicineDto.setDescription(m.getDescription());
				medicineDto.setImage(new String(m.getImage()));
				
				medicineDtoList.add(medicineDto);
			}
			
			responseDto.setMedicineDtoList(medicineDtoList);
			responseDto.setPharmacyDtoList(pharmacyDtoList);
		} catch (Exception e) {
			// TODO: handle exception
			responseDto.setSuccess(false);
			System.out.println("searchPharmacies : " + e);
		}	
		return responseDto;
	}
	

	public ProcessResponseDto addInventoryItem(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
			
		try {
			
			if(requestDto.getItemDto() != null) {
				InventoryItem item = new InventoryItem();
				Pharmacy pharmacy = pharmacyRepository.findPharmacyByUserId(requestDto.getUserId());
				item.setItemName(requestDto.getItemDto().getItemName());
				item.setType(requestDto.getItemDto().getType());
				item.setPricePerItem(requestDto.getItemDto().getPricePerItem());
				item.setQty(requestDto.getItemDto().getQty());
				item.setDescription(requestDto.getItemDto().getDescription());
				item.setPharmacy(pharmacy);
				
				//Save
				InventoryItem result = itemRepository.save(item);
				
				if(result != null) {
					responseDto.setSuccess(true);
				}else {
					responseDto.setSuccess(false);
				}
			} else {
				responseDto.setSuccess(false);
			}			
				
		} catch (Exception e) {
			// TODO: handle exception
			responseDto.setSuccess(false);
			System.out.println("addInventoryItem : " + e);
		}	
		return responseDto;
	}
	
	
	public ProcessResponseDto updateInventoryItem(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {
			
			if(requestDto.getItemDto() != null) {
				
				InventoryItem item = new InventoryItem();
				
				item.setItemName(requestDto.getItemDto().getItemName());
				item.setPricePerItem(requestDto.getItemDto().getPricePerItem());
				item.setQty(requestDto.getItemDto().getQty());
				item.setDescription(requestDto.getItemDto().getDescription());
				
				//update
				
				int result = itemRepository.updateItem(requestDto.getItemDto().getItemName(), requestDto.getItemDto().getPricePerItem(), requestDto.getItemDto().getQty(), requestDto.getItemDto().getDescription(), requestDto.getItemDto().getItemId());
				
				if(result > 0) {
					responseDto.setSuccess(true);
				}else {
					responseDto.setSuccess(false);
				}

			}			
				
		} catch (Exception e) {
			// TODO: handle exception
			responseDto.setSuccess(false);
			System.out.println("updateInventoryItem : " + e);
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
	
	
	
	
	//----------------------------------- Bhashitha ---------------------------------//
	
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
	
	
	public ProcessResponseDto updateAppUser(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		try {			
			if(requestDto.getUserDto() != null) {
				
				AppUser app = new AppUser();
				app.setUserId(requestDto.getUserDto().getUserId());
				app.setUsername(requestDto.getUserDto().getUsername());
				app.setFirstName(requestDto.getUserDto().getFirstName());
				app.setLastName(requestDto.getUserDto().getLastName());
				app.setEmail(requestDto.getUserDto().getEmail());
				app.setMobile(requestDto.getUserDto().getMobile());
				app.setUserRole(requestDto.getUserDto().getUserRole());
				app.setPassword(bcryptEncoder.encode(requestDto.getUserDto().getPassword()));
				jwtUserRepository.save(app);
				responseDto.setSuccess(true);
				
			} else {
				responseDto.setSuccess(false);
			}
			
			responseDto.setSuccess(true);
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("editAppUser : " + e);
		}
		
		return responseDto;
	}
	
	
	public ProcessResponseDto findMedicineById(ProcessRequestDto requestDto) {
		
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		
		try {
			
			if(requestDto.getMedicineDto()!= null) {
				
				Medicine m = medicineRepository.findById(requestDto.getMedicineDto().getId()).get();
				
				if(m != null) {
					MedicineDto medicineDto = new MedicineDto();
					
					medicineDto.setId(m.getId());
					medicineDto.setName(m.getName());
					medicineDto.setBrand(m.getBrand());
					medicineDto.setDescription(m.getDescription());
					medicineDto.setImage(new String(m.getImage()));
					
					List<Pharmacy> pharmacyList = itemRepository.findPharmaciesByItemName(m.getName());
					List<PharmacyDto> pharmacyDtoList = new ArrayList<PharmacyDto>();
					
					for(Pharmacy p : pharmacyList) {
						PharmacyDto pharmacyDto = new PharmacyDto();
						pharmacyDto.setPid(p.getPid());
						pharmacyDto.setOwner(p.getOwner().getFirstName() + ' ' + p.getOwner().getLastName());
						pharmacyDto.setPharmacyName(p.getPharmacyName());
						pharmacyDto.setEmail(p.getOwner().getEmail());
						pharmacyDto.setContactNo(p.getOwner().getMobile());
						pharmacyDto.setAddress(p.getAddress());
						pharmacyDto.setAbout(p.getAbout());
						pharmacyDto.setCity(p.getCity());
						
						pharmacyDtoList.add(pharmacyDto);
					}
					
					responseDto.setSuccess(true);
					responseDto.setMedicineDto(medicineDto);
					responseDto.setPharmacyDtoList(pharmacyDtoList);

				} else {
					responseDto.setSuccess(false);
				}
		
			}else {
				responseDto.setSuccess(false);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			responseDto.setSuccess(false);
			System.out.println("findMedicineById : " + e);
		}
		
		return responseDto;
	}

	
	public ProcessResponseDto addMedicine (ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();	
		
		try {
			if(requestDto.getMedicineDto() != null) {
				Medicine medicine = new Medicine();

				medicine.setName(requestDto.getMedicineDto().getName());
				medicine.setBrand(requestDto.getMedicineDto().getBrand());
				medicine.setDescription(requestDto.getMedicineDto().getDescription());
				medicine.setImage(requestDto.getMedicineDto().getImage().getBytes());
				

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
	
	
	public ProcessResponseDto getPharmacyList(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		try {			
			
			List<Pharmacy> pharmacyList = pharmacyRepository.findAll();
			List<PharmacyDto> pharmacyDtoList = new ArrayList<PharmacyDto>();
			
			if(pharmacyList != null) {
				for(Pharmacy p : pharmacyList) {
					PharmacyDto pharmacyDto = new PharmacyDto();
					pharmacyDto.setPid(p.getPid());
					pharmacyDto.setOwner(p.getOwner().getFirstName() + ' ' + p.getOwner().getLastName());
					pharmacyDto.setPharmacyName(p.getPharmacyName());
					pharmacyDto.setEmail(p.getOwner().getEmail());
					pharmacyDto.setContactNo(p.getOwner().getMobile());
					pharmacyDto.setAddress(p.getAddress());
					pharmacyDto.setAbout(p.getAbout());
					pharmacyDto.setCity(p.getCity());
					
					pharmacyDtoList.add(pharmacyDto);
				}
				responseDto.setPharmacyDtoList(pharmacyDtoList);
				responseDto.setSuccess(true);
			} else {
				responseDto.setSuccess(false);
			}
			
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("getPharmacyList : " + e);
		}
		
		return responseDto;
	}


	public ProcessResponseDto getMedicineList(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		try {			
			List<Medicine> medicineList = medicineRepository.findAll();
			List<MedicineDto> medicineDtoList = new ArrayList<MedicineDto>();
			
			for(Medicine m : medicineList) {
				MedicineDto medicineDto = new MedicineDto();
				
				medicineDto.setId(m.getId());
				medicineDto.setName(m.getName());
				medicineDto.setBrand(m.getBrand());
				medicineDto.setDescription(m.getDescription());
				medicineDto.setImage(new String(m.getImage()));
				
				medicineDtoList.add(medicineDto);
			}
			
			responseDto.setMedicineDtoList(medicineDtoList);
			responseDto.setSuccess(true);
			
			
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("approveOrders : " + e);
		}
		
		return responseDto;
	}


	public ProcessResponseDto updateMedicine(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		try {			
			if(requestDto.getMedicineDto() != null) {
				Medicine medicine = new Medicine();

				medicine.setId(requestDto.getMedicineDto().getId());
				medicine.setName(requestDto.getMedicineDto().getName());
				medicine.setBrand(requestDto.getMedicineDto().getBrand());
				medicine.setDescription(requestDto.getMedicineDto().getDescription());
				medicine.setImage(requestDto.getMedicineDto().getImage().getBytes());
				

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
			System.out.println("updateMedicine : " + e);
		}	
		return responseDto;
	}


	public ProcessResponseDto deleteMedicine(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {
			medicineRepository.deleteById(requestDto.getMedicineId());
			responseDto.setSuccess(true);
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("deleteMedicine : " + e);
		}
		return responseDto;
	}
	
	
	
	
	//----------------------------------- Punsisi ---------------------------------//
	
	public ProcessResponseDto saveOrder(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {
			//Order Status : pending - 0 | approved - 1 | completed - 2 | rejected - 3
			
			if(requestDto.getOrderDto() != null) {
				
				Order order = new Order();
				List<OrderItem> orderItems = new ArrayList<OrderItem>();
				
				Pharmacy pharmacy = pharmacyRepository.findPharmacyByUserId(requestDto.getUserId());
				
				order.setCid(requestDto.getOrderDto().getCid());
				order.setPid(pharmacy.getPid());
				order.setTotal(requestDto.getOrderDto().getTotal());
				order.setDate(new Date());
				order.setStatus(0);
				
			
				//Save
				Order orderDetails = orderRepository.save(order);
				int result = quotationRepository.updateQuotationStatus(requestDto.getQuotationId(), 1);
				
				if(orderDetails != null) {
					
					for(ItemDto i : requestDto.getOrderDto().getOrderItems()) {
						OrderItem orderItem = new OrderItem();
						
						orderItem.setItemId(i.getItemId());
						orderItem.setItemName(i.getItemName());
						orderItem.setPrice(i.getPricePerItem());
						orderItem.setQty(i.getQty());
						orderItem.setOrder(orderDetails);
						orderItems.add(orderItem);
					}
					
					orderItemRepository.saveAll(orderItems);
					
					responseDto.setSuccess(true);
				}else {
					responseDto.setSuccess(false);
				}
			} else {
				responseDto.setSuccess(false);
			}			
				
		} catch (Exception e) {
			// TODO: handle exception
			responseDto.setSuccess(false);
			System.out.println("saveOrder : " + e);
		}	
		return responseDto;
	}
	
	
	public ProcessResponseDto findOrdersByPharmacyId(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {
			//Order Status : pending - 0 | approved - 1 | completed - 2 | rejected - 3
			
			if(requestDto.getUserId() != null) {
				
				List<OrderDto> orderDtolist = new ArrayList<OrderDto>();
				
				Pharmacy pharmacy = pharmacyRepository.findPharmacyByUserId(requestDto.getUserId());
				
				List<Order> orders = orderRepository.findOrdersByPharmacyId(pharmacy.getPid());
				
				if(orders != null) {
					for(Order o : orders) {
						
						AppUser app = appUserRepository.findById(o.getCid()).get();
						
						OrderDto orderDto = new OrderDto();
						orderDto.setOid(o.getOid());
						orderDto.setPid(o.getPid());
						orderDto.setCid(o.getCid());
						orderDto.setTotal(o.getTotal());
						orderDto.setDate(o.getDate());
						orderDto.setStatus(o.getStatus());
						orderDto.setAddress(o.getAddress());
						orderDto.setMobile(o.getMobile());
						orderDto.setDelivery(o.getDelivery());
						orderDto.setPayment(o.getPayment());
						orderDto.setCusName(app.getFirstName() + " " + app.getLastName());
						
						List<ItemDto> itemList = new ArrayList<ItemDto>();
						
						for(OrderItem i : o.getOrderItems()) {
							ItemDto itemDto = new ItemDto();					
							itemDto.setItemId(i.getItemId());
							itemDto.setItemName(i.getItemName());
							itemDto.setPricePerItem(i.getPrice());
							itemDto.setQty(i.getQty());
							itemList.add(itemDto);							
						}
						
						orderDto.setOrderItems(itemList);
						
						orderDtolist.add(orderDto);
						
					}
					responseDto.setSuccess(true);
					responseDto.setOrderDtoList(orderDtolist);
				} else {
					responseDto.setSuccess(false);
				}
				
				
			} else {
				responseDto.setSuccess(false);
			}			
				
		} catch (Exception e) {
			// TODO: handle exception
			responseDto.setSuccess(false);
			System.out.println("findOrdersByPharmacyId : " + e);
		}	
		return responseDto;
	}
	
	
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
				pharmacy.setCity(requestDto.getPharmacyDto().getCity());
				pharmacy.setAbout(requestDto.getPharmacyDto().getAbout());
				
				//Save
				Pharmacy result = pharmacyRepository.save(pharmacy);
				
				appUserRepository.updateUserRole(requestDto.getUserId(), 2);
				
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
					pharmacyDto.setCity(pharmacy.getCity());
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

	
	public ProcessResponseDto getAllRequestsByPharmacyId(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {			
			Pharmacy pharmacy = pharmacyRepository.findPharmacyByUserId(requestDto.getUserId());
				List<Quotation> quotationList = quotationRepository.getAllRequestsByPharmacyId(pharmacy.getPid());
				List<QuotationDto> quotationDtoList = new ArrayList<QuotationDto>();
				
				for(Quotation q : quotationList) {
					AppUser appUser = appUserRepository.findById(q.getUserId()).get();
					QuotationDto quotationDto = new QuotationDto();
					quotationDto.setPharmacyId(q.getPharmacyId());
					quotationDto.setUserId(q.getUserId());
					
					AppUserDto appUserDto = new AppUserDto();
					appUserDto.setUserId(appUser.getUserId());
					appUserDto.setFirstName(appUser.getFirstName());
					appUserDto.setLastName(appUser.getLastName());
	
					quotationDto.setStatus(q.getStatus());
					quotationDto.setId(q.getId());
					quotationDto.setPrescription(new String(q.getPrescription()));
					quotationDto.setUserDto(appUserDto);
					quotationDtoList.add(quotationDto);
				}
				
				responseDto.setQuotationDtoList(quotationDtoList);	

			
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("getAllRequestsByPharmacyId : " + e);
		}
		
		return responseDto;
	}


	public ProcessResponseDto getUserProfile(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {	
			
			if(requestDto.getUserId() != null) {
				AppUser app = appUserRepository.findUserById(requestDto.getUserId());
				Pharmacy pharmacy = pharmacyRepository.findPharmacyByUserId(requestDto.getUserId());
				
				AppUserDto userDto = new AppUserDto();
				userDto.setFirstName(app.getFirstName());
				userDto.setLastName(app.getLastName());
				userDto.setEmail(app.getEmail());
				userDto.setMobile(app.getMobile());
				userDto.setUserRole(app.getUserRole());
				
				if(pharmacy != null) {
					PharmacyDto pharmacyDto = new PharmacyDto();
					
					pharmacyDto.setOwner(pharmacy.getOwner().getFirstName()+" "+pharmacy.getOwner().getLastName());
					pharmacyDto.setPharmacyName(pharmacy.getPharmacyName());
					pharmacyDto.setRegisteredId(pharmacy.getRegisteredId());
					pharmacyDto.setEmail(pharmacy.getOwner().getEmail());
					pharmacyDto.setContactNo(pharmacy.getOwner().getMobile());
					pharmacyDto.setAddress(pharmacy.getAddress());
					pharmacyDto.setAbout(pharmacy.getAbout());
					pharmacyDto.setCity(pharmacy.getCity());
					pharmacyDto.setPid(pharmacy.getPid());
					responseDto.setPharmacydto(pharmacyDto);
				}	
				responseDto.setSuccess(true);
				responseDto.setUser(userDto);
		
			}
			
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("getUserProfile : " + e);
		}
		
		return responseDto;
	}


	public ProcessResponseDto updatePharmacyProfile(ProcessRequestDto requestDto) {
		
		ProcessResponseDto responseDto = new ProcessResponseDto();
				
		try {
			
			if(requestDto.getPharmacyDto() != null && requestDto.getUserDto() != null) {
				
				int res1 = appUserRepository.updateProfile(requestDto.getUserDto().getUserId(), requestDto.getUserDto().getFirstName(), requestDto.getUserDto().getLastName(), requestDto.getUserDto().getEmail(), requestDto.getUserDto().getMobile());
				int res2 = pharmacyRepository.updatePharmacy(requestDto.getPharmacyDto().getPid(), requestDto.getPharmacyDto().getPharmacyName(), requestDto.getPharmacyDto().getAbout(), requestDto.getPharmacyDto().getAddress(), requestDto.getPharmacyDto().getRegisteredId(), requestDto.getPharmacyDto().getCity());
				
				if(res1 > 0 && res2 > 0) {
					responseDto.setSuccess(true);
				} else {
					responseDto.setSuccess(false);
				}
			} else {
				responseDto.setSuccess(false);
			}
			
			
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("updatePharmacyProfile : " + e);
		}
		
		return responseDto;
	}


	public ProcessResponseDto deletePharmacyProfile(ProcessRequestDto requestDto) {
		ProcessResponseDto responseDto = new ProcessResponseDto();
		
		try {
			pharmacyRepository.deleteById(requestDto.getPharmacyId());
			responseDto.setSuccess(true);
		} catch (Exception e) {
			responseDto.setSuccess(false);
			System.out.println("deletePharmacyProfile : " + e);
		}
		
		return responseDto;
	}

	
	
}
