/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.cmm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovSampleService;
import egovframework.com.cmm.service.SampleDefaultVO;
import egovframework.com.cmm.util.ConvertUtil;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("sampleService")
public class EgovSampleServiceImpl extends EgovAbstractServiceImpl implements EgovSampleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSampleServiceImpl.class);

	/** SampleDAO */
	// TODO ibatis 사용
	//@Resource(name = "sampleDAO")
	//private SampleDAO sampleDAO;
	// TODO mybatis 사용
	//  @Resource(name="sampleMapper")
	//	private SampleMapper sampleDAO;
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectTestDataList(String testData) throws Exception {

		/* ====================== 제3자 제공현황 조회 =======================================*/
    	Map<String, Object> outputMap = new HashMap<String, Object>();
    	SampleDefaultVO searchVO = new SampleDefaultVO();
		// 제3자 제공현황 조회
		//List<?> resultList = sampleDAO.selectSampleList(searchVO);
		
		/* ====================== 더미 데이터 =======================================*/
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	
    	Map<String, Object >mapData1 = new HashMap<String,Object>();
    	mapData1.put("pvsnDt", 			"20240213");
    	mapData1.put("rcptnInstCdNm", 	"교육부_target2");
    	mapData1.put("pvsnPrpsCtt",		"정보주체 신청 및 실적정보 제공");
    	mapData1.put("pvsnInfoCtt", 	"봉사 실적 정보");
    	mapData1.put("pvsnBasis", 		"개인정보보호법 및 자원봉사활동 기본법");
    	
    	list.add(mapData1);
    	
    	Map<String, Object >mapData2 = new HashMap<String,Object>();
    	mapData2.put("pvsnDt", 			"20240206");
    	mapData2.put("rcptnInstCdNm", 	"보건복지부_target2");
    	mapData2.put("pvsnPrpsCtt",		"정보주체 신청 및 실적정보 제공");
    	mapData2.put("pvsnInfoCtt", 	"봉사 실적 정보");
    	mapData2.put("pvsnBasis", 		"개인정보보호법 및 자원봉사활동 기본법");
    	
    	list.add(mapData2);
    	
    	Map<String, Object >mapData3 = new HashMap<String,Object>();
    	mapData3.put("pvsnDt", 			"20230102");
    	mapData3.put("rcptnInstCdNm", 	"교육부_target2");
    	mapData3.put("pvsnPrpsCtt",		"정보주체 신청 및 실적정보 제공");
    	mapData3.put("pvsnInfoCtt", 	"봉사 실적 정보");
    	mapData3.put("pvsnBasis", 		"개인정보보호법 및 자원봉사활동 기본법");
    	
    	list.add(mapData3);	
    	
    	String jsonData = ConvertUtil.convertListToJsonString(list);
		//String jsonData = ConvertUtil.convertListToJsonString((List<Map<String, Object>>) list);
		
		outputMap.put("rspnsCode", "100");
		outputMap.put("rspnsMssage", "정상 처리되었습니다.111");
		outputMap.put("result", jsonData);
		
		return outputMap;		
	}
}
