package top.dacchen.lottery.domain.award.service.factory;

import org.springframework.stereotype.Service;
import top.dacchen.lottery.domain.award.service.goods.IDistributionGoods;

/**
 * @description: 配送商品简单工厂，提供获取配送服务
 * @author：dacchen，微信：c18946551961
 * @date: 2023/8/8
 * @Copyright： 最终解释权由dacchen保留
 */
@Service
public class DistributionGoodsFactory extends GoodsConfig{

    public IDistributionGoods getDistributionGoodsService(Integer awardType){
        return goodsMap.get(awardType);
    }
}
