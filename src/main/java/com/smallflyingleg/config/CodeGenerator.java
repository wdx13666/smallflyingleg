package com.smallflyingleg.config;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {

	private static String[] tables= {"sf_sys_permission","sf_sys_role","sf_sys_role_permission","sf_sys_user_role","sf_sys_user"};

    public static void main(String[] args) {
    // 全局配置
    GlobalConfig config = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		config.setActiveRecord(true) // 是否支持AR模式
                .setAuthor("wdx") // 作者
				.setOutputDir(projectPath + "/src/main/java") // 生成路径
				.setFileOverride(false)// 文件覆盖
				.setServiceName("%sService") // 设置生成的service接口名
				.setIdType(IdType.AUTO) // 主键策略
    ;
    // 数据源配置
    DataSourceConfig dsConfig = new DataSourceConfig();
		dsConfig.setDbType(DbType.MYSQL).setUrl("jdbc:mysql://localhost:3306/small?serverTimezone=UTC&characterEncoding=utf-8").setDriverName("com.mysql.jdbc.Driver")
				.setUsername("root").setPassword("root");
    // 策略配置
    StrategyConfig stConfig = new StrategyConfig();
		stConfig.setCapitalMode(true) // 全局大写命名
                .setDbColumnUnderline(true) // 表名 字段名 是否使用下滑线命名
				.setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
				.setInclude(tables)// 生成的表
				.setTablePrefix("sf_"); // 表前缀
    // 包名策略
    PackageConfig pkConfig = new PackageConfig();
		pkConfig.setParent("com.smallflyingleg").setController("controller").setEntity("pojo").setService("service").setXml("mapper");
    AutoGenerator ag = new AutoGenerator().setGlobalConfig(config).setDataSource(dsConfig).setStrategy(stConfig)
            .setPackageInfo(pkConfig);
		ag.execute();
		}
}
