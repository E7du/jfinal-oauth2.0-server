/**
 * 
 */
package cn.zhucongqi.oauth2.services;

import com.jfinal.ext.core.Service;

import cn.zhucongqi.oauth2.base.services.OAuthApi;
import cn.zhucongqi.oauth2.clientcredentials.PasswordClientCredentials;
import cn.zhucongqi.oauth2.consts.OAuthRequestConsts;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.request.OAuthHttpServletRequest;
import cn.zhucongqi.oauth2.request.OAuthRequest;
import cn.zhucongqi.oauth2.response.OAuthErrResponse;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class OAuthService extends Service implements OAuthApi {

	private Object respClient(int requestType, OAuthHttpServletRequest req) {
		Object o = null;
		OAuthRequest request = null;
		try {
			switch (requestType) {
			case OAuthRequestConsts.AUTHORIZATION_REQUEST: {
				request = OAuthRequest.authorizatonRequest(req, new PasswordClientCredentials());
			}
				break;

			case OAuthRequestConsts.ACCESS_TOKEN_REQUEST: {
				request = OAuthRequest.accessTokenRequest(req, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.CLIENT_CREDENTIAL_REQUEST: {
				request = OAuthRequest.clientCredentialRequest(req, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.IMPLICIT_REQUEST: {
				request = OAuthRequest.implicitRequest(req, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.PASSOWRD_CREDENTIAL_REQUEST: {
				request = OAuthRequest.passwordCredentialRequest(req, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.REFRESH_TOKEN_REQUEST: {
				request = OAuthRequest.refreshTokenRequest(req, new PasswordClientCredentials());
			}
				break;
			}

			o = request.validate();//push to o: o = request.validate();
			
		} catch (OAuthProblemException e) {
			e.printStackTrace();
			this.controller.onExceptionError(e);
			OAuthErrResponse error = new OAuthErrResponse(request.getValidator(), e);
			o = error.parameters();
		}
		return o;
	}
 	
	@Override
	public Object authrize(OAuthHttpServletRequest request) {
		return this.respClient(OAuthRequestConsts.AUTHORIZATION_REQUEST, request);
	}
	
	@Override
	public Object authrizeCode(OAuthHttpServletRequest request) {
		return null;
	}

	@Override
	public Object accessToken(OAuthHttpServletRequest request) {
		return this.respClient(OAuthRequestConsts.ACCESS_TOKEN_REQUEST, request);
	}
	
	@Override
	public Object secureAccessToken(OAuthHttpServletRequest request) {
		return this.respClient(OAuthRequestConsts.PASSOWRD_CREDENTIAL_REQUEST, request);
	}

	@Override
	public Object refreshToken(OAuthHttpServletRequest request) {
		return this.respClient(OAuthRequestConsts.REFRESH_TOKEN_REQUEST, request);
	}

}
