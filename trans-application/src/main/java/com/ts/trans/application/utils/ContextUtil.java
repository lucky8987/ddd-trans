package com.ts.trans.application.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ts.pay.common.constant.PayAppEnum;
import com.ts.pay.common.context.PayContext;
import com.ts.pay.common.context.PayUser;
import com.ts.pay.member.response.MerchantInfoItem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextUtil {

    public static PayUser getUser() {
        return PayContext.getUser();
    }

    public static PayUser getUser(boolean ifNullThr) {
        return PayContext.getUser(ifNullThr);
    }

    public static PayAppEnum getApp() {

        return PayContext.getApp();
    }

    public static String getUserId() {

        return PayContext.getUserId();
    }

    public static MerchantInfoItem getData() {
        try {
            PayUser payUser = getUser();
            JSONObject data;
            if (payUser.getData() instanceof JSONObject) {
                data = (JSONObject) payUser.getData();
            } else {
                log.info("The class of data is :{}", payUser.getData().getClass());
                data = JSON.parseObject(JSON.toJSONString(payUser.getData()),
                        JSONObject.class);
            }
            if (data == null) {
                log.warn("商户信息为空,重新登录后请重试");
                return null;
            }
            JSONObject merchantInfo = data.getJSONObject("merchantInfoItem");
            if (merchantInfo == null) {
                log.warn("商户信息为空,重新登录后请重试");
                return null;
            }

            return JSON.parseObject(merchantInfo.toJSONString(), MerchantInfoItem.class);
        }catch (Exception e){
            return null;
        }


    }

    public static String getOrgCode() {
        return PayContext.getOrgCode();
    }

    public static String getOriginOrgCode() {
        return PayContext.getOriginOrgCode();
    }

    public static String getAuthData() {
        return PayContext.getAuthData();
    }

    public static String getReqToken(){
        return PayContext.getReqToken();
    }

    public static String getClientIp(){
        return PayContext.getClientIp();
    }

    public static String getHeader(){
        return PayContext.getHeader();
    }
}
