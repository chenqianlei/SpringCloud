package online.qsx.demo.eurekaconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * �������ķ���
 */
@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class EurekaconfigApplication {

	public static void main(String[] args) {

        SpringApplication.run(EurekaconfigApplication.class, args);	
	}

}
