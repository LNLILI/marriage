package com.marriage.information.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.marriage.information.entity.WomanInformation;
import com.marriage.information.service.InformationService;
import com.marriage.uitl.PhotoUtil;
import com.marriage.user.entity.ManInformation;


@Controller
@RequestMapping("/woman")
public class InformationController {
	
	@Autowired
	private InformationService informationService;
	
	
	
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)//TODO  post
	@ResponseBody
	public Map<String, Object> addWomanInformation(@RequestParam("avatar") MultipartFile file,@RequestParam Map<String,Object> map,HttpServletRequest request){
		int num = 0;
		Map<String, Object> replyMap = new HashMap<String,Object>();
		if (map.containsKey("trueName") && map.containsKey("country")) { 
			String avatar = PhotoUtil.saveFile(file,request);
			replyMap.put("avatar",avatar);
			num = informationService.addWomanInformation(map);
		}
		
		if(num > 0){
			replyMap.put("code", "200");
			replyMap.put("state", "success");
		}else{
			replyMap.put("code", "500");
			replyMap.put("state", "error");
		}
		return replyMap;
	}
	
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> selectWomanInformationByPage(@RequestParam Map<String,Object> map){
		Map<String, Object> replyMap = new HashMap<String,Object>();
		//接收的list
		List<WomanInformation> womanList = null;
		try {
		
				//页数
				if(map.get("page") != null && map.get("size") != null){
					//算页
					int page = Integer.parseInt(map.get("page").toString());
					int size = Integer.parseInt(map.get("size").toString());
					if (page <= 0 || size <= 0) {
						replyMap.put("message", "page error");
					}
					int start = size*(page-1);
					map.put("start", start);
					map.put("page", page);
					map.put("size", size);
					
					Integer total = informationService.selectWomanInformationByTotal(map);
					womanList = informationService.selectWomanInformationBypage(map);
					replyMap.put("total",total);
					replyMap.put("womanList", womanList);
					replyMap.put("code", "200");
					replyMap.put("state", "success");
				} else {
					womanList = informationService.selectWomanInformationBypage(map);
					Integer total =informationService.selectWomanInformationByTotal(map);
					replyMap.put("total",total);
					replyMap.put("womanList", womanList);
					replyMap.put("code", "200");
					replyMap.put("state", "success");
				}
		
		} catch (Exception e) {
			replyMap.put("code", "500");
			replyMap.put("state", "error");
		}
		
		
		return replyMap;
	}
	
	
	
	@RequestMapping(value="/update")
	@ResponseBody
	public Integer updateWomanInformationByWomanId(@RequestParam Map<String,Object> map){
		
		if (map.get("womanId") != null && map.size() > 1) {
			
			return informationService.updateWomanInformationByWomanId(map);
		}
		
		return 0;
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Integer deleteWomanById(@RequestParam Map<String,Object> map){
		
		//大概需要取个数组 放 map.ids
		
		if (map.get("userId") != null || map.get("ids") != null) {
			
			//id同样当做数组放进ids里
			if (map.get("userId") != null) {
				
				String id = map.get("userId").toString();
				map.put("ids", id);
			}
			
			
			return informationService.deleteWomanById(map);
		}
		
		return 0;
	}
	
	@RequestMapping(value="/selectWomanInformationById",method=RequestMethod.GET)//TODO  post
	@ResponseBody
	public Map<String, Object>  userSelectById(@RequestParam Map<String,Object> map){
		WomanInformation  womanInformation = null;
		Map<String, Object> replyMap = new HashMap<String,Object>();
		if (map.containsKey("userId") ) {
			womanInformation = informationService.selectWomanInformationById(map);
		}
		if(womanInformation != null){
			replyMap.put("code", "200");
			replyMap.put("state", "success");
			replyMap.put("user", womanInformation);
		}else{
			replyMap.put("code", "500");
			replyMap.put("state", "error");
		}
		return replyMap;
	}
	
}
