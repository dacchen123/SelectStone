package top.dacchen.lottery.domain.award.service.factory;

import top.dacchen.lottery.common.Constants;
import top.dacchen.lottery.domain.award.service.goods.IDistributionGoods;
import top.dacchen.lottery.domain.award.service.goods.impl.CouponGoods;
import top.dacchen.lottery.domain.award.service.goods.impl.DescGoods;
import top.dacchen.lottery.domain.award.service.goods.impl.PhysicalGoods;
import top.dacchen.lottery.domain.award.service.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 各类发奖奖品配置类
 * @author：dacchen，微信：c18946551961
 * @date: 2023/8/8
 * @Copyright： 最终解释权由dacchen保留
 */
public class GoodsConfig {
    /** 奖品发放策略组 */
    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();

    @Resource
    private DescGoods descGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @PostConstruct
    public void init() {
        goodsMap.put(Constants.AwardType.DESC.getCode(), descGoods);
        goodsMap.put(Constants.AwardType.RedeemCodeGoods.getCode(), redeemCodeGoods);
        goodsMap.put(Constants.AwardType.CouponGoods.getCode(), couponGoods);
        goodsMap.put(Constants.AwardType.PhysicalGoods.getCode(), physicalGoods);
    }
}
