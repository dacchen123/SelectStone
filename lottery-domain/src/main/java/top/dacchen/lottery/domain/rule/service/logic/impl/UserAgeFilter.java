package top.dacchen.lottery.domain.rule.service.logic.impl;


import org.springframework.stereotype.Component;
import top.dacchen.lottery.domain.rule.model.req.DecisionMatterReq;
import top.dacchen.lottery.domain.rule.service.logic.BaseLogic;

/**
 * @description: 年龄规则
 * @author: 小傅哥，微信：fustack
 * @date: 2021/10/8
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Component
public class UserAgeFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatterReq decisionMatter) {
        return decisionMatter.getValMap().get("age").toString();
    }

}
