package mx.com.d4.test.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import mx.com.d4.services.IClientService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class) 
public class TestServiceClient {

	private final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
    IClientService clienteService;
	
	
	
}
