/**
 * 注意： view 对应的 func 的命名，为 xxv，如 loginv，对应的处理为 login
 */
package cn.zhucongqi.oauth2.controllers;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.core.ControllerExt;

import cn.zhucongqi.oauth2.consts.ActionUrls;
import cn.zhucongqi.oauth2.issuer.MD5Generator;
import cn.zhucongqi.oauth2.issuer.ValueGenerator;
import cn.zhucongqi.oauth2.request.RequestType;
import cn.zhucongqi.oauth2.services.OAuth2Service;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class OAuth2Controller extends ControllerExt {
	
	private OAuth2Service _auth2Service;

	private ValueGenerator valueGenerator = new MD5Generator();
	
	private void codeTokenReqLinkToOAuthRequest() {
		_auth2Service.setReqType(RequestType.CODE_TOKEN_REQUEST)
				.setValGenerator(valueGenerator).doOAuthAction();
	}

	private void grantReqLinkToOAuthGrantRequest() {
		if (!_auth2Service.isAuthorizationed()) {
			return;
		}
		_auth2Service.setReqType(RequestType.GRANT_REQUEST)
				.setValGenerator(valueGenerator).doOAuthAction();
	}

	/**
	 * generate code 
	 * <br/>
	 * using OAuthRequest <br/>
	 * AuthorizationValidator <br/>
	 */
	@ActionKey(ActionUrls.AUTHORIZE_URL)
	public void onAuthorize() {
		this.codeTokenReqLinkToOAuthRequest();
	}

	/**
	 * validate code
	 * <br/>
	 * using OAuthGrantRequest <br/>
	 * AuthorizationCodeValidator <br/>
	 */
	@ActionKey(ActionUrls.AUTHORIZE_CODE_URL)
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
	@ActionKey(ActionUrls.SECURE_ACCESS_TOKEN_URL)
	public void onAccessTokenSecure() {
		this.grantReqLinkToOAuthGrantRequest();
	}
	

	/**
	 * get access token
	 * <br/>
	 * using OAuthRequest <br/>
	 * ImplicitCodeValidator <br/>
	 */
	@ActionKey(ActionUrls.ACCESS_TOKEN_URL)
	public void onAcessToken() {
		this.codeTokenReqLinkToOAuthRequest();
	}
	
	/**
	 * refresh access token , return new access token and new refresh token
	 * <br/>
	 * using OAuthGrantRequest <br/>
	 * RefreshTokenValidator <br/>
	 */
	@ActionKey(ActionUrls.REFRESH_TOKEN_URL)
	public void onRefreshToken() {	
		this.grantReqLinkToOAuthGrantRequest();
	}

	@Override
	public void onExceptionError(Exception e) {
		
	}
	
}
