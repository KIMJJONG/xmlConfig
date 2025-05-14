package sample.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sample.model.EmployeeInfo;
import sample.service.AoService;

@Controller
public class AoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AoController.class);
	
	@Resource(name = "aoService")
	private AoService aoService;
	
	public AoController() {
		LOGGER.info("aoController 생성자");
	}
	
	@RequestMapping("/getEmployee.ao")
	public ModelAndView getEmployee(@ModelAttribute EmployeeInfo employeeInfo) {
		LOGGER.info("getEmployee.ao 호출");
		LOGGER.info(employeeInfo.getEmployeeId());
		aoService.getEmployeeService(employeeInfo.getEmployeeId());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:index.jsp");
		return mav;
	}
	
}
