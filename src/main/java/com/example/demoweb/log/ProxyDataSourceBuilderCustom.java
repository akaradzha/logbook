package com.example.demoweb.log;

import net.ttddyy.dsproxy.ConnectionIdManager;
import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.*;
import net.ttddyy.dsproxy.listener.lifecycle.JdbcLifecycleEventExecutionListener;
import net.ttddyy.dsproxy.listener.lifecycle.JdbcLifecycleEventListener;
import net.ttddyy.dsproxy.listener.logging.*;
import net.ttddyy.dsproxy.proxy.*;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import net.ttddyy.dsproxy.transform.ParameterTransformer;
import net.ttddyy.dsproxy.transform.QueryTransformer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class ProxyDataSourceBuilderCustom extends ProxyDataSourceBuilder {

    private boolean logQueryByPB;
    private DataSource dataSource;
    private String dataSourceName;
    private boolean createTracingMethodListener;
    private TracingMethodListener.TracingCondition tracingCondition;
    private TracingMethodListener.TracingMessageConsumer tracingMessageConsumer;
    private boolean createCommonsQueryListener;
    private CommonsLogLevel commonsLogLevel;
    private String commonsLoggerName;
    private boolean createSlf4jQueryListener;
    private SLF4JLogLevel slf4jLogLevel;
    private String slf4jLoggerName;
    private boolean createJulQueryListener;
    private Level julLogLevel;
    private String julLoggerName;
    private boolean createSysOutQueryListener;
    private long slowQueryThreshold;
    private TimeUnit slowQueryTimeUnit;
    private boolean createCommonsSlowQueryListener;
    private CommonsLogLevel commonsSlowQueryLogLevel;
    private String commonsSlowQueryLogName;
    private boolean createSlf4jSlowQueryListener;
    private SLF4JLogLevel slf4jSlowQueryLogLevel;
    private String slf4jSlowQueryLoggerName;
    private boolean createJulSlowQueryListener;
    private Level julSlowQueryLogLevel;
    private String julSlowQueryLoggerName;
    private boolean createSysOutSlowQueryListener;
    private boolean createDataSourceQueryCountListener;
    private QueryCountStrategy queryCountStrategy;
    private boolean jsonFormat;
    private boolean multiline;
    private List<QueryExecutionListener> queryExecutionListeners = new ArrayList();
    private ParameterTransformer parameterTransformer;
    private QueryTransformer queryTransformer;
    private JdbcProxyFactory jdbcProxyFactory;
    private ConnectionIdManager connectionIdManager;
    private ResultSetProxyLogicFactory resultSetProxyLogicFactory;
    private boolean autoRetrieveGeneratedKeys;
    private Boolean retrieveGeneratedKeysForBatchStatement;
    private Boolean retrieveGeneratedKeysForBatchPreparedOrCallable;
    private boolean autoCloseGeneratedKeys;
    private ResultSetProxyLogicFactory generatedKeysProxyLogicFactory;
    private List<MethodExecutionListener> methodExecutionListeners = new ArrayList();

    public static ProxyDataSourceBuilderCustom create() {
        return new ProxyDataSourceBuilderCustom();
    }

    public static ProxyDataSourceBuilderCustom create(DataSource dataSource) {
        return new ProxyDataSourceBuilderCustom(dataSource);
    }

    public static ProxyDataSourceBuilderCustom create(String dataSourceName, DataSource dataSource) {
        return (ProxyDataSourceBuilderCustom) (new ProxyDataSourceBuilder(dataSource)).name(dataSourceName);
    }

    public ProxyDataSourceBuilderCustom() {
    }

    public ProxyDataSourceBuilderCustom(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ProxyDataSourceBuilderCustom dataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public ProxyDataSourceBuilderCustom logQueryByPB() {
        this.logQueryByPB = true;
        return this;
    }

    public ProxyDataSourceBuilderCustom logQueryByCommons() {
        return this.logQueryByCommons((CommonsLogLevel)null, (String)null);
    }

    public ProxyDataSourceBuilderCustom logQueryByCommons(CommonsLogLevel logLevel) {
        return this.logQueryByCommons(logLevel, (String)null);
    }

    public ProxyDataSourceBuilderCustom logQueryByCommons(String commonsLoggerName) {
        return this.logQueryByCommons((CommonsLogLevel)null, commonsLoggerName);
    }

    public ProxyDataSourceBuilderCustom logQueryByCommons(CommonsLogLevel logLevel, String commonsLoggerName) {
        this.createCommonsQueryListener = true;
        this.commonsLogLevel = logLevel;
        this.commonsLoggerName = commonsLoggerName;
        return this;
    }

    public ProxyDataSourceBuilderCustom logSlowQueryByCommons(long thresholdTime, TimeUnit timeUnit) {
        return this.logSlowQueryByCommons(thresholdTime, timeUnit, (CommonsLogLevel)null, (String)null);
    }

    public ProxyDataSourceBuilderCustom logSlowQueryByCommons(long thresholdTime, TimeUnit timeUnit, CommonsLogLevel logLevel) {
        return this.logSlowQueryByCommons(thresholdTime, timeUnit, logLevel, (String)null);
    }

    public ProxyDataSourceBuilderCustom logSlowQueryByCommons(long thresholdTime, TimeUnit timeUnit, String logName) {
        return this.logSlowQueryByCommons(thresholdTime, timeUnit, (CommonsLogLevel)null, logName);
    }

    public ProxyDataSourceBuilderCustom logSlowQueryByCommons(long thresholdTime, TimeUnit timeUnit, CommonsLogLevel logLevel, String logName) {
        this.createCommonsSlowQueryListener = true;
        this.slowQueryThreshold = thresholdTime;
        this.slowQueryTimeUnit = timeUnit;
        this.commonsSlowQueryLogLevel = logLevel;
        this.commonsSlowQueryLogName = logName;
        return this;
    }

    public ProxyDataSourceBuilderCustom logQueryBySlf4j() {
        return this.logQueryBySlf4j((SLF4JLogLevel)null, (String)null);
    }

    public ProxyDataSourceBuilderCustom logQueryBySlf4j(SLF4JLogLevel logLevel) {
        return this.logQueryBySlf4j(logLevel, (String)null);
    }

    public ProxyDataSourceBuilderCustom logQueryBySlf4j(String slf4jLoggerName) {
        return this.logQueryBySlf4j((SLF4JLogLevel)null, slf4jLoggerName);
    }

    public ProxyDataSourceBuilderCustom logQueryBySlf4j(SLF4JLogLevel logLevel, String slf4jLoggerName) {
        this.createSlf4jQueryListener = true;
        this.slf4jLogLevel = logLevel;
        this.slf4jLoggerName = slf4jLoggerName;
        return this;
    }

    public ProxyDataSourceBuilderCustom logSlowQueryBySlf4j(long thresholdTime, TimeUnit timeUnit) {
        return this.logSlowQueryBySlf4j(thresholdTime, timeUnit, (SLF4JLogLevel)null, (String)null);
    }

    public ProxyDataSourceBuilderCustom logSlowQueryBySlf4j(long thresholdTime, TimeUnit timeUnit, SLF4JLogLevel logLevel) {
        return this.logSlowQueryBySlf4j(thresholdTime, timeUnit, logLevel, (String)null);
    }

    public ProxyDataSourceBuilderCustom logSlowQueryBySlf4j(long thresholdTime, TimeUnit timeUnit, String loggerName) {
        return this.logSlowQueryBySlf4j(thresholdTime, timeUnit, (SLF4JLogLevel)null, loggerName);
    }

    public ProxyDataSourceBuilderCustom logSlowQueryBySlf4j(long thresholdTime, TimeUnit timeUnit, SLF4JLogLevel logLevel, String loggerName) {
        this.createSlf4jSlowQueryListener = true;
        this.slowQueryThreshold = thresholdTime;
        this.slowQueryTimeUnit = timeUnit;
        this.slf4jSlowQueryLogLevel = logLevel;
        this.slf4jSlowQueryLoggerName = loggerName;
        return this;
    }

    public ProxyDataSourceBuilderCustom logQueryByJUL() {
        return this.logQueryByJUL((Level)null, (String)null);
    }

    public ProxyDataSourceBuilderCustom logQueryByJUL(Level logLevel) {
        return this.logQueryByJUL(logLevel, (String)null);
    }

    public ProxyDataSourceBuilderCustom logQueryByJUL(String julLoggerName) {
        return this.logQueryByJUL((Level)null, julLoggerName);
    }

    public ProxyDataSourceBuilderCustom logQueryByJUL(Level logLevel, String julLoggerName) {
        this.createJulQueryListener = true;
        this.julLogLevel = logLevel;
        this.julLoggerName = julLoggerName;
        return this;
    }

    public ProxyDataSourceBuilderCustom logSlowQueryByJUL(long thresholdTime, TimeUnit timeUnit) {
        return this.logSlowQueryByJUL(thresholdTime, timeUnit, (Level)null, (String)null);
    }

    public ProxyDataSourceBuilderCustom logSlowQueryByJUL(long thresholdTime, TimeUnit timeUnit, Level logLevel) {
        return this.logSlowQueryByJUL(thresholdTime, timeUnit, logLevel, (String)null);
    }

    public ProxyDataSourceBuilderCustom logSlowQueryByJUL(long thresholdTime, TimeUnit timeUnit, String loggerName) {
        return this.logSlowQueryByJUL(thresholdTime, timeUnit, (Level)null, loggerName);
    }

    public ProxyDataSourceBuilderCustom logSlowQueryByJUL(long thresholdTime, TimeUnit timeUnit, Level logLevel, String loggerName) {
        this.createJulSlowQueryListener = true;
        this.slowQueryThreshold = thresholdTime;
        this.slowQueryTimeUnit = timeUnit;
        this.julSlowQueryLogLevel = logLevel;
        this.julSlowQueryLoggerName = loggerName;
        return this;
    }

    public ProxyDataSourceBuilderCustom logQueryToSysOut() {
        this.createSysOutQueryListener = true;
        return this;
    }

    public ProxyDataSourceBuilderCustom logSlowQueryToSysOut(long thresholdTime, TimeUnit timeUnit) {
        this.createSysOutSlowQueryListener = true;
        this.slowQueryThreshold = thresholdTime;
        this.slowQueryTimeUnit = timeUnit;
        return this;
    }

    public ProxyDataSourceBuilderCustom countQuery() {
        this.createDataSourceQueryCountListener = true;
        return this;
    }

    public ProxyDataSourceBuilderCustom countQuery(QueryCountStrategy queryCountStrategy) {
        this.createDataSourceQueryCountListener = true;
        this.queryCountStrategy = queryCountStrategy;
        return this;
    }

    public ProxyDataSourceBuilderCustom listener(QueryExecutionListener listener) {
        this.queryExecutionListeners.add(listener);
        return this;
    }

    public ProxyDataSourceBuilderCustom listener(JdbcLifecycleEventListener listener) {
        JdbcLifecycleEventExecutionListener executionListener = new JdbcLifecycleEventExecutionListener(listener);
        this.queryExecutionListeners.add(executionListener);
        this.methodExecutionListeners.add(executionListener);
        return this;
    }

    public ProxyDataSourceBuilderCustom beforeQuery(final ProxyDataSourceBuilder.SingleQueryExecution callback) {
        QueryExecutionListener listener = new NoOpQueryExecutionListener() {
            public void beforeQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
                callback.execute(execInfo, queryInfoList);
            }
        };
        this.queryExecutionListeners.add(listener);
        return this;
    }

    public ProxyDataSourceBuilderCustom afterQuery(final ProxyDataSourceBuilder.SingleQueryExecution callback) {
        QueryExecutionListener listener = new NoOpQueryExecutionListener() {
            public void afterQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
                callback.execute(execInfo, queryInfoList);
            }
        };
        this.queryExecutionListeners.add(listener);
        return this;
    }

    public ProxyDataSourceBuilderCustom asJson() {
        this.jsonFormat = true;
        return this;
    }

    public ProxyDataSourceBuilderCustom name(String dataSourceName) {
        this.dataSourceName = dataSourceName;
        return this;
    }

    public ProxyDataSourceBuilderCustom queryTransformer(QueryTransformer queryTransformer) {
        this.queryTransformer = queryTransformer;
        return this;
    }

    public ProxyDataSourceBuilderCustom parameterTransformer(ParameterTransformer parameterTransformer) {
        this.parameterTransformer = parameterTransformer;
        return this;
    }

    public ProxyDataSourceBuilderCustom multiline() {
        this.multiline = true;
        return this;
    }

    public ProxyDataSourceBuilderCustom jdbcProxyFactory(JdbcProxyFactory jdbcProxyFactory) {
        this.jdbcProxyFactory = jdbcProxyFactory;
        return this;
    }

    public ProxyDataSourceBuilderCustom connectionIdManager(ConnectionIdManager connectionIdManager) {
        this.connectionIdManager = connectionIdManager;
        return this;
    }

    public ProxyDataSourceBuilderCustom proxyResultSet() {
        this.resultSetProxyLogicFactory = ResultSetProxyLogicFactory.DEFAULT;
        return this;
    }

    public ProxyDataSourceBuilderCustom proxyResultSet(ResultSetProxyLogicFactory factory) {
        this.resultSetProxyLogicFactory = factory;
        return this;
    }

    public ProxyDataSourceBuilderCustom proxyGeneratedKeys() {
        this.generatedKeysProxyLogicFactory = ResultSetProxyLogicFactory.DEFAULT;
        return this;
    }

    public ProxyDataSourceBuilderCustom proxyGeneratedKeys(ResultSetProxyLogicFactory factory) {
        this.generatedKeysProxyLogicFactory = factory;
        return this;
    }

    public ProxyDataSourceBuilderCustom repeatableReadGeneratedKeys() {
        this.generatedKeysProxyLogicFactory = new RepeatableReadResultSetProxyLogicFactory();
        return this;
    }

    public ProxyDataSourceBuilderCustom autoRetrieveGeneratedKeys(boolean autoClose) {
        this.autoRetrieveGeneratedKeys = true;
        this.autoCloseGeneratedKeys = autoClose;
        return this;
    }

    public ProxyDataSourceBuilderCustom autoRetrieveGeneratedKeys(boolean autoClose, ResultSetProxyLogicFactory factory) {
        this.autoRetrieveGeneratedKeys = true;
        this.autoCloseGeneratedKeys = autoClose;
        this.generatedKeysProxyLogicFactory = factory;
        return this;
    }

    public ProxyDataSourceBuilderCustom autoRetrieveGeneratedKeysWithRepeatableReadProxy(boolean autoClose) {
        this.autoRetrieveGeneratedKeys = true;
        this.autoCloseGeneratedKeys = autoClose;
        this.generatedKeysProxyLogicFactory = new RepeatableReadResultSetProxyLogicFactory();
        return this;
    }

    public ProxyDataSourceBuilderCustom retrieveGeneratedKeysForBatch(boolean forStatement, boolean forPreparedOrCallable) {
        this.retrieveGeneratedKeysForBatchStatement = forStatement;
        this.retrieveGeneratedKeysForBatchPreparedOrCallable = forPreparedOrCallable;
        return this;
    }

    public ProxyDataSourceBuilderCustom repeatableReadResultSet() {
        this.resultSetProxyLogicFactory = new RepeatableReadResultSetProxyLogicFactory();
        return this;
    }

    public ProxyDataSourceBuilderCustom methodListener(MethodExecutionListener listener) {
        this.methodExecutionListeners.add(listener);
        return this;
    }

    public ProxyDataSourceBuilderCustom beforeMethod(final ProxyDataSourceBuilder.SingleMethodExecution callback) {
        MethodExecutionListener listener = new NoOpMethodExecutionListener() {
            public void beforeMethod(MethodExecutionContext executionContext) {
                callback.execute(executionContext);
            }
        };
        this.methodExecutionListeners.add(listener);
        return this;
    }

    public ProxyDataSourceBuilderCustom afterMethod(final ProxyDataSourceBuilder.SingleMethodExecution callback) {
        MethodExecutionListener listener = new NoOpMethodExecutionListener() {
            public void afterMethod(MethodExecutionContext executionContext) {
                callback.execute(executionContext);
            }
        };
        this.methodExecutionListeners.add(listener);
        return this;
    }

    public ProxyDataSourceBuilderCustom traceMethods() {
        this.createTracingMethodListener = true;
        return this;
    }

    public ProxyDataSourceBuilderCustom traceMethods(TracingMethodListener.TracingMessageConsumer messageConsumer) {
        this.createTracingMethodListener = true;
        this.tracingMessageConsumer = messageConsumer;
        return this;
    }

    public ProxyDataSourceBuilderCustom traceMethodsWhen(TracingMethodListener.TracingCondition condition) {
        this.createTracingMethodListener = true;
        this.tracingCondition = condition;
        return this;
    }

    public ProxyDataSourceBuilderCustom traceMethodsWhen(TracingMethodListener.TracingCondition condition, TracingMethodListener.TracingMessageConsumer messageConsumer) {
        this.createTracingMethodListener = true;
        this.tracingCondition = condition;
        this.tracingMessageConsumer = messageConsumer;
        return this;
    }

    public ProxyDataSource build() {
        List<QueryExecutionListener> listeners = new ArrayList();
        if (this.createCommonsQueryListener) {
            listeners.add(this.buildCommonsQueryListener());
        }

        if(this.logQueryByPB){
            listeners.add(this.buildSlf4jCustomQueryListener());
        }

        if (this.createSlf4jQueryListener) {
            listeners.add(this.buildSlf4jQueryListener());
        }

        if (this.createJulQueryListener) {
            listeners.add(this.buildJulQueryListener());
        }

        if (this.createSysOutQueryListener) {
            listeners.add(this.buildSysOutQueryListener());
        }

        if (this.createCommonsSlowQueryListener) {
            listeners.add(this.buildCommonsSlowQueryListener());
        }

        if (this.createSlf4jSlowQueryListener) {
            listeners.add(this.buildSlf4jSlowQueryListener());
        }

        if (this.createJulSlowQueryListener) {
            listeners.add(this.buildJulSlowQueryListener());
        }

        if (this.createSysOutSlowQueryListener) {
            listeners.add(this.buildSysOutSlowQueryListener());
        }

        if (this.createDataSourceQueryCountListener) {
            DataSourceQueryCountListener countListener = new DataSourceQueryCountListener();
            if (this.queryCountStrategy != null) {
                countListener.setQueryCountStrategy(this.queryCountStrategy);
            }

            listeners.add(countListener);
        }

        if (this.createTracingMethodListener) {
            this.methodExecutionListeners.add(this.buildTracingMethodListenr());
        }



        listeners.addAll(this.queryExecutionListeners);
        ProxyConfig.Builder proxyConfigBuilder = ProxyConfig.Builder.create();
        Iterator var3 = listeners.iterator();

        while(var3.hasNext()) {
            QueryExecutionListener listener = (QueryExecutionListener)var3.next();
            proxyConfigBuilder.queryListener(listener);
        }

        var3 = this.methodExecutionListeners.iterator();

        while(var3.hasNext()) {
            MethodExecutionListener methodListener = (MethodExecutionListener)var3.next();
            proxyConfigBuilder.methodListener(methodListener);
        }

        if (this.queryTransformer != null) {
            proxyConfigBuilder.queryTransformer(this.queryTransformer);
        }

        if (this.parameterTransformer != null) {
            proxyConfigBuilder.parameterTransformer(this.parameterTransformer);
        }

        if (this.dataSourceName != null) {
            proxyConfigBuilder.dataSourceName(this.dataSourceName);
        }

        if (this.jdbcProxyFactory != null) {
            proxyConfigBuilder.jdbcProxyFactory(this.jdbcProxyFactory);
        } else {
            proxyConfigBuilder.jdbcProxyFactory(JdbcProxyFactory.DEFAULT);
        }

        if (this.connectionIdManager != null) {
            proxyConfigBuilder.connectionIdManager(this.connectionIdManager);
        } else {
            proxyConfigBuilder.connectionIdManager(new DefaultConnectionIdManager());
        }

        proxyConfigBuilder.resultSetProxyLogicFactory(this.resultSetProxyLogicFactory);
        proxyConfigBuilder.autoRetrieveGeneratedKeys(this.autoRetrieveGeneratedKeys);
        if (this.retrieveGeneratedKeysForBatchStatement != null) {
            proxyConfigBuilder.retrieveGeneratedKeysForBatchStatement(this.retrieveGeneratedKeysForBatchStatement);
        }

        if (this.retrieveGeneratedKeysForBatchPreparedOrCallable != null) {
            proxyConfigBuilder.retrieveGeneratedKeysForBatchPreparedOrCallable(this.retrieveGeneratedKeysForBatchPreparedOrCallable);
        }

        proxyConfigBuilder.autoCloseGeneratedKeys(this.autoCloseGeneratedKeys);
        proxyConfigBuilder.generatedKeysProxyLogicFactory(this.generatedKeysProxyLogicFactory);
        ProxyDataSource proxyDataSource = new ProxyDataSource();
        if (this.dataSource != null) {
            proxyDataSource.setDataSource(this.dataSource);
        }

        ProxyConfig proxyConfig = proxyConfigBuilder.build();
        proxyDataSource.setProxyConfig(proxyConfig);
        return proxyDataSource;
    }

    private CommonsQueryLoggingListener buildCommonsQueryListener() {
        CommonsQueryLoggingListener listener = new CommonsQueryLoggingListener();
        if (this.commonsLogLevel != null) {
            listener.setLogLevel(this.commonsLogLevel);
        }

        if (this.commonsLoggerName != null && !this.commonsLoggerName.isEmpty()) {
            listener.setLog(this.commonsLoggerName);
        }

        if (this.jsonFormat) {
            listener.setQueryLogEntryCreator(new DefaultJsonQueryLogEntryCreator());
        }

        if (this.multiline) {
            listener.setQueryLogEntryCreator(this.buildMultilineQueryLogEntryCreator());
        }

        return listener;
    }

    private CommonsSlowQueryListener buildCommonsSlowQueryListener() {
        CommonsSlowQueryListener listener = new CommonsSlowQueryListener(this.slowQueryThreshold, this.slowQueryTimeUnit);
        if (this.commonsSlowQueryLogLevel != null) {
            listener.setLogLevel(this.commonsSlowQueryLogLevel);
        }

        if (this.commonsSlowQueryLogName != null) {
            listener.setLog(this.commonsSlowQueryLogName);
        }

        if (this.multiline) {
            listener.setQueryLogEntryCreator(this.buildMultilineQueryLogEntryCreator());
        }

        return listener;
    }

    private SLF4JQueryLoggingListener buildSlf4jQueryListener() {
        SLF4JQueryLoggingListener listener = new SLF4JQueryLoggingListener();
        if (this.slf4jLogLevel != null) {
            listener.setLogLevel(this.slf4jLogLevel);
        }

        if (this.slf4jLoggerName != null && !this.slf4jLoggerName.isEmpty()) {
            listener.setLogger(this.slf4jLoggerName);
        }

        if (this.jsonFormat) {
            listener.setQueryLogEntryCreator(new DefaultJsonQueryLogEntryCreator());
        }

        if (this.multiline) {
            listener.setQueryLogEntryCreator(this.buildMultilineQueryLogEntryCreator());
        }

        return listener;
    }

    private SLF4JQueryLoggingListenerCustom buildSlf4jCustomQueryListener() {
        SLF4JQueryLoggingListenerCustom listener = new SLF4JQueryLoggingListenerCustom();
        listener.setQueryLogEntryCreator(new DefaultJsonQueryLogEntryCreatorCustom(this.dataSource));
        return listener;
    }

    private SLF4JSlowQueryListener buildSlf4jSlowQueryListener() {
        SLF4JSlowQueryListener listener = new SLF4JSlowQueryListener(this.slowQueryThreshold, this.slowQueryTimeUnit);
        if (this.slf4jSlowQueryLogLevel != null) {
            listener.setLogLevel(this.slf4jSlowQueryLogLevel);
        }

        if (this.slf4jSlowQueryLoggerName != null) {
            listener.setLogger(this.slf4jSlowQueryLoggerName);
        }

        if (this.multiline) {
            listener.setQueryLogEntryCreator(this.buildMultilineQueryLogEntryCreator());
        }

        return listener;
    }

    private JULQueryLoggingListener buildJulQueryListener() {
        JULQueryLoggingListener listener = new JULQueryLoggingListener();
        if (this.julLogLevel != null) {
            listener.setLogLevel(this.julLogLevel);
        }

        if (this.julLoggerName != null && !this.julLoggerName.isEmpty()) {
            listener.setLogger(this.julLoggerName);
        }

        if (this.jsonFormat) {
            listener.setQueryLogEntryCreator(new DefaultJsonQueryLogEntryCreator());
        }

        if (this.multiline) {
            listener.setQueryLogEntryCreator(this.buildMultilineQueryLogEntryCreator());
        }

        return listener;
    }

    private JULSlowQueryListener buildJulSlowQueryListener() {
        JULSlowQueryListener listener = new JULSlowQueryListener(this.slowQueryThreshold, this.slowQueryTimeUnit);
        if (this.julSlowQueryLogLevel != null) {
            listener.setLogLevel(this.julSlowQueryLogLevel);
        }

        if (this.julSlowQueryLoggerName != null) {
            listener.setLogger(this.julSlowQueryLoggerName);
        }

        if (this.multiline) {
            listener.setQueryLogEntryCreator(this.buildMultilineQueryLogEntryCreator());
        }

        return listener;
    }

    private SystemOutQueryLoggingListener buildSysOutQueryListener() {
        SystemOutQueryLoggingListener listener = new SystemOutQueryLoggingListener();
        if (this.jsonFormat) {
            listener.setQueryLogEntryCreator(new DefaultJsonQueryLogEntryCreator());
        }

        if (this.multiline) {
            listener.setQueryLogEntryCreator(this.buildMultilineQueryLogEntryCreator());
        }

        return listener;
    }

    private SystemOutSlowQueryListener buildSysOutSlowQueryListener() {
        SystemOutSlowQueryListener listener = new SystemOutSlowQueryListener(this.slowQueryThreshold, this.slowQueryTimeUnit);
        if (this.multiline) {
            listener.setQueryLogEntryCreator(this.buildMultilineQueryLogEntryCreator());
        }

        return listener;
    }

    private DefaultQueryLogEntryCreator buildMultilineQueryLogEntryCreator() {
        DefaultQueryLogEntryCreator entryCreator = new DefaultQueryLogEntryCreator();
        entryCreator.setMultiline(true);
        return entryCreator;
    }

    private TracingMethodListener buildTracingMethodListenr() {
        TracingMethodListener listener = new TracingMethodListener();
        if (this.tracingMessageConsumer != null) {
            listener.setTracingMessageConsumer(this.tracingMessageConsumer);
        }

        if (this.tracingCondition != null) {
            listener.setTracingCondition(this.tracingCondition);
        }

        return listener;
    }

    public interface SingleQueryExecution {
        void execute(ExecutionInfo var1, List<QueryInfo> var2);
    }

    public interface SingleMethodExecution {
        void execute(MethodExecutionContext var1);
    }




}
