package top.dacchen.lottery.domain.strategy.service.algorithm.impl;

import org.springframework.stereotype.Component;
import top.dacchen.lottery.common.StrategyModeEnum;
import top.dacchen.lottery.domain.strategy.annotation.StrategyMode;
import top.dacchen.lottery.domain.strategy.model.vo.AwardRateVO;
import top.dacchen.lottery.domain.strategy.service.algorithm.BaseAlgorithm;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 必中奖策略抽奖，排掉已经中奖的概率，重新计算中奖范围
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/17
 * @Copyright： 最终解释权由dacchen保留
 */
@Component("defaultRateRandomDrawAlgorithm")
@StrategyMode(strategyMode = StrategyModeEnum.DEFAULT_RATE_RANDOM_DRAW_ALGORITHM)
public class DefaultRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        //分母
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        // 排除掉不在抽奖范围的奖品ID集合
        List<AwardRateVO> differenceAwardRateList = new ArrayList<>();
        List<AwardRateVO> awardRateIntervalValList = awardRateInfoMap.get(strategyId);
        for (AwardRateVO awardRateInfo : awardRateIntervalValList) {
            String awardId = awardRateInfo.getAwardId();
            if (excludeAwardIds.contains(awardId)) {
                continue;
            }
            differenceAwardRateList.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        // 前置判断
        if (differenceAwardRateList.size() == 0) {
            return "";
        }
        if (differenceAwardRateList.size() == 1) {
            return differenceAwardRateList.get(0).getAwardId();
        }

        // 获取随机概率值
        SecureRandom secureRandom = new SecureRandom();
        int randomVal = secureRandom.nextInt(100) + 1;

        // 循环获取直至落在概率范围内的奖品
        String awardId = "";
        int cursorVal = 0;
        for (AwardRateVO awardRateInfo : differenceAwardRateList) {
            int rateVal = awardRateInfo.getAwardRate()
                    .divide(differenceDenominator, 2, BigDecimal.ROUND_UP)
                    .multiply(new BigDecimal(100))
                    .intValue();
            if (randomVal <= (cursorVal + rateVal)) {
                awardId = awardRateInfo.getAwardId();
                break;
            }
            cursorVal += rateVal;
        }

        // 返回中奖结果
        return awardId;
    }
}
