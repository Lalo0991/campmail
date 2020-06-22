package mx.com.d4.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.d4.dto.ChargeHeaderDto;
import mx.com.d4.dto.mapper.ChargeHeaderMapper;
import mx.com.d4.exceptions.D4Exception;
import mx.com.d4.exceptions.EntityType;
import mx.com.d4.exceptions.ExceptionType;
import mx.com.d4.models.ChargeHeader;
import mx.com.d4.repositories.ChargerHeaderReposiroty;
import mx.com.d4.services.IChargerHeaderService;

@Service
public class ChargeHeaderService implements IChargerHeaderService{
	
	@Autowired
	ChargerHeaderReposiroty chargerHeaderReposiroty;

	@Override
	public ChargeHeaderDto findById(String id) {
		ChargeHeaderDto chargeHeaderDto = new ChargeHeaderDto();
		Optional<ChargeHeader> chargeOptional = chargerHeaderReposiroty.findById(id);
		if (chargeOptional.isPresent()) {
			chargeHeaderDto = ChargeHeaderMapper.toChargeHeaderDto(chargeOptional.get());
			return chargeHeaderDto;
		}
		throw exception(EntityType.CHARGE, ExceptionType.ENTITY_NOT_FOUND, id.toString());
	}

	@Override
	public ChargeHeaderDto save(ChargeHeaderDto ch) {
		ChargeHeaderDto chargeHeaderDto = new ChargeHeaderDto();
		
		Optional<ChargeHeader> chargeOptional = chargerHeaderReposiroty.findById(ch.getId());
		if(!chargeOptional.isPresent()) {
			ChargeHeader chargeModel = new ChargeHeader(ch.getId(),ch.getDay(),ch.getStatus(),ch.getReadClient(),ch.getInsertClient(),"campmail",new Date());
			
			chargeHeaderDto = ChargeHeaderMapper.toChargeHeaderDto(chargerHeaderReposiroty.save(chargeModel));
			return chargeHeaderDto;
		}
		
		throw exception(EntityType.CHARGE,ExceptionType.DUPLICATE_ENTITY,ch.getId().toString());
	}

	@Override
	public ChargeHeaderDto update(ChargeHeaderDto ch) {
		ChargeHeaderDto chargeHeaderDto = new ChargeHeaderDto();
		
		Optional<ChargeHeader> chargeOptional = chargerHeaderReposiroty.findById(ch.getId());
		if(!chargeOptional.isPresent()) {
			ChargeHeader chargeModel = new ChargeHeader(ch.getId(),ch.getDay(),ch.getStatus(),ch.getReadClient(),ch.getInsertClient(),"campmail",new Date());
			
			chargeHeaderDto = ChargeHeaderMapper.toChargeHeaderDto(chargerHeaderReposiroty.save(chargeModel));
			return chargeHeaderDto;
		}
		
		throw exception(EntityType.CHARGE,ExceptionType.DUPLICATE_ENTITY,ch.getId().toString());
	}

	@Override
	public boolean delete(String id) {
		boolean charge = false;
		Optional<ChargeHeader> chargeOptional = chargerHeaderReposiroty.findById(id);
		if (chargeOptional.isPresent()) {
			chargerHeaderReposiroty.delete(chargeOptional.get());
			charge = true;
			return charge;
		}
		throw exception(EntityType.CHARGE, ExceptionType.ENTITY_NOT_FOUND, id.toString());
	}

	@Override
	public List<ChargeHeaderDto> getAll() {

		List<ChargeHeaderDto> chargeList = new ArrayList<>(); 
		for(ChargeHeader entity : chargerHeaderReposiroty.findAll()) {
			ChargeHeaderDto dto = ChargeHeaderMapper.toChargeHeaderDto(entity);
			chargeList.add(dto);
		}
		return chargeList;
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
