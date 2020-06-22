package mx.com.d4.dto;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChargeHeaderDto {
	
	private String id;
	private Date day;	
	private String status;
	private int readClient;
	private int insertClient;
}
