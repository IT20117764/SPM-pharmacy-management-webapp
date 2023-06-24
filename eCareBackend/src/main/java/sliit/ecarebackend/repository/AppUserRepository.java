package sliit.ecarebackend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sliit.ecarebackend.dao.domain.AppUser;




public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	
	public AppUser findByUsername(String username);
	
	@Query("SELECT a FROM AppUser a")
	public List<AppUser> getAllUsers();
	
	
	
	@Query("SELECT a FROM AppUser a WHERE a.username=:username")
	public AppUser getUserByUserName(@Param("username") String username);
	
	@Transactional
	@Query("UPDATE AppUser a SET a.username=:username WHERE a.userId=:userId ")
	@Modifying(clearAutomatically = true)
	public int updateUsername(@Param("userId") Integer userId, @Param("username") String username);
	
	@Query("SELECT a FROM AppUser a WHERE a.userId=:userId")
	public AppUser findUserById(@Param("userId") Integer userId);
	
	@Transactional
	@Query("UPDATE AppUser a SET a.userRole=:userRole WHERE a.userId=:userId ")
	@Modifying(clearAutomatically = true)
	public int updateUserRole(@Param("userId") Integer userId, @Param("userRole") Integer userRole);
	
	@Transactional
	@Query("UPDATE AppUser a SET a.firstName=:firstName, a.lastName=:lastName, a.email=:email, a.mobile=:mobile WHERE a.userId=:userId")
	@Modifying(clearAutomatically = true)
	public int updateProfile(@Param("userId") Integer userId, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("mobile") String mobile);
	
	
}
