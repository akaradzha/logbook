package com.example.demoweb.log;

import net.ttddyy.dsproxy.listener.logging.LoggingCondition;
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

public class SLF4JQueryLoggingListenerCustom extends SLF4JQueryLoggingListener {
//    private boolean logQueryByPB;


//    protected SLF4JLogLevel logLevel = SLF4JLogLevel.INFO; // default DEBUG
//
    public SLF4JQueryLoggingListenerCustom() {
        super.loggingCondition = new LoggingCondition() {
            @Override
            public boolean getAsBoolean() {
                return true;
            }
        };
    }
//        this.loggingCondition = new LoggingCondition() {
//            @Override
//            public boolean getAsBoolean() {
//                switch (logLevel) {
//                    case TRACE:
//                        return logger.isTraceEnabled();
//                    case DEBUG:
//                        return logger.isDebugEnabled();
//                    case INFO:
//                        return logger.isInfoEnabled();
//                    case WARN:
//                        return logger.isWarnEnabled();
//                    case ERROR:
//                        return logger.isErrorEnabled();
//                }
//                return false;
//            }
//        };
//    }



    @Override
    protected void writeLog(String message) {
//        System.out.println(message);
//        super.writeLog(message);
    }


}

