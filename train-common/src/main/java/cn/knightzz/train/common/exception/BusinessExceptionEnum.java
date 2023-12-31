package cn.knightzz.train.common.exception;

/**
 * @author 王天赐
 * @title: BusinessException
 * @projectName train-project
 * @description: 业务异常枚举类
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-07-11 17:43
 */
public enum BusinessExceptionEnum {

    /**
     * 注册手机号已存在
     */
    MEMBER_MOBILE_EXIST("手机号已存在!");

    /**
     * 定义枚举字段
     */
    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
