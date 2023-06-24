package sliit.ecarebackend.dao.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "quotation", catalog = "ecare")
public class Quotation {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "pharmacy_id")
	private Integer pharmacyId;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "date")
	private Date date;
	
	private byte[] prescription;
}
