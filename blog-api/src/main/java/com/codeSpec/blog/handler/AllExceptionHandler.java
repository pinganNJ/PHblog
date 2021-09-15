package com.codeSpec.blog.handler;

/**
 * @Author: pinga-a
 * @Date: 2021-09-03 13:21
 * @Description: 在controller层进行全局异常处理，防止页面出现500，404
 */

/*//对加了@Controller注解的方法进行拦截处理 AOP的实现 （暂时关闭）
@ControllerAdvice
public class AllExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result doException(Exception e) {
        e.printStackTrace();
        //可以对不同的异常做一些定制化的处理
        if (e instanceof NegativeArraySizeException) {
            return Result.fail(1001, "NegativeArraySizeException异常");
        } else {
            return Result.fail(-999,"服务器异常");
        }
    }
}*/
