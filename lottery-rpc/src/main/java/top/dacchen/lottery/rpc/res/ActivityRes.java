package top.dacchen.lottery.rpc.res;


import top.dacchen.lottery.common.Result;
import top.dacchen.lottery.rpc.dto.ActivityDto;

import java.io.Serializable;

/**
 * @description: Dubbo 接口无论是入参、出参，以及参数中的包括的对象，都必须 implements Serializable
 * @author：dacchen，微信：c18946551961
 * @date: 2023/6/27
 * @Copyright： 最终解释权由dacchen保留
 */
public class ActivityRes implements Serializable {
    private Result result;
    private ActivityDto activity;

    public ActivityRes() {
    }

    public ActivityRes(Result result) {
        this.result = result;
    }

    public ActivityRes(Result result, ActivityDto activity) {
        this.result = result;
        this.activity = activity;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public void setActivity(ActivityDto activity) {
        this.activity = activity;
    }
}
