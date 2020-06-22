package mx.com.d4.dto.mapper;

import mx.com.d4.dto.ChargeHeaderDto;
import mx.com.d4.models.ChargeHeader;

public class ChargeHeaderMapper {
	
	public static ChargeHeaderDto toChargeHeaderDto(ChargeHeader chargeHeader){
		return new ChargeHeaderDto().setId(chargeHeader.getId()).setInsertClient(chargeHeader.getInsertClient()).setReadClient(chargeHeader.getReadClient()).setDay(chargeHeader.getDay()).setStatus(chargeHeader.getStatus());
	}
}
