package org.hswebframework.web.workflow.flowable;


import com.alibaba.fastjson.JSONObject;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.hswebframework.web.tests.SimpleWebApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author wangwei
 * @Date 2017/8/1.
 */
@Configuration
@ComponentScan("org.hswebframework.web.workflow")
public class ControllerTest extends SimpleWebApplicationTests {

    @Test
    public void testRest() throws Exception {
        //todo 获取所有可办理流程
        JSONObject result = testGet("/workflow/process/definition").exec().resultAsJson();
        Assert.assertNotNull( result.getJSONObject("result"));
        Assert.assertEquals(200, result.get("status"));
        System.out.println(result);
    }


    @Configuration
    public static class config {
        @Autowired(required = false)
        private List<SessionFactory> sessionFactories;

        @Bean
        public ProcessEngineConfigurationConfigurer processEngineConfigurationConfigurer() {
            return configuration -> {
                configuration
                        .setAsyncExecutorActivate(false)
                        .setJobExecutorActivate(false)
                        .setDatabaseSchemaUpdate("false")
                        .setActivityFontName("宋体")
                        .setLabelFontName("宋体")
                        .setAnnotationFontName("宋体");

                if (sessionFactories != null) {
                    configuration.setCustomSessionFactories(sessionFactories);
                }
            };
        }
    }
}
