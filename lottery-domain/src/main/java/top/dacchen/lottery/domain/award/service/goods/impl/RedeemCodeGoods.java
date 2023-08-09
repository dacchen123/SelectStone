package top.dacchen.lottery.domain.award.service.goods.impl;

import org.springframework.stereotype.Component;
import top.dacchen.lottery.common.Constants;
import top.dacchen.lottery.domain.award.model.req.GoodsReq;
import top.dacchen.lottery.domain.award.model.res.DistributionRes;
import top.dacchen.lottery.domain.award.service.goods.DistributionBase;
import top.dacchen.lottery.domain.award.service.goods.IDistributionGoods;

/**
 * @description: 兑换码类商品
 * @author：dacchen，微信：c18946551961
 * @date: 2023/8/9
 * @Copyright： 最终解释权由dacchen保留
 */
@Component
public class RedeemCodeGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {

        // 模拟调用兑换码
        logger.info("模拟调用兑换码 uId：{} awardContent：{}", req.getuId(), req.getAwardContent());

        // 更新用户领奖结果
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.RedeemCodeGoods.getCode();
    }

}
