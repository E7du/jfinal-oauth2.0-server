/**
 */
package cn.zhucongqi.oauth2.base.validator;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.exception.OAuthProblemException;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 * @param <T>
 */
public interface OAuthValidator<T extends HttpServletRequest> {

    public void validateMethod(T request) throws OAuthProblemException;

    public void validateContentType(T request) throws OAuthProblemException;

    public void validateRequiredParameters(T request) throws OAuthProblemException;

    public void validateOptionalParameters(T request) throws OAuthProblemException;

    public void validateNotAllowedParameters(T request) throws OAuthProblemException;

    public void validateClientAuthenticationCredentials(T request) throws OAuthProblemException;

    public void performAllValidations(T request) throws OAuthProblemException;

}
