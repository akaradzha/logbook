package com.example.demoweb.log;

import com.pb.logging.handler.impl.ParamHandlerAbs;
import com.pb.logging.handler.result.request.output.DataBaseOutputRequestParamHandlerResult;
import com.pb.logging.proceeder.proceed_info.ProceedInfoAfterExceptionI;
import com.pb.logging.proceeder.proceed_info.ProceedInfoAfterSuccessfulExecutionI;
import com.pb.logging.proceeder.proceed_info.ProceedInfoI;
import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HibernateOutputParamHandler extends ParamHandlerAbs<DataBaseOutputRequestParamHandlerResult.DataBaseOutputRequestParamHandlerResultBuilder> {

    DefaultJsonQueryLogEntryCreatorCustom jsonQueryLogEntryCreator;

    public HibernateOutputParamHandler() {
        jsonQueryLogEntryCreator = new DefaultJsonQueryLogEntryCreatorCustom(StringUtils.EMPTY);
    }

    @Override
    protected void handleSpecificMethodParamsBeforeExecution(ProceedInfoI proceedInfo, DataBaseOutputRequestParamHandlerResult.DataBaseOutputRequestParamHandlerResultBuilder builder) {
        String dataSource = (String) proceedInfo.getArgs()[0];
        ExecutionInfo execInfo = (ExecutionInfo) proceedInfo.getArgs()[1];
        List<QueryInfo> queryInfoList = (List<QueryInfo>) proceedInfo.getArgs()[2];

        builder.addRequestUri(dataSource);
        builder.addRequestBody(getRequestBody(execInfo, queryInfoList));
        builder.addAdditionalParams(getAdditionalParam(execInfo, queryInfoList));
    }

    @Override
    protected void handleSpecificMethodParamsAfterSuccessfulExecution(ProceedInfoAfterSuccessfulExecutionI proceedInfo, DataBaseOutputRequestParamHandlerResult.DataBaseOutputRequestParamHandlerResultBuilder builder) {
    }

    @Override
    protected void handleSpecificMethodParamsAfterException(ProceedInfoAfterExceptionI proceedInfoAfterException, DataBaseOutputRequestParamHandlerResult.DataBaseOutputRequestParamHandlerResultBuilder builder) {
    }

    private Map<String, String> getAdditionalParam(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        Map<String, String> params = new HashMap<>();
        params.put("success", execInfo.isSuccess() ? "true" : "false");
        params.put("dataSource", jsonQueryLogEntryCreator.escapeSpecialCharacter(
                execInfo.getDataSourceName()
        ));
        params.put("batch", execInfo.isBatch() ? "true" : "false");
        params.put("batchSize", String.valueOf(execInfo.getBatchSize()));
        return params;
    }

    protected String getRequestBody(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        jsonQueryLogEntryCreator.writeQueriesEntry(sb, execInfo, queryInfoList);
        jsonQueryLogEntryCreator.writeParamsEntry(sb, execInfo, queryInfoList);
        sb.append("}");
        return sb.toString().replaceAll("\"","");
    }
}