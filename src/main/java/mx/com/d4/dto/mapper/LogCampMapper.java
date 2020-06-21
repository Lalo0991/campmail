package mx.com.d4.dto.mapper;

import mx.com.d4.dto.LogCampDto;
import mx.com.d4.models.LogCamp;

public class LogCampMapper {

	public static LogCampDto toLogDto(LogCamp logCamp) {
		return new LogCampDto().setId(logCamp.getId()).setOperation(logCamp.getOperation()).setStatus(logCamp.getStatus()).setOperationDate(logCamp.getOperationDate()).setDescription(logCamp.getDescription()).setUser(logCamp.getUser()).setCreationDate(logCamp.getCreationDate()).setModificationDate(logCamp.getModificationDate());
	}
}
