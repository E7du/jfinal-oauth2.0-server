/**
 * 
 */
package cn.zhucongqi.oauth2.services;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.ext.core.Service;

import cn.zhucongqi.oauth2.base.services.OAuthApi;
import cn.zhucongqi.oauth2.clientcredentials.PasswordClientCredentials;
import cn.zhucongqi.oauth2.consts.OAuthRequestConsts;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.request.OAuthRequest;
import cn.zhucongqi.oauth2.response.OAuthErrResponse;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class OAuthService extends Service implements OAuthApi {

	private void respClient(int requestType) {
		HttpServletRequest req = this.controller.getRequest();
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
		this.controller.renderJson(o);
	}
 	
	@Override
	public void authrize() {
		this.respClient(OAuthRequestConsts.AUTHORIZATION_REQUEST);
	}
	
	@Override
	public void authrizeCode() {
		
	}

	@Override
	public void accessToken() {
		this.respClient(OAuthRequestConsts.ACCESS_TOKEN_REQUEST);
	}
	
	@Override
	public void secureAccessToken() {
		this.respClient(OAuthRequestConsts.PASSOWRD_CREDENTIAL_REQUEST);
	}

	@Override
	public void refreshToken() {
		this.respClient(OAuthRequestConsts.REFRESH_TOKEN_REQUEST);
	}

}
