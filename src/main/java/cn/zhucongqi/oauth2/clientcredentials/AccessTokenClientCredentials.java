package cn.zhucongqi.oauth2.clientcredentials;

import cn.zhucongqi.oauth2.base.clientcredentials.OAuthClientCredentials;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * AccessTokenClientCredentials
 * @author Jobsz
 *
 */
public class AccessTokenClientCredentials implements OAuthClientCredentials {

	@Override
	public Object validateClientCredentials(OAuthValidator validator) throws OAuthProblemException {
		//TODO build your own OAuthAccessToken client credentials code in here
		return null;
	}
	
}
