/**
 * 
 */
package cn.zhucongqi.oauth2.base.response.types;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public enum TokenType {
	
    BEARER("Bearer"),
    MAC("MAC");

    private String tokenType;

    TokenType(String grantType) {
        this.tokenType = grantType;
    }

    @Override
    public String toString() {
        return tokenType;
    }
}
