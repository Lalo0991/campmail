package mx.com.d4.services;

import java.util.List;

import mx.com.d4.dto.LogDto;

public interface ILogService {
	
	LogDto findById(Long id);

	LogDto save(LogDto cl);

	LogDto update(LogDto cl);
	
	boolean delete(Long id);

	List<LogDto> getAll();
}
