/*
 * 
 */
package cn.zhucongqi.oauth2.base.response.types;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public enum ResponseType {

    CODE("code"),
    TOKEN("token");

    private String code;

    ResponseType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
