/**
 * 
 */
package cn.zhucongqi.oauth2.issuer;

import java.util.UUID;

import com.jfinal.kit.HashKit;


/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public final class ValueGenerator {
	
    public String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    public String generateValue(String param) {
        return HashKit.sha256(UUID.fromString(UUID.nameUUIDFromBytes(param.getBytes()).toString()).toString());
    }
}
