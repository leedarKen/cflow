package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.dao.HistoryInstanceInputValueDao;
import com.csoft.resource.cflow.pojo.HistoryInstanceInputValue;
import com.csoft.resource.cflow.service.inner.entry.service.HistoryInstanceInputValueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("historyInstanceInputValueManager")
public class HistoryInstanceInputValueManagerImpl extends GenericManagerImpl<HistoryInstanceInputValue, Integer> implements HistoryInstanceInputValueManager {

    @Autowired
    private HistoryInstanceInputValueDao historyInstanceInputValueDao;

    @Autowired
    public HistoryInstanceInputValueManagerImpl(HistoryInstanceInputValueDao historyInstanceInputValueDao) {
        super(historyInstanceInputValueDao);
        this.historyInstanceInputValueDao = historyInstanceInputValueDao;
    }
}
