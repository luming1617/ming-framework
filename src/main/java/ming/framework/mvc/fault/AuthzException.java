package ming.framework.mvc.fault;

/**
 * 授权异常（当权限无效时抛出）
 *
 * @author liuluming
 * @since 2.1
 */
public class AuthzException extends RuntimeException {

    public AuthzException() {
        super();
    }

    public AuthzException(String message) {
        super(message);
    }

    public AuthzException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthzException(Throwable cause) {
        super(cause);
    }
}
