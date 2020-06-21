package mx.com.d4.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import mx.com.d4.models.Client;

public class DBLogProcessor implements ItemProcessor<Client, Client>
{
	private final Log log = LogFactory.getLog(this.getClass());

    public Client process(Client cient) throws Exception
    {    	log.info("Inserting client : " + cient);
        return cient;
    }
}
