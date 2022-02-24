package cc.zenking.cloud.comertc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import cc.zenking.cloud.comertc.mapper.UserMapper;

/**
 * 启动类
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-16
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@MapperScan(basePackageClasses = UserMapper.class)
@EnableScheduling
@SpringBootApplication
public class ComeRTCApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(ComeRTCApplication.class, args);
	}

}
