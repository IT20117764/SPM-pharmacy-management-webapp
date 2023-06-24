package sliit.ecarebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sliit.ecarebackend.dao.domain.AppUser;



public interface JwtUserRepository extends JpaRepository<sliit.ecarebackend.dao.domain.AppUser, Integer>{
	public AppUser findByUsername(String username);
}
