/**
 * 
 */
package cn.zhucongqi.oauth2.kit;

import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.kit.StrKit;

import cn.zhucongqi.oauth2.consts.OAuthRequestErrCodes;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public final class OAuthExceptionHandleKit {

	private OAuthExceptionHandleKit() {

	}

	private static OAuthProblemException handleInvalidReqOAuthProblemException(
			String message) {
		return OAuthProblemException.error(OAuthRequestErrCodes.REQUEST_ERR_CODE, message);
	}

	/**
	 * checkout request context type
	 * @param requestContentType
	 * @param requiredContentType
	 * @return
	 */
	public static boolean hasContentType(String requestContentType,
			String requiredContentType) {
		if (StrKit.isBlank(requiredContentType)
				|| StrKit.isBlank(requestContentType)) {
			return false;
		}
		StringTokenizer tokenizer = new StringTokenizer(requestContentType, ";");
		while (tokenizer.hasMoreTokens()) {
			if (requiredContentType.equals(tokenizer.nextToken())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * handle bad content type
	 * @param expectedContentType
	 * @return
	 */
	public static OAuthProblemException handleBadContentTypeException(
			String expectedContentType) {
		StringBuilder errorMsg = new StringBuilder(
				"Bad request content type. Expecting: ")
				.append(expectedContentType);
		return OAuthExceptionHandleKit
				.handleInvalidReqOAuthProblemException(errorMsg.toString());
	}
	
	
	/**
	 * handle miss parameter
	 * @param missingParams
	 * @return
	 */
	public static OAuthProblemException handleMissingParameters(
			Set<String> missingParams) {
		StringBuilder sb = new StringBuilder("Missing parameters: ");
		if (missingParams != null && !missingParams.isEmpty()) {
			for (String missingParam : missingParams) {
				sb.append(missingParam).append(" ");
			}
		}
		return OAuthExceptionHandleKit.handleInvalidReqOAuthProblemException(sb.toString()
				.trim());
	}
	
	/**
	 * handle invalid param default values
	 * @param key paramKey
	 * @param validValue
	 * @return
	 */
	public static OAuthProblemException handleInvalidValueException(String paramKey,
			String validValue) {
		StringBuilder desc = new StringBuilder(
				"Invalid value for ").append(validValue).append(" the valid value is '").append(validValue).append("'");
		return OAuthProblemException
				.error(OAuthRequestErrCodes.REQUEST_ERR_CODE)
				.description(desc.toString().trim())
				.responseStatus(HttpServletResponse.SC_FORBIDDEN);
	}
	
	/**
	 * handle invalid client
	 * @return
	 */
	public static OAuthProblemException handleInvalidClientException() {
		return OAuthProblemException
				.error(OAuthRequestErrCodes.REQUEST_ERR_CODE)
				.description("Invalid Client")
				.responseStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
