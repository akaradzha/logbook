package com.example.demoweb.log;

import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.logging.DefaultJsonQueryLogEntryCreator;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class DefaultJsonQueryLogEntryCreatorCustom extends DefaultJsonQueryLogEntryCreator {
    private DataSource dataSource;

    public DefaultJsonQueryLogEntryCreatorCustom(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public String getLogEntry(ExecutionInfo execInfo, List<QueryInfo> queryInfoList, boolean writeDataSourceName, boolean writeConnectionId) {
//        return super.getLogEntry(execInfo, queryInfoList, writeDataSourceName, writeConnectionId);
//        try {
            return "AAAARRRRRGH" + " : " + execInfo.getMethod().getName();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "";
    }
}
