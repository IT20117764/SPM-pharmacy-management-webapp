package sliit.ecarebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sliit.ecarebackend.dao.domain.Medicine;


public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

	@Query("SELECT m FROM Medicine m")
	public List<Medicine> getAllMedicineDetails();
}
