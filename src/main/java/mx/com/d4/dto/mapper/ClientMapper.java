package mx.com.d4.dto.mapper;

import mx.com.d4.dto.ClientDto;
import mx.com.d4.models.Client;

public class ClientMapper {

	public static ClientDto toClientDTO(Client client) {

		return new ClientDto().setId(client.getId()).setName(client.getName()).setContact(client.getContact()).setStatus(client.getStatus()).setMail(client.getMail()).setTag(client.getTag());
	}
	
}
