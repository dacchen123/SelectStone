package top.dacchen.lottery.domain.award.service.goods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.dacchen.lottery.domain.award.repository.IAwardRepository;

import javax.annotation.Resource;

/**
 * @description: 配送货物基础共用类
 * @author：dacchen，微信：c18946551961
 * @date: 2023/8/8
 * @Copyright： 最终解释权由dacchen保留
 */
public class DistributionBase {
    protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);

    @Resource
    private IAwardRepository awardRepository;

    protected void updateUserAwardState(String uId, String orderId, String awardId, Integer awardState, String awardStateInfo) {
        // TODO 后期添加更新分库分表中，用户个人的抽奖记录表中奖品发奖状态
        logger.info("TODO 后期添加更新分库分表中，用户个人的抽奖记录表中奖品发奖状态 uId：{}", uId);
    }
}
