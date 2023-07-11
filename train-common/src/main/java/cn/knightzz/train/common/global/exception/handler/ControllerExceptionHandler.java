package cn.knightzz.train.common.global.exception.handler;

import cn.knightzz.train.common.exception.BusinessException;
import cn.knightzz.train.common.response.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 王天赐
 * @title: ControllerExceptionHandler
 * @projectName train-project
 * @description:
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-07-11 17:35
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    public static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 处理非业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResp<String> exceptionHandler(Exception e) {

        CommonResp<String> commonResp = new CommonResp<>();
        LOG.error("系统异常", e);

        commonResp.setSuccess(false);
        commonResp.setMessage("系统执行错误, 请联系管理员");

        return commonResp;
    }

    /**
     * 处理业务异常
     *
     * @param e
     * @return 异常响应结果
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResp<String> businessExceptionHandler(BusinessException e) {

        CommonResp<String> commonResp = new CommonResp<>();
        LOG.error("系统异常", e);

        commonResp.setSuccess(false);
        commonResp.setMessage(e.getE().getDesc());

        return commonResp;
    }

}
