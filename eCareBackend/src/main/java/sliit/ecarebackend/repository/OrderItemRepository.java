package sliit.ecarebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sliit.ecarebackend.dao.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
