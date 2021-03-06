package com.yongfeng.qianfeng.breadhunter.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CUSTOMER".
 */
public class Customer {

    private Long id;
    /** Not-null value. */
    private String customerName;

    public Customer() {
    }

    public Customer(Long id) {
        this.id = id;
    }

    public Customer(Long id, String customerName) {
        this.id = id;
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getCustomerName() {
        return customerName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
