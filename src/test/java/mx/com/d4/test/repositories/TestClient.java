package mx.com.d4.test.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import mx.com.d4.repositories.ClientRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
class ClientRepositoryTest {
	
		
	@Autowired
	ClientRepository clientRepository;

	@Test
	public void saveClient() {
		
//		Client client = new Client( "d100", "1", "Freddy", "fred@mail.com", "Luis Alfredo" , "TAG-12", "campmail", new Date() );		
//		clientRepository.save(client);		
//		assertNotNull(client);
//		
//		Optional<Client>  client2 =  clientRepository.findById( client.getId() );		
//		assertEquals( client.getId(), client2.get().getId() );			
		
	}
}
