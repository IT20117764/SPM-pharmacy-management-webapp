package sliit.spm.ecare.backend.dao.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "inventory_item", catalog = "ecare")
public class InventoryItem {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "item_id", unique = true, nullable = false)
	private Integer itemId;
	
	@Column(name = "item_name")
	private String itemName;
	private Integer type;
	
	@Column(name = "price_per_item")
	private float pricePerItem;
	private Integer qty;
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pharmacy_id")
	private Pharmacy pharmacy;
	
	
	
	
}
