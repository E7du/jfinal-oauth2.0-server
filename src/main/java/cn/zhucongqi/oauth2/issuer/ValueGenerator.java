/**
 * 
 */
package cn.zhucongqi.oauth2.issuer;

import java.util.UUID;


/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public abstract class ValueGenerator {
	
    public String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    public abstract String generateValue(String param);
}
