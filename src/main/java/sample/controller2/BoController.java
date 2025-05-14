package sample.controller2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class BoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BoController.class);
	
	public BoController() {
		LOGGER.info("BoController 생성자");
	}
	
}
