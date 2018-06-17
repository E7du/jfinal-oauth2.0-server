/**
 * 
 */

package cn.zhucongqi.oauth2.issuer;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public final class OAuthIssuerKit implements OAuthIssuer {

    private ValueGenerator vg = null;

    public OAuthIssuerKit(ValueGenerator vg) {
        this.vg = vg;
    }

    public static OAuthIssuerKit issuer() {
    	return (new OAuthIssuerKit(new ValueGenerator()));
    }
    
    public String accessToken() {
        return this.vg.generateValue();
    }

    public String refreshToken() {
        return this.vg.generateValue();
    }

    public String authorizationCode() {
        return this.vg.generateValue();
    }
}
