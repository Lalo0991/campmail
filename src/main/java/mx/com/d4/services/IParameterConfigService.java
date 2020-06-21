package mx.com.d4.services;

import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.d4.dto.ParameterConfigDto;

@Service
public interface IParameterConfigService {

	ParameterConfigDto findByParameter(String parameter);
	
	ParameterConfigDto save(ParameterConfigDto parameter);

	ParameterConfigDto update(ParameterConfigDto parameter);

	boolean delete(String idParameter);

	List<ParameterConfigDto> getAll();
	
	
	
}
