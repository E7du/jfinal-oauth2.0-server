/**
 * 
 */
package cn.zhucongqi.oauth2.clientcredentials;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public interface ClientCredentials<T extends HttpServletRequest> {
	
	/**
	 * validateClientCredentials
	 * @param request
	 * @throws AuthProblemException
	 */
	public void validateClientCredentials(T request) throws OAuthProblemException;
}
