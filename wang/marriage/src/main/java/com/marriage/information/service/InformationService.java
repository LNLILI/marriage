package com.marriage.information.service;

import java.util.List;
import java.util.Map;

import com.marriage.information.entity.WomanInformation;
import com.marriage.user.entity.ManInformation;

public interface InformationService {

	Integer addWomanInformation(Map<String,Object> map);

	List<WomanInformation> selectWomanInformationBypage(Map<String, Object> map);

	Integer updateWomanInformationByWomanId(Map<String, Object> map);

	Integer deleteWomanById(Map<String, Object> map);

	Integer selectWomanInformationByTotal(Map<String, Object> map);

}
