package top.dacchen.lottery.domain.strategy.support.ids.policy;

import org.springframework.stereotype.Component;
import top.dacchen.lottery.domain.strategy.support.ids.IIdGenerator;

import java.util.Calendar;
import java.util.Random;

/**
 * @description: 短码生成策略，仅支持很小的调用量，用于生成活动配置类编号，保证全局唯一
 *               采用了日期拼接的形式
 * @author：dacchen，微信：c18946551961
 * @date: 2023/8/14
 * @Copyright： 最终解释权由dacchen保留
 */
@Component
public class ShortCode implements IIdGenerator {
    @Override
    public synchronized long nextId() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // 打乱排序：2020年为准 + 小时 + 周期 + 日 + 三位随机数
        StringBuilder idStr = new StringBuilder();
        idStr.append(year - 2020);
        idStr.append(hour);
        idStr.append(String.format("%02d", week));
        idStr.append(day);
        idStr.append(String.format("%03d", new Random().nextInt(1000)));

        return Long.parseLong(idStr.toString());
    }
}
