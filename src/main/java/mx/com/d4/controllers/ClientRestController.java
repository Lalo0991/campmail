//package mx.com.d4.controllers;
//
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import mx.com.d4.dto.ClientDto;
//import mx.com.d4.exceptions.D4Exception;
//import mx.com.d4.services.IClientService;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping(path = "/rest/client")
//public class ClientRestController {
//
//	@Autowired
//	IClientService clientService;
//
//	private final Log log = LogFactory.getLog(this.getClass());
//
//	@GetMapping()
//	public ResponseEntity<List<ClientDto>> getAll() throws InterruptedException {
//
//		ResponseEntity<List<ClientDto>> responseEntity;
//
//		List<ClientDto> all = clientService.getAll();
//		responseEntity = new ResponseEntity<List<ClientDto>>(all, HttpStatus.OK);
//		Thread.sleep(50);
//		return responseEntity;
//	}
//
//	
//	@GetMapping(path = "/{id}")
//	public ResponseEntity<ClientDto> getById(@PathVariable("id") String id) {
//		ResponseEntity<ClientDto> response;
//		ClientDto byId;
//		@SuppressWarnings("rawtypes")
//		MultiValueMap headers = new LinkedMultiValueMap();
//
//		try {
//			
//			byId = clientService.findById(id);			
//			response = new ResponseEntity<ClientDto>(byId, HttpStatus.OK);
//
//		} catch (D4Exception.EntityNotFoundException e) {
//
//			log.error(e.getMessage());
//			headers.set("Contentlenght", "-10");
//			headers.set("ExceptionMessage", e.getMessage());
//			response = new ResponseEntity<ClientDto>(headers, HttpStatus.NOT_FOUND);
//		}
//		return response;
//	}
//
//	@PostMapping()
//	public ResponseEntity<ClientDto> createNew(@RequestBody ClientDto user) {
//
//		user = clientService.save(user);
//		return new ResponseEntity<ClientDto>(user, HttpStatus.CREATED);
//	}
//
//
//	//ECO SERVICE
//
//	@PutMapping()
//	@SuppressWarnings("unchecked")
//	public ResponseEntity<ClientDto> update(@RequestBody ClientDto user) {
//
//		ResponseEntity response;
//		MultiValueMap headers = new LinkedMultiValueMap();
//		try {
//			user = clientService.update(user);
//			response = new ResponseEntity<ClientDto>(user, HttpStatus.ACCEPTED);
//		} catch (D4Exception.EntityNotFoundException e) {
//			headers.set("ExceptionMessage", e.getMessage());
//			response = new ResponseEntity<ClientDto>(headers, HttpStatus.NOT_ACCEPTABLE);
//		}
//
//		return response;
//	}
//
//	@DeleteMapping(path = "/{id}")
//	@SuppressWarnings("unchecked")
//	public ResponseEntity<Boolean> delete(@PathVariable(name = "id") String idUser) {
//		MultiValueMap headers = new LinkedMultiValueMap();
//		ResponseEntity<Boolean> response;
//		try {
//			ClientDto user = clientService.findById(idUser);
//			clientService.delete(user.getId());
//			response = new ResponseEntity<>(true, HttpStatus.ACCEPTED);
//			// response = new ResponseEntity<>(true , HttpStatus.ACCEPTED);
//			return response;
//		} catch (D4Exception.EntityNotFoundException e) {
//			log.error(e.getMessage());
//			headers.set("ExceptionMessage", e.getMessage());
//			response = new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
//		}
//		return response;
//	}
//
//}
