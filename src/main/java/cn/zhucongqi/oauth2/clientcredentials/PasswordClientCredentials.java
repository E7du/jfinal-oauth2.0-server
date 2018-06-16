package cn.zhucongqi.oauth2.clientcredentials;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;

import cn.zhucongqi.oauth2.consts.OAuthConsts;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.kit.OAuthExceptionHandleKit;

/**
 * PasswordClientCredentials
 * @author Jobsz
 *
 */
public class PasswordClientCredentials implements OAuthClientCredentials {

	@Override
	public void validateClientCredentials(HttpServletRequest request) throws OAuthProblemException {
		String client_id = request.getParameter(OAuthConsts.OAuth.OAUTH_CLIENT_ID);
		
		String username = request.getParameter(OAuthConsts.OAuth.OAUTH_USERNAME);
		if (StrKit.isBlank(username) || !client_id.equals(HashKit.md5(username))) {
			throw OAuthExceptionHandleKit.handleInvalidClientException();
		}

		String client_secret = request.getParameter(OAuthConsts.OAuth.OAUTH_CLIENT_SECRET);
		String password = request.getParameter(OAuthConsts.OAuth.OAUTH_PASSWORD);
		if (StrKit.isBlank(password) || !client_secret.equals(HashKit.md5(password))) {
			throw OAuthExceptionHandleKit.handleInvalidClientException();
		}		
	}

}
