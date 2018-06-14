/**
 * 
 */
package cn.zhucongqi.oauth2.services;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import cn.zhucongqi.oauth2.consts.OAuth;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.issuer.MD5Generator;
import cn.zhucongqi.oauth2.issuer.OAuthIssuer;
import cn.zhucongqi.oauth2.issuer.OAuthIssuerKit;
import cn.zhucongqi.oauth2.issuer.ValueGenerator;
import cn.zhucongqi.oauth2.message.OAuthResponse;
import cn.zhucongqi.oauth2.message.types.GrantType;
import cn.zhucongqi.oauth2.message.types.ResponseType;
import cn.zhucongqi.oauth2.request.OAuthGrantRequest;
import cn.zhucongqi.oauth2.request.OAuthRequest;
import cn.zhucongqi.oauth2.request.RequestType;
import cn.zhucongqi.oauth2.response.OAuthASResponse;

import com.jfinal.ext2.core.Service;

/**
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class OAuth2Service extends Service {

	private RequestType reqType = RequestType.GRANT_REQUEST;
	private ValueGenerator valGenerator = new MD5Generator();
	
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
		try {
			OAuthResponse r = null;
			OAuthIssuer oauthIssuer = new OAuthIssuerKit(this.valGenerator);
			String accessToken = oauthIssuer.accessToken();
			String refreshToken = oauthIssuer.refreshToken();
			
			switch (reqType) {
			case CODE_TOKEN_REQUEST: {
				OAuthRequest req = new OAuthRequest(
						this.controller.getRequest());
				// response_type : code
				if (String.valueOf(ResponseType.CODE).equals(req.getResponseType())) {
					String authzCode = oauthIssuer.authorizationCode();
					r = OAuthASResponse
							.authorizationResponse(this.controller.getRequest())
							.setCode(authzCode)
							.buildJSONMessage();
				// response_type : token
				} else if (String.valueOf(ResponseType.TOKEN).equals(req.getResponseType())) {
					r = this.repTokenToClient(accessToken, refreshToken);
				}
			}
				break;
			// grant_type :　authorization_code, password, client_credentials
			case GRANT_REQUEST: {
				OAuthGrantRequest req = new OAuthGrantRequest(
						this.controller.getRequest());
				//authorization_code
				if (String.valueOf(GrantType.AUTHORIZATION_CODE).equals(req.getGrantType())) {
					//TODO 校验
					req.getCode();
				}else{
					r = this.repTokenToClient(accessToken, refreshToken);
				}
			}
				break;
			}
			this.controller.getResponse().setStatus(r.getResponseStatus());
			this.controller.renderJson(r.getBody());
		} catch (OAuthProblemException ex) {
			OAuthResponse r = OAuthResponse
					.errorBadReqResponse(this.controller.getRequest())
					.error(ex).buildJSONMessage();
			this.controller.getResponse().setStatus(r.getResponseStatus());
			this.controller.renderJson(r.getBody());
		}
	}
	
	/**
	 * 把token返回客户端
	 * @param accessToken
	 * @param refreshToken
	 * @return
	 */
	private OAuthResponse repTokenToClient(String accessToken, String refreshToken) {
		return OAuthASResponse
				.tokenResponse()
				.setAccessToken(accessToken)
				.setRefreshToken(refreshToken)
				.setExpiresIn("3600")
				.setExampleParamter("exampleValue").buildJSONMessage();
	}
	
	/**
	 * 检查是否完成授权
	 * @return
	 */
	public boolean isAuthorizationed() {
		boolean authOK = false;
		String Authorization = this.controller.getRequest().getHeader(
				OAuth.HeaderType.AUTHORIZATION);
		//validate header
		if (null == Authorization || Authorization.trim().isEmpty()) {
			this.notifyClientAuth();
			return authOK;
		}
		
		// validate Authorization
		String[] basicArray = Authorization.split("\\s+");
		if (null == basicArray || 2 != basicArray.length) {
			this.notifyClientAuth();
			return authOK;
		}
		
		//validate id and password
		String base64 = basicArray[1];
		String idpass = new String(Base64.decodeBase64(base64));
		if (null == idpass || idpass.trim().isEmpty()) {
			this.notifyClientAuth();
			return authOK;
		}
		String[] idpassArray = idpass.split(":");
		if (null == idpassArray || 2 != idpassArray.length) {
			this.notifyClientAuth();
			return authOK;
		}
		String _id = idpassArray[0];
		String _pass = idpassArray[1];
		authOK = this.validate(_id, _pass);
		if (!authOK) {
			this.notifyClientAuth();
		}
		return authOK;
	}
    
	/**
	 * 通知客户端需要授权
	 */
    private void notifyClientAuth() {
		this.controller.getResponse().setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
		this.controller.getResponse().addHeader(OAuth.HeaderType.WWW_AUTHENTICATE,"Basic realm=\"Authorization First!\""); 
		OAuthResponse r = OAuthResponse
				.errorUnAuthResponse(this.controller.getRequest()).setErrorDescription("UnAuthorization!Authorization First!").buildJSONMessage();
		this.controller.getResponse().setStatus(r.getResponseStatus());
		this.controller.renderJson(r.getBody());
    }
	
    /**
     * @param id
     * @param password
     * @return
     * TODO db 校验
     */
    private boolean validate(String id, String password) {
    	return true;
    }
}
