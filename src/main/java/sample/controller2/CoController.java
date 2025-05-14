package sample.controller2;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sample.model.SoliderInfo;
import sample.service.CoService;

@Controller
public class CoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoController.class);
	
	@Resource(name = "coService")
	private CoService coService;
	
	public CoController() {
		LOGGER.info("CoController 생성자");
	}
	
	@RequestMapping("/getSolider.co")
	public ModelAndView getSolider(@ModelAttribute SoliderInfo soliderInfo) {
		LOGGER.info("getSolider.co 호출");
		LOGGER.info(soliderInfo.getSoliderId());
		coService.getSoliderService(soliderInfo.getSoliderId());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:index.jsp");
		return mav;
	}
}
