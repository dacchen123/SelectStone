package top.dacchen.lottery.domain.strategy.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;
import top.dacchen.lottery.domain.strategy.support.ids.IIdGenerator;

import javax.annotation.PostConstruct;

/**
 * @description: hutool 工具包下的雪花算法
 * @author：dacchen，微信：c18946551961
 * @date: 2023/8/14
 * @Copyright： 最终解释权由dacchen保留
 */
@Component
public class SnowFlake implements IIdGenerator {

    private Snowflake snowflake;

    @PostConstruct
    public void init() {
        // 0 ~ 31 位，可以采用配置的方式使用
        long workerId;
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workerId = NetUtil.getLocalhostStr().hashCode();
        }

        workerId = workerId >> 16 & 31;

        long dataCenterId = 1L;
        snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
    }

    /**
     * 注意方法上使用了同步限制
     * @return
     */
    @Override
    public synchronized long nextId() {
        return snowflake.nextId();
    }
}
