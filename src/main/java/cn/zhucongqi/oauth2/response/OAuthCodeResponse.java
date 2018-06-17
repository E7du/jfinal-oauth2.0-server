/**
 * 
 */
package cn.zhucongqi.oauth2.response;

import cn.zhucongqi.oauth2.base.response.OAuthResponse;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.OAuthConsts;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class OAuthCodeResponse extends OAuthResponse {

	public OAuthCodeResponse(OAuthValidator validator) {
		super(validator);
	}

	/**
	 * Set code
	 * @param code
	 */
	public OAuthCodeResponse setCode(String code) {
		this.putParameter(OAuthConsts.OAuth.OAUTH_CODE, code);
		return this;
	}
}
