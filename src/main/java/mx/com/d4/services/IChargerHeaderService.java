package mx.com.d4.services;

import java.util.List;

import mx.com.d4.dto.ChargeHeaderDto;

public interface IChargerHeaderService {
	
	ChargeHeaderDto findById(String id);

	ChargeHeaderDto save(ChargeHeaderDto lg);

	ChargeHeaderDto update(ChargeHeaderDto lg);
	
	boolean delete(String id);

	List<ChargeHeaderDto> getAll();
}
