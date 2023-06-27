package top.dacchen.lottery.rpc.req;

import java.io.Serializable;

/**
 * @description: Dubbo 接口无论是入参、出参，以及参数中的包括的对象，都必须 implements Serializable
 * @author：dacchen，微信：c18946551961
 * @date: 2023/6/27
 * @Copyright： 最终解释权由dacchen保留
 */
public class ActivityReq implements Serializable {
    private Long activityId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
