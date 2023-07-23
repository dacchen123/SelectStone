package top.dacchen.lottery.domain.strategy.annotation;

import top.dacchen.lottery.common.StrategyModeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 抽奖策略模式注解
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/23
 * @Copyright： 最终解释权由dacchen保留
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StrategyMode {
    /**
     * 抽奖策略模式枚举
     * @return
     */
    StrategyModeEnum strategyMode();
}
