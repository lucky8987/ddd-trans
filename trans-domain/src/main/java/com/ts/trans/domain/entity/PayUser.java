package com.ts.trans.domain.entity;

import com.ts.trans.exception.InsufficientFundsException;
import com.ts.trans.exception.InvalidCurrencyException;
import com.ts.trans.types.*;
import lombok.Data;

/**
 * 交易值对象（entity）
 */
@Data
public class PayUser {

    /**
     * 会员编号
     */
    private MemberId id;

    /**
     * 名称
     */
    private String name;

    /**
     * 所属机构
     */
    private Org org;

    /**
     * 所属通道
     */
    private Channel channel;

    /**
     * 可用余额
     */
    private Money available;

    /**
     * 支付密码
     */
    private PayPwd payPwd;

    /**
     * 校验方式
     */
    private PayVerifyMethodEnum verifyMethod;


    /**
     * 密码校验
     * @return
     */
    public boolean isVerifyByPwd() {
        return PayVerifyMethodEnum.PASSWORD == verifyMethod;
    }

    /**
     * 短信校验
     * @return
     */
    public boolean isVerifyByCode() {
        return PayVerifyMethodEnum.VERIFY_CODE == verifyMethod;
    }

    // 转出
    public void withdraw(Money money) throws Exception {
        if (this.available.compareTo(money) < 0){
            throw new InsufficientFundsException();
        }
        this.available = this.available.subtract(money);
    }

    // 转入
    public void deposit(Money money) throws Exception {
        if (!this.getAvailable().getCurrency().equals(money.getCurrency())){
            throw new InvalidCurrencyException();
        }
        this.available = this.available.add(money);
    }


}
