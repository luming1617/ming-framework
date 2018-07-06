package ming.framework.plugin;

import java.util.List;

import ming.framework.aop.proxy.Proxy;

/**
 * 插件代理
 *
 * @author liuluming
 * @since 2.0
 */
public abstract class PluginProxy implements Proxy {

    public abstract List<Class<?>> getTargetClassList();
}
