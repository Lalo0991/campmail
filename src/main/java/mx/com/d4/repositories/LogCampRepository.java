package mx.com.d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.com.d4.models.LogCamp;

public interface LogCampRepository extends JpaRepository<LogCamp,Long> {
	
	@Query(value = "SELECT COUNT(DISTINCT(USER)) FROM LOG", nativeQuery = true) 	 
	Long findAllAccess();
	
}
