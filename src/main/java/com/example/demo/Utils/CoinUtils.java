package com.example.demo.Utils;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;


import com.googlecode.jsonrpc4j.Base64;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.stereotype.Service;

@Service
public class CoinUtils {


    private static CoinUtils instance;
    private static void init() throws Throwable {
        if(null == instance){
            instance = new CoinUtils();
        }
    }

    private JsonRpcHttpClient client;
    public CoinUtils() throws Throwable{
        // 身份认证
        String cred = Base64.encodeBytes((Constants.RPC_USER + ":" + Constants.RPC_PASSWORD).getBytes());
        Map<String, String> headers = new HashMap<String, String>(1);
        headers.put("Authorization", "Basic " + cred);
        client = new JsonRpcHttpClient(new URL("http://"+Constants.RPC_ALLOWIP+":"+Constants.RPC_PORT), headers);
    }


    public static CoinUtils getInstance() throws Throwable {
        init();
        return instance;
    }


    /**
     * 验证地址是否存在
     * @param address
     * @return
     * @throws Throwable
     */
    public String validateaddress(String address)throws Throwable{
        return  (String) client.invoke("validateaddress", new Object[] {address}, Object.class).toString();
    }


    /**
     * 如果钱包加密需要临时解锁钱包
     * @param password
     * @param time
     * @return
     * @throws Throwable
     */
    public String walletpassphase(String password,int time)throws Throwable{
        return  (String) client.invoke("walletpassphase", new Object[] {password,time}, Object.class).toString();
    }

    /**
     * 转账到制定的账户中
     * @param address
     * @param amount
     * @return
     * @throws Throwable
     */
    public String sendtoaddress(String address,double amount)throws Throwable{
        return  (String) client.invoke("sendtoaddress", new Object[] {address,amount}, Object.class).toString();
    }

    /**
     * 查询账户下的交易记录
     * @param account
     * @param count
     * @param offset
     * @return
     * @throws Throwable
     */
    public String listtransactions(String account, int count ,int offset )throws Throwable{
        return  (String) client.invoke("listtransactions", new Object[] {account,count,offset}, Object.class).toString();
    }

    /**
     * 获取地址下未花费的币量
     * @param minconf
     * @param maxconf
     * @param address
     * @return
     * @throws Throwable
     */
    public String listunspent( int minconf ,int maxconf ,String address)throws Throwable{
        String[] addresss= new String[]{address};
        return  (String) client.invoke("listunspent", new Object[] {minconf,maxconf,addresss}, Object.class).toString();
    }

    /**
     * 生成新的接收地址
     * @return
     * @throws Throwable
     */
    public String getNewaddress() throws Throwable{
        return  (String) client.invoke("getnewaddress", new Object[] {}, Object.class).toString();
    }

    /**
     * 获取钱包信息
     * @return
     * @throws Throwable
     */
    public String getWalletInfo() throws Throwable{
        return  client.invoke("getwalletinfo", new Object[] {}, Object.class).toString();
    }

    /**
     * 获取区块信息
     * @return
     * @throws Throwable
     */
    public String getBlockChainInfo() throws  Throwable{
        return client.invoke("getblockchaininfo",new Object(){},Object.class).toString();
    }

    /**
     * 获取区块的Hashc
     * @return
     * @throws Throwable
     */
    public String getBlockHash() throws  Throwable{
        return client.invoke("getblockhash",new Object(){},Object.class).toString();
    }

    /**
     * 获取区块详细信息
     * @return
     * @throws Throwable
     */
    public String getBlock() throws  Throwable{
        return client.invoke("getblock",new Object(){},Object.class).toString();
    }

    /**
     * 指定账户关联的所有地址列表
     * @param account
     * @return
     * @throws Throwable
     */
    public String getAddressesbyaccount(String account)throws Throwable{
        return (String )client.invoke("getaddressesbyaccount",new Object[]{account},Object.class).toString();
    }


    /**
     * 获取账户余额
     * @param  account
     * @return
     * @throws Throwable
     */
    public String getBalance(String account) throws  Throwable{
        return (String)client.invoke("getbalance",new Object[]{account},Object.class).toString();
    }

    public String createrawtransaction(String txid) throws  Throwable{
        return (String)client.invoke("createrawtransaction",new Object[]{txid},Object.class).toString();
    }

}

