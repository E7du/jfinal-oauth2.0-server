/**
 * 
 */

package cn.zhucongqi.oauth2.issuer;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public interface OAuthIssuer {
	
    public String accessToken();

    public String authorizationCode();

    public String refreshToken();
}
