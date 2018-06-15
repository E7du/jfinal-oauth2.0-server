/**
 * 所有系统中用到的常量
 */
package cn.zhucongqi.oauth2.consts;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class Consts {

	/**
	 * CaptchaRender random key
	 */
	public static final String CAPTCHA_RDNDOM_KEY = "captcha";
	
	public static final String TOKEN_EXPIRES_IN = "3600";
	
	/**
	 * HttpMethod
	 * @author BruceZCQ [zcq@zhucongqi.cn]
	 * @version
	 */
    public static final class HttpMethod {
        public static final String POST = "POST";
        public static final String GET = "GET";
        public static final String DELETE = "DELETE";
        public static final String PUT = "PUT";
    }

    /**
     * HeaderType
     * @author BruceZCQ [zcq@zhucongqi.cn]
     * @version
     */
    public static final class HeaderType {
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
        public static final String AUTHORIZATION = "Authorization";
    }
    
    /**
     * ContentType
     * @author BruceZCQ [zcq@zhucongqi.cn]
     * @version
     */
    public static final class ContentType {
        public static final String URL_ENCODED = "application/x-www-form-urlencoded";
        public static final String JSON = "application/json";
    }
    
    /**
     * AuthConsts
     * @author BruceZCQ [zcq@zhucongqi.cn]
     * @version
     */
    public static final class AuthConsts {
    	public static final String AUTH_RESPONSE_TYPE = "response_type";
        public static final String AUTH_CLIENT_ID = "client_id";
        public static final String AUTH_CLIENT_SECRET = "client_secret";
        public static final String AUTH_USERNAME = "username";
        public static final String AUTH_NICKNAME = "nickname";
        public static final String AUTH_PASSWORD = "password";
        public static final String AUTH_SCOPE = "scope";
        public static final String AUTH_STATE = "state";
        public static final String AUTH_GRANT_TYPE = "grant_type";
        public static final String AUTH_CODE = "code";
        public static final String AUTH_ACCESS_TOKEN = "access_token";
        public static final String AUTH_EXPIRES_IN = "expires_in";
        public static final String AUTH_REFRESH_TOKEN = "refresh_token";
        public static final String AUTH_SEX = "sex";
        public static final String AUTH_AVATAR_URL = "avatar_url";
        
        public static final String AUTH_BIRTHDAY = "birthday";
        public static final String AUTH_PID = "pid";
        public static final String AUTH_CID = "cid";
        public static final String AUTH_ACTION = "action";
        public static final String AUTH_MOBILE = "mobile";
        public static final String AUTH_SMSCODE = "smscode";
        public static final String AUTH_CHANNEL = "channel";
        public static final String AUTH_VERCODE = "vercode";
    }
    
    /**
     * RepConsts
     * @author congqizhu
     *
     */
    public static final class RepConsts {
    	// 用户id
    	public static final String REP_UID = "uid";
    	// 用户类型：1=粉丝；2=篮球星；3=足球星；
    	public static final String REP_UTYPE = "utype";
    }
    
    /**
     * GrantTypeConsts <br/>
     * @author BruceZCQ [zcq@zhucongqi.cn]
     * @version
     */
    public static final class GrantType {
    	// authorization & code validate
    	public static final String CODE = "code";
    	public static final String VERCODE = "vercode";
    	public static final String ACCESS_TOKEN = "access_token";
    	public static final String REFRESH_TOKEN = "refresh_token";
    }
    
    /**
     * ResponseTypeConsts <br/>
     * @author BruceZCQ [zcq@zhucongqi.cn]
     * @version
     */
    public static final class ResponseType {
    	public static final String CODE = "code";
    	public static final String TOKEN = "token";
    	public static final String SMSCODE = "smscode";
    	public static final String VERCODE = "vercode";
    }
}
