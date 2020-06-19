package mx.com.d4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.com.d4.models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {
	
	List<Request> findByIdClient(String idClient);
	
	@Query(value = "select * from request where id_client = ?1 and status = ?2", nativeQuery = true) 	 
	Request findByIdClient(String idClient, String status);
	
	//Request findByIdClientAndStatus(String idC, String status);
		
}
