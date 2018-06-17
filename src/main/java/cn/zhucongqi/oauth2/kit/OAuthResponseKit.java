/**
 * 
 */
package cn.zhucongqi.oauth2.kit;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.response.AccessToken;
import cn.zhucongqi.oauth2.response.CodeResponse;
import cn.zhucongqi.oauth2.response.ErrorResponse;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public final class OAuthResponseKit {
	
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
