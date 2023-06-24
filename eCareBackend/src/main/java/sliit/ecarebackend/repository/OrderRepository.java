package sliit.ecarebackend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sliit.ecarebackend.dao.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("SELECT o FROM Order o WHERE o.pid=:pid")
	public List<Order> findOrdersByPharmacyId(@Param("pid") Integer pid);
	
	@Query("SELECT o FROM Order o WHERE o.cid=:cid")
	public List<Order> findOrdersByCid(@Param("cid") Integer cid);
	
	@Transactional
	@Query("UPDATE Order o SET o.status=:status, o.address=:address, o.mobile=:mobile, o.delivery=:delivery, o.payment=:payment  WHERE o.oid=:oid ")
	@Modifying(clearAutomatically = true)
	public int updateOrderDetailsById(@Param("oid") Integer oid, @Param("status") Integer status, @Param("address") String address,@Param("mobile") String mobile,@Param("delivery") String delivery,@Param("payment") String payment);
	
	@Transactional
	@Query("UPDATE Order o SET o.status=:status WHERE o.oid=:oid ")
	@Modifying(clearAutomatically = true)
	public int changeOrderStatus(@Param("oid") Integer oid, @Param("status") Integer status);
	
}
