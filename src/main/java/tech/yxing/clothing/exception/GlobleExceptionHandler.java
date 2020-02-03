package tech.yxing.clothing.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.yxing.clothing.result.CodeMsg;
import tech.yxing.clothing.result.Result;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

@ControllerAdvice(basePackages = "tech.yxing.clothing.controller")
@ResponseBody
public class GlobleExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {

		if (e instanceof BindException) {//绑定异常（端口被占用，通常出现在启动服务的时候）
			e.printStackTrace();
			BindException exception = (BindException) e;
			//异常信息
			String message = exception.getMessage();
			return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
		} else if (e instanceof GlobleException) {
			GlobleException exception = (GlobleException) e;
			return Result.error(exception.getCm());

//		} else {
//			return Result.error(CodeMsg.SERVER_ERROR);
		} else {
			e.printStackTrace();
		}
		return null;
	}
}
