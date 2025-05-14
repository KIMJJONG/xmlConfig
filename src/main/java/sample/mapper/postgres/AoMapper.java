package sample.mapper.postgres;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import sample.model.EmployeeInfo;

@Mapper("aoMapper")
public interface AoMapper {
	
	EmployeeInfo getEmployeeInfo(String employeeId);
	
}
