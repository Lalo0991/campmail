package mx.com.d4.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ParameterConfigDto {
		
	private String parameter;	
	private String value;
	private String description;
	
	public ParameterConfigDto(String parameter, String value, String description) {
		super();
		this.parameter = parameter;
		this.value = value;
		this.description = description;
	}
}
