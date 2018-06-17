package cn.zhucongqi.oauth2.base.clientcredentials;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 */
public interface OAuthClientCredentials {
	
	/**
	 * validateClientCredentials
	 * @param request
	 * @throws AuthProblemException
	 */
	public void validateClientCredentials(HttpServletRequest request, OAuthValidator validator) throws OAuthProblemException;
}
