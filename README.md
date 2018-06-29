# jfinal-oauth2.0-server
基于[JFinal3.x](https://github.com/JFinal/JFinal)，[jfinal-ext3](https://github.com/E7du/jfinal-ext3)
参考[RFC6749](http://www.rfcreader.com/#rfc6749)实现了4.节描述的内容。

实现了OAuth 2.0定义了四种授权方式

- 授权码模式（authorization code）： 先获取下次请求token的code，然后在带着code去请求token；
- 简化模式（implicit）：直接请求token； 
- 密码模式（resource owner password credentials）： 先完成授权，然后再获取token；
- 客户端模式（client credentials）： 类似密码保护模式；
​

demo [http://localhost:8080/jfinal-oauth2.0-server/index.jsp](http://localhost:8080/jfinal-oauth2.0-server/index.jsp)


#自定义clientcredentials
- implements OAuthClientCredentials

```java
public class PasswordClientCredentials implements OAuthClientCredentials {

	@Override
	public Object validateClientCredentials(OAuthValidator validator) throws OAuthProblemException {
		//TODO build your own Password client credentials code in here	
		 OAuthAccessToken accessToken =  OAuthResponseKit.tokenResp(validator);
		 //put other paramters
		 accessToken.putExtenstionParameter("other", "other value");
		 return accessToken.parameters();
	}
}
```
- 使用

```java
private void respClient(int requestType) {
		HttpServletRequest req = this.controller.getRequest();
		Object o = null;
		OAuthRequest request = null;
		try {
			switch (requestType) {
			case OAuthRequestConsts.AUTHORIZATION_REQUEST: {
				request = OAuthRequest.authorizatonRequest(req, new PasswordClientCredentials());
			}
				break;

			case OAuthRequestConsts.ACCESS_TOKEN_REQUEST: {
				request = OAuthRequest.accessTokenRequest(req, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.CLIENT_CREDENTIAL_REQUEST: {
				request = OAuthRequest.clientCredentialRequest(req, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.IMPLICIT_REQUEST: {
				request = OAuthRequest.implicitRequest(req, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.PASSOWRD_CREDENTIAL_REQUEST: {
				request = OAuthRequest.passwordCredentialRequest(req, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.REFRESH_TOKEN_REQUEST: {
				request = OAuthRequest.refreshTokenRequest(req, new PasswordClientCredentials());
			}
				break;
			}

			o = request.validate();//push to o: o = request.validate();
			
		} catch (OAuthProblemException e) {
			e.printStackTrace();
			this.controller.onExceptionError(e);
			OAuthErrResponse error = new OAuthErrResponse(request.getValidator(), e);
			o = error.parameters();
		}
		this.controller.renderJson(o);
	}
```