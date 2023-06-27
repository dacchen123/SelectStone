package top.dacchen.lottery.rpc;

import top.dacchen.lottery.rpc.req.ActivityReq;
import top.dacchen.lottery.rpc.res.ActivityRes;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/6/27
 * @Copyright： 最终解释权由dacchen保留
 */
public interface IActivityBooth {
    ActivityRes queryActivityById(ActivityReq req);
}
