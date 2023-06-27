package top.dacchen.lottery.interfaces;

import org.apache.dubbo.config.annotation.Service;
import top.dacchen.lottery.common.Constants;
import top.dacchen.lottery.common.Result;
import top.dacchen.lottery.infrastructure.dao.IActivityDao;
import top.dacchen.lottery.infrastructure.po.Activity;
import top.dacchen.lottery.rpc.IActivityBooth;
import top.dacchen.lottery.rpc.dto.ActivityDto;
import top.dacchen.lottery.rpc.req.ActivityReq;
import top.dacchen.lottery.rpc.res.ActivityRes;

import javax.annotation.Resource;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/6/27
 * @Copyright： 最终解释权由dacchen保留
 */
@Service
public class ActivityBooth implements IActivityBooth {

    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo()), activityDto);
    }
}
