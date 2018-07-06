package ming.framework.mvc.bean;

import ming.framework.core.bean.BaseBean;

/**
 * 封装返回数据
 *
 * @author liuluming
 * @since 1.0
 */
public class JsonViewResult extends BaseBean {

    /**
     * 结果编码（=0:成功，<0:系统异常，>0:业务异常，默认为0）
     */
    private int resultCode = 0;

    /**
     * 结果说明信息
     */
    private String message;

    /**
     * 响应内容
     */
    private Object content;

    public JsonViewResult(int resultCode, String message, Object content) {
        this.message = message;
        this.resultCode = resultCode;
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
