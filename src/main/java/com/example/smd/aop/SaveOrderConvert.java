package com.example.smd.aop;

/**
 * @author shanmingda
 */
public class SaveOrderConvert implements Convert<SaveOrder>{
    @Override
    public OperateLogDO covert(SaveOrder saveOrder) {

        OperateLogDO operateLogDO = new OperateLogDO();
        operateLogDO.setOrderId(saveOrder.getId());
        return operateLogDO;
    }
}
