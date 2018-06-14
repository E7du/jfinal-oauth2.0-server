/**
 * 
 */

package cn.zhucongqi.oauth2.base.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.OAuth;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.kit.OAuthKit;

/**
 * The Abstract OAuth request for the Authorization server.
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public abstract class OAuthBaseRequest {

    protected HttpServletRequest request;
    protected OAuthValidator<HttpServletRequest> validator;
    protected Map<String, Class<? extends OAuthValidator<HttpServletRequest>>> validators =
        new HashMap<String, Class<? extends OAuthValidator<HttpServletRequest>>>();

    public OAuthBaseRequest(HttpServletRequest request) throws OAuthProblemException {
        this.request = request;
        validate();
    }

    public OAuthBaseRequest() {
    }

	protected void validate() throws OAuthProblemException {
		validator = initValidator();
		validator.validateMethod(request);
		validator.validateContentType(request);
		validator.validateRequiredParameters(request);
		validator.validateClientAuthenticationCredentials(request);
	}

    protected abstract OAuthValidator<HttpServletRequest> initValidator() throws OAuthProblemException;

    public String getParam(String name) {
        return request.getParameter(name);
    }

    public String getClientId() {
        String[] creds = OAuthKit.decodeClientAuthenticationHeader(request.getHeader(OAuth.HeaderType.AUTHORIZATION));
        if (creds != null) {
            return creds[0];
        }
        return getParam(OAuth.OAUTH_CLIENT_ID);
    }

    public String getRedirectURI() {
        return getParam(OAuth.OAUTH_REDIRECT_URI);
    }

    public String getClientSecret() {
        String[] creds = OAuthKit.decodeClientAuthenticationHeader(request.getHeader(OAuth.HeaderType.AUTHORIZATION));
        if (creds != null) {
            return creds[1];
        }
        return getParam(OAuth.OAUTH_CLIENT_SECRET);
    }

    /**
     *
     * @return
     */
    public boolean isClientAuthHeaderUsed() {
        return OAuthKit.decodeClientAuthenticationHeader(request.getHeader(OAuth.HeaderType.AUTHORIZATION)) != null;
    }

	public String getState() {
		return getParam(OAuth.OAUTH_STATE);
	}

    public Set<String> getScopes() {
        String scopes = getParam(OAuth.OAUTH_SCOPE);
        return OAuthKit.decodeScopes(scopes);
    }

}
