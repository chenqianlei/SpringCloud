package online.qsx.demo.eurekahystrixturbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableHystrixDashboard
@EnableTurbine
@SpringBootApplication
public class EurekahystrixturbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekahystrixturbineApplication.class, args);
	}

}
