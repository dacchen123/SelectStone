package top.dacchen.lottery.domain.strategy.service.draw.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.dacchen.lottery.domain.strategy.model.aggregates.StrategyRich;
import top.dacchen.lottery.domain.strategy.model.req.DrawReq;
import top.dacchen.lottery.domain.strategy.model.res.DrawResult;
import top.dacchen.lottery.domain.strategy.repository.IStrategyRepository;
import top.dacchen.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import top.dacchen.lottery.domain.strategy.service.draw.AbstractDrawBase;

import top.dacchen.lottery.domain.strategy.service.draw.IDrawExec;
import top.dacchen.lottery.infrastructure.po.Award;
import top.dacchen.lottery.infrastructure.po.Strategy;
import top.dacchen.lottery.infrastructure.po.StrategyDetail;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 抽象方法的具体实现类 DrawExecImpl，分别实现了 queryExcludeAwardIds、drawAlgorithm 两个方法，
 *               之所以定义这2个抽象方法，是因为这2个方法可能随着实现方有不同的方式变化，不适合定义成通用的方法。
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/23
 * @Copyright： 最终解释权由dacchen保留
 */
@Service("drawExec")
public class DrawExecImpl extends AbstractDrawBase {

    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);



    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds) {
        // 执行抽奖
        String awardId = drawAlgorithm.randomDraw(strategyId, excludeAwardIds);
        // 判断抽奖结果
        if (awardId == null) {
            return null;
        }
        // 扣减库存；暂采用数据库行级锁方式进行库存扣减，后续优化为Redis分布式锁扣减decr/incr
        boolean isSuccess = strategyRepository.deductStock(strategyId, awardId);
        // 返回结果，库存扣减成功返回奖品ID，否则返回null [实际业务场景中，如果中将奖品库存为空，则会发送兜底奖品，比如各类券]
        return isSuccess ? awardId : null;
    }

    @Override
    protected List<String> queryExcludeAwardIds(Long strategyId) {
        List<String> awardList = strategyRepository.queryNoStockStrategyAwardList(strategyId);
        logger.info("执行抽奖策略 strategyId：{}，无库存排除奖品列表ID集合 awardList：{}", strategyId, JSON.toJSONString(awardList));
        return awardList;
    }
}
