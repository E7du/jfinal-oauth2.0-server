/**
 * 
 */
package cn.zhucongqi.oauth2.response;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.consts.OAuthConsts;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class CodeResponse extends Response {

	public CodeResponse(HttpServletRequest request) {
		super(request);
	}

	/**
	 * Set code
	 * @param code
	 */
	public CodeResponse setCode(String code) {
		this.params.put(OAuthConsts.OAuth.OAUTH_CODE, code);
		return this;
	}
}
