/**
 * 
 */
package cn.zhucongqi.oauth2.response;

import cn.zhucongqi.oauth2.base.response.OAuthResponse;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.Consts;
import cn.zhucongqi.oauth2.consts.OAuthConsts;
import cn.zhucongqi.oauth2.issuer.OAuthIssuerKit;


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
	
	private String accessToken = "";
	private String expiresIn = "";
	private String refreshToken = "";
	private OAuthIssuerKit issuer = null;
	
	private void init() {
		this.setAccessToken(this.issuer.accessToken());
		this.setRefreshToken(this.issuer.refreshToken());
		this.setExpiresIn(Consts.TOKEN_EXPIRES_IN);//default value
	}
	
	public OAuthAccessToken(OAuthValidator validator) {
		super(validator);
		this.issuer = OAuthIssuerKit.uuidIssuer();
		this.init();
	}
	
	public OAuthAccessToken(OAuthValidator validator, OAuthIssuerKit issuer) {
		super(validator);
		this.issuer = issuer;
		this.init();
	}
	
	/**
	 * Set Access　Token
	 * @param accessToken
	 */
	private OAuthAccessToken setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		this.putParameter(OAuthConsts.OAuth.OAUTH_ACCESS_TOKEN, accessToken);
		return this;
	}
	
	public String getAccessToken() {
		return this.accessToken;
	}
	
	/**
	 * Set Expires In
	 * @param expiresIn
	 */
	public OAuthAccessToken setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
		this.putParameter(OAuthConsts.OAuth.OAUTH_EXPIRES_IN, expiresIn);
		return this;
	}
	
	public String getExpriresIn() {
		return this.expiresIn;
	}
	
	/**
	 * Set Refresh Token
	 * @param refreshToken
	 */
	private OAuthAccessToken setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		this.putParameter(OAuthConsts.OAuth.OAUTH_REFRESH_TOKEN, refreshToken);
		return this;
	}
	
	public String getRefreshToken() {
		return this.refreshToken;
	}
}
