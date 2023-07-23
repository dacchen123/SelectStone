package top.dacchen.lottery.domain.strategy.service.draw;

import top.dacchen.lottery.domain.strategy.model.vo.AwardRateInfo;
import top.dacchen.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import top.dacchen.lottery.infrastructure.po.StrategyDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/23
 * @Copyright： 最终解释权由dacchen保留
 */
public class DrawBase extends DrawConfig{
    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList) {
        if (1 != strategyMode) {
            return;
        }

        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);
        if (existRateTuple) {
            return;
        }

        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetail strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);
    }
}
