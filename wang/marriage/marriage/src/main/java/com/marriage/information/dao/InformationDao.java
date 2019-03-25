package com.marriage.information.dao;

import java.util.List;
import java.util.Map;

import com.marriage.information.entity.WomanInformation;
import com.marriage.user.entity.ManInformation;

public interface InformationDao {

	Integer addWomanInformation(Map<String, Object> map);

	List<WomanInformation> selectWomanInformationBypage(Map<String, Object> map);

	Integer updateWomanInformationByWomanId(Map<String, Object> map);

	Integer updateWomanDelFlagByIds(Map<String, Object> map);

	Integer selectWomanInformationByTotal(Map<String, Object> map);
	
	//通过ID查询女性详情
	WomanInformation selectWomanInformationById(Map<String, Object> map);

}
