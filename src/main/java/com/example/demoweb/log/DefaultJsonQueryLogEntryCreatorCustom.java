package com.example.demoweb.log;

import com.pb.logging.annotation.outreq.HibernateDataProxyOutputRequest;
import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.logging.DefaultJsonQueryLogEntryCreator;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class DefaultJsonQueryLogEntryCreatorCustom extends DefaultJsonQueryLogEntryCreator {
    private String dataSourceUrl;

    public DefaultJsonQueryLogEntryCreatorCustom(String dataSource) {
        this.dataSourceUrl = dataSourceUrl;
    }

    @Override
    public String getLogEntry(ExecutionInfo execInfo, List<QueryInfo> queryInfoList, boolean writeDataSourceName, boolean writeConnectionId) {
        return hibernateStatement(dataSourceUrl,execInfo,queryInfoList,writeDataSourceName,writeConnectionId,execInfo.getElapsedTime());
    }

    @HibernateDataProxyOutputRequest(paramHandler = HibernateOutputParamHandler.class)
    public String hibernateStatement(String dataSourceUrl, ExecutionInfo execInfo, List<QueryInfo> queryInfoList, boolean writeDataSourceName, boolean writeConnectionId , long duration) {
        return StringUtils.EMPTY;
    }

    @Override
    public void writeDataSourceNameEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeDataSourceNameEntry(sb, execInfo, queryInfoList);
    }

    @Override
    public void writeConnectionIdEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeConnectionIdEntry(sb, execInfo, queryInfoList);
    }

    @Override
    public void writeTimeEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeTimeEntry(sb, execInfo, queryInfoList);
    }

    @Override
    public void writeResultEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeResultEntry(sb, execInfo, queryInfoList);
    }

    @Override
    public void writeTypeEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeTypeEntry(sb, execInfo, queryInfoList);
    }

    @Override
    public void writeBatchEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeBatchEntry(sb, execInfo, queryInfoList);
    }

    @Override
    public void writeQuerySizeEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeQuerySizeEntry(sb, execInfo, queryInfoList);
    }

    @Override
    public void writeBatchSizeEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeBatchSizeEntry(sb, execInfo, queryInfoList);
    }

    @Override
    public void writeQueriesEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeQueriesEntry(sb, execInfo, queryInfoList);
    }

    @Override
    public void writeParamsEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeParamsEntry(sb, execInfo, queryInfoList);
    }

    @Override
    public void writeParamsForSinglePreparedEntry(StringBuilder sb, SortedMap<String, String> paramMap, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeParamsForSinglePreparedEntry(sb, paramMap, execInfo, queryInfoList);
    }

    @Override
    public void writeParamsForSingleCallableEntry(StringBuilder sb, Map<String, String> paramMap, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        super.writeParamsForSingleCallableEntry(sb, paramMap, execInfo, queryInfoList);
    }

    @Override
    public String escapeSpecialCharacter(String input) {
        return super.escapeSpecialCharacter(input);
    }
}
