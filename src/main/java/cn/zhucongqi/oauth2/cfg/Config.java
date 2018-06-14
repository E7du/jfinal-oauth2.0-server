/**
 * JFinal 的配置信息
 */
package cn.zhucongqi.oauth2.cfg;

import cn.zhucongqi.oauth2.data.DataKit;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext2.config.JFinalConfigExt;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class Config extends JFinalConfigExt {
	
	public void afterJFinalStarted(){
		//初始化数据
		DataKit.init();
	}

	@Override
	public void configMoreConstants(Constants me) {
		
	}

	@Override
	public void configMoreRoutes(Routes me) {

	}

	@Override
	public void configMorePlugins(Plugins me) {
	}

	@Override
	public void configTablesMapping(String configName, ActiveRecordPlugin arp) {
		// 关联数据库表
	}

	@Override
	public void configMoreInterceptors(Interceptors me) {

	}

	@Override
	public void configMoreHandlers(Handlers me) {
		
	}
}
