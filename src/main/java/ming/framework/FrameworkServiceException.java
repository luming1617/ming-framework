package ming.framework;

import java.io.Serializable;

/**
 *
 * @author liuluming
 */
public class FrameworkServiceException extends RuntimeException {

    /**
     * 异常编码（取值必须大于0）
     */
    private final int code;

    /**
     * 系统ID
     */
    private  String systemId;

    /**
     * 扩展对象
     */
    private  Serializable data;

    public FrameworkServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public FrameworkServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public FrameworkServiceException(int code, String message, Serializable data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public FrameworkServiceException(int code, String message, Serializable data, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.data = data;
    }

    public FrameworkServiceException(int code, String message, String systemId, Serializable data) {
        super(message);
        this.code = code;
        this.systemId = systemId;
        this.data = data;
    }

    public FrameworkServiceException(int code, String message, String systemId, Serializable data, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.systemId = systemId;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getSystemId() {
        return systemId;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "FrameworkServiceException{" +
                "code='" + code + '\'' +
                ", message='" + getMessage() + '\'' +
                ", systemId='" + systemId + '\'' +
                ", data=" + data +
                '}';
    }
}
