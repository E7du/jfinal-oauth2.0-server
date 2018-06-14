/**
 * 
 */

package cn.zhucongqi.oauth2.base.request;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.OAuth;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.kit.OAuthKit;

import com.jfinal.aop.Duang;
import com.jfinal.kit.StrKit;

/**
 * Abstract OAuth Token request class
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public abstract class OAuthTokenBaseRequest extends OAuthBaseRequest {

	protected OAuthTokenBaseRequest(HttpServletRequest request)
			throws OAuthProblemException {
		super(request);
	}

	protected OAuthValidator<HttpServletRequest> initValidator()
			throws OAuthProblemException {
		final String requestTypeValue = getParam(OAuth.OAUTH_GRANT_TYPE);
		if (StrKit.isBlank(requestTypeValue)) {
			throw OAuthKit
					.handleOAuthProblemException("Missing grant_type parameter value");
		}
		final Class<? extends OAuthValidator<HttpServletRequest>> clazz = validators
				.get(requestTypeValue);
		if (clazz == null) {
			throw OAuthKit
					.handleOAuthProblemException("Invalid grant_type parameter value");
		}
		return Duang.duang(clazz);
	}

	public String getPassword() {
		return getParam(OAuth.OAUTH_PASSWORD);
	}

	public String getUsername() {
		return getParam(OAuth.OAUTH_USERNAME);
	}

	public String getRefreshToken() {
		return getParam(OAuth.OAUTH_REFRESH_TOKEN);
	}

	/**
	 * response_type : code, 中获得的code
	 * @return
	 */
	public String getCode() {
		return getParam(OAuth.OAUTH_CODE);
	}

	public String getGrantType() {
		return getParam(OAuth.OAUTH_GRANT_TYPE);
	}
}
