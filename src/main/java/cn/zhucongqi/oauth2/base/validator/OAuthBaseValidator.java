/**
 * 
 */
package cn.zhucongqi.oauth2.base.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.StrKit;

import cn.zhucongqi.oauth2.consts.OAuth;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.kit.OAuthKit;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 * @param <T>
 */
public abstract class OAuthBaseValidator<T extends HttpServletRequest> implements OAuthValidator<T> {

    protected List<String> requiredParams = new ArrayList<String>();
    protected Map<String, String[]> optionalParams = new HashMap<String, String[]>();
    protected List<String> notAllowedParams = new ArrayList<String>();
    protected boolean enforceClientAuthentication = false;

    @Override
    public void validateMethod(T request) throws OAuthProblemException {
        if (!request.getMethod().equals(OAuth.HttpMethod.POST)) {
            throw OAuthKit.handleOAuthProblemException("Method not set to POST.");
        }
    }

    @Override
    public void validateContentType(T request) throws OAuthProblemException {
        String contentType = request.getContentType();
        final String expectedContentType = OAuth.ContentType.URL_ENCODED;
        if (!OAuthKit.hasContentType(contentType, expectedContentType)) {
            throw OAuthKit.handleBadContentTypeException(expectedContentType);
        }
    }

    @Override
    public void validateRequiredParameters(T request) throws OAuthProblemException {
        final Set<String> missingParameters = new HashSet<String>();
        for (String requiredParam : requiredParams) {
            String val = request.getParameter(requiredParam);
            if (StrKit.isBlank(val)) {
                missingParameters.add(requiredParam);
            }
        }
        if (!missingParameters.isEmpty()) {
            throw OAuthKit.handleMissingParameters(missingParameters);
        }
    }

    @Override
    public void validateOptionalParameters(T request) throws OAuthProblemException {
        final Set<String> missingParameters = new HashSet<String>();

        for (Map.Entry<String, String[]> requiredParam : optionalParams.entrySet()) {
            final String paramName = requiredParam.getKey();
            String val = request.getParameter(paramName);
            if (StrKit.notBlank(val)) {
                String[] dependentParams = requiredParam.getValue();
                if (StrKit.notBlank(dependentParams)) {
                    for (String dependentParam : dependentParams) {
                        val = request.getParameter(dependentParam);
                        if (StrKit.isBlank(val)) {
                            missingParameters.add(dependentParam);
                        }
                    }
                }
            }
        }

        if (!missingParameters.isEmpty()) {
            throw OAuthKit.handleMissingParameters(missingParameters);
        }
    }

    @Override
    public void validateNotAllowedParameters(T request) throws OAuthProblemException {
        List<String> notAllowedParameters = new ArrayList<String>();
        for (String requiredParam : notAllowedParams) {
            String val = request.getParameter(requiredParam);
            if (StrKit.notBlank(val)) {
                notAllowedParameters.add(requiredParam);
            }
        }
        if (!notAllowedParameters.isEmpty()) {
            throw OAuthKit.handleNotAllowedParametersOAuthException(notAllowedParameters);
        }
    }

    @Override
    public void validateClientAuthenticationCredentials(T request) throws OAuthProblemException {
        if (enforceClientAuthentication) {
            Set<String> missingParameters = new HashSet<String>();
            String clientAuthHeader = request.getHeader(OAuth.HeaderType.AUTHORIZATION);
            String[] clientCreds = OAuthKit.decodeClientAuthenticationHeader(clientAuthHeader);

            // Only fallback to params if the auth header is not correct. Don't allow a mix of auth header vs params
            if (clientCreds == null || StrKit.isBlank(clientCreds[0]) || StrKit.isBlank(clientCreds[1])) {

                if (StrKit.isBlank(request.getParameter(OAuth.OAUTH_CLIENT_ID))) {
                    missingParameters.add(OAuth.OAUTH_CLIENT_ID);
                }
                if (StrKit.isBlank(request.getParameter(OAuth.OAUTH_CLIENT_SECRET))) {
                    missingParameters.add(OAuth.OAUTH_CLIENT_SECRET);
                }
            }

            if (!missingParameters.isEmpty()) {
                throw OAuthKit.handleMissingParameters(missingParameters);
            }
        }
    }

    @Override
    public void performAllValidations(T request) throws OAuthProblemException {
        this.validateContentType(request);
        this.validateMethod(request);
        this.validateRequiredParameters(request);
        this.validateOptionalParameters(request);
        this.validateNotAllowedParameters(request);
        this.validateClientAuthenticationCredentials(request);
    }
}
