package sliit.spm.ecare.backend.dao.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="medicine", catalog ="ecare")
public class Medicine {

	private Integer mId;
	private String mName;
	private String pharmacyName;
	private String mPrice;
	private String mDescription;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
	}
	
	@Column(name = "m_name")
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	
	@Column(name = "pharmacy_name")
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	
	@Column(name = "m_price")
	public String getmPrice() {
		return mPrice;
	}
	public void setmPrice(String mPrice) {
		this.mPrice = mPrice;
	}
	
	@Column(name = "m_description")
	public String getmDescription() {
		return mDescription;
	}
	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}
	
	
}
