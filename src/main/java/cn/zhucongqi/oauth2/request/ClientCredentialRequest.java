package cn.zhucongqi.oauth2.request;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.request.OAuthRequest;
import cn.zhucongqi.oauth2.clientcredentials.ClientCredentials;

/**
 * ClientCredentialRequest
 * @author Jobsz
 */
public class ClientCredentialRequest extends OAuthRequest{

	public ClientCredentialRequest(HttpServletRequest request) {
		super(request);
		this.setClientClientCredentials(new ClientCredentials());
	}
}
