package top.dacchen.lottery.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import top.dacchen.lottery.domain.activity.model.vo.AlterStateVO;
import top.dacchen.lottery.infrastructure.po.Activity;

/**
 * @description: 要注意dao均是接口而非类
 * @author：dacchen，微信：c18946551961
 * @date: 2023/6/27
 * @Copyright： 最终解释权由dacchen保留
 */
@Mapper
public interface IActivityDao {
    /**
     * 插入数据
     *
     * @param req 入参
     */
    void insert(Activity req);

    /**
     * 根据活动号查询活动信息
     *
     * @param activityId 活动号
     * @return 活动信息
     */
    Activity queryActivityById(Long activityId);

    /**
     * 变更活动状态
     *
     * @param alterStateVO  [activityId、beforeState、afterState]
     * @return 更新数量
     */
    int alterState(AlterStateVO alterStateVO);

    /**
     * 扣减活动库存
     * @param activityId 活动ID
     * @return 更新数量
     */
    int subtractionActivityStock(Long activityId);
}
