package com.holybell.mybatis.service;

import com.alibaba.fastjson.JSONObject;
import com.holybell.mybatis.mapper.SendExpressAgentServiceMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SendExpressAgentServiceService {

    @Autowired
    private SendExpressAgentServiceMapper mapper;

//    private Log logger = LogFactory.getLog(this.getClass().getName());

    private Log logger = LogFactory.getLog(this.getClass().getName());

    public void print() {
        logger.info(JSONObject.toJSONString(mapper.get(1L)));
    }
}
