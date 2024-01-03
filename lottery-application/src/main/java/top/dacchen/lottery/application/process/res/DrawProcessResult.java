package top.dacchen.lottery.application.process.res;


import top.dacchen.lottery.common.Result;
import top.dacchen.lottery.domain.strategy.model.vo.DrawAwardVO;

/**
 * @description: 活动抽奖结果
 * @author: 小傅哥，微信：fustack
 * @date: 2021/10/3
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class DrawProcessResult extends Result {

    private DrawAwardVO drawAwardInfo;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardVO drawAwardInfo) {
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawAwardVO getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardVO drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }
}
