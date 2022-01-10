package com.ts.trans.domain.external;

import com.ts.trans.domain.entity.PayUser;
import com.ts.trans.types.ControlType;
import com.ts.trans.types.MemberId;
import com.ts.trans.types.Money;

/**
 * 会员服务接口
 */
public interface MemberService {

    /**
     * 支付密码校验
     * @return
     */
    boolean checkPayPwd(PayUser payUser);

    /**
     * 获取商户详情
     * @param memberId
     */
    PayUser getMeberInfo(MemberId memberId);

    /**
     * 产品权限校验
     * @param payUser
     * @param type
     */
    void checkPermission(PayUser payUser, ControlType type);

    /**
     * 余额查询
     * @return
     */
    Money balance(MemberId memberId);
}
