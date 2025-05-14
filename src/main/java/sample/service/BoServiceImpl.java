package sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("boService")
public class BoServiceImpl implements BoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BoServiceImpl.class);
	
	public BoServiceImpl() {
		LOGGER.info("BoServiceImpl 생성자");
	}
	
}
