package cn.zhucongqi.oauth2.request;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.request.OAuthRequest;
import cn.zhucongqi.oauth2.clientcredentials.RefreshTokenClientCredentials;

/**
 * RefreshTokenRequest
 * @author Jobsz
 */
public class RefreshTokenRequest extends OAuthRequest{

	public RefreshTokenRequest(HttpServletRequest request) {
		super(request);
		this.setClientClientCredentials(new RefreshTokenClientCredentials());
	}
}
