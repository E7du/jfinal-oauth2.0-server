/**
 * 
 */
package cn.zhucongqi.oauth2.services;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.ext.core.Service;

import cn.zhucongqi.oauth2.base.services.OAuthApi;
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
		OAuthRequest request = new OAuthRequest(req, requestType);
		Object o = null;
		try {
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
