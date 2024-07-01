/**
 *
 */
package egovframework.com.cmm.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * <b>공공 마이데이터 유통체계 구축 사업</b><br>
 * ConvertUtil
 *
 * @author			: LJY
 * @since			: 2020. 10. 26.
 * @modifier 		: GJH
 * @modified 		: 2024.  1. 22.
 * @modification
 * @
 * @ 수정일                    수정자       	수정내용
 * @ -------------	---------	---------------------
 * @ 2020. 10. 26.	LJY	               최초 생성
 * @ 2024.  1. 22.	GJH	        [자체개선] 개발가이드 주석정리
 */
public class ConvertUtil {
	
	public static HashMap<String, Object> convertJsonToMap(String body) throws JsonParseException, JsonMappingException, IOException {

		if (body == null || body.isEmpty()) {
			return new HashMap<>();
		}

		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map = mapper.readValue(body, new TypeReference<Map<String, Object>>(){});

        return map;
	}

	/*
	public static String convertVoToJsonString(BaseResponseVO baseResponseVO) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(baseResponseVO);
        return jsonString;
	}
	*/

	public static String convertMapToJsonString(Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(map);
        return jsonString;
	}

	public static String convertListToJsonString(List<Map<String, Object>> list) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(list);
        return jsonString;
	}
	
	/**
	 * 지정한 타임스탬프를 지정한 날짜시간 포맷의 문자열로 리턴하기
	 */
	public static String getDateStr(long timeStamp, String format) throws JsonParseException, JsonMappingException, IOException {
		if (StringUtils.isEmpty(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		Date date = new Date(timeStamp);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	/**
	 * 지정한 타임스탬프를 지정한 날짜시간 포맷의 문자열로 리턴하기
	 */
	public static String getUtcDateStr(long timeStamp, String format) throws JsonParseException, JsonMappingException, IOException {
		if (StringUtils.isEmpty(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		Date date = new Date(timeStamp);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formatter.format(date);
	}
	
	/*
	public void getConvert(ParameterMap parameterMap) throws Exception {
	    //31/3/2015 4:01:59 PM ---> yyyy-MM-dd HH:mm:ss 변환
	    logger.debug("TIMES " + convertToDate("31/3/2015 4:44:30 PM"));
	    logger.debug("TIMES " + utcToLocaltime(convertToDate("31/3/2015 4:44:30 PM")));
	}
	*/
	
	/* convertToDate
	 * @param datetime
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss") HH 대문자 : 24 Hour
	 * 소문자 : 12 Hour  
	 */
	public static String convertToDate(String inputDate) throws Exception{
	    String dateTime = inputDate;
	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.KOREA);
	    Date parseDate = null;
	    String convertedDate = null;
		 
	    try {
	        parseDate = format.parse(dateTime);
	        DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        convertedDate = dateFormatNeeded.format(parseDate);  
	    } catch (ParseException e) {
	        //logger.error("error " + e);
	        throw new Exception(e);
	    }
		
	    return convertedDate;
	}
	
}
