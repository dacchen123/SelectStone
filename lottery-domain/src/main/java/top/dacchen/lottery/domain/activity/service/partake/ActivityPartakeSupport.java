package top.dacchen.lottery.domain.activity.service.partake;



import top.dacchen.lottery.domain.activity.model.req.PartakeReq;
import top.dacchen.lottery.domain.activity.model.vo.ActivityBillVO;
import top.dacchen.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @description: 活动领取模操作，一些通用的数据服务
 * @author: 小傅哥，微信：fustack
 * @date: 2021/10/1
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req){
        return activityRepository.queryActivityBill(req);
    }

}
