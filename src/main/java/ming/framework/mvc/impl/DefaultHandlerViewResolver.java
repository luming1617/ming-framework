package ming.framework.mvc.impl;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ming.framework.FrameworkConstant;
import ming.framework.mvc.UploadHelper;
import ming.framework.mvc.bean.JsonViewResult;
import ming.framework.util.MapUtil;
import ming.framework.mvc.ViewResolver;
import ming.framework.mvc.bean.ViewResult;
import ming.framework.util.WebUtil;

/**
 * 默认视图解析器
 *
 * @author liuluming
 * @since 2.3
 */
public class DefaultHandlerViewResolver implements ViewResolver {

    @Override
    public void resolveResult(HttpServletRequest request, HttpServletResponse response, Object actionMethodResult) {
        if (actionMethodResult != null) {
            // Action 返回值可为 ViewResult 或 JsonViewResult
            if (actionMethodResult instanceof ViewResult) {
                // 若为 ViewResult，则需考虑两种视图类型（重定向 或 转发）
                ViewResult viewResult = (ViewResult) actionMethodResult;
                if (viewResult.isRedirect()) {
                    // 获取路径
                    String path = viewResult.getPath();
                    // 重定向请求
                    WebUtil.redirectRequest(path, request, response);
                } else {
                    // 获取路径
                    String path = FrameworkConstant.JSP_PATH + viewResult.getPath();
                    // 初始化请求属性
                    Map<String, Object> data = viewResult.getData();
                    if (MapUtil.isNotEmpty(data)) {
                        for (Map.Entry<String, Object> entry : data.entrySet()) {
                            request.setAttribute(entry.getKey(), entry.getValue());
                        }
                    }
                    // 转发请求
                    WebUtil.forwardRequest(path, request, response);
                }
            } else {
                // 若为 JsonViewResult，则需考虑两种请求类型（文件上传 或 普通请求）
                JsonViewResult jsonViewResult = (JsonViewResult) actionMethodResult;
                if (UploadHelper.isMultipart(request)) {
                    // 对于 multipart 类型，说明是文件上传，需要转换为 HTML 格式并写入响应中
                    WebUtil.writeHTML(response, jsonViewResult);
                } else {
                    // 对于其它类型，统一转换为 JSON 格式并写入响应中
                    WebUtil.writeJSON(response, jsonViewResult);
                }
            }
        }
    }
}
