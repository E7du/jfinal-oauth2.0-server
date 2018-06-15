/**
 * 
 */
package cn.zhucongqi.oauth2.response;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.StrKit;

import cn.zhucongqi.oauth2.consts.Consts;

/**
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public abstract class Response {
	
	protected HashMap<String, String> params = null;
	private String scope = "";
	private String state = "";
	
	public Response(HttpServletRequest request) {
		this.params = new HashMap<String, String>();
		
		String state = request.getParameter(Consts.AuthConsts.AUTH_STATE);
		if (StrKit.notBlank(state)) {
			this.setState(state);
		}
		
		String scope = request.getParameter(Consts.AuthConsts.AUTH_SCOPE);
		if (StrKit.notBlank(scope)) {
			this.setScope(scope);
		}
	}
	
	private void setState(String state) {
		this.state = state;
		this.params.put(Consts.AuthConsts.AUTH_STATE, this.state);
	}
	
	public String getState() {
		return this.state;
	}
	
	private void setScope(String scope) {
		this.scope = scope;
		this.params.put(Consts.AuthConsts.AUTH_SCOPE, this.scope);
	}
	
	public String getScope() {
		return this.scope;
	}
	
	public Object param() {
		System.out.println("download data ="+params);
		return params;
	}
}
