package sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sample.model.StudentInfo;
import sample.service.DoService;

@Controller
public class DoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DoController.class);
	
	@Autowired
	private DoService doService;
	
	public DoController() {
		LOGGER.info("DoController 생성자");
	}
	
	@RequestMapping("/getStudent.do")
	public ModelAndView getStudent(@ModelAttribute StudentInfo studentInfo) {
		LOGGER.info("getStudent.do 호출");
		LOGGER.info(studentInfo.getStudentId());
		doService.getStudentService(studentInfo.getStudentId());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:index.jsp");
		return mav;
	}
}
