package mx.com.d4.services.impl;

import static mx.com.d4.exceptions.EntityType.CLIENT;
import static mx.com.d4.exceptions.ExceptionType.DUPLICATE_ENTITY;
import static mx.com.d4.exceptions.ExceptionType.ENTITY_NOT_FOUND;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.d4.dto.ClientDto;
import mx.com.d4.dto.mapper.ClientMapper;
import mx.com.d4.exceptions.D4Exception;
import mx.com.d4.exceptions.EntityType;
import mx.com.d4.exceptions.ExceptionType;
import mx.com.d4.models.Client;
import mx.com.d4.repositories.ClientRepository;
import mx.com.d4.services.IClientService;

@Service
public class ClientService implements IClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ClientDto findById(String id) {

		Optional<Client> client = clientRepository.findById(id);

		if (client.isPresent()) {
			ClientDto clientDto = ClientMapper.toClientDTO(client.get());
			return clientDto;
		} else {
			throw exception(CLIENT, ENTITY_NOT_FOUND, id);
		}
	}

	@Override
	public ClientDto save(ClientDto clientDto) {

		Optional<Client> cl = Optional.ofNullable(clientRepository.findByName(clientDto.getName()));
		ClientDto resp = new ClientDto();
		resp.setCode("1").setValue("FAIL").setMessage("EMPTY");

		if (!cl.isPresent()) {
			Client client = new Client();
			client.setId(clientDto.getId());
			client.setName(clientDto.getName());
			client.setContact(clientDto.getContact());
			client.setStatus(clientDto.getStatus());
			client.setMail(clientDto.getMail());
			client.setTag(clientDto.getTag());
			client.setAudithUsr("campmail");
			client.setAudithTime(new Date());

			resp = ClientMapper.toClientDTO(clientRepository.save(client));
			resp.setCode("0").setValue("OK").setMessage("EMPTY");

			return resp;
		}
		throw exception(CLIENT, DUPLICATE_ENTITY, clientDto.getName());
	}

	@Override
	public ClientDto update(ClientDto clientDto) {

		Optional<Client> cl = clientRepository.findById(clientDto.getId());
		ClientDto resp = new ClientDto();
		resp.setCode("1").setValue("FAIL").setMessage("EMPTY");

		if (cl.isPresent()) {
			Client client = new Client();
			client.setId(clientDto.getId());
			client.setName(clientDto.getName());
			client.setContact(clientDto.getContact());
			client.setStatus(clientDto.getStatus());
			client.setMail(clientDto.getMail());
			client.setTag(clientDto.getTag());
			client.setAudithUsr("campmail");
			client.setAudithTime(new Date());

			resp = ClientMapper.toClientDTO(clientRepository.save(client));
			resp.setCode("0").setValue("OK").setMessage("EMPTY");

			return resp;
		}
		throw exception(CLIENT, ENTITY_NOT_FOUND, clientDto.getName());
	}

	@Override
	public boolean delete(String id) {

		Optional<Client> cl = clientRepository.findById(id);
		boolean resp = false;

		if (cl.isPresent()) {
			clientRepository.delete(cl.get());
			resp = true;
			return resp;
		}
		throw exception(CLIENT, ENTITY_NOT_FOUND, id);
	}

	@Override
	public List<ClientDto> getAll() {

		List<ClientDto> clientList = new ArrayList<ClientDto>();
		for (Client entity : clientRepository.findAll()) {
			ClientDto dto = ClientMapper.toClientDTO(entity);
			clientList.add(dto);
		}
		return clientList;
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
