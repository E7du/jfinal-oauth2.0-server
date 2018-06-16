package cn.zhucongqi.oauth2.clientcredentials;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.clientcredentials.OAuthClientCredentials;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * ImplicitClientCredentials
 * @author Jobsz
 *
 */
public class ImplicitClientCredentials implements OAuthClientCredentials {

	@Override
	public void validateClientCredentials(HttpServletRequest request) throws OAuthProblemException {
		//TODO build your own ImplicitClientCredentials client credentials code in here
	}
	
}
