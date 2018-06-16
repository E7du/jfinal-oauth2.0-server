/**
 * 
 */
package cn.zhucongqi.oauth2.response;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.consts.OAuthError;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * error : ErrorConsts.CodeResponse <br/>
 * error_description : the erros repsonse description <br/>
 * scope : The scope of the access request; <br/>  
 * state : The exact value received from the
         client.
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class ErrorResponse extends Response {

	public ErrorResponse(HttpServletRequest request) {
		super(request);
	}

	public ErrorResponse(HttpServletRequest request, OAuthProblemException e) {
		super(request);
		this.setError(e.getError());
		this.setErrorDescription(e.getDescription());
	}

	/**
	 * Set Error
	 * @param error
	 */
	public ErrorResponse setError(String error) {
		this.params.put(OAuthError.OAUTH_ERROR, error);
		return this;
	}
	
	/**
	 * Set Error Description
	 * @param errorDecription
	 */
	public ErrorResponse setErrorDescription(String errorDecription) {
		this.params.put(OAuthError.OAUTH_ERROR_DESCRIPTION, errorDecription);
		return this;
	}
}
