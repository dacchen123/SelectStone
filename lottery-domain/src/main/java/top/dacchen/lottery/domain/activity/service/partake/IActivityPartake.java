package top.dacchen.lottery.domain.activity.service.partake;

import top.dacchen.lottery.common.Result;
import top.dacchen.lottery.domain.activity.model.req.PartakeReq;
import top.dacchen.lottery.domain.activity.model.res.PartakeResult;
import top.dacchen.lottery.domain.activity.model.vo.DrawOrderVO;

/**
 * @description: 抽奖活动参与接口
 * @author：dacchen，微信：c18946551961
 * @date: 2023/8/9
 * @Copyright： 最终解释权由dacchen保留
 */
public interface IActivityPartake {
    /**
     * 参与活动
     * @param req
     * @return
     */
    PartakeResult doPartake(PartakeReq req);

    /**
     * 保存奖品单
     * @param drawOrder 奖品单
     * @return          保存结果
     */
    Result recordDrawOrder(DrawOrderVO drawOrder);
}
