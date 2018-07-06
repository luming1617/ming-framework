package ming.framework.mvc.impl;

import ming.framework.FrameworkServiceException;
import ming.framework.mvc.HandlerExceptionResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ming.framework.mvc.bean.JsonViewResult;
import ming.framework.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认 Handler 异常解析器
 *
 * @author liuluming
 * @since 2.3
 */
public class DefaultHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(DefaultHandlerExceptionResolver.class);

    @Override
    public void resolveHandlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        // 判断异常原因
        Throwable cause = e.getCause();
        if (cause == null) {
            logger.error(e.getMessage(), e);
            return;
        }
        // 根据不同的响应内容类型，做相应的操作。(注意：即使发生异常，contentType也不会变)
        String contentType = response.getContentType();
        switch (contentType) {
            case WebUtil.RESPONSE_CONTENT_TYPE_FILE:
                // 跳转到500错误页面
                WebUtil.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, cause.getMessage(), response);
                break;
            case WebUtil.RESPONSE_CONTENT_TYPE_HTML:
                // 跳转到500错误页面
                WebUtil.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, cause.getMessage(), response);
                break;
            case WebUtil.RESPONSE_CONTENT_TYPE_PLAIN:
                JsonViewResult jsonViewResult;
                // 如果业务异常，将异常原因封装，并返回。
                if (cause instanceof FrameworkServiceException) {
                    FrameworkServiceException serviceException = (FrameworkServiceException) cause;
                    jsonViewResult = new JsonViewResult(serviceException.getCode(), serviceException.getMessage(), null);
                }
                // 如果其他运行时异常，统一返回“系统出现异常”。
                else {
                    jsonViewResult = new JsonViewResult(-1, "系统出现严重问题，请尽快排查", null);
                }
                WebUtil.writeJSON(response, jsonViewResult);
                break;
            case WebUtil.RESPONSE_CONTENT_TYPE_JSON:
                JsonViewResult jsonViewResult2;
                // 如果业务异常，将异常原因封装，并返回。
                if (cause instanceof FrameworkServiceException) {
                    FrameworkServiceException serviceException = (FrameworkServiceException) cause;
                    jsonViewResult2 = new JsonViewResult(serviceException.getCode(), serviceException.getMessage(), null);
                }
                // 如果其他运行时异常，统一返回“系统出现异常”。
                else {
                    jsonViewResult2 = new JsonViewResult(-1, "系统出现严重问题，请尽快排查", null);
                }
                WebUtil.writeJSON(response, jsonViewResult2);
                break;
            default:
        }


//
//        if (cause instanceof AuthcException) {
//            // 分两种情况进行处理
//            if (WebUtil.isAJAX(request)) {
//                // 跳转到 403 页面
//                WebUtil.sendError(HttpServletResponse.SC_FORBIDDEN, "", response);
//            } else {
//                // 重定向到首页
//                WebUtil.redirectRequest(FrameworkConstant.HOME_PAGE, request, response);
//            }
//        } else if (cause instanceof AuthzException) {
//            // 跳转到 403 页面
//            WebUtil.sendError(HttpServletResponse.SC_FORBIDDEN, "", response);
//        } else {
//            // 跳转到 500 页面
//            WebUtil.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, cause.getMessage(), response);
//        }
    }
}
