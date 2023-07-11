package cn.knightzz.train.common.util;

import cn.hutool.core.util.IdUtil;

/**
 * @author 王天赐
 * @title: SnowUtil
 * @projectName train-project
 * @description: 封装雪花算法
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-07-11 19:31
 */
public class SnowUtil {

    /**
     * 数据中心
     */
    private static final long DATA_CENTER_ID = 1;
    /**
     * 机器ID
     */
    private static final long WORKER_ID = 1;

    public static long getSnowflakeNextId() {
        return IdUtil.getSnowflake(WORKER_ID, DATA_CENTER_ID).nextId();
    }

    public static String getSnowflakeNextIdStr() {
        return IdUtil.getSnowflake(WORKER_ID, DATA_CENTER_ID).nextIdStr();
    }
}
