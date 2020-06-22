package mx.com.d4.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientDto  {
		
	private String code;
	private String value;
	private String message;
	
    private String id;
    private String chargeHdr;
	private String status;	
	private String contact;
	private String mail;
	private String name;	
	private String tag;
	
}
