/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，1995-2017，所有权利保留。
 * 项目名：gift-card
 * 文件名：ResponseResult
 * 模块说明：
 * 修改历史：
 * 2017/8/1 - liweihua - 创建
 */
package com.example.demo.api.base;

import java.util.HashSet;

public enum ReturnCodeEnum {
    SUCCESS("0000", "成功"),
    FAIL("0500", "处理失败，服务器异常"),
    BAD_REQUEST("0400", "请求参数有误"),
    NOT_FOUND_RESULT("0600","找不到结果"),
    AUTH_FAIL("3004", "鉴权失败，请重新登录"),
    JOB_ISSUE_CHECK_ERROR("6666", "券job处理验证出错"),
    COUPON_HAS_CHANGE_OWNER("7100", "晚来一步，券已被别人领走了"),
    COUPON_ABORTED("7101", "券已作废，不能再领取了"),
    COUPON_CONSUMED("7102", "券已使用，不能再领取了"),
    COUPON_EXPIRED("7103", "券已过期，不能再领取了"),
    COUPON_MEMBER_NOT_MATCH("7104", "券属于别人了"),
    COUPON_CHANGE_OWNER_NOT_SUPPORT("7105", "券不支持转赠"),
    COUPON_CHANGE_OWNER_REACH_CEILING("7106", "券转赠达到上限"),
    COUPON_NOT_FOUND("7107", "获取券信息失败"),
    COUPON_STOCK_NOT_ENOUGH("8000", "券库存不足");
    /**
     * The value.
     */
    private final String value;

    /**
     * The desc.
     */
    private final String desc;

    private ReturnCodeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String value() {
        return value;
    }

    /**
     * Gets the desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    private static HashSet<String> hashSet;

    static {
        hashSet = new HashSet<String>();
        hashSet.clear();
        for (ReturnCodeEnum returnCodeEnum : ReturnCodeEnum.values()) {
            hashSet.add(returnCodeEnum.value());
        }
    }

    public static boolean isDefined(Integer value) {
        if (hashSet.contains(value)) {
            return true;
        }
        return false;
    }
}
