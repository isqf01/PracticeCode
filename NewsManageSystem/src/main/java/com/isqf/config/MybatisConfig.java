package com.isqf.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean SqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sessionFactoryBean= new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        //设置模型类的别名扫描
        sessionFactoryBean.setTypeAliasesPackage("com.isqf.domain");
        return sessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        //映射文件扫描包路径
        msc.setBasePackage("com.isqf.dao");
        return msc;
    }

}
