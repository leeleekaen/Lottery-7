package com.bob.lottery.bean;

import com.bob.lottery.util.GlobalParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/5.
 */

//购物车
public class ShoppingCart {
    private static ShoppingCart instance = new ShoppingCart();

    private ShoppingCart() {
    }

    public static ShoppingCart getInstance() {
        return instance;
    }
    // lotteryid string * 玩法编号
    // issue string * 期号（当前销售期）
    // lotterycode string * 投注号码，注与注之间^分割
    // lotterynumber string 注数
    // lotteryvalue string 方案金额，以分为单位

    // appnumbers string 倍数
    // issuesnumbers string 追期
    // issueflag int * 是否多期追号 0否，1多期
    // bonusstop int * 中奖后是否停止：0不停，1停


    private Integer lotteryid;//玩法编号
    private String issue;//期号（当前销售期）


    public Integer getLotteryid() {
        return lotteryid;
    }

    public void setLotteryid(Integer lotteryid) {
        this.lotteryid = lotteryid;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }


    private List<Ticket> tickets = new ArrayList<Ticket>();//投注号码，注与注之间^分割
    private Integer lotterynumber;// 计算，注数
    private Integer lotteryvalue;//方案金额，以分为单位



    public List<Ticket> getTickets() {
        return tickets;
    }

    public Integer getLotteryvalue() {
        lotteryvalue=2*getLotterynumber();
        return lotteryvalue;
    }

    public Integer getLotterynumber() {
        lotterynumber=0;
        for (Ticket item: tickets){
            lotterynumber+=item.getNum();
        }
        return lotterynumber;
    }

    private Integer appnumbers = 1;
    private Integer issuesnumbers = 1;

    /**
     * 操作倍数
     */
    public boolean addAppnumbers(boolean isAdd) {
        if (isAdd) {
            appnumbers++;
            if (appnumbers > 99) {
                appnumbers--;
                return false;
            }

            if (getLotteryvalue() > GlobalParams.MONEY) {
                appnumbers--;
                return false;
            }
        } else {
            appnumbers--;
            if (appnumbers == 0) {
                appnumbers++;
                return false;
            }
        }
        return true;
    }

    /**
     * 操作追期
     */
    public boolean addIssuesnumbers(boolean isAdd) {
        if (isAdd) {
            issuesnumbers++;
            if (issuesnumbers > 99) {
                issuesnumbers--;
                return false;
            }

            if (getLotteryvalue() > GlobalParams.MONEY) {
                issuesnumbers--;
                return false;
            }
        } else {
            issuesnumbers--;
            if (issuesnumbers == 0) {
                issuesnumbers++;
                return false;
            }
        }
        return true;
    }

    public void clear() {
        tickets.clear();
        lotterynumber = 0;
        lotteryvalue = 0;

        appnumbers = 1;
        issuesnumbers = 1;
    }

    public Integer getAppnumbers() {
        return appnumbers;
    }

    public Integer getIssuesnumbers() {
        return issuesnumbers;
    }
}
