package top.dacchen.lottery.application.process.impl;

import org.springframework.stereotype.Service;
import top.dacchen.lottery.application.process.IActivityProcess;
import top.dacchen.lottery.application.process.req.DrawProcessReq;
import top.dacchen.lottery.application.process.res.DrawProcessResult;
import top.dacchen.lottery.common.Constants;
import top.dacchen.lottery.common.DrawState;
import top.dacchen.lottery.domain.activity.model.req.PartakeReq;
import top.dacchen.lottery.domain.activity.model.res.PartakeResult;
import top.dacchen.lottery.domain.activity.model.vo.DrawOrderVO;
import top.dacchen.lottery.domain.activity.service.partake.IActivityPartake;
import top.dacchen.lottery.domain.strategy.model.req.DrawReq;
import top.dacchen.lottery.domain.strategy.model.res.DrawResult;
import top.dacchen.lottery.domain.strategy.model.vo.DrawAwardVO;
import top.dacchen.lottery.domain.strategy.service.draw.IDrawExec;
import top.dacchen.lottery.domain.strategy.support.ids.IIdGenerator;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/12/25
 * @Copyright： 最终解释权由dacchen保留
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public DrawProcessResult doDrawProcess(DrawProcessReq req) {
        // 1. 领取活动
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getuId(), req.getActivityId()));
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())) {
            return new DrawProcessResult(partakeResult.getCode(), partakeResult.getInfo());
        }

        Long strategyId = partakeResult.getStrategyId();
        Long takeId = partakeResult.getTakeId();

        // 2. 执行抽奖，注意此处takeId承担了就是UUID的防重作用
        // 但需要注意的是，takeId传入后并没有使用
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(), strategyId, String.valueOf(takeId)));
        if (DrawState.FAIL.getCode().equals(drawResult.getDrawState())) {
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());
        }

        DrawAwardVO drawAwardInfo = drawResult.getDrawAwardInfo();

        // 3. 结果落库
        activityPartake.recordDrawOrder(buildDrawOrderVO(req, strategyId, takeId, drawAwardInfo));

        // 4.发送MQ，触发发奖流程

        // 5.返回结果
        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo(), drawAwardInfo);
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardVO drawAwardInfo) {
        long orderId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setuId(req.getuId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardInfo.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardInfo.getGrantType());
        drawOrderVO.setGrantDate(drawAwardInfo.getGrantDate());
        drawOrderVO.setGrantState(Constants.GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardInfo.getAwardId());
        drawOrderVO.setAwardType(drawAwardInfo.getAwardType());
        drawOrderVO.setAwardName(drawAwardInfo.getAwardName());
        drawOrderVO.setAwardContent(drawAwardInfo.getAwardContent());
        return drawOrderVO;
    }
}
