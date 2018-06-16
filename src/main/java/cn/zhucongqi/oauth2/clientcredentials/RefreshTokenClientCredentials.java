package cn.zhucongqi.oauth2.clientcredentials;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * RefreshTokenClientCredentials
 * @author Jobsz
 *
 */
public class RefreshTokenClientCredentials implements OAuthClientCredentials {

	@Override
	public void validateClientCredentials(HttpServletRequest request) throws OAuthProblemException {
		//TODO build your own RefreshTokenClientCredentials client credentials code in here
	}
	
}
