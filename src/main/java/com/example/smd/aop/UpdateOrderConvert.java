package com.example.smd.aop;

/**
 * @author shanmingda
 */
public class UpdateOrderConvert implements Convert<UpdateOrder>{
    @Override
    public OperateLogDO covert(UpdateOrder updateOrder) {

        OperateLogDO operateLogDO = new OperateLogDO();
        operateLogDO.setOrderId(updateOrder.getOrderId());
        return operateLogDO;
    }
}
