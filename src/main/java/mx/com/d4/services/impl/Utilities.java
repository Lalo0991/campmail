package mx.com.d4.services.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import mx.com.d4.dto.ChargeHeaderDto;
import mx.com.d4.dto.ClientDto;
import mx.com.d4.dto.DashboardDto;
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
	
	public static DashboardDto getInfoDashBoard(List<ChargeHeaderDto> listCharge) {
		DecimalFormat df = new DecimalFormat("#.00");
		DashboardDto info = new DashboardDto(0L,0L,0L,0L,0L,0L,0L,0.0,0.0,1.0,0.0);
		
		for(ChargeHeaderDto charge : listCharge) {
			switch (charge.getStatus()) {
			case "1":
				info.setTotalClients(info.getTotalClients()+charge.getInsertClient());
				info.setTotalCamps(info.getTotalCamps()+1);
				break;
			case "2":
				info.setTotalClients(info.getTotalClients()+charge.getInsertClient());
				info.setTotalCamps(info.getTotalCamps()+1);
				break;	
			case "3":
				info.setTotalClients(info.getTotalClients()+charge.getInsertClient());
				info.setSendCamps(info.getSendCamps()+1);
				info.setTotalCamps(info.getTotalCamps()+1);
				info.setClients(info.getClients() + charge.getInsertClient());
				break;
			case "4":
				info.setTotalClients(info.getTotalClients()+charge.getInsertClient());
				info.setErrorCamps(info.getErrorCamps()+1);
				info.setTotalCamps(info.getTotalCamps()+1);
				info.setErrors(info.getErrors() + charge.getInsertClient());
				break;
			}
		}
		
		info.setClientsPercent(Double.valueOf(df.format((double)info.getClients()/(double)info.getTotalClients())));
		info.setSendCampsPercent(Double.valueOf(df.format((double)info.getSendCamps()/(double)info.getTotalCamps())));
		info.setErrorsPercent(Double.valueOf(df.format((double)info.getErrorCamps()/(double)info.getTotalCamps())));
		return info;
	}
	
	private RuntimeException exception(String message, String... args) {
		return D4Exception.throwException(message, args);
	}




}
