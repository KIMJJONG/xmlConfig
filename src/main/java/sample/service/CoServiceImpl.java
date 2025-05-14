package sample.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sample.mapper.sqlite.CoMapper;
import sample.model.SoliderInfo;

@Service("coService")
public class CoServiceImpl implements CoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoServiceImpl.class);
	
	@Resource(name = "coMapper")
	private CoMapper coMapper;
	
	public CoServiceImpl() {
		LOGGER.info("CoServiceImpl 생성자");
	}
	
	public void getSoliderService(String soliderId) {
		LOGGER.info("getSoliderService 실행");
		SoliderInfo info = coMapper.getSoliderInfo(soliderId);
		LOGGER.info("Solider 이름: {}", info.getSoliderName());
	}
	
}
