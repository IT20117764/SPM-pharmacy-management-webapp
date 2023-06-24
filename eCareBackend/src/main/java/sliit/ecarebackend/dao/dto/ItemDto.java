package sliit.ecarebackend.dao.dto;

import lombok.Data;

@Data
public class ItemDto {
	private Integer itemId;
	private String itemName;
	private Integer type;
	private float pricePerItem;
	private Integer qty;
	private String description;	
}
