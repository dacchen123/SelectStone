package top.dacchen.lottery.domain.strategy.service.algorithm;

import top.dacchen.lottery.domain.strategy.model.vo.AwardRateVO;

import java.util.List;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/6/28
 * @Copyright： 最终解释权由dacchen保留
 */
public interface IDrawAlgorithm {

    /**
     *
     * @param strategyId 策略ID
     * @param excludeAwardIds 排除掉已经不能作为抽奖的奖品ID，留给风控和空库存使用
     * @return
     */
    String randomDraw(Long strategyId, List<String> excludeAwardIds);

    /**
     * 程序启动时初始化概率元组，在初始化完成后使用过程中不允许修改元组数据
     * @param strategyId
     * @param awardRateInfoList
     */
    void initRateTuple(Long strategyId, List<AwardRateVO> awardRateInfoList);

    /**
     * 判断是否已经做了数据初始化
     * @param strategyId
     * @return
     */
    boolean isExistRateTuple(Long strategyId);
}
