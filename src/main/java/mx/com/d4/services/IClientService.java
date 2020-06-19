package mx.com.d4.services;

import java.util.List;

import mx.com.d4.dto.ClientDto;

public interface IClientService {

	ClientDto findById(String id);

	ClientDto save(ClientDto cl);

	ClientDto update(ClientDto cl);
	
	boolean delete(String id);

	List<ClientDto> getAll();


}
