package mx.com.d4.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.d4.dto.LogDto;
import mx.com.d4.repositories.LogRepository;
import mx.com.d4.services.ILogService;

@Service
public class LogService implements ILogService{
	
	@Autowired
	private LogRepository logRepository;

	@Override
	public LogDto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogDto save(LogDto cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogDto update(LogDto cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<LogDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
