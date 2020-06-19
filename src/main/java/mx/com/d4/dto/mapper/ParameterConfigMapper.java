package mx.com.d4.dto.mapper;

import mx.com.d4.dto.ParameterConfigDto;
import mx.com.d4.models.ParameterConfig;

public class ParameterConfigMapper {
	
	public static ParameterConfigDto toParameterConfigDTO(ParameterConfig param) {
		
		return new ParameterConfigDto().setParameter(param.getParameter()).setValue(param.getValue()).setDescription(param.getDescription()); 		
	}

}
