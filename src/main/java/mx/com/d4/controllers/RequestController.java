package mx.com.d4.controllers;

import static mx.com.d4.models.RequestType.CANCEL;
import static mx.com.d4.models.RequestType.CONTACT;
import static mx.com.d4.models.RequestType.REQ_CANCEL;
import static mx.com.d4.models.RequestType.REQ_CONTACT;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mx.com.d4.dto.RequestDto;
import mx.com.d4.dto.mapper.RequestMapper;
import mx.com.d4.exceptions.D4Exception;
import mx.com.d4.models.Request;
import mx.com.d4.services.IClientService;
import mx.com.d4.services.IRequestService;
import mx.com.d4.services.IUtilities;

@Controller
@RequestMapping(path = "/requestInfo")
public class RequestController {

	@Autowired
	IRequestService requestService;

	@Autowired
	IClientService clienteService;

	@Autowired
	IUtilities utilitiesService;

	private final Log log = LogFactory.getLog(this.getClass());

	@GetMapping(path = "/new")
	public ModelAndView getNewRequestView(@RequestParam("idClient") String idClient) {

		RequestDto request = new RequestDto().setIdClient(idClient).setStatus(REQ_CONTACT.toString()).setReason(CONTACT.toString());
		ModelAndView mav;

		try {
			request = requestService.save(request);
			log.info(String.format("RequestController.getNewRequestView: %s  ", request.toString()));
			utilitiesService.sendEmail(request);
			mav = new ModelAndView("request/index");

		} catch (D4Exception.EntityNotFoundException e) {
			mav = new ModelAndView("request/error");
			log.error(String.format("RequestController.getNewRequestView: %s ", e.getMessage()));

		} catch (D4Exception.DuplicateEntityException e) {
			mav = new ModelAndView("request/repeat");
			log.warn(String.format("RequestController.getNewRequestView: %s ", e.getMessage()));
		}

		return mav;
	}

	@GetMapping(path = "/cancel")
	public ModelAndView cancelRequestView(@RequestParam("idClient") String idClient, @ModelAttribute(name = "req") Request req) {

		RequestDto request = new RequestDto().setIdClient(idClient).setStatus(REQ_CANCEL.toString()).setReason(CANCEL.toString());
		ModelAndView mav;

		try {
			request = requestService.save(request);
			log.info(String.format("RequestController.cancelRequestView: %s  ", request.toString()));
			mav = new ModelAndView("request/baja");

		} catch (D4Exception.EntityNotFoundException e) {
			mav = new ModelAndView("request/error");
			log.error(String.format("RequestController.cancelRequestView: %s ", e.getMessage()));

		} catch (D4Exception.DuplicateEntityException e) {
			mav = new ModelAndView("request/repeat");
			log.warn(String.format("RequestController.getNewRequestView: %s ", e.getMessage()));
		}

		return mav;
	}

	@PostMapping(path = "/cancelReason")
	public String cancelReasonRequestView(@ModelAttribute(name = "req") Request req) {

		log.info(String.format("RequestController.cancelReasonRequestView: %s ", req.toString()));		
		RequestDto request = new RequestDto();

		try {
			request = RequestMapper.toRequestDTO(req);
			request.setStatus(REQ_CANCEL.toString());
			log.info(String.format("RequestController.cancelReasonRequestView: %s  ", request.toString()));
			requestService.update(request);		

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/requestInfo/enddown";
	}

	@GetMapping(path = "/enddown")
	public ModelAndView endDown() {

		ModelAndView mav = new ModelAndView("request/enddown");

		return mav;
	}
}
