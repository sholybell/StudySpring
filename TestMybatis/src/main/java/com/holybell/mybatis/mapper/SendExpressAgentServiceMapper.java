package com.holybell.mybatis.mapper;

import com.holybell.mybatis.entity.SendExpressAgentService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SendExpressAgentServiceMapper {

    @Select("SELECT * FROM send_express_agent_service WHERE id = #{id}")
    SendExpressAgentService get(@Param("id") Long id);
}
