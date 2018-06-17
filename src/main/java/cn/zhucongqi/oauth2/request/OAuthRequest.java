/**
 * 
 */
package cn.zhucongqi.oauth2.request;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.clientcredentials.OAuthClientCredentials;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.clientcredentials.AccessTokenClientCredentials;
import cn.zhucongqi.oauth2.clientcredentials.AuthorizationClientCredentials;
import cn.zhucongqi.oauth2.clientcredentials.ClientCredentials;
import cn.zhucongqi.oauth2.clientcredentials.ImplicitClientCredentials;
import cn.zhucongqi.oauth2.clientcredentials.PasswordClientCredentials;
import cn.zhucongqi.oauth2.clientcredentials.RefreshTokenClientCredentials;
import cn.zhucongqi.oauth2.consts.OAuthRequestConsts;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.validators.AccessTokenRequestValidator;
import cn.zhucongqi.oauth2.validators.AuthorizationRequestValidator;
import cn.zhucongqi.oauth2.validators.ClientCredentialValidator;
import cn.zhucongqi.oauth2.validators.ImplicitValidator;
import cn.zhucongqi.oauth2.validators.PasswordCredentialValidator;
import cn.zhucongqi.oauth2.validators.RefreshTokenValidator;

/**
 * @author Jobsz
 */
public class OAuthRequest {

	private HttpServletRequest request = null;
	private OAuthValidator validator = null;
	
	/**
	 * init oauth request
	 * @param request
	 */
	public OAuthRequest(HttpServletRequest request) {
		this.request = request;
		this.validator = new AccessTokenRequestValidator(request);
	}
	
	/**
	 * init oauth request
	 * @param request
	 * @param reqType: current request type {@link OAuthRequestConsts}
	 */
	public OAuthRequest(HttpServletRequest request, int reqType) {
		
		OAuthClientCredentials clientCredential = null;
		switch (reqType) {
		case OAuthRequestConsts.AUTHORIZATION_REQUEST: {
			this.validator = new AuthorizationRequestValidator(request);
			clientCredential = new AuthorizationClientCredentials();
		}
			break;

		case OAuthRequestConsts.ACCESS_TOKEN_REQUEST: {
			this.validator = new AccessTokenRequestValidator(request);
			clientCredential = new AccessTokenClientCredentials();
		}
			break;
		case OAuthRequestConsts.CLIENT_CREDENTIAL_REQUEST: {
			this.validator = new ClientCredentialValidator(request);
			clientCredential = new ClientCredentials();
		}
			break;
		case OAuthRequestConsts.IMPLICIT_REQUEST: {
			this.validator = new ImplicitValidator(request);
			clientCredential = new ImplicitClientCredentials();
		}
			break;
		case OAuthRequestConsts.PASSOWRD_CREDENTIAL_REQUEST: {
			this.validator = new PasswordCredentialValidator(request);
			clientCredential = new PasswordClientCredentials();
		}
			break;
		case OAuthRequestConsts.REFRESH_TOKEN_REQUEST: {
			this.validator = new RefreshTokenValidator(request);
			clientCredential = new RefreshTokenClientCredentials();
		}
			break;
		}
		
		this.validator.setClientCredentials(clientCredential);
	}
	
	/**
	 * Get current request's validator
	 * @return
	 */
	public OAuthValidator getValidator() {
		return this.validator;
	}

	/**
	 * Get current request
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return this.request;
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
