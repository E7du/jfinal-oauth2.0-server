/**
 * http://www.rfcreader.com/#rfc6749
 * 4.4.2.  Access Token Request
 * 
 * The client can request an access token using only its client
   credentials (or other supported means of authentication) when the
   client is requesting access to the protected resources under its
   control, or those of another resource owner that have been previously
   arranged with the authorization server (the method of which is beyond
   the scope of this specification).

   The client credentials grant type MUST only be used by confidential
   clients.

     +---------+                                  +---------------+
     |         |                                  |               |
     |         |>--(A)- Client Authentication --->| Authorization |
     | Client  |                                  |     Server    |
     |         |<--(B)---- Access Token ---------<|               |
     |         |                                  |               |
     +---------+                                  +---------------+

                     Figure 6: Client Credentials Flow

   The flow illustrated in Figure 6 includes the following steps:

   (A)  The client authenticates with the authorization server and
        requests an access token from the token endpoint.

   (B)  The authorization server authenticates the client, and if valid,
        issues an access token.
 */
package cn.zhucongqi.oauth2.validators;

import javax.servlet.http.HttpServletRequest;

import cn.zhucongqi.oauth2.base.response.types.GrantType;
import cn.zhucongqi.oauth2.base.validator.OAuthValidator;
import cn.zhucongqi.oauth2.consts.OAuthConsts;

/**
 * Client Credentials Grant
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class ClientCredentialValidator extends OAuthValidator {
   
	public ClientCredentialValidator(HttpServletRequest request) {
		super(request);
    }

	@Override
	public void initParamDefaultValues() {
		this.paramDefaultValues.put(OAuthConsts.OAuth.OAUTH_GRANT_TYPE, GrantType.CLIENT_CREDENTIALS.toString());
	}

	@Override
	public void initRequiredParams() {
		this.requiredParams.add(OAuthConsts.OAuth.OAUTH_GRANT_TYPE);//Value MUST be set to "client_credentials".
	}

}
