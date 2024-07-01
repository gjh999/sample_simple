package egovframework.com.cmm.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.util.ConvertUtil;

/**
 * <b>공공 마이데이터 유통체계 구축 사업</b><br>
 * ApiLogFilterRequestWrapper
 *
 * @author			: EKW
 * @since			: 2020. 10. 27.
 * @modifier 		: GJH
 * @modified 		: 2024.  1. 22.
 * @modification
 * @
 * @ 수정일                    수정자       	수정내용
 * @ -------------	---------	---------------------
 * @ 2020. 10. 27.	EKW	               최초 생성
 * @ 2024.  1. 22.	GJH	        [자체개선] 개발가이드 주석정리
 */
public class EgovRequestWrapper extends HttpServletRequestWrapper {
	
	private HashMap<String, Object> params ;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovRequestWrapper.class);

	@SuppressWarnings("unchecked")
	public EgovRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		this.params  = new HashMap<String, Object>(request.getParameterMap());
		
		byte[] orgByte = IOUtils.toByteArray(request.getInputStream());
		String jsonBody = new String(orgByte);
		HashMap<String, Object> reqMap = ConvertUtil.convertJsonToMap(jsonBody);
		
		String encData = (String) reqMap.get("encData");
		
		setParameter("encData", encData);
	}

	public String getParameter(String name) {
		String returnValue = null;
		String[] paramArray = getParameterValues(name);
		if (paramArray != null && paramArray.length > 0) {
			returnValue =  paramArray[0];
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public Map getParameterMap() {
		return Collections.unmodifiableMap(params);
	}

	@SuppressWarnings("unchecked")
	public Enumeration getParameterNames() {
		return Collections.enumeration(this.params.keySet());
	}

	public String[] getParameterValues(String name) {
		String[] result = null;
		String[] dummyParamValue = (String[]) this.params.get(name);

		if (dummyParamValue != null) {
			result = new String[dummyParamValue.length];
			System.arraycopy(dummyParamValue, 0, result, 0, dummyParamValue.length);
		}
		return result;
	}

	public void setParameter(String name, String value) {
		String[] param = { value };
		setParameter(name, param);
	}

	public void setParameter(String name, String[] values) {
		this.params.put(name, values);
	}
}
