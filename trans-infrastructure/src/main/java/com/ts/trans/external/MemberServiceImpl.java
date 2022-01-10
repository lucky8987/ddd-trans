package com.ts.trans.external;

import com.alibaba.fastjson.JSON;
import com.ts.pay.member.enums.BankAccountTypeEnums;
import com.ts.pay.member.facade.inner.AccountFacade;
import com.ts.pay.member.facade.inner.MemberFacade;
import com.ts.pay.member.facade.inner.MerchantFacade;
import com.ts.pay.member.facade.inner.PasswordFacade;
import com.ts.pay.member.request.BalanceRequest;
import com.ts.pay.member.request.CheckPayPwdRequest;
import com.ts.pay.member.request.PermissionRequest;
import com.ts.pay.member.request.QueryMemberAllRequest;
import com.ts.pay.member.response.BalanceResponse;
import com.ts.pay.member.response.QueryMemberInfoResponse;
import com.ts.trans.domain.entity.PayUser;
import com.ts.trans.domain.external.MemberService;
import com.ts.trans.types.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Reference
    private PasswordFacade passwordFacade;

    @Reference
    private MemberFacade memberFacade;

    @Reference
    private MerchantFacade merchantFacade;

    @Reference
    private AccountFacade accountFacade;

    /**
     * 校验支付密码
     * @return
     */
    @Override
    public boolean checkPayPwd(PayUser payUser) {
        CheckPayPwdRequest checkPayPwdRequest = new CheckPayPwdRequest();
        checkPayPwdRequest.setPassword(payUser.getPayPwd().getValue());
        checkPayPwdRequest.setInternalMemberNo(payUser.getId().getValue());
        return passwordFacade.checkPayPwd(checkPayPwdRequest);
    }

    @Override
    public PayUser getMeberInfo(MemberId memberId) {
        QueryMemberAllRequest queryMemberAllRequest = new QueryMemberAllRequest();
        queryMemberAllRequest.setQueryExtendInfo("1");
        queryMemberAllRequest.setInternalMemberNo(memberId.getStrValue());
        QueryMemberInfoResponse response = memberFacade.query(queryMemberAllRequest);
        // 将response转换为值对象
        PayUser payUser = new PayUser();
        payUser.setId(new MemberId(response.getMerchantInfoItem().getInternalMemberNo()));
        payUser.setOrg(new Org(response.getMerchantInfoItem().getOrgCode()));
        payUser.setChannel(new Channel(response.getMerchantInfoItem().getChannelCode()));
        return payUser;
    }

    @Override
    public void checkPermission(PayUser payUser, ControlType type) {
        PermissionRequest permissionRequest = new PermissionRequest();
        permissionRequest.setControlType(type.getCode());
        permissionRequest.setInternalMemberNo(payUser.getId().getValue());
        permissionRequest.setOrgCode(payUser.getOrg().getCode());
        log.info("校验产品权限请求数据:{}", JSON.toJSONString(permissionRequest));
        merchantFacade.checkPermission(permissionRequest);
    }

    @Override
    public Money balance(MemberId memberId) {
        BalanceRequest balanceRequest = new BalanceRequest();
        balanceRequest.setInternalMemberNo(memberId.getValue());
        balanceRequest.setBankAccountType(BankAccountTypeEnums.ELECTRONIC_ACCOUNT.getType());
        BalanceResponse balance = accountFacade.balance(balanceRequest);
        return new Money(balance.getAvailableBalance(), new Currency("CNY"));
    }

}
