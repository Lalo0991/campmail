package mx.com.d4.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.com.d4.services.IUtilities;
import mx.com.d4.services.impl.ClientService;
import mx.com.d4.services.impl.ParameterConfigService;
import mx.com.d4.services.impl.RequestService;

@Controller
@RequestMapping(path = "/dashboard")
public class DashboardController {

	@Autowired
	IUtilities utilitiesService;
	@Autowired
	ClientService clientService;
	@Autowired
	ParameterConfigService parameterConfigService;
	@Autowired
	RequestService requestService;
	
	private final Log log = LogFactory.getLog(this.getClass());	
	
	@GetMapping(path = "/get")
	public ModelAndView getDashboardView() {
		ModelAndView mav;
		log.info("DashboardController.getDashboardView");		
		mav = new ModelAndView("admin/dashboard");
		return mav;
	}
	
	@GetMapping(path = "/")
	public ModelAndView getDashboardInfo() {
		ModelAndView mav;
		log.info("DashboardController.getDashboardInfo");		
		mav = new ModelAndView("admin/dashboard");
		
		
		
		return mav;
	}
	
}
