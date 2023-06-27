package top.dacchen.lottery.common;

import java.io.Serializable;

/**
 * @description:  Dubbo 接口无论是入参、出参，以及参数中的包括的对象，都必须 implements Serializable
 *                 统一返回对象中，Code码、Info描述
 * @author：dacchen，微信：c18946551961
 * @date: 2023/6/27
 * @Copyright： 最终解释权由dacchen保留
 */
public class Result implements Serializable {
    private static final long serialVersionUID = -3826891916021780628L;
    private String code;
    private String info;

    public static Result buildResult(String code, String info) {
        return new Result(code, info);
    }

    public static Result buildSuccessResult() {
        return new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static Result buildErrorResult() {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
    }

    public Result(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }



}
