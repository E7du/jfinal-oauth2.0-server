package cn.zhucongqi.oauth2.request;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.request.OAuthRequest;
import cn.zhucongqi.oauth2.clientcredentials.PasswordClientCredentials;

/**
 * PasswordCredentialRequest
 * @author Jobsz
 */
public class PasswordCredentialRequest extends OAuthRequest{

	public PasswordCredentialRequest(HttpServletRequest request) {
		super(request);
		this.setClientClientCredentials(new PasswordClientCredentials());
	}
}
