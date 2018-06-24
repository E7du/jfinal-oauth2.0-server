package cn.zhucongqi.oauth2.clientcredentials;

import cn.zhucongqi.oauth2.base.clientcredentials.OAuthClientCredentials;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * AuthorizationClientCredentials
 * @author Jobsz
 *
 */
public class AuthorizationClientCredentials implements OAuthClientCredentials {

	@Override
	public Object validateClientCredentials(OAuthValidator validator) throws OAuthProblemException {
		//TODO build your own Authorization client credentials code in here
		return null;
	}
	
}
