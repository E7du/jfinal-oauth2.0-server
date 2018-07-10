/**
 * 注意： view 对应的 func 的命名，为 xxv，如 loginv，对应的处理为 login
 */
package cn.zhucongqi.oauth2.controllers;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.core.ControllerExt;
import com.jfinal.ext.interceptor.OnExceptionInterceptorExt;

import cn.zhucongqi.oauth2.consts.ActionUrls;
import cn.zhucongqi.oauth2.kit.OAuthRequestKit;
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
	
	@ActionKey(ActionUrls.AUTHORIZE_URL)
	public void onAuthorize() {
		this.renderJson(this.oauthService.authrize(OAuthRequestKit.cp(this.getRequest())));
	}

	@ActionKey(ActionUrls.AUTHORIZE_CODE_URL)
	public void onAuthorizeCode() {
		this.renderJson(this.oauthService.authrizeCode(OAuthRequestKit.cp(this.getRequest())));
	}
	
	@ActionKey(ActionUrls.SECURE_ACCESS_TOKEN_URL)
	public void onAccessTokenSecure() {
		this.renderJson(this.oauthService.secureAccessToken(OAuthRequestKit.cp(this.getRequest())));
	}

	@ActionKey(ActionUrls.ACCESS_TOKEN_URL)
	public void onAcessToken() {
		this.renderJson(this.oauthService.accessToken(OAuthRequestKit.cp(this.getRequest())));
	}
	
	@ActionKey(ActionUrls.REFRESH_TOKEN_URL)
	public void onRefreshToken() {	
		this.renderJson(this.oauthService.refreshToken(OAuthRequestKit.cp(this.getRequest())));
	}

	@Override
	public void onExceptionError(Exception e) {
		
	}
	
}
