package slancer.mindfly.service.account.auth.argresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import slancer.mindfly.core.exception.ExceptionBuilder;
import slancer.mindfly.service.account.entity.UserEntity;
import slancer.mindfly.service.account.error.AccountErrorCodeEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * class UserEntityMethodArgResolver
 *
 * @author xiayy860612
 * @date 2018/6/7
 */
public class UserEntityMethodArgResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(UserEntity.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest,
            WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Object obj = request.getAttribute(UserEntity.class.getName());
        if (obj == null) {
            throw ExceptionBuilder.build(AccountErrorCodeEnum.NotSetUserEntityDuringAuth);
        }
        return obj;
    }
}
