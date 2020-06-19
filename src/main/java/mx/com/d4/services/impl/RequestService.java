package mx.com.d4.services.impl;

import static mx.com.d4.models.RequestType.CONTACT;
import static mx.com.d4.models.RequestType.REQ_CANCEL;
import static mx.com.d4.models.RequestType.REQ_CONTACT;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.d4.dto.RequestDto;
import mx.com.d4.dto.mapper.RequestMapper;
import mx.com.d4.exceptions.D4Exception;
import mx.com.d4.exceptions.EntityType;
import mx.com.d4.exceptions.ExceptionType;
import mx.com.d4.models.Client;
import mx.com.d4.models.Request;
import mx.com.d4.repositories.ClientRepository;
import mx.com.d4.repositories.RequestRepository;
import mx.com.d4.services.IRequestService;

@Service
public class RequestService implements IRequestService {

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private ClientRepository clientRepository;

	private final Log log = LogFactory.getLog(this.getClass());

	@Override
	public RequestDto save(RequestDto request) {

		RequestDto responseDto = new RequestDto();

		// Validamos la existencia del cliente
		Optional<Client> clientOptional = clientRepository.findById(request.getIdClient());
		if (clientOptional.isPresent()) {

			Optional<Request> requestOpt = Optional.ofNullable(requestRepository.findByIdClient(request.getIdClient(), request.getStatus()));
			if (!requestOpt.isPresent()) {

				Request requestModel = new Request(request.getIdClient(), request.getStatus(), request.getReason(), "campmail", new Date());

				responseDto = RequestMapper.toRequestDTO(requestRepository.save(requestModel));
				return responseDto;
			}
			throw exception(EntityType.CLIENT, ExceptionType.DUPLICATE_ENTITY, request.getIdClient());
		}
		throw exception(EntityType.CLIENT, ExceptionType.ENTITY_NOT_FOUND, request.getIdClient());
	}

	@Override
	public RequestDto update(RequestDto request) {

		RequestDto responseDto = new RequestDto();

		Optional<Client> clientOptional = clientRepository.findById(request.getIdClient());
		if (clientOptional.isPresent()) {
			Optional<Request> requestOpt;
			requestOpt = Optional.ofNullable(requestRepository.findByIdClient(request.getIdClient(), request.getStatus()));

			if (requestOpt.isPresent()) {

				Request requestModel = new Request(request.getIdClient(), request.getStatus(), request.getReason(), "campmail-u", new Date());
				requestModel.setId(requestOpt.get().getId());
				responseDto = RequestMapper.toRequestDTO(requestRepository.save(requestModel));
				return responseDto;
			}
			throw exception(EntityType.REQUEST, ExceptionType.ENTITY_NOT_FOUND, request.getIdClient());
		}
		throw exception(EntityType.CLIENT, ExceptionType.ENTITY_NOT_FOUND, request.getIdClient());
	}

	@Override
	public boolean delete(RequestDto request) {

		boolean response = false;
		Optional<Request> requestOpt = Optional.ofNullable(requestRepository.findByIdClient(request.getIdClient(), request.getStatus()));
		if (requestOpt.isPresent()) {
			requestRepository.delete(requestOpt.get());
			response = true;
			return response;
		}
		throw exception(EntityType.PARAMETER, ExceptionType.ENTITY_NOT_FOUND, request.getIdClient());
	}

	@Override
	public RequestDto findRequest(String id, String status) {

		RequestDto responseDto = new RequestDto();
		Optional<Request> requestOpt = Optional.ofNullable(requestRepository.findByIdClient(id, status));
		if (requestOpt.isPresent()) {

			responseDto = RequestMapper.toRequestDTO(requestOpt.get());
			return responseDto;
		}
		throw exception(EntityType.REQUEST, ExceptionType.ENTITY_NOT_FOUND, id);
	}

	@Override
	public List<RequestDto> getAll() {

		List<RequestDto> requestList = new ArrayList<RequestDto>();
		for (Request entity : requestRepository.findAll()) {
			RequestDto dto = RequestMapper.toRequestDTO(entity);
			requestList.add(dto);
		}
		return requestList;
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
