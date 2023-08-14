package top.dacchen.lottery.domain.strategy.support.ids.policy;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import top.dacchen.lottery.domain.strategy.support.ids.IIdGenerator;

/**
 * @description: 工具类生成 org.apache.commons.lang3.RandomStringUtils
 * @author：dacchen，微信：c18946551961
 * @date: 2023/8/14
 * @Copyright： 最终解释权由dacchen保留
 */
@Component
public class RandomNumeric implements IIdGenerator {
    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
