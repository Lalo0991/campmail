package mx.com.d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.d4.models.ParameterConfig;

@Repository
public interface ParameterConfigRepository extends JpaRepository<ParameterConfig , Integer>{
	
	ParameterConfig findByParameter(String parameter);

}
