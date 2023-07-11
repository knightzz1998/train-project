package cn.knightzz.train.common.exception;

/**
 * @author 王天赐
 * @title: BusinessException
 * @projectName train-project
 * @description: 业务异常
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-07-11 17:46
 */
public class BusinessException extends RuntimeException {

    private BusinessExceptionEnum e;


    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }

    public BusinessExceptionEnum getE() {
        return e;
    }

    public void setE(BusinessExceptionEnum e) {
        this.e = e;
    }
}
