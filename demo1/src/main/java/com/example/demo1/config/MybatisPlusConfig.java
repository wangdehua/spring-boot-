package com.example.demo1.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.HashMap;

@Configuration
@MapperScan(value = "com.example.demo1.mapper")
@EnableTransactionManagement(order = 2)//由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
@ComponentScan({"com.example.demo1.config"})
public class MybatisPlusConfig {

    @Autowired
    private MasterProperties masterProperties;

    @Autowired
    private SlaveProperties slaveProperties;

    private DruidDataSource stationDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        this.dataSourceConfig(dataSource, masterProperties, null);
        return dataSource;
    }

    /**
     * 另一个数据源
     */
    private DruidDataSource subBureauDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        this.dataSourceConfig(dataSource, null, slaveProperties);
        return dataSource;
    }

    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "datasource", name = "muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return this.stationDataSource();
    }


    /**
     * 多数据源连接池配置
     * ConditionalOnProperty注解用来控制多数据源下DataSourceAspect切面生效
     */
    @Bean
    @ConditionalOnProperty(prefix = "datasource", name = "muti-datasource-open", havingValue = "true")
    public DynamicDataSource mutiDataSource() {
        // a数据源
        DruidDataSource stationDataSource = this.stationDataSource();
        // b数据源
        DruidDataSource subBureauDataSource = this.subBureauDataSource();

        try {
            stationDataSource.init();
            subBureauDataSource.init();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> hashMap = new HashMap();
        hashMap.put("stationDataSource", stationDataSource);
        hashMap.put("subBureau", subBureauDataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(stationDataSource);
        return dynamicDataSource;
    }

    /**
     * 分页插件，自动识别数据库类型
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    /**
     * 配置数据源连接信息
     *
     * @param dataSource
     */
    private void dataSourceConfig(DruidDataSource dataSource, MasterProperties masterProperties,
                                  SlaveProperties slaveProperties) {
        dataSource.setUrl(masterProperties == null ? slaveProperties.getUrl() : masterProperties.getUrl());
        dataSource.setUsername(masterProperties == null ? slaveProperties.getUsername() : masterProperties.getUsername());
        dataSource.setPassword(masterProperties == null ? slaveProperties.getPassword() : masterProperties.getPassword());
        dataSource.setDriverClassName(masterProperties == null ? slaveProperties.getDriverClassName() : masterProperties.getDriverClassName());
    }

}
