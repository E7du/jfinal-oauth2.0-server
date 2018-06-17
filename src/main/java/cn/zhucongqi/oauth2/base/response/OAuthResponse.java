/**
 * 
 */
package cn.zhucongqi.oauth2.base.response;

import java.util.HashMap;

import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;

import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.OAuthConsts;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public abstract class OAuthResponse {
	
	protected HashMap<String, String> parameters = null;
	private String scope = "";
	private String state = "";
	
	public OAuthResponse(OAuthValidator validator) {
		this.parameters = new HashMap<String, String>();
		
		String state = validator.getState();
		if (StrKit.notBlank(state)) {
			this.setState(state);
		}
		
		String scope = validator.getScope();
		if (StrKit.notBlank(scope)) {
			this.setScope(scope);
		}
	}
	
	protected void putParameter(String parameter, String value) {
		this.parameters.put(parameter, value);
	}
	
	private void setState(String state) {
		this.state = state;
		this.putParameter(OAuthConsts.OAuth.OAUTH_STATE, this.state);
	}
	
	public String getState() {
		return this.state;
	}
	
	private void setScope(String scope) {
		this.scope = scope;
		this.putParameter(OAuthConsts.OAuth.OAUTH_SCOPE, this.scope);
	}
	
	public String getScope() {
		return this.scope;
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
		return "OAuthResponse [parameters=" + parameters + ", scope=" + scope + ", state=" + state + "] AND JSON Format "+JsonKit.toJson(this);
	}
}
