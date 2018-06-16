/**
 * 
 */
package cn.zhucongqi.oauth2.base.request;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.clientcredentials.OAuthClientCredentials;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.validators.AccessTokenRequestValidator;

/**
 * @author Jobsz
 */
public class OAuthRequest {

	private OAuthValidator validator = null;
	
	/**
	 * Set client's using credentials
	 * @param clientCredentials
	 */
	protected void setClientClientCredentials(OAuthClientCredentials clientCredentials) {
		this.validator.setClientCredentials(clientCredentials);
	}

	public OAuthRequest(HttpServletRequest request) {
		this.validator = new AccessTokenRequestValidator(request);
	}
	
	/**
	 * Get current request's validator
	 * @return
	 */
	public OAuthValidator getValidator() {
		return this.validator;
	}

	 /**
     * validate request
     * 
     * @throws OAuthProblemException
     */
    public void validate() throws OAuthProblemException {
    	this.validator.validate();
    }
}
