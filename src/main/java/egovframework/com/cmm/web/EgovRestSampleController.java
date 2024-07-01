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
package egovframework.com.cmm.web;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.filter.EgovRequestWrapper;
import egovframework.com.cmm.service.EgovSampleService;
import egovframework.com.cmm.util.ConvertUtil;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
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

@RestController
public class EgovRestSampleController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;
	
	private ObjectMapper objectMapper =  new ObjectMapper();
	
	@RequestMapping(value = "/api/testApi01.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> testApi01(HttpServletRequest request) throws Exception {
		
		System.out.println("testApi01 테스트 시작 (target2)");
		
		EgovRequestWrapper m = new EgovRequestWrapper(request);
		request = (HttpServletRequest) m;
		
		String testData = request.getParameter("testData");
		System.out.println("testData = " + testData);

		Map<String, Object> resultMap = sampleService.selectTestDataList(testData);

		System.out.println("testApi01 테스트 끝222 (target2)");
		
		String rspnsCode 	= resultMap.get("rspnsCode").toString();
		String rspnsMssage 	= resultMap.get("rspnsMssage").toString();
		String rspnsResult = resultMap.get("result").toString();
		
		String result = "";
		
		/* 모두 String Return 방식 */
		Map<String, Object> outputMap = new HashMap<String, Object>();
		outputMap.put("result", rspnsResult);
		outputMap.put("rspnsCode", rspnsCode);
		outputMap.put("rspnsMssage", rspnsMssage);
		
		result = ConvertUtil.convertMapToJsonString(outputMap);
		
		/* 선택적 String Return 방식
		if(StringUtils.isEmpty(rspnsEncData)) {
			result = "code : " + rspnsCode + " / mssage : " + rspnsMssage;
		} else {
			result = rspnsEncData;
		}
		*/
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/log/saveLogFilter.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> saveLogFilter(HttpServletRequest request) throws Exception {
		
		//System.out.println("saveLogFilter 테스트 시작33");
		
		//objectMapper
		
		ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        //System.out.println("messageBody = " + messageBody);
        
        JSONParser jsonParser = new JSONParser();
        
        Object obj = jsonParser.parse(messageBody);
        
        JSONObject jsonObj = (JSONObject) obj;
        /*
        System.out.println("service = " + jsonObj.get("service")); //service
        System.out.println("route = " + jsonObj.get("route")); //service
        System.out.println("request = " + jsonObj.get("request")); //request
        System.out.println("response = " + jsonObj.get("response")); //response
        System.out.println("latencies" + jsonObj.get("latencies")); //latencies
        System.out.println("tries = " + jsonObj.get("tries")); //tries
        System.out.println("client_ip = " + jsonObj.get("client_ip")); //client_ip
        System.out.println("workspace = " + jsonObj.get("workspace")); //workspace
        System.out.println("workspace_name" + jsonObj.get("workspace_name")); //workspace_name
        System.out.println("upstream_uri" + jsonObj.get("upstream_uri")); //upstream_uri
        System.out.println("authenticated_entity" + jsonObj.get("authenticated_entity")); //authenticated_entity
        System.out.println("consumer = " + jsonObj.get("consumer")); //consumer
        */
        long started_at = (long) jsonObj.get("started_at");
        
        System.out.println("started_at (Timestamp) = " + started_at); //started_at
        System.out.println("(Local Time) = " + ConvertUtil.getDateStr(started_at, "")); //started_at
        System.out.println("(UTC Time) = " + ConvertUtil.getUtcDateStr(started_at, "")); //started_at 
          

		//System.out.println("saveLogFilter 로그저장  끝\n");
		
		/*String rspnsCode 	= resultMap.get("rspnsCode").toString();
		String rspnsMssage 	= resultMap.get("rspnsMssage").toString();
		String rspnsResult = resultMap.get("result").toString();*/
		
		String result = "";
		
		/* 모두 String Return 방식 */
		/*Map<String, Object> outputMap = new HashMap<String, Object>();
		outputMap.put("result", rspnsResult);
		outputMap.put("rspnsCode", rspnsCode);
		outputMap.put("rspnsMssage", rspnsMssage);
		
		result = ConvertUtil.convertMapToJsonString(outputMap);*/
		
		/* 선택적 String Return 방식
		if(StringUtils.isEmpty(rspnsEncData)) {
			result = "code : " + rspnsCode + " / mssage : " + rspnsMssage;
		} else {
			result = rspnsEncData;
		}
		*/
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
		
	}

}
