package mx.com.d4.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import mx.com.d4.dto.ClientDto;
import mx.com.d4.dto.RequestDto;
import mx.com.d4.exceptions.D4Exception;
import mx.com.d4.services.IClientService;
import mx.com.d4.services.IParameterConfigService;
import mx.com.d4.services.IUtilities;

@Service
public class Utilities implements IUtilities {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private IParameterConfigService parameterService;
	
	@Autowired
	IClientService clienteService;

	private final Log log = LogFactory.getLog(this.getClass());

	@Override
	public boolean sendEmail(RequestDto req) {

		SimpleMailMessage msg;
		boolean respuesta = false;

		try {
			
			ClientDto cl = clienteService.findById(req.getIdClient());			
			msg = new SimpleMailMessage();
			msg.setTo(parameterService.findByParameter("SMTP-Destinos").getValue());
			msg.setSubject(parameterService.findByParameter("SMTP-Asunto").getValue());
			msg.setText("SOLICITUD DE CONTACTO. El usuario con el id: " + cl.getId() + ",  Nombre:  " + cl.getContact() + "  Correo:  " + cl.getMail() + "  Desea ponerse en contacto con D4.");

			javaMailSender.send(msg);
			log.info(String.format("Utilities.sendEmail - Ok"));

			respuesta = true;

		} catch (Exception e) {
			
			log.error(String.format("Utilities.sendEmail - Error al enviar solicitud de contacto - "+ e.getMessage()));
			throw exception("Utilities.sendEmail - Error al enviar solicitud de contacto");
		}
		return respuesta;
	}
	
	private RuntimeException exception(String message, String... args) {
		return D4Exception.throwException(message, args);
	}
}
