package com.ts.trans.external;

import com.ts.bfs.common.util.uid.BFIdFactory;
import com.ts.trans.domain.external.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 公共服务
 */
@Slf4j
@Component
public class CommonServiceImpl implements CommonService {

    @Autowired
    private BFIdFactory bfIdFactory;

    @Override
    public long getId() {
        return bfIdFactory.getIdGenerator().getId();
    }
}
