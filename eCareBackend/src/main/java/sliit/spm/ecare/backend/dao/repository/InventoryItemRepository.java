package sliit.spm.ecare.backend.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sliit.spm.ecare.backend.dao.domain.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
	
	@Query("SELECT i FROM InventoryItem i WHERE i.pharmacy.owner.userId=:userId")
	public List<InventoryItem> findByPharmacyId(@Param("userId") Integer userId);
}
