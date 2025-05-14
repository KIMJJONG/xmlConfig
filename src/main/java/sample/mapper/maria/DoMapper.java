package sample.mapper.maria;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import sample.model.StudentInfo;

@Mapper("doMapper")
public interface DoMapper {
	
	StudentInfo getStudentInfo(String studentId);
	
}
