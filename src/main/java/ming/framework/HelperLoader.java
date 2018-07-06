package ming.framework;

import ming.framework.ioc.IocHelper;
import ming.framework.plugin.PluginHelper;
import ming.framework.aop.AopHelper;
import ming.framework.dao.DatabaseHelper;
import ming.framework.ioc.BeanHelper;
import ming.framework.mvc.ActionHelper;
import ming.framework.orm.EntityHelper;
import ming.framework.util.ClassUtil;

/**
 * 加载相应的 Helper 类
 *
 * @author liuluming
 * @since 2.0
 */
public final class HelperLoader {

    public static void init() {
        // 定义需要加载的 Helper 类
        Class<?>[] classList = {
            DatabaseHelper.class,
            EntityHelper.class,
            ActionHelper.class,
            BeanHelper.class,
            AopHelper.class,
            IocHelper.class,
            PluginHelper.class,
        };
        // 按照顺序加载类
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
