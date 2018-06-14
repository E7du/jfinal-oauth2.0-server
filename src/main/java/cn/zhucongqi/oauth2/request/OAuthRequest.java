/**
 * 
 */
package cn.zhucongqi.oauth2.request;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.request.OAuthBaseRequest;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.OAuth;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.kit.OAuthKit;
import cn.zhucongqi.oauth2.message.types.ResponseType;
import cn.zhucongqi.oauth2.validator.AuthorizationValidator;
import cn.zhucongqi.oauth2.validator.ImplicitCodeValidator;

import com.jfinal.aop.Duang;
import com.jfinal.kit.StrKit;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class OAuthRequest extends OAuthBaseRequest {

	public OAuthRequest(HttpServletRequest request)
			throws OAuthProblemException {
		super(request);
	}

	@Override
	protected OAuthValidator<HttpServletRequest> initValidator()
			throws OAuthProblemException {
		// end user authorization validators
		validators.put(ResponseType.CODE.toString(), AuthorizationValidator.class);
		validators.put(ResponseType.TOKEN.toString(), ImplicitCodeValidator.class);
		final String requestTypeValue = getParam(OAuth.OAUTH_RESPONSE_TYPE);
		if (StrKit.isBlank(requestTypeValue)) {
			throw OAuthKit
					.handleOAuthProblemException("Missing response_type parameter value");
		}
		final Class<? extends OAuthValidator<HttpServletRequest>> clazz = validators
				.get(requestTypeValue);
		if (clazz == null) {
			throw OAuthKit
					.handleOAuthProblemException("Invalid response_type parameter value");
		}

		return Duang.duang(clazz);
	}

	public String getResponseType() {
		return getParam(OAuth.OAUTH_RESPONSE_TYPE);
	}

}
