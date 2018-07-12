/*
 * Copyright 2018 Jobsz (zcq@zhucongqi.cn)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
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
