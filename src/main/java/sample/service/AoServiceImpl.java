package sample.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sample.mapper.postgres.AoMapper;
import sample.model.EmployeeInfo;

@Service("aoService")
public class AoServiceImpl implements AoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AoServiceImpl.class);
	
	@Resource(name = "aoMapper")
	private AoMapper aoMapper;
	
	public AoServiceImpl() {
		LOGGER.info("AoServiceImpl 생성자");
	}
	
	public void getEmployeeService(String employeeId) {
		LOGGER.info("getEmployeeService 실행");
		EmployeeInfo info = aoMapper.getEmployeeInfo(employeeId);
		LOGGER.info("Employee 이름: {}", info.getEmployeeName());
	}
	
}
