/**
 * 
 */
package cn.zhucongqi.oauth2.base.response.types;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public enum GrantType {
	
    // NONE("none"),
    AUTHORIZATION_CODE("authorization_code"),
    PASSWORD("password"),
    REFRESH_TOKEN("refresh_token"),
    CLIENT_CREDENTIALS("client_credentials");

    private String grantType;

    GrantType(String grantType) {
        this.grantType = grantType;
    }

    @Override
    public String toString() {
        return grantType;
    }
}
