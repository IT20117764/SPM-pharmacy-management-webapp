package sliit.spm.ecare.backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sliit.spm.ecare.backend.dao.domain.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
	@Query("SELECT p FROM Pharmacy p WHERE p.pid=:pid")
	public Pharmacy findPharmacyByPharmacyId(@Param("pid") Integer pid);
}
