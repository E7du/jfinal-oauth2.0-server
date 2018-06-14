/**
 * 注意： view 对应的 func 的命名，为 xxv，如 loginv，对应的处理为 login
 */
package cn.zhucongqi.oauth2.controllers;

import cn.zhucongqi.oauth2.issuer.MD5Generator;
import cn.zhucongqi.oauth2.request.RequestType;
import cn.zhucongqi.oauth2.services.OAuth2Service;

import com.jfinal.core.ActionKey;
import com.jfinal.ext2.core.ControllerExt;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class OAuth2Controller extends ControllerExt {
	
	private OAuth2Service _auth2Service;

	private void codeTokenReqLinkToOAuthRequest() {
		_auth2Service.setReqType(RequestType.CODE_TOKEN_REQUEST)
				.setValGenerator(new MD5Generator()).doOAuthAction();
	}

	private void grantReqLinkToOAuthGrantRequest() {
		if (!_auth2Service.isAuthorizationed()) {
			return;
		}
		_auth2Service.setReqType(RequestType.GRANT_REQUEST)
				.setValGenerator(new MD5Generator()).doOAuthAction();
	}

	/**
	 * generate code 
	 * <br/>
	 * using OAuthRequest <br/>
	 * AuthorizationValidator <br/>
	 */
	@ActionKey("oauth2/authorize")
	public void onAuthorize() {
		this.codeTokenReqLinkToOAuthRequest();
	}

	/**
	 * validate code
	 * <br/>
	 * using OAuthGrantRequest <br/>
	 * AuthorizationCodeValidator <br/>
	 */
	@ActionKey("oauth2/authorize_code")
	public void onAuthorizeCode() {
		this.grantReqLinkToOAuthGrantRequest();
	}
	
	/**
	 * get secure access token
	 * <br/>
	 * using OAuthGrantRequest <br/>
	 * PasswordCredentialValidator <br/>
	 * ClientCredentialValidator <br/>
	 */
	@ActionKey("oauth2/secure_access_token")
	public void onAccessTokenSecure() {
		this.grantReqLinkToOAuthGrantRequest();
	}
	

	/**
	 * get access token
	 * <br/>
	 * using OAuthRequest <br/>
	 * ImplicitCodeValidator <br/>
	 */
	@ActionKey("oauth2/access_token")
	public void onAcessToken() {
		this.codeTokenReqLinkToOAuthRequest();
	}
	
	/**
	 * refresh access token , return new access token and new refresh token
	 * <br/>
	 * using OAuthGrantRequest <br/>
	 * RefreshTokenValidator <br/>
	 */
	@ActionKey("oauth2/refresh_token")
	public void onRefreshToken() {	
		this.grantReqLinkToOAuthGrantRequest();
	}

	@Override
	public void onExceptionError(Exception e) {
		// TODO Auto-generated method stub
		
	}
	
}
