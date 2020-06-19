package mx.com.d4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.d4.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
	
	Client findByName(String name);	

}
