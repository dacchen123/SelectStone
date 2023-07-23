package top.dacchen.lottery.domain.strategy.service.draw;

import top.dacchen.lottery.domain.strategy.model.req.DrawReq;
import top.dacchen.lottery.domain.strategy.model.res.DrawResult;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/17
 * @Copyright： 最终解释权由dacchen保留
 */
public interface IDrawExec {

    DrawResult doDrawExec(DrawReq req);
}
