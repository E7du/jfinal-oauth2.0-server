/**
 * 
 */
package cn.zhucongqi.oauth2.services;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.ext.core.Service;

import cn.zhucongqi.oauth2.base.clientcredentials.OAuthClientCredentials;
import cn.zhucongqi.oauth2.base.services.OAuthApi;
import cn.zhucongqi.oauth2.clientcredentials.AccessTokenClientCredentials;
import cn.zhucongqi.oauth2.clientcredentials.AuthorizationClientCredentials;
import cn.zhucongqi.oauth2.clientcredentials.ClientCredentials;
import cn.zhucongqi.oauth2.clientcredentials.ImplicitClientCredentials;
import cn.zhucongqi.oauth2.clientcredentials.PasswordClientCredentials;
import cn.zhucongqi.oauth2.clientcredentials.RefreshTokenClientCredentials;
import cn.zhucongqi.oauth2.consts.OAuthRequestConsts;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.kit.OAuthResponseKit;
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
			OAuthClientCredentials clientCredential = null;
			switch (requestType) {
			case OAuthRequestConsts.AUTHORIZATION_REQUEST: {
				clientCredential = new AuthorizationClientCredentials();
			}
				break;

			case OAuthRequestConsts.ACCESS_TOKEN_REQUEST: {
				clientCredential = new AccessTokenClientCredentials();
			}
				break;
			case OAuthRequestConsts.CLIENT_CREDENTIAL_REQUEST: {
				clientCredential = new ClientCredentials();
			}
				break;
			case OAuthRequestConsts.IMPLICIT_REQUEST: {
				clientCredential = new ImplicitClientCredentials();
			}
				break;
			case OAuthRequestConsts.PASSOWRD_CREDENTIAL_REQUEST: {
				clientCredential = new PasswordClientCredentials();
			}
				break;
			case OAuthRequestConsts.REFRESH_TOKEN_REQUEST: {
				clientCredential = new RefreshTokenClientCredentials();
			}
				break;
			}

			request = new OAuthRequest(req, requestType, clientCredential);
			request.validate();
			
			o = OAuthResponseKit.tokenResp(request.getValidator());
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
