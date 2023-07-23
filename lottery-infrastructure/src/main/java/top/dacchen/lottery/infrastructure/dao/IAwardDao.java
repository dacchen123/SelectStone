package top.dacchen.lottery.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import top.dacchen.lottery.infrastructure.po.Award;

/**
 * @description: 要注意dao均是接口而非类
 * @author：dacchen，微信：c18946551961
 * @date: 2023/7/10
 * @Copyright： 最终解释权由dacchen保留
 */
@Mapper
public interface IAwardDao {

    Award queryAwardInfo(String awardId);

}
