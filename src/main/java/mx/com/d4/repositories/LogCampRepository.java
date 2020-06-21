package mx.com.d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.d4.models.LogCamp;

public interface LogCampRepository extends JpaRepository<LogCamp,Long> {
	
}
