package top.dacchen.lottery.domain.strategy.repository.impl;

import org.springframework.stereotype.Component;
import top.dacchen.lottery.domain.strategy.model.aggregates.StrategyRich;
import top.dacchen.lottery.domain.strategy.repository.IStrategyRepository;
import top.dacchen.lottery.infrastructure.dao.IAwardDao;
import top.dacchen.lottery.infrastructure.dao.IStrategyDao;
import top.dacchen.lottery.infrastructure.dao.IStrategyDetailDao;
import top.dacchen.lottery.infrastructure.po.Award;
import top.dacchen.lottery.infrastructure.po.Strategy;
import top.dacchen.lottery.infrastructure.po.StrategyDetail;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/10
 * @Copyright： 最终解释权由dacchen保留
 */
@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);
        return new StrategyRich(strategyId, strategy, strategyDetailList);
    }

    @Override
    public Award queryAwardInfo(String awardId) {
        return awardDao.queryAwardInfo(awardId);
    }
}
