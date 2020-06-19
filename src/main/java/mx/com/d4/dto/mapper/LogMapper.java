package mx.com.d4.dto.mapper;

import mx.com.d4.dto.LogDto;
import mx.com.d4.models.Log;

public class LogMapper {

	public static LogDto toLogDto(Log log) {
		return new LogDto().setId(log.getId()).setOperation(log.getOperation()).setStatus(log.getStatus()).setOperationDate(log.getOperationDate()).setDescription(log.getDescription()).setUser(log.getUser()).setCreationDate(log.getCreationDate()).setModificationDate(log.getModificationDate());
	}
}
