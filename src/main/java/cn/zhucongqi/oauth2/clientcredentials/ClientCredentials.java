package cn.zhucongqi.oauth2.clientcredentials;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.clientcredentials.OAuthClientCredentials;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * ClientCredentials
 * @author Jobsz
 *
 */
public class ClientCredentials implements OAuthClientCredentials {

	@Override
	public void validateClientCredentials(HttpServletRequest request, OAuthValidator validator) throws OAuthProblemException {
		//TODO build your own client credentials code in here
	}
	
}
