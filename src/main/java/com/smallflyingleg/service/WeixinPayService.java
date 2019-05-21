package com.smallflyingleg.service;

import java.util.Map;

/**
 * <p>
 * 微信支付接口
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public interface WeixinPayService {
	/**
	 * 生成微信支付二维码
	 * @param out_trade_no 订单号
	 * @param total_fee 金额(分)
	 * @return
	 */
	public Map createNative(String out_trade_no, String total_fee);
	
	/**
	 * 查询支付状态
	 * @param out_trade_no
	 */
	public Map queryPayStatus(String out_trade_no);
}
