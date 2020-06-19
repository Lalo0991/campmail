package mx.com.d4.dto.mapper;

import mx.com.d4.dto.RequestDto;
import mx.com.d4.models.Request;

public class RequestMapper {

	public static RequestDto toRequestDTO(Request request) {

		return new RequestDto().setId(request.getId()).setIdClient(request.getIdClient()).setStatus(request.getStatus()).setReason(request.getReason());
	}
}
