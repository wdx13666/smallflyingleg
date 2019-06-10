package com.smallflyingleg.utils;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MBG {

    public static void main(String[] args) {
        // 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) // 是否支持AR模式
                .setAuthor("wdx") // 作者
                .setOutputDir("F:\\project\\smallflyingleg\\src\\main\\java") // 生成路径
                .setEnableCache(false) //缓存  false不开启
                .setFileOverride(true)// 文件覆盖
                .setServiceName("%sService") // 设置生成的service接口名
                .setIdType(IdType.AUTO) // 主键策略
        ;
        // 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL).setUrl("jdbc:mysql://localhost:3306/small?serverTimezone=GMT").setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root").setPassword("root");
        // 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) // 全局大写命名
                .setDbColumnUnderline(true) // 表名 字段名 是否使用下滑线命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setInclude(new String [] {"sf_small","sf_small_check"}) // 生成的表
//                ,"sf_workflow_event","sf_workflow_event_user","sf_workflow_node","sf_workflow_record"
                .setTablePrefix("sf_"); // 表前缀
        // 包名策略
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.smallflyingleg").setController("controller").setEntity("pojo").setService("service").setXml("mapper");
        AutoGenerator ag = new AutoGenerator().setGlobalConfig(config).setDataSource(dsConfig).setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        ag.execute();
    }


}
