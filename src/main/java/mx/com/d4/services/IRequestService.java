package mx.com.d4.services;

import java.util.List;

import mx.com.d4.dto.RequestDto;

public interface IRequestService {

		
	RequestDto findRequest(String id , String status);
	
	RequestDto save(RequestDto request);

	RequestDto update(RequestDto request);

	boolean delete(RequestDto request);

	List<RequestDto> getAll();	
}
