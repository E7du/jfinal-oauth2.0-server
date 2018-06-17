/**
   An example successful response:

     HTTP/1.1 200 OK
     Content-Type: application/json;charset=UTF-8
     Cache-Control: no-store
     Pragma: no-cache

     {
       "access_token":"2YotnFZFEjr1zCsicMWpAA",
       "token_type":"example",
       "expires_in":3600,
       "refresh_token":"tGzv3JOkF0XG5Qx2TlKWIA",
       "example_parameter":"example_value"
     }
 */
package cn.zhucongqi.oauth2.validators;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.response.types.GrantType;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.OAuthConsts;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 */
public class AccessTokenRequestValidator extends OAuthValidator {

    public AccessTokenRequestValidator(HttpServletRequest request) {
    	super(request);
    }

	@Override
	public void initParamDefaultValues() {
		this.paramDefaultValues.put(OAuthConsts.OAuth.OAUTH_GRANT_TYPE, GrantType.AUTHORIZATION_CODE.toString());
		//code,clientid,redurect_uri in OAuthClientCredentials logic validate.
	}

	@Override
	public void initRequiredParams() {
		this.requiredParams.add(OAuthConsts.OAuth.OAUTH_GRANT_TYPE);//Value MUST be set to "authorization_code".
		this.requiredParams.add(OAuthConsts.OAuth.OAUTH_AUTHORIZATION_CODE);//REQUIRED. The authorization code received from the authorization server.
		this.requiredParams.add(OAuthConsts.OAuth.OAUTH_REDIRECT_URI);//REQUIRED, if the "redirect_uri" parameter was included in the authorization request as described in Section 4.1.1, and their values MUST be identical.
		this.requiredParams.add(OAuthConsts.OAuth.OAUTH_CLIENT_ID);//REQUIRED, if the client is not authenticating with the authorization server as described in Section 3.2.1.
	}

}
