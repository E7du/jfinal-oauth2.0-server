/**
 * 
 */
package cn.zhucongqi.oauth2.services;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.ext.core.Service;

import cn.zhucongqi.oauth2.consts.Consts;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.issuer.MD5Generator;
import cn.zhucongqi.oauth2.issuer.OAuthIssuerKit;
import cn.zhucongqi.oauth2.issuer.ValueGenerator;
import cn.zhucongqi.oauth2.request.RequestType;
import cn.zhucongqi.oauth2.response.AccessToken;
import cn.zhucongqi.oauth2.response.ErrorResponse;
import cn.zhucongqi.oauth2.response.ResponseKit;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class OAuth2Service extends Service implements OAuth2ServiceApi {

	private RequestType reqType = RequestType.GRANT_REQUEST;
	private ValueGenerator valGenerator = new MD5Generator();
	
	private void repErrorToClient(HttpServletRequest request, OAuthProblemException e) {
		// rep error to client
		ErrorResponse errorRep = ResponseKit.errorRep(request, e);
//		this.controller.getResponse().setStatus(e.getResponseStatus());
		this.log.error("authproblem =>"+e.getMessage());
		this.controller.renderJson(errorRep.param());
	}
	
	private void renderAccessToken(String scope, String state, HttpServletRequest request) {
		//generate access token
		OAuthIssuerKit issuer = null;//OAuthIssuerKit.();
		String accessToken = issuer.accessToken();
		String refreshToken = issuer.refreshToken();
		//store access token and refresh token and account
		//rep to client
		AccessToken accessTokenRep = ResponseKit.tokenRep(request);
		accessTokenRep.setAccessToken(accessToken)
		.setExpiresIn(Consts.TOKEN_EXPIRES_IN)
		.setRefreshToken(refreshToken);
		this.controller.renderJson(accessTokenRep.param());
	}
	
	public OAuth2Service setReqType(RequestType reqType) {
		this.reqType = reqType;
		return this;
	}
	
	public OAuth2Service setValGenerator(ValueGenerator valGenerator) {
		this.valGenerator = valGenerator;
		return this;
	}
	
	/**
	 * 处理授权请求
	 */
	public void doOAuthAction() {
	}

	@Override
	public void authrize() {
		
	}
	
	@Override
	public void authrizeCode() {
		
	}

	@Override
	public void accessToken() {
		
	}
	
	@Override
	public void secureAccessToken() {
		
	}

	@Override
	public void refreshToken() {
		
	}

}
