package com.example.smd.aop;

/**
 * @author shanmingda
 */
public class OperateLogDO {

    private Long orderId;

    private String desc;



    private String result;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "OperateLogDO{" +
                "orderId=" + orderId +
                ", desc='" + desc + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
