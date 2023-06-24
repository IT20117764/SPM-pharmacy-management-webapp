package sliit.spm.ecare.backend.dao.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pharmacy", catalog = "ecare")
public class Pharmacy {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer pid;
	
	@Column(name = "registered_Id")
	private String registeredId;
	
	private String address;
	
	private String about;
	
	@Column(name = "pharmacy_name")
	private String pharmacyName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pharmacy")
	private List<InventoryItem> itemList;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private AppUser owner;
}
