/**
 * 
 */

package cn.zhucongqi.oauth2.parameters;

import java.util.Map;

import com.jfinal.kit.JsonKit;

import cn.zhucongqi.oauth2.base.response.OAuthMessage;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class JSONBodyParametersApplier {

    public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params) {
        String json = JsonKit.toJson(params);
        message.setBody(json);
        return message;
    }

}
