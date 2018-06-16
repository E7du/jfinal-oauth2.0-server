package cn.zhucongqi.oauth2.request;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.request.OAuthRequest;
import cn.zhucongqi.oauth2.clientcredentials.AuthorizationClientCredentials;

/**
 * AuthorizationRequest
 * @author Jobsz
 */
public class AuthorizationRequest extends OAuthRequest{

	public AuthorizationRequest(HttpServletRequest request) {
		super(request);
		this.setClientClientCredentials(new AuthorizationClientCredentials());
	}
}
