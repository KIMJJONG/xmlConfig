package sample.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sample.mapper.maria.DoMapper;
import sample.model.StudentInfo;

@Service("doService")
public class DoServiceImpl implements DoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DoServiceImpl.class);
	
	@Resource(name = "doMapper")
	private DoMapper doMapper;
	
	public DoServiceImpl() {
		LOGGER.info("BoServiceImpl 생성자");
	}
	
	public void getStudentService(String studentId) {
		LOGGER.info("getStudentService 실행");
		StudentInfo info = doMapper.getStudentInfo(studentId);
		LOGGER.info("Student 이름: {}", info.getStudentName());
	}
	
}
