package mx.com.d4.controllers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mx.com.d4.services.IUtilities;

@Controller
@RequestMapping(path = "/campanias")
public class CampaniasController {

	@Autowired
	IUtilities utilitiesService;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	private final Log log = LogFactory.getLog(this.getClass());

	@GetMapping(path = "/get")
	public ModelAndView getCampaniasView() {
		ModelAndView mav;
		log.info("CampaniasController.getCampaniasView");
		mav = new ModelAndView("admin/campanias");
		return mav;
	}

	@PostMapping(value = "/uploadToDB")
	public String create(@RequestParam("file") MultipartFile multipartFile) throws Exception {

		String path = new ClassPathResource("tmpuploads/").getURL().getPath();
		// path = FilenameUtils.getPath(path);
		log.info("PATH: " + path);
		File fileToImport = new File(path + multipartFile.getOriginalFilename());
		log.info("PATHFILE: " + path + multipartFile.getOriginalFilename());		
		multipartFile.transferTo(fileToImport);
		perform(fileToImport);
				

		return "redirect:/campanias/get";
	}

	public void perform(File file) throws Exception {
		JobParameters params = new JobParametersBuilder()
				.addString("fullPathFileName" , String.valueOf(file.getAbsolutePath())).toJobParameters();
		jobLauncher.run(job, params);
	}

	@GetMapping(path = "/programar")
	public ModelAndView getProgramarView() {
		ModelAndView mav;
		log.info("CampaniasController.getProgramarView");
		mav = new ModelAndView("admin/programar");
		return mav;
	}

}
