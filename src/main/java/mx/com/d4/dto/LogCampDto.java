package mx.com.d4.dto;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LogCampDto {
	
	private Long id;
	private String operation;
	private String status;
	private Date operationDate;
	private String description;
	private String user;
	private Date creationDate;
	private Date modificationDate;
	
}
