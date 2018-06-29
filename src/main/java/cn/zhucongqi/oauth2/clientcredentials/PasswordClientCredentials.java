package cn.zhucongqi.oauth2.clientcredentials;

import cn.zhucongqi.oauth2.base.clientcredentials.OAuthClientCredentials;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.kit.OAuthResponseKit;
import cn.zhucongqi.oauth2.response.OAuthAccessToken;

/**
 * PasswordClientCredentials
 * @author Jobsz
 *
 */
public class PasswordClientCredentials implements OAuthClientCredentials {

	@Override
	public Object validateClientCredentials(OAuthValidator validator) throws OAuthProblemException {
		//TODO build your own Password client credentials code in here	
		 OAuthAccessToken accessToken =  OAuthResponseKit.tokenResp(validator);
		 //put other paramters
		 accessToken.putExtenstionParameter("other", "other value");
		 return accessToken.parameters();
	}

}
