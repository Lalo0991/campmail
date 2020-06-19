package mx.com.d4.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.com.d4.services.IUtilities;

@Controller
@RequestMapping(path = "/campanias")
public class CampaniasController {
	
	@Autowired
	IUtilities utilitiesService;
	
	private final Log log = LogFactory.getLog(this.getClass());	
	
	@GetMapping(path = "/get")
	public ModelAndView getCampaniasView() {
		ModelAndView mav;
		log.info("CampaniasController.getCampaniasView");		
		mav = new ModelAndView("admin/campanias");
		return mav;
	}
	
	@GetMapping(path = "/programar")
	public ModelAndView getProgramarView() {
		ModelAndView mav;
		log.info("CampaniasController.getProgramarView");		
		mav = new ModelAndView("admin/programar");
		return mav;
	}


}
