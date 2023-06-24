package sliit.ecarebackend.dao.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name ="medicine", catalog ="ecare")
public class Medicine {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String name;
	private String brand;
	private String description;
	private byte[] image;
	
}
