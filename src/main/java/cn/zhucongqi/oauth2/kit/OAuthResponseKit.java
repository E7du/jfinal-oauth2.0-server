/**
 * 
 */
package cn.zhucongqi.oauth2.kit;

import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.response.OAuthAccessToken;
import cn.zhucongqi.oauth2.response.OAuthCodeResponse;
import cn.zhucongqi.oauth2.response.OAuthErrResponse;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public final class OAuthResponseKit {
	
	public static OAuthCodeResponse codeResp(OAuthValidator validator) {
		return (new OAuthCodeResponse(validator));
	}
	
	public static OAuthAccessToken tokenResp(OAuthValidator validator) {
		return (new OAuthAccessToken(validator));
	}

	public static OAuthErrResponse errorResp(OAuthValidator validator, OAuthProblemException e) {
		return (new OAuthErrResponse(validator, e));
	}
}
