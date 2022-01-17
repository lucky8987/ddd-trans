package com.ts.trans.domain.service;

import com.ts.trans.domain.entity.OrderInfo;
import com.ts.trans.domain.entity.PayUser;
import com.ts.trans.domain.external.ChannelService;
import com.ts.trans.domain.external.CommonService;
import com.ts.trans.domain.external.MemberService;
import com.ts.trans.domain.repository.OrderInfoRepository;
import com.ts.trans.exception.InsufficientFundsException;
import com.ts.trans.types.*;
import java.math.BigDecimal;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试：对核心业务流程类，覆盖率：100%
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@Slf4j
public class TransferServiceTest {

    @Rule
    public ExpectedException exptException = ExpectedException.none();

    @Autowired
    private TransferService transferService;

    @MockBean
    private MemberService memberService;

    @MockBean
    private CommonService commonService;

    @MockBean
    private ChannelService channelService;

    @MockBean
    private OrderInfoRepository orderInfoRepository;

    private static final Currency CNY = new Currency("CNY");

    private PayUser sourceUser = new PayUser();

    private PayUser targetUser = new PayUser();

    @Before
    public void init() {
        Mockito.when(commonService.getId()).thenReturn(new Random().nextLong());

        // 初始化-付款方
        sourceUser.setId(new MemberId("1111"));
        sourceUser.setName("付款人");
        sourceUser.setOrg(new Org("test_org"));
        sourceUser.setPayPwd(new PayPwd("*******"));
        sourceUser.setVerifyMethod(PayVerifyMethodEnum.PASSWORD);
        sourceUser.setChannel(new Channel("bank_test"));

        // 初始化-收款方
        targetUser.setId(new MemberId("2222"));
        targetUser.setName("收款人");
        targetUser.setOrg(new Org("test_org"));
        targetUser.setChannel(new Channel("bank_test"));
    }

    /**
     * case：转账-成功
     * 付款方 -转账40-> 收款方
     */
    @Test
    public void transferSuccess() throws Exception {
        // 密码校验：true
        Mockito.when(memberService.checkPayPwd(Mockito.any())).thenReturn(true);
        // 付款方余额：100 元
        Mockito.when(memberService.balance(sourceUser.getId())).thenReturn(new Money(new BigDecimal(100), CNY));
        // 收款方余额：10 元
        Mockito.when(memberService.balance(targetUser.getId())).thenReturn(new Money(new BigDecimal(10), CNY));
        // 持久化：true
        Mockito.when(orderInfoRepository.save(Mockito.any())).thenReturn(true);
        // 渠道交易：success
        Mockito.when(channelService.agentPay(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(OrderStatusEnum.SUCCESS);
        // 发起转账，转账金额：40元
        OrderInfo transferOrderInfo = transferService.transfer(sourceUser, targetUser, new Money(new BigDecimal(40), CNY));

        // 订单状态校验
        Assert.assertTrue(transferOrderInfo.getOrderStatus() == OrderStatusEnum.SUCCESS);

        // 资金校验
        Assert.assertTrue(sourceUser.getAvailable().getAmout().equals(new BigDecimal(60)));
        Assert.assertTrue(targetUser.getAvailable().getAmout().equals(new BigDecimal(50)));
    }

    /**
     * case: 转账-资金异常：InsufficientFundsException
     * @throws Exception
     */
    @Test(expected = InsufficientFundsException.class)
    public void transferFundExp() throws Exception {
        // 密码校验：true
        Mockito.when(memberService.checkPayPwd(Mockito.any())).thenReturn(true);
        // 付款方余额：100 元
        Mockito.when(memberService.balance(sourceUser.getId())).thenReturn(new Money(new BigDecimal(20), CNY));
        // 收款方余额：10 元
        Mockito.when(memberService.balance(targetUser.getId())).thenReturn(new Money(new BigDecimal(10), CNY));
        // 持久化：true
        Mockito.when(orderInfoRepository.save(Mockito.any())).thenReturn(true);

        // 发起转账，转账金额：40元, 预期抛出异常
        transferService.transfer(sourceUser, targetUser, new Money(new BigDecimal(40), CNY));
    }

    /**
     * case：转账-存储异常
     * @throws Exception
     */
    @Test
    public void transferSaveExp() throws Exception {
        // 密码校验：true
        Mockito.when(memberService.checkPayPwd(Mockito.any())).thenReturn(true);
        // 付款方余额：100 元
        Mockito.when(memberService.balance(sourceUser.getId())).thenReturn(new Money(new BigDecimal(100), CNY));
        // 收款方余额：10 元
        Mockito.when(memberService.balance(targetUser.getId())).thenReturn(new Money(new BigDecimal(10), CNY));
        // 持久化：false
        Mockito.when(orderInfoRepository.save(Mockito.any())).thenReturn(false);

        // 预期抛出异常
        exptException.expect(RuntimeException.class);
        exptException.expectMessage("数据存储失败");

        // 发起转账，转账金额：40元, 预期抛出异常
        transferService.transfer(sourceUser, targetUser, new Money(new BigDecimal(40), CNY));
    }

    /**
     * case：转账-失败
     * @throws Exception
     */
    @Test
    public void transferFail() throws Exception {
        // 密码校验：true
        Mockito.when(memberService.checkPayPwd(Mockito.any())).thenReturn(true);
        // 付款方余额：100 元
        Mockito.when(memberService.balance(sourceUser.getId())).thenReturn(new Money(new BigDecimal(100), CNY));
        // 收款方余额：10 元
        Mockito.when(memberService.balance(targetUser.getId())).thenReturn(new Money(new BigDecimal(10), CNY));
        // 持久化：true
        Mockito.when(orderInfoRepository.save(Mockito.any())).thenReturn(true);
        // 渠道交易：fail
        Mockito.when(channelService.agentPay(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(OrderStatusEnum.FAIL);

        // 发起转账，转账金额：40元, 预期失败
        OrderInfo transferOrderInfo = transferService.transfer(sourceUser, targetUser, new Money(new BigDecimal(40), CNY));

        // 订单状态校验
        Assert.assertTrue(transferOrderInfo.getOrderStatus() == OrderStatusEnum.FAIL);

        // 资金校验
        Assert.assertTrue(sourceUser.getAvailable().getAmout().equals(new BigDecimal(100)));
        Assert.assertTrue(targetUser.getAvailable().getAmout().equals(new BigDecimal(10)));
    }
}