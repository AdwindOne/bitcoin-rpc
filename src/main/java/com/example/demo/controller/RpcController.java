package com.example.demo.controller;

import com.example.demo.Utils.CoinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Adwind.
 * User: liuyunlong
 * Date: 2018/10/10
 * Time: 11:43
 */
@RestController
@RequestMapping(value = "/rpc")
public class RpcController {
    @Autowired
    private CoinUtils coin;

    @GetMapping(value = "/getWalletInfo")
    public Object getWalletInfo() {
        try {
            String info = coin.getWalletInfo();
            System.out.println(info);
            return info;
        }catch (Throwable throwable){
            return throwable;
        }
    }
     @GetMapping(value = "/getBlock")
     public Object getBlock() {
            try {
                String info = coin.getBlock();
                System.out.println(info);
                return info;
            }catch (Throwable throwable){
                return throwable;
            }
        }

    @GetMapping(value = "/getAddressesByAccount")
    public Object getAddressesbyaccount(){
        try {
            String info = coin.getAddressesbyaccount("");
            return info;
        }catch (Throwable throwable) {
            return throwable;
        }
    }

    @GetMapping(value = "/getBalance")
    public Object getBalance(){
        try {
            String info = coin.getBalance("");
            return info;
        }catch (Throwable throwable) {
            return throwable;
        }
    }

    @GetMapping(value = "/transfer")
    public Object sendtoaddress(){
        try{
            String txhash = coin.sendtoaddress("2MsVCvmaYByT6eBqyXRFS6hCD7UK5sKmYZD",0.99996240);
            return txhash;
        }catch (Throwable throwable){
            return throwable;
        }
    }

    @GetMapping(value = "/listtransactions")
    public Object listtransactions(){
        try{
            String txhash = coin.listtransactions("",10,20);
            return txhash;
        }catch (Throwable throwable){
            return throwable;
        }
    }

}
