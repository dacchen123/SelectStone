package top.dacchen.lottery.domain.strategy.model.req;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/6/29
 * @Copyright： 最终解释权由dacchen保留
 */
public class DrawReq {
    // 用户ID
    private String uId;

    // 策略ID
    private Long strategyId;

    public DrawReq() {
    }

    public DrawReq(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
