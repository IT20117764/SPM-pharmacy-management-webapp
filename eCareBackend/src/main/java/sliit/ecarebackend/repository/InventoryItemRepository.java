package sliit.ecarebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import sliit.ecarebackend.dao.domain.InventoryItem;
import sliit.ecarebackend.dao.domain.Pharmacy;



public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
	
	@Query("SELECT i FROM InventoryItem i WHERE i.pharmacy.owner.userId=:userId")
	public List<InventoryItem> findByPharmacyId(@Param("userId") Integer userId);
	
	@Query("SELECT i.pharmacy FROM InventoryItem i WHERE i.itemName LIKE %:itemName%")
	public List<Pharmacy> findPharmaciesByItemName(@Param("itemName") String itemName);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE InventoryItem i SET i.itemName=:itemName, i.pricePerItem=:pricePerItem, i.qty=:qty, i.description=:description WHERE i.itemId=:itemId")
	public int updateItem(@Param("itemName") String itemName, @Param("pricePerItem") float pricePerItem, @Param("qty") Integer qty, @Param("description") String description, @Param("itemId") Integer itemId);
}
