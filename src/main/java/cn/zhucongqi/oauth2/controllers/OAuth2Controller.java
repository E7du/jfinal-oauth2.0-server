/**
 * 注意： view 对应的 func 的命名，为 xxv，如 loginv，对应的处理为 login
 */
package cn.zhucongqi.oauth2.controllers;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.core.ControllerExt;

import cn.zhucongqi.oauth2.consts.ActionUrls;
import cn.zhucongqi.oauth2.services.OAuth2Service;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class OAuth2Controller extends ControllerExt {
	
	private OAuth2Service _auth2Service;

	/**
	 * generate code 
	 * <br/>
	 * using OAuthRequest <br/>
	 * AuthorizationValidator <br/>
	 */
	@ActionKey(ActionUrls.AUTHORIZE_URL)
	public void onAuthorize() {
		this._auth2Service.authrize();
	}

	/**
	 * validate code
	 * <br/>
	 * using OAuthGrantRequest <br/>
	 * AuthorizationCodeValidator <br/>
	 */
	@ActionKey(ActionUrls.AUTHORIZE_CODE_URL)
	public void onAuthorizeCode() {
		this._auth2Service.authrizeCode();
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
		this._auth2Service.secureAccessToken();
	}
	

	/**
	 * get access token
	 * <br/>
	 * using OAuthRequest <br/>
	 * ImplicitCodeValidator <br/>
	 */
	@ActionKey(ActionUrls.ACCESS_TOKEN_URL)
	public void onAcessToken() {
		this._auth2Service.accessToken();
	}
	
	/**
	 * refresh access token , return new access token and new refresh token
	 * <br/>
	 * using OAuthGrantRequest <br/>
	 * RefreshTokenValidator <br/>
	 */
	@ActionKey(ActionUrls.REFRESH_TOKEN_URL)
	public void onRefreshToken() {	
		this._auth2Service.refreshToken();
	}

	@Override
	public void onExceptionError(Exception e) {
		
	}
	
}
