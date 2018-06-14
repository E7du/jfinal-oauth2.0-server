/**
 * 
 */
package cn.zhucongqi.oauth2.issuer;

import com.jfinal.kit.HashKit;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class MD5Generator extends ValueGenerator {

	@Override
	public String generateValue(String param) {
		return HashKit.md5(param);
	}
	
}
