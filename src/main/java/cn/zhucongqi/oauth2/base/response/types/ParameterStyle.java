/**
 * 
 */
package cn.zhucongqi.oauth2.base.response.types;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public enum ParameterStyle {
	
    BODY("body"),
    QUERY("query"),
    HEADER("header");

    private String parameterStyle;

    ParameterStyle(String parameterStyle) {
        this.parameterStyle = parameterStyle;
    }

    @Override
    public String toString() {
        return parameterStyle;
    }
}
