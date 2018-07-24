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
package cn.zhucongqi.oauth2.services;

import com.jfinal.ext.core.Service;

import cn.zhucongqi.oauth2.base.response.OAuthResponse;
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

	private OAuthResponse respClient(int requestType, OAuthHttpServletRequest req) {
		OAuthResponse res = null;
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

			res = request.validate();
			
		} catch (OAuthProblemException e) {
			e.printStackTrace();
			this.controller.onExceptionError(e);
			res = new OAuthErrResponse(request.getValidator(), e);
		}
		return res;
	}
 	
	@Override
	public OAuthResponse authrize(OAuthHttpServletRequest request) {
		return this.respClient(OAuthRequestConsts.AUTHORIZATION_REQUEST, request);
	}
	
	@Override
	public OAuthResponse authrizeCode(OAuthHttpServletRequest request) {
		return null;
	}

	@Override
	public OAuthResponse accessToken(OAuthHttpServletRequest request) {
		return this.respClient(OAuthRequestConsts.ACCESS_TOKEN_REQUEST, request);
	}
	
	@Override
	public OAuthResponse secureAccessToken(OAuthHttpServletRequest request) {
		return this.respClient(OAuthRequestConsts.PASSOWRD_CREDENTIAL_REQUEST, request);
	}

	@Override
	public OAuthResponse refreshToken(OAuthHttpServletRequest request) {
		return this.respClient(OAuthRequestConsts.REFRESH_TOKEN_REQUEST, request);
	}

}
