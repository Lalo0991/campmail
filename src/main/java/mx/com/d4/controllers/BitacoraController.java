package mx.com.d4.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.com.d4.models.LogCamp;
import mx.com.d4.services.IUtilities;
import mx.com.d4.services.impl.LogCampService;

@Controller
@RequestMapping(path = "/bitacora")
public class BitacoraController {
	

	@Autowired
	IUtilities utilitiesService;
	@Autowired
	LogCampService logCampService;
	
	private final Log log = LogFactory.getLog(this.getClass());	
	
	@GetMapping(path = "/get")
	public ModelAndView getBitacoraView() {
		ModelAndView mav;
		log.info("BitacoraController.getBitacoraView");		
		mav = new ModelAndView("admin/bitacora");
		return mav;
	}
	
	
	@GetMapping(value = "/get/page/{page}")
	public ModelAndView getPagedLogs(@PathVariable("page") int page) {
		ModelAndView mav;
		log.info("BitacoraController.getPagedLogs");
		mav = new ModelAndView("admin/bitacora");
		PageRequest pageable = PageRequest.of(page - 1, 5);		
		Page<LogCamp> logsPage = logCampService.getAll(pageable);
		Integer totalPages = logsPage.getTotalPages();
		mav.addObject("logsPage", logsPage);
		if(totalPages>0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            mav.addObject("pageNumbers", pageNumbers);
		}
		
		mav.addObject("activeLogList", true);
        mav.addObject("logList", logsPage.getContent());
		return mav;
	}

}
