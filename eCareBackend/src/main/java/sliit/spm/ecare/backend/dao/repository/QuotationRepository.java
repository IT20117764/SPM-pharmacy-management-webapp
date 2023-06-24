package sliit.spm.ecare.backend.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sliit.spm.ecare.backend.dao.domain.Quotation;

public interface QuotationRepository extends JpaRepository<Quotation, Integer> {
	
	//Find Quotations by Pharmacy ID
	@Query("SELECT q FROM Quotation q WHERE q.pharmacyId=:pharmacyId")
	public List<Quotation>  findQuotationById(@Param("pharmacyId") Integer pharmacyId);
	
	//Find Quotations by Pharmacy ID
	@Query("SELECT q FROM Quotation q WHERE q.userId=:userId")
	public List<Quotation>  getQuotationById(@Param("userId") Integer userId);
	
}
