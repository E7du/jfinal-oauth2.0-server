/**
 * 4.2.1.  Authorization Request
 * GET /authorize?response_type=token&client_id=s6BhdRkqt3&state=xyz
        &redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb HTTP/1.1
    Host: server.example.com
    4.2.2.  Access Token Response
     HTTP/1.1 302 Found
     Location: http://example.com/cb#access_token=2YotnFZFEjr1zCsicMWpAA
               &state=xyz&token_type=example&expires_in=3600
 */

package cn.zhucongqi.oauth2.validators;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.response.types.ResponseType;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.OAuthConsts;


/**
 * Implicit Grant
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class ImplicitValidator extends OAuthValidator {

    public ImplicitValidator(HttpServletRequest request) {
    	super(request);
    }

	@Override
	public void initParamDefaultValues() {
		this.paramDefaultValues.put(OAuthConsts.OAuth.OAUTH_RESPONSE_TYPE, ResponseType.TOKEN.toString());
		//OAuthConsts.OAUTH_CLIENT_ID into OAuthClientCredentials logic validate.
	}

	@Override
	public void initRequiredParams() {
		this.requiredParams.add(OAuthConsts.OAuth.OAUTH_RESPONSE_TYPE);//REQUIRED.  Value MUST be set to "token".
		this.requiredParams.add(OAuthConsts.OAuth.OAUTH_CLIENT_ID);//REQUIRED.  The client identifier as described in Section 2.2.
	}

}
