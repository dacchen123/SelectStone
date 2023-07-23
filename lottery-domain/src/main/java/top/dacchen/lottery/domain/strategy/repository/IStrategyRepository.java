package top.dacchen.lottery.domain.strategy.repository;

import top.dacchen.lottery.domain.strategy.model.aggregates.StrategyRich;
import top.dacchen.lottery.infrastructure.po.Award;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/10
 * @Copyright： 最终解释权由dacchen保留
 */
public interface IStrategyRepository {

    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);
}
