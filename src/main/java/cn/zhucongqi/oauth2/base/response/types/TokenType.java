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

    TokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public String toString() {
        return tokenType;
    }
}
