package top.dacchen.lottery.common;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/23
 * @Copyright： 最终解释权由dacchen保留
 */
public enum StrategyModeEnum {

    DEFAULT_RATE_RANDOM_DRAW_ALGORITHM(1,"必中奖策略抽奖，排掉已经中奖的概率，重新计算中奖范围"),

    SINGLE_RATE_RANDOM_DRAW_ALGORITHM(2,"单项随机概率抽奖，抽到一个已经排掉的奖品则未中奖");



    private StrategyModeEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
        /**
         * 策略id
         */
        private Integer id;

        /**
         * 策略描述
         */
        private String description;

        public Integer getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }


}
