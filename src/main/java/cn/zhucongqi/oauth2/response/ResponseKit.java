/**
 * 
 */
package cn.zhucongqi.oauth2.response;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class ResponseKit {
	
	public static CodeResponse codeRep(HttpServletRequest request) {
		return (new CodeResponse(request));
	}
	
	public static AccessToken tokenRep(HttpServletRequest request) {
		return (new AccessToken(request));
	}

	public static ErrorResponse errorRep(HttpServletRequest request, OAuthProblemException e) {
		return (new ErrorResponse(request,e));
	}
}
