package top.dacchen.lottery.domain.strategy.service.draw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.dacchen.lottery.common.DrawState;
import top.dacchen.lottery.domain.activity.model.vo.StrategyVO;
import top.dacchen.lottery.domain.strategy.model.aggregates.StrategyRich;
import top.dacchen.lottery.domain.strategy.model.req.DrawReq;
import top.dacchen.lottery.domain.strategy.model.res.DrawResult;
import top.dacchen.lottery.domain.strategy.model.vo.*;
import top.dacchen.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;


import java.util.ArrayList;
import java.util.List;

/**
 * @description: 定义抽象抽奖过程，模板模式
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/23
 * @Copyright： 最终解释权由dacchen保留
 */
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec{
    private Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        // 1.获取抽奖策略
        StrategyRich strategyRich = super.queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();

        // 2.校验抽奖策略是否已经初始化到内存
        this.checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(), strategyRich.getStrategyDetailList());

        // 3.获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等
        List<String> excludeAwardIds = this.queryExcludeAwardIds(req.getStrategyId());

        // 4.执行抽奖算法
        String awardId = this.drawAlgorithm(req.getStrategyId(), drawAlgorithmMap.get(strategy.getStrategyMode()), excludeAwardIds);

        // 5.包装中奖结果
        return buildDrawResult(req.getuId(), req.getStrategyId(), awardId, strategy);
    }

    /**
     * 包装抽奖结果
     * @param uId                 用户ID
     * @param strategyId          策略ID
     * @param awardId             奖品ID，null 情况：并发抽奖情况下，库存临界值1 -> 0，会有用户中奖结果为 null
     * @return 中奖结果
     */
    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId, StrategyBriefVO strategy) {
        if (awardId == null) {
            logger.info("执行策略抽奖完成【未中奖】，用户：{} 策略ID：{}", uId, strategyId);
            return new DrawResult(uId, strategyId, DrawState.FAIL.getCode());
        }

        AwardBriefVO award = super.queryAwardInfoByAwardId(awardId);
        DrawAwardVO drawAwardInfo = new DrawAwardVO(award.getAwardId(), award.getAwardType(), award.getAwardName(), award.getAwardContent());

        // 加上如下三个设置，就能设置好相应字段，否则为null
        drawAwardInfo.setStrategyMode(strategy.getStrategyMode());
        drawAwardInfo.setGrantType(strategy.getGrantType());
        drawAwardInfo.setGrantDate(strategy.getGrantDate());
        logger.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, awardId, award.getAwardName());

        return new DrawResult(uId, strategyId, DrawState.SUCCESS.getCode(), drawAwardInfo);
    }

    /**
     *  执行抽奖算法
     * @param strategyId        策略ID
     * @param iDrawAlgorithm    抽奖算法模型
     * @param excludeAwardIds   排除的抽奖ID集合
     * @return  中奖奖品ID
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm iDrawAlgorithm, List<String> excludeAwardIds);

    /**
     * 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等，这类数据是含有业务逻辑的，所以需要由具体的实现方决定
     * @param strategyId 策略ID
     * @return 排除的奖品ID集合
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    /**
     *  校验抽奖策略是否已经初始化到内存
     * @param strategyId                抽奖策略ID
     * @param strategyMode              抽奖策略模式
     * @param strategyDetailList        抽奖策略详情
     */
    private void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailList) {
        // 非单项概率，不必存入缓存
        // 如下代码注释，避免总体概率使用是未初始化报空指针错误
        // if (!StrategyModeEnum.SINGLE_RATE_RANDOM_DRAW_ALGORITHM.getId().equals(strategyMode)) {
        //     return;
        // }

        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        // 已经初始化过的数据，不必重复初始化
        if (drawAlgorithm.isExistRateTuple(strategyId)) {
            return;
        }

        // 解析并初始化中奖概率数据到散列表
        List<AwardRateVO> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetailBriefVO strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateVO(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);
    }
}
