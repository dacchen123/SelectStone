package top.dacchen.lottery.domain.strategy.service.algorithm.impl;

import org.springframework.stereotype.Component;
import top.dacchen.lottery.common.StrategyModeEnum;
import top.dacchen.lottery.domain.strategy.annotation.StrategyMode;
import top.dacchen.lottery.domain.strategy.service.algorithm.BaseAlgorithm;

import java.security.SecureRandom;
import java.util.List;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/17
 * @Copyright： 最终解释权由dacchen保留
 */
@Component("singleRateRandomDrawAlgorithm")
@StrategyMode(strategyMode = StrategyModeEnum.SINGLE_RATE_RANDOM_DRAW_ALGORITHM)
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        // 获取策略对应的元组
        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        // 随机索引
        int randomVal = new SecureRandom().nextInt(100) + 1;
        int idx = super.hashIdx(randomVal);

        // 返回结果
        String awardId = rateTuple[idx];
        if (excludeAwardIds.contains(awardId)) return "未中奖";

        return awardId;
    }
}
