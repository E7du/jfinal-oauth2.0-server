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

	@Override
	protected void init() {
		this.setAuthorizationCode(this.issuer.authorizationCode());
	}
	
	public OAuthCodeResponse(OAuthValidator validator) {
		super(validator);
	}

	/**
	 * Set code
	 * @param code
	 */
	private OAuthCodeResponse setAuthorizationCode(String code) {
		this.putParameter(OAuthConsts.OAuth.OAUTH_AUTHORIZATION_CODE, code);
		return this;
	}

	public String getAuthorizationCode() {
		return this.getParamter(OAuthConsts.OAuth.OAUTH_AUTHORIZATION_CODE);
	}
}
