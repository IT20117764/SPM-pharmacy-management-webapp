package sliit.ecarebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sliit.ecarebackend.dao.domain.Quotation;


public interface QuotationRepository extends JpaRepository<Quotation, Integer> {
	
	//Find Quotations by Pharmacy ID
	@Query("SELECT q FROM Quotation q WHERE q.pharmacyId=:pharmacyId")
	public List<Quotation>  findQuotationById(@Param("pharmacyId") Integer pharmacyId);
	
	//Find Quotations by Pharmacy ID
	@Query("SELECT q FROM Quotation q WHERE q.userId=:userId")
	public List<Quotation>  getQuotationById(@Param("userId") Integer userId);
	
	@Query("SELECT q FROM Quotation q WHERE q.pharmacyId=:pharmacyId")
	public List<Quotation> getAllRequestsByPharmacyId(@Param("pharmacyId") Integer pid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Quotation q SET q.status=:status WHERE q.id=:id")
	public int updateQuotationStatus(@Param("id") Integer id, @Param("status") Integer status);
	
}
