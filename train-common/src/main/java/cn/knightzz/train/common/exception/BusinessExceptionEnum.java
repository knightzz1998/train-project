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
    MEMBER_MOBILE_EXIST("手机号已存在!"),
    MEMBER_MOBILE_NOT_EXIST("手机号不存在!"),
    MEMBER_MOBILE_CODE_ERROR("短信验证码错误!"),
    BUSINESS_STATION_NAME_UNIQUE_ERROR("车站已存在"),
    BUSINESS_TRAIN_CODE_UNIQUE_ERROR("车次编号已存在"),
    BUSINESS_TRAIN_STATION_INDEX_UNIQUE_ERROR("同车次站序已存在"),
    BUSINESS_TRAIN_STATION_NAME_UNIQUE_ERROR("同车次站名已存在"),
    BUSINESS_TRAIN_CARRIAGE_INDEX_UNIQUE_ERROR("同车次厢号已存在");

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
