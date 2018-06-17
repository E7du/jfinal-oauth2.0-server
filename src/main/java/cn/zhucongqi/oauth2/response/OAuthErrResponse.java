/**
 * 
 */
package cn.zhucongqi.oauth2.response;

import cn.zhucongqi.oauth2.base.response.OAuthResponse;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.OAuthError;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class OAuthErrResponse extends OAuthResponse {

	@Override
	protected void init() {
		
	}
	
	public OAuthErrResponse(OAuthValidator validator) {
		super(validator);
	}

	public OAuthErrResponse(OAuthValidator validator, OAuthProblemException e) {
		super(validator);
		this.setError(e.getError()).setErrorDescription(e.getDescription());
	}

	/**
	 * Set Error
	 * @param error
	 */
	public OAuthErrResponse setError(String error) {
		this.putParameter(OAuthError.OAUTH_ERROR, error);
		return this;
	}
	
	/**
	 * Set Error Description
	 * @param errorDecription
	 */
	public OAuthErrResponse setErrorDescription(String errorDecription) {
		this.putParameter(OAuthError.OAUTH_ERROR_DESCRIPTION, errorDecription);
		return this;
	}

}
