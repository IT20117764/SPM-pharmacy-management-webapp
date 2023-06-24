package sliit.ecarebackend.dao.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "order", catalog = "ecare")
public class Order {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer oid;
	
	private Integer pid;
	private Integer cid;
	private float total;
	private Date date;
	private Integer status;
	private String address;
	private String mobile;
	private String delivery;
	private String payment;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
	private List<OrderItem> orderItems;
	
}
