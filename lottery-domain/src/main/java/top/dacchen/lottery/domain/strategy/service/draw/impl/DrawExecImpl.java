package top.dacchen.lottery.domain.strategy.service.draw.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.dacchen.lottery.domain.strategy.model.aggregates.StrategyRich;
import top.dacchen.lottery.domain.strategy.model.req.DrawReq;
import top.dacchen.lottery.domain.strategy.model.res.DrawResult;
import top.dacchen.lottery.domain.strategy.repository.IStrategyRepository;
import top.dacchen.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import top.dacchen.lottery.domain.strategy.service.draw.DrawBase;
import top.dacchen.lottery.domain.strategy.service.draw.IDrawExec;
import top.dacchen.lottery.infrastructure.po.Award;
import top.dacchen.lottery.infrastructure.po.Strategy;
import top.dacchen.lottery.infrastructure.po.StrategyDetail;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/23
 * @Copyright： 最终解释权由dacchen保留
 */
@Service("drawExec")
public class DrawExecImpl extends DrawBase implements IDrawExec {

    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);

    @Resource
    private IStrategyRepository strategyRepository;


    @Override
    public DrawResult doDrawExec(DrawReq req) {
        logger.info("执行策略抽奖开始，strategyId：{}", req.getStrategyId());

        // 获取抽奖策略配置数据
        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();
        List<StrategyDetail> strategyDetailList = strategyRich.getStrategyDetailList();

        // 校验和初始化数据
        checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(), strategyDetailList);

        // 根据策略方式抽奖
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategy.getStrategyMode());
        String awardId = drawAlgorithm.randomDraw(req.getStrategyId(), new ArrayList<>());

        Award award = strategyRepository.queryAwardInfo(awardId);

        logger.info("执行策略抽奖完成，中奖用户：{} 奖品ID：{} 奖品名称：{}", req.getuId(), awardId, award.getAwardName());

        // 封装结果
        return new DrawResult(req.getuId(), req.getStrategyId(), awardId, award.getAwardName());
    }
}
