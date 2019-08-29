//package com.example.demoweb;
//
//import brave.SpanCustomizer;
//import brave.Tracer;
//import brave.Tracing;
//import lombok.extern.slf4j.Slf4j;
//import org.aopalliance.intercept.MethodInvocation;
//import org.springframework.cloud.sleuth.annotation.NewSpan;
//import org.springframework.cloud.sleuth.annotation.NewSpanParser;
//import org.springframework.cloud.sleuth.util.SpanNameUtil;
//import org.springframework.util.StringUtils;
//
//@Slf4j
//public class MySpanCreator implements NewSpanParser {
//
////    private static final Log log = LogFactory.getLog(MySpanCreator.class);
//
//
//    private final Tracer tracer;
//
//    public MySpanCreator(Tracer tracer) {
//        this.tracer = tracer;
//    }
//
//    @Override
//    public void parse(MethodInvocation pjp, NewSpan newSpan, SpanCustomizer span) {
//        String name = newSpan != null && !StringUtils.isEmpty(newSpan.name()) ? newSpan.name() : pjp.getMethod().getName();
//        String changedName = SpanNameUtil.toLowerHyphen(name);
//        if (log.isDebugEnabled()) {
//            log.debug("For the class [" + pjp.getThis().getClass() + "] method [" + pjp.getMethod().getName() + "] will name the span [" + changedName + "]");
//        }
//        span.name(changedName);
//        this.tracer.nextSpan().name(changedName).start();
//    }
////
////    @Override public Span createSpan(MethodInvocation pjp, NewSpan newSpanAnnotation) {
////        String name = StringUtils.isEmpty(newSpanAnnotation.name()) ?
////                pjp.getMethod().getName() : newSpanAnnotation.name();
////        String changedName = SpanNameUtil.toLowerHyphen(name);
////        if (log.isDebugEnabled()) {
////            log.debug("For the class [" + pjp.getThis().getClass() + "] method "
////                    + "[" + pjp.getMethod().getName() + "] will name the span [" + changedName + "]");
////        }
////        return this.tracer.tracer().nextSpan().name(changedName).start();
////    }
//}
