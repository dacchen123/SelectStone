package top.dacchen.lottery.domain.strategy.support.ids;

/**
 * @description: 生成ID接口
 * @author：dacchen，微信：c18946551961
 * @date: 2023/8/14
 * @Copyright： 最终解释权由dacchen保留
 */
public interface IIdGenerator {

    /**
     * 获取ID，目前有两种实现方式
     * 1. 雪花算法，用于生成单号
     * 2. 日期算法，用于生成活动编号类，特性是生成数字串较短，但指定时间内不能生成太多
     * 3. 随机算法，用于生成策略ID
     *
     * @return ID
     */
    long nextId();
}
