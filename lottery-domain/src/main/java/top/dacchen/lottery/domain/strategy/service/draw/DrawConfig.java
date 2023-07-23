package top.dacchen.lottery.domain.strategy.service.draw;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import top.dacchen.lottery.common.StrategyModeEnum;
import top.dacchen.lottery.domain.strategy.annotation.StrategyMode;
import top.dacchen.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/23
 * @Copyright： 最终解释权由dacchen保留
 */
public class DrawConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();

    /**
     * @postConstruct注解 在服务器加载后运行，且只运行一次
     */
    @PostConstruct
    public void init() {
        Map<String, Object> strategyModeMap = applicationContext.getBeansWithAnnotation(StrategyMode.class);
        // strategyModeMap.entrySet().forEach(r -> {
        //     StrategyMode strategyMode = AnnotationUtils.findAnnotation(r.getValue().getClass(), StrategyMode.class);
        //     if (r.getValue() instanceof IDrawAlgorithm) {
        //         drawAlgorithmMap.put(strategyMode.strategyMode().getId(), (IDrawAlgorithm)r.getValue() );
        //     }
        // });
        for (Map.Entry<String, Object> stringObjectEntry : strategyModeMap.entrySet()) {
            Object entryValue = stringObjectEntry.getValue();
            Class<?> strategyModeClass = entryValue.getClass();
            StrategyMode strategyMode = AnnotationUtils.findAnnotation(strategyModeClass, StrategyMode.class);
            if (entryValue instanceof IDrawAlgorithm) {
                StrategyModeEnum strategyModeEnum = strategyMode.strategyMode();
                Integer id = strategyModeEnum.getId();
                drawAlgorithmMap.put(id, (IDrawAlgorithm) entryValue);
            }
        }

    }


    /**
     * 注入ApplicationContext
     * 需要注意applicationContext 不能从通过其他类获取，必须直接注入到当前类，
     * 否则@PostConstruct的方法执行时，其他类不一定已经完成了ApplicationContext的注入
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
