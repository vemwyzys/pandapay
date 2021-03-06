package config;

/**
 * 二维码支付参数
 * @author gql
 *
 */
public class PayConfig_code {
		
	/** 账号注册接口地址 */
    public static final String url_businessRegister = "http://www.4007112233.com:22001/mpcctp/org/reg.json";
    
    /** 创建订单接口地址 */
    public static final String url_createOrder = "http://www.4007112233.com:22001/mpcctp/org/pay.json";
    
    /** 查询订单接口地址 */
    public static final String url_queryOrder = "http://www.4007112233.com:22001/mpcctp/org/query.json";
    
    /** 机构号，平台下发的机构编号 */
    public static final String orgNo = "8161000204";
    
    /** 签名密钥（私钥） */
    public static final String secret_private = "FFFFFFFFFFFFFFFF";
    
    /** 支付回调服务器地址 */
    public static final String notifyUrl_pay_service = "http://www.nverzuida.com/pandapay/codePay/notify/notify.htm";
    
}
