package mx.com.d4.config;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.google.common.primitives.Ints;

import mx.com.d4.exceptions.D4Exception;
import mx.com.d4.exceptions.EntityType;
import mx.com.d4.exceptions.ExceptionType;
import mx.com.d4.services.IParameterConfigService;

@Configuration
public class EmailConfig {
	
	@Autowired
	private IParameterConfigService parameterService;
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		
		JavaMailSenderImpl mailSender;
		Properties props;
		
		try {
			
			mailSender = new JavaMailSenderImpl();
//			mailSender.setHost(parameterService.findByParameter("SMTP-Host").getValue());
//			mailSender.setPort(Ints.tryParse(parameterService.findByParameter("SMTP-Puerto").getValue()));
//			mailSender.setUsername(parameterService.findByParameter("SMTP-Usuario").getValue());
//			mailSender.setPassword(parameterService.findByParameter("SMTP-Contrasenia").getValue());
			
			mailSender.setHost(parameterService.findByParameter("SMTP-Host").getValue());
			mailSender.setPort(Ints.tryParse(parameterService.findByParameter("SMTP-Puerto").getValue()));
			mailSender.setUsername(parameterService.findByParameter("SMTP-Usuario").getValue());
			mailSender.setPassword(parameterService.findByParameter("SMTP-Contrasenia").getValue());

			props = mailSender.getJavaMailProperties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.debug", "false");
			
			log.info( String.format("EmailConfig - Conexion correcta ") );
			log.info( String.format("EmailConfig - " + mailSender.toString()) );	
		
		}catch(Exception e) {
			log.error( String.format("EmailConfig - Conexion incorrecta") );
			throw exception("EmailConfig - Conexion incorrecta");		
		}
		
		return mailSender;
	}
	
	private RuntimeException exception(String message, String... args) {
		return D4Exception.throwException(message, args);
	}

}