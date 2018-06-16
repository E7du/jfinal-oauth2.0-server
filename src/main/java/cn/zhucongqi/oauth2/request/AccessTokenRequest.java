package cn.zhucongqi.oauth2.request;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.request.OAuthRequest;
import cn.zhucongqi.oauth2.clientcredentials.AccessTokenClientCredentials;

/**
 * AccessTokenRequest
 * @author Jobsz
 */
public class AccessTokenRequest extends OAuthRequest{

	public AccessTokenRequest(HttpServletRequest request) {
		super(request);
		this.setClientClientCredentials(new AccessTokenClientCredentials());
	}
}
