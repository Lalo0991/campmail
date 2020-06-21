package mx.com.d4.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.com.d4.dto.LogCampDto;
import mx.com.d4.models.LogCamp;

public interface ILogCampService {
	
	LogCampDto findById(Long id);

	LogCampDto save(LogCampDto lg);

	LogCampDto update(LogCampDto lg);
	
	boolean delete(Long id);

	Page<LogCamp> getAll(Pageable pageable);
}
