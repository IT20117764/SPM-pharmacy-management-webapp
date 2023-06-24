package sliit.ecarebackend.dao.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;


@Data
public class OrderDto {
	private Integer oid;
	private Integer pid;
	private Integer cid;
	private float total;
	private Date date;
	private Integer status;
	private String cusName;
	private String address;
	private String mobile;
	private String delivery;
	private String payment;
	private List<ItemDto> orderItems;
}
