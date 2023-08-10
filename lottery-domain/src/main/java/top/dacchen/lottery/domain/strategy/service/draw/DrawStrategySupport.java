package top.dacchen.lottery.domain.strategy.service.draw;

import top.dacchen.lottery.domain.strategy.model.aggregates.StrategyRich;
import top.dacchen.lottery.domain.strategy.model.vo.AwardBriefVO;
import top.dacchen.lottery.domain.strategy.repository.IStrategyRepository;

import javax.annotation.Resource;

/**
 * @description: 抽奖策略数据支撑，一些通用的数据服务
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/23
 * @Copyright： 最终解释权由dacchen保留
 */
public class DrawStrategySupport extends DrawConfig{

    @Resource
    protected IStrategyRepository strategyRepository;

    /**
     * 查询策略配置信息
     * @param strategyId 策略id
     * @return 策略配置信息
     */
    protected StrategyRich queryStrategyRich(Long strategyId) {
        return strategyRepository.queryStrategyRich(strategyId);
    }

    /**
     * 查询奖品详情信息
     * @param awardId 奖品ID
     * @return 中奖详情
     */
    protected AwardBriefVO queryAwardInfoByAwardId(String awardId) {
        return strategyRepository.queryAwardInfo(awardId);
    }

}
