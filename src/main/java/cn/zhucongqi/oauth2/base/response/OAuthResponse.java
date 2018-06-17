/**
 * 
 */
package cn.zhucongqi.oauth2.base.response;

import java.util.HashMap;

import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;

import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.OAuthConsts;
import cn.zhucongqi.oauth2.issuer.OAuthIssuerKit;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public abstract class OAuthResponse {
	
	protected HashMap<String, String> parameters = null;
	protected OAuthIssuerKit issuer = null;
	
	public OAuthResponse(OAuthValidator validator) {
		this.parameters = new HashMap<String, String>();
		this.issuer = OAuthIssuerKit.issuer();
		this.init();
		String state = validator.getState();
		if (StrKit.notBlank(state)) {
			this.setState(state);
		}
		
		String scope = validator.getScope();
		if (StrKit.notBlank(scope)) {
			this.setScope(scope);
		}
	}
	
	protected abstract void init();
	
	protected void putParameter(String parameter, String value) {
		this.parameters.put(parameter, value);
	}
	
	protected String getParamter(String parameter) {
		return this.parameters.get(parameter);
	}
	
	private void setState(String state) {
		this.putParameter(OAuthConsts.OAuth.OAUTH_STATE, state);
	}
	
	public String getState() {
		return this.getParamter(OAuthConsts.OAuth.OAUTH_STATE);
	}
	
	private void setScope(String scope) {
		this.putParameter(OAuthConsts.OAuth.OAUTH_SCOPE, scope);
	}
	
	public String getScope() {
		return this.getParamter(OAuthConsts.OAuth.OAUTH_SCOPE);
	}
	
	/**
	 * Put other extension parameter into the parameters
	 * @param parameter
	 * @param value
	 */
	public void putExtenstionParameter(String parameter, String value) {
		this.putParameter(parameter, value);
	}
	
	public HashMap<String, String> parameters() {
		return this.parameters;
	}

	@Override
	public String toString() {
		return "OAuthResponse [parameters=" + parameters + "] AND JSON Format "+JsonKit.toJson(this);
	}
}
