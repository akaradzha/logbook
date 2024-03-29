package com.example.demoweb.log;



import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Method;

@Component
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {

//    @Resource
//    DataSource dataSource;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof DataSource && !(bean instanceof ProxyDataSource)) {
            // Instead of directly returning a less specific datasource bean
            // (e.g.: HikariDataSource -> DataSource), return a proxy object.
            // See following links for why:
            //   https://stackoverflow.com/questions/44237787/how-to-use-user-defined-database-proxy-in-datajpatest
            //   https://gitter.im/spring-projects/spring-boot?at=5983602d2723db8d5e70a904
            //   http://blog.arnoldgalovics.com/2017/06/26/configuring-a-datasource-proxy-in-spring-boot/
            final ProxyFactory factory = new ProxyFactory(bean);
            factory.setProxyTargetClass(true);
            factory.addAdvice(new ProxyDataSourceInterceptor((DataSource) bean));
            return factory.getProxy();
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    private static class ProxyDataSourceInterceptor implements MethodInterceptor {
        private final DataSource dataSource;

        public ProxyDataSourceInterceptor(final DataSource dataSource) {
//            SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
//            loggingListener.setQueryLogEntryCreator(new InlineQueryLogEntryCreator());

            this.dataSource =
                    ProxyDataSourceBuilder.create(dataSource)
//                    ProxyDataSourceBuilderCustom.create(dataSource)
                    .name("MyDS")
//                    .logQueryByPB()
                    .multiline()
//                    .logQueryToSysOut()
//                    .logQueryByJUL()
//                    .listener(loggingListener)
//                    .traceMethods()
                    .asJson()
                    .logQueryBySlf4j(SLF4JLogLevel.INFO)
                    .build();
        }

        @Override
        public Object invoke(final MethodInvocation invocation) throws Throwable {
//            final Method proxyMethod = ReflectionUtils.findMethod(this.dataSource.getClass(),
//                    invocation.getMethod().getName());
//            if (proxyMethod != null) {
//                return proxyMethod.invoke(this.dataSource, invocation.getArguments());
//            }
            return invocation.proceed();
        }
    }
}