package mx.com.d4.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.com.d4.dto.LogCampDto;
import mx.com.d4.dto.mapper.LogCampMapper;
import mx.com.d4.exceptions.D4Exception;
import mx.com.d4.exceptions.EntityType;
import mx.com.d4.exceptions.ExceptionType;
import mx.com.d4.models.LogCamp;
import mx.com.d4.repositories.LogCampRepository;
import mx.com.d4.services.ILogCampService;

@Service
public class LogCampService implements ILogCampService{
	
	@Autowired
	private LogCampRepository logCampRepository;

	@Override
	public LogCampDto findById(Long id) {
		LogCampDto logCampDto = new LogCampDto();
		Optional<LogCamp> logOpt = logCampRepository.findById(id);
		if (logOpt.isPresent()) {
			logCampDto = LogCampMapper.toLogDto(logOpt.get());
			return logCampDto;
		}
		throw exception(EntityType.LOG, ExceptionType.ENTITY_NOT_FOUND, id.toString());
	}

	@Override
	public LogCampDto save(LogCampDto lg) {
		LogCampDto logCampDto = new LogCampDto();
		
		Optional<LogCamp> logOptional = logCampRepository.findById(lg.getId());
		if(!logOptional.isPresent()) {
			LogCamp logModel = new LogCamp(lg.getId(),lg.getOperation(),lg.getStatus(),lg.getOperationDate(),lg.getDescription(),lg.getUser(),lg.getCreationDate(),lg.getModificationDate());
			
			logCampDto = LogCampMapper.toLogDto(logCampRepository.save(logModel));
			return logCampDto;
		}
		
		throw exception(EntityType.LOG,ExceptionType.DUPLICATE_ENTITY,lg.getId().toString());
		
	}

	@Override
	public LogCampDto update(LogCampDto lg) {
		LogCampDto logCampDto = new LogCampDto();

		Optional<LogCamp> logOptional = logCampRepository.findById(lg.getId());
		if (logOptional.isPresent()) {
			LogCamp logModel = new LogCamp(lg.getId(),lg.getOperation(),lg.getStatus(),lg.getOperationDate(),lg.getDescription(),lg.getUser(),lg.getCreationDate(),lg.getModificationDate());

			logCampDto = LogCampMapper.toLogDto(logCampRepository.save(logModel));
			return logCampDto;
		}
		throw exception(EntityType.LOG, ExceptionType.ENTITY_NOT_FOUND, lg.getId().toString());
	}

	@Override
	public boolean delete(Long id) {
		boolean log = false;
		Optional<LogCamp> logOptional = logCampRepository.findById(id);
		if (logOptional.isPresent()) {
			logCampRepository.delete(logOptional.get());
			log = true;
			return log;
		}
		throw exception(EntityType.LOG, ExceptionType.ENTITY_NOT_FOUND, id.toString());
	}

	@Override
	public Page<LogCamp> getAll(Pageable pageable) {
		return logCampRepository.findAll(pageable);
	}
	
	@Override
	public Long findAllAccess() {
		return logCampRepository.findAllAccess();
	}
	
	/**
	 * Devuelve RuntimeException
	 *
	 * @param entityType
	 * @param exceptionType
	 * @param args
	 * @return
	 */
	private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
		return D4Exception.throwException(entityType, exceptionType, args);
	}



}
