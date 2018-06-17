/**
 * 注意： view 对应的 func 的命名，为 xxv，如 loginv，对应的处理为 login
 */
package cn.zhucongqi.oauth2.controllers;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.core.ControllerExt;
import com.jfinal.ext.interceptor.OnExceptionInterceptorExt;

import cn.zhucongqi.oauth2.consts.ActionUrls;
import cn.zhucongqi.oauth2.services.OAuthService;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
@Before(OnExceptionInterceptorExt.class)
public class OAuth2Controller extends ControllerExt {
	
	//using ControllerExt auto init the service instance
	private OAuthService oauthService;

	/**
	 * generate code 
	 * <br/>
	 * using OAuthRequest <br/>
	 * IGAuthorizationRequestValidator <br/>
	 */
	@ActionKey(ActionUrls.AUTHORIZE_URL)
	public void onAuthorize() {
		this.oauthService.authrize();
	}

	/**
	 * validate code
	 * <br/>
	 * using OAuthGrantRequest <br/>
	 * AccessTokenRequestValidator <br/>
	 */
	@ActionKey(ActionUrls.AUTHORIZE_CODE_URL)
	public void onAuthorizeCode() {
		this.oauthService.authrizeCode();
	}
	
	/**
	 * get secure access token
	 * <br/>
	 * using OAuthGrantRequest <br/>
	 * PasswordCredentialValidator <br/>
	 * ClientCredentialValidator <br/>
	 */
	@ActionKey(ActionUrls.SECURE_ACCESS_TOKEN_URL)
	public void onAccessTokenSecure() {
		this.oauthService.secureAccessToken();
	}
	

	/**
	 * get access token
	 * <br/>
	 * using OAuthRequest <br/>
	 * ImplicitValidator <br/>
	 */
	@ActionKey(ActionUrls.ACCESS_TOKEN_URL)
	public void onAcessToken() {
		this.oauthService.accessToken();
	}
	
	/**
	 * refresh access token , return new access token and new refresh token
	 * <br/>
	 * using OAuthGrantRequest <br/>
	 * RefreshTokenValidator <br/>
	 */
	@ActionKey(ActionUrls.REFRESH_TOKEN_URL)
	public void onRefreshToken() {	
		this.oauthService.refreshToken();
	}

	@Override
	public void onExceptionError(Exception e) {
		
	}
	
}
