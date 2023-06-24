package sliit.ecarebackend.util.common;

public class REST_CONTROLLER_URL {
	
	//----------------------------------- Tharuka ---------------------------------//
	
	public static final String SAVE_QUOTATION = "/user/saveQuotation";
	public static final String FIND_QUOTATIONS_BY_PHARMACY_ID = "/pharmacy/findQuotationsByPharamacyId";
	public static final String GET_QUOTATIONS_BY_USER_ID = "/user/findQuotationsByUserId";
	public static final String DELETE_QUOTATION_BY_USER_ID = "/user/deleteQuotationById";
	public static final String GET_ORDERS_BY_USER_ID = "/user/findUserOrdersByUserId";
	public static final String APPROVE_ORDER = "/user/approveOrders";
	public static final String CHANGE_ORDER_STATUS = "/user/changeOrderStatusById";
	
	
	
	//----------------------------------- Shashika ---------------------------------//
	
	public static final String DELETE_ITEM_BY_ID = "/inventory/deleteItemById";
	public static final String FIND_INVENTORY_BU_USER_ID = "/inventory/findInventoryByUserId";
	public static final String SAVE_INVENTORY_ITEM = "/inventory/save";
	public static final String UPDATE_INVENTORY_ITEM = "/inventory/update";
	public static final String GET_PHARAMACIES_AND_MEDICINES = "/user/getAllPharmaciesAndMedicines";
	
		
	
	//----------------------------------- Bhashitha ---------------------------------//
	
	public static final String USER_REGISTRATION = "/user/userRegistration";
	public static final String UPDATE_USER = "/user/update";
	public static final String GET_MEDICINE_BY_ID = "/medicine/getMedicineById";
	public static final String ADD_MEDICINE = "/medicine/addMedicine";
	public static final String GET_PHARMACY_LIST = "/pharmacy/getPharmacyList";
	public static final String GET_MEDICINE_LIST = "/medicine/getMedicineList";
	public static final String UPDATE_MEDICINE = "/medicine/updateMedicine";
	public static final String DELETE_MEDICINE = "/medicine/deleteMedicine";
	
	
	
	//----------------------------------- Punsisi ---------------------------------//
	
	public static final String SAVE_ORDER = "/pharmacy/saveOrder";
	public static final String SAVE_PHARMACY = "/pharmacy/savePharmacy";
	public static final String GET_ORDERS_PHARMACY_ID = "/pharmacy/findOrders";
	public static final String FIND_PHARMACY_BY_PHARMACY_ID = "/pharmacy/findPharmacyByPid";
	public static final String GET_REQUESTS_BY_PHARMACY_ID = "/pharmacy/getAllRequestsByPharmacyId";
	public static final String GET_PROFILE = "/user/getProfile";
	public static final String UPDATE_PROFILE = "/pharmacy/update";
	public static final String DELETE_PROFILE = "/pharmacy/delete";
	
	
}
