package mx.com.d4.services.impl;

import static mx.com.d4.exceptions.EntityType.PARAMETER;
import static mx.com.d4.exceptions.ExceptionType.DUPLICATE_ENTITY;
import static mx.com.d4.exceptions.ExceptionType.ENTITY_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.d4.dto.ParameterConfigDto;
import mx.com.d4.dto.mapper.ParameterConfigMapper;
import mx.com.d4.exceptions.D4Exception;
import mx.com.d4.exceptions.EntityType;
import mx.com.d4.exceptions.ExceptionType;
import mx.com.d4.models.ParameterConfig;
import mx.com.d4.repositories.ParameterConfigRepository;
import mx.com.d4.services.IParameterConfigService;

@Service
public class ParameterConfigService implements IParameterConfigService {

	@Autowired
	private ParameterConfigRepository parameterRepository;

	@Override
	public ParameterConfigDto findByParameter(String parameter) {

		Optional<ParameterConfig> param = Optional.ofNullable(parameterRepository.findByParameter(parameter));

		if (param.isPresent()) {
			ParameterConfigDto responseDto = ParameterConfigMapper.toParameterConfigDTO(param.get());
			return responseDto;
		} else {
			throw exception(PARAMETER, ENTITY_NOT_FOUND, parameter);
		}
	}

	@Override
	public ParameterConfigDto save(ParameterConfigDto parameter) {

		Optional<ParameterConfig> param = Optional.ofNullable(parameterRepository.findByParameter(parameter.getParameter()));
		ParameterConfigDto responseDto = new ParameterConfigDto();

		if (!param.isPresent()) {
			ParameterConfig paramModel = new ParameterConfig();
			paramModel.setParameter(parameter.getParameter());
			paramModel.setValue(parameter.getValue());
			paramModel.setDescription(parameter.getDescription());

			responseDto = ParameterConfigMapper.toParameterConfigDTO(parameterRepository.save(paramModel));
			return responseDto;
		}
		throw exception(PARAMETER, DUPLICATE_ENTITY, parameter.getParameter());
	}

	@Override
	public ParameterConfigDto update(ParameterConfigDto parameter) {

		Optional<ParameterConfig> param = Optional.ofNullable(parameterRepository.findByParameter(parameter.getParameter()));
		ParameterConfigDto responseDto = new ParameterConfigDto();

		if (param.isPresent()) {
			ParameterConfig paramModel = new ParameterConfig();
			paramModel.setParameter(parameter.getParameter());
			paramModel.setValue(parameter.getValue());
			paramModel.setDescription(parameter.getDescription());

			responseDto = ParameterConfigMapper.toParameterConfigDTO(parameterRepository.save(paramModel));
			return responseDto;
		}
		throw exception(PARAMETER, ENTITY_NOT_FOUND, parameter.getParameter());

	}

	@Override
	public boolean delete(String parameter) {

		Optional<ParameterConfig> param = Optional.ofNullable(parameterRepository.findByParameter(parameter));
		boolean resp = false;

		if (param.isPresent()) {
			parameterRepository.delete(param.get());
			resp = true;
			return resp;
		}
		throw exception(PARAMETER, ENTITY_NOT_FOUND, parameter);
	}

	@Override
	public List<ParameterConfigDto> getAll() {

		List<ParameterConfigDto> parameterList = new ArrayList<ParameterConfigDto>();
		for (ParameterConfig entity : parameterRepository.findAll()) {
			ParameterConfigDto dto = ParameterConfigMapper.toParameterConfigDTO(entity);
			parameterList.add(dto);
		}
		return parameterList;
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