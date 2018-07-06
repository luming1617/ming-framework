package ming.framework.mvc.bean;

import java.util.HashMap;
import java.util.Map;

import ming.framework.core.bean.BaseBean;

/**
 * 封装视图对象
 *
 * @author liuluming
 * @since 1.0
 */
public class ViewResult extends BaseBean {

    /**
     * 视图路径
     */
    private String path;

    /**
     * 相关数据
     */
    private Map<String, Object> data;

    public ViewResult(String path) {
        this.path = path;
        data = new HashMap<>();
    }

    public ViewResult data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public boolean isRedirect() {
        return path.startsWith("/");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
