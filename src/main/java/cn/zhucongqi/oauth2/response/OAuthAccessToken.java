/**
 * 
 */
package cn.zhucongqi.oauth2.response;

import cn.zhucongqi.oauth2.base.response.OAuthResponse;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.OAuthConsts;


/**
 * access_token : The access token issued by the authorization server. <br/>
 * expires_in : The lifetime in seconds of the access token. <br/>
 * refresh_token : he refresh token, which can be used to obtain new
         access tokens using the same authorization grant. <br/>
 * scope : The scope of the access request; <br/>
 * state : The exact value received from the
         client. <br/>
         
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class OAuthAccessToken extends OAuthResponse {
	
	public OAuthAccessToken(OAuthValidator validator) {
		super(validator);
	}
	
	/**
	 * Set Access　Token
	 * @param accessToken
	 */
	public OAuthAccessToken setAccessToken(String accessToken) {
		this.putParameter(OAuthConsts.OAuth.OAUTH_ACCESS_TOKEN, accessToken);
		return this;
	}
	
	/**
	 * Set Expires In
	 * @param expiresIn
	 */
	public OAuthAccessToken setExpiresIn(String expiresIn) {
		this.putParameter(OAuthConsts.OAuth.OAUTH_EXPIRES_IN, expiresIn);
		return this;
	}
	
	/**
	 * Set Refresh Token
	 * @param refreshToken
	 */
	public OAuthAccessToken setRefreshToken(String refreshToken) {
		this.putParameter(OAuthConsts.OAuth.OAUTH_REFRESH_TOKEN, refreshToken);
		return this;
	}
}
