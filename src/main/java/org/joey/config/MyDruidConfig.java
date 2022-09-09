package org.joey.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author: JoeyShelby
 * @date: 2022-09-07 09:57
 *
 * druid 监控信息
 */
@Configuration
public class MyDruidConfig {

    // 绑定 springBoot 配置前缀

    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    // druid 后台监控配置

    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        HashMap<String, String> initParameters = new HashMap<>();

        //添加配置
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","0000");
        //任何人都可以访问
        initParameters.put("allow","");
        bean.setInitParameters(initParameters);
        return bean;

    }
}