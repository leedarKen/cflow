package com.csoft.resource.cflow.service.impl;

import com.csoft.resource.cflow.mapper.RmServiceMapper;
import com.csoft.resource.cflow.pojo.RmService;
import com.csoft.resource.cflow.service.Rmservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ken.kang on 2015/11/20.
 */
@Service("rmservice")
public class RmServiceImpl implements Rmservice{

    //User接口
    @Autowired
    private RmServiceMapper rmServiceMapper;

    @Override
    public RmService selectByPrimaryKey(Integer id) {
        return rmServiceMapper.selectByPrimaryKey(id);
    }
}
