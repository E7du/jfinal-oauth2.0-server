package cn.zhucongqi.oauth2.clientcredentials;

import javax.servlet.http.HttpServletRequest;

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
	public void validateClientCredentials(HttpServletRequest request) throws OAuthProblemException;
}
