package com.concurrent.entity;

import lombok.Data;
import lombok.ToString;

/**
 * <h3>Account  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-09-09 17:02
 **/
@Data
@ToString
public class Account {
    private String accountNo;
    private String accountName;
    private boolean valid;

    public Account(){}

    public Account(String accountNo,String accountName,boolean valid){
        this.accountNo=accountNo;
        this.accountName=accountName;
        this.valid=valid;
    }
}
