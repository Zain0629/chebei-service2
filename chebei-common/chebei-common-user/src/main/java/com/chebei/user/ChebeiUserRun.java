package com.chebei.user;

import com.chebei.ams.server.AmsApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author shengjeiWang
 * @since 2020/08/06
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.chebei", "com.chebei.ams"})
@MapperScans(value = {@MapperScan("com.chebei.*.mapper")})
@PropertySource(value = {"classpath:application.yml"})
public class ChebeiUserRun {

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(ChebeiUserRun.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		ApplicationContext applicationContext = springApplication.run(args);
		AmsApplication.start(applicationContext);
	}

}
