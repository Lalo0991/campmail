package mx.com.d4.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class RequestDto {
		
	private String code;
	private String value;
	private String message;	
	
	private Integer id;
	private String idClient;
	private String status;
	private String reason;
	
	public RequestDto(Integer id, String idClient, String status, String reason) {
		super();
		this.id = id;
		this.idClient = idClient;
		this.status = status;
		this.reason = reason;
	}
}
