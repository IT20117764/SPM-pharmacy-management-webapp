package sliit.ecarebackend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sliit.ecarebackend.dao.domain.Pharmacy;



public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
	@Query("SELECT p FROM Pharmacy p WHERE p.pid=:pid")
	public Pharmacy findPharmacyByPharmacyId(@Param("pid") Integer pid);
	
	@Query("SELECT p FROM Pharmacy p WHERE p.owner.userId=:userId")
	public Pharmacy findPharmacyByUserId(@Param("userId") Integer userId);
	
	@Query("SELECT p.city FROM Pharmacy p GROUP BY p.city ORDER BY p.city")
	public List<String> findCities();
	
	@Transactional
	@Query("UPDATE Pharmacy p SET p.pharmacyName=:pharmacyName, p.about=:about, p.address=:address, p.registeredId=:registeredId, p.city=:city WHERE p.pid=:pid")
	@Modifying(clearAutomatically = true)
	public int updatePharmacy(@Param("pid") Integer pid, @Param("pharmacyName") String pharmacyName, @Param("about") String about, @Param("address") String address, @Param("registeredId") String registeredId, @Param("city") String city);
	
}
