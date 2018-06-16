/**
 * 
 */

package cn.zhucongqi.oauth2.issuer;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class OAuthIssuerKit implements OAuthIssuer {

    private ValueGenerator vg;

    private OAuthIssuerKit(ValueGenerator vg) {
        this.vg = vg;
    }

    public static OAuthIssuerKit md5Issuer() {
    	return (new OAuthIssuerKit(new MD5Generator()));
    }
    
    public static OAuthIssuerKit uuidIssuer() {
    	return (new OAuthIssuerKit(new UUIDValueGenerator()));
    }
    
    public String accessToken() {
        return vg.generateValue();
    }

    public String refreshToken() {
        return vg.generateValue();
    }

    public String authorizationCode() {
        return vg.generateValue();
    }
}
