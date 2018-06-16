/**
 * 4.1.3.  Access Token Request


   The client makes a request to the token endpoint by sending the
   following parameters using the "application/x-www-form-urlencoded"
   format per Appendix B with a character encoding of UTF-8 in the HTTP
   request entity-body:

   grant_type
         REQUIRED.  Value MUST be set to "authorization_code".

   code
         REQUIRED.  The authorization code received from the
         authorization server.

   redirect_uri
         REQUIRED, if the "redirect_uri" parameter was included in the
         authorization request as described in Section 4.1.1, and their
         values MUST be identical.

   client_id
         REQUIRED, if the client is not authenticating with the
         authorization server as described in Section 3.2.1.
         
   4.1.4.  Access Token Response


   If the access token request is valid and authorized, the
   authorization server issues an access token and optional refresh
   token as described in Section 5.1.  If the request client
   authentication failed or is invalid, the authorization server returns
   an error response as described in Section 5.2.

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

import cn.zhucongqi.oauth2.base.validator.OAuthBaseValidator;
import cn.zhucongqi.oauth2.consts.OAuth;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 */
public class AccessTokenRequestValidator extends OAuthBaseValidator<HttpServletRequest> {

    public AccessTokenRequestValidator() {
        requiredParams.add(OAuth.OAUTH_GRANT_TYPE);//Value MUST be set to "authorization_code".
        requiredParams.add(OAuth.OAUTH_CODE);//REQUIRED. The authorization code received from the authorization server.
        requiredParams.add(OAuth.OAUTH_REDIRECT_URI);//REQUIRED, if the "redirect_uri" parameter was included in the authorization request as described in Section 4.1.1, and their values MUST be identical.
        requiredParams.add(OAuth.OAUTH_CLIENT_ID);//REQUIRED, if the client is not authenticating with the authorization server as described in Section 3.2.1.
    }

	@Override
	public void paramValuesValidation() {
		
	}

	@Override
	public boolean enforceClientAuthentication() {
		return true;
	}

}
