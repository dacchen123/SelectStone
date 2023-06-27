package top.dacchen.lottery.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import top.dacchen.lottery.infrastructure.po.Activity;

/**
 * @description:
 * @author：dacchen，微信：c18946551961
 * @date: 2023/6/27
 * @Copyright： 最终解释权由dacchen保留
 */
@Mapper
public interface IActivityDao {
    void insert(Activity req);

    Activity queryActivityById(Long activityId);
}
