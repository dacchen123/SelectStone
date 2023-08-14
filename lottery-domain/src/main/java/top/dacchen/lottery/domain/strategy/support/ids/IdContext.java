package top.dacchen.lottery.domain.strategy.support.ids;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.dacchen.lottery.common.Constants;
import top.dacchen.lottery.domain.strategy.support.ids.policy.RandomNumeric;
import top.dacchen.lottery.domain.strategy.support.ids.policy.ShortCode;
import top.dacchen.lottery.domain.strategy.support.ids.policy.SnowFlake;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 创建ID生成策略对象，基于策略模式
 * @author：dacchen，微信：c18946551961
 * @date: 2023/8/14
 * @Copyright： 最终解释权由dacchen保留
 */
@Configuration
public class IdContext {

    @Bean
    public Map<Constants.Ids, IIdGenerator> idGenerator(SnowFlake snowFlake, ShortCode shortCode, RandomNumeric randomNumeric) {
        Map<Constants.Ids, IIdGenerator> idGeneratorMap = new HashMap<>();
        idGeneratorMap.put(Constants.Ids.SnowFlake, snowFlake);
        idGeneratorMap.put(Constants.Ids.ShortCode, shortCode);
        idGeneratorMap.put(Constants.Ids.RandomNumeric, randomNumeric);
        return idGeneratorMap;
    }
}
