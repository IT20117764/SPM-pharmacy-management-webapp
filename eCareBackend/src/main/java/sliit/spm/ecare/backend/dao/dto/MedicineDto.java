package sliit.spm.ecare.backend.dao.dto;

import lombok.Data;

public class MedicineDto {

	private Integer mId;
	private String mName;
	private String pharmacyName;
	private String mPrice;
	private String mDescription;
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPrice() {
		return mPrice;
	}
	public void setmPrice(String mPrice) {
		this.mPrice = mPrice;
	}
	public String getmDescription() {
		return mDescription;
	}
	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	
	
}
