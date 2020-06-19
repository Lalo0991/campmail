package mx.com.d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.d4.models.Log;

public interface LogRepository extends JpaRepository<Log,Long> {

}
