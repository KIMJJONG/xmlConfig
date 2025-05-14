package sample.mapper.sqlite;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import sample.model.SoliderInfo;

@Mapper("coMapper")
public interface CoMapper {
	
	SoliderInfo getSoliderInfo(String soliderId);
	
}
