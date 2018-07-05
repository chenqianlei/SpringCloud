package online.qsx.demo.eurekaribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * ���������� rest+ribbon ���ø��ؾ���
 */
@EnableHystrix
@RestController
@SpringBootApplication
public class EurekaribbonApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaribbonApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	// ���ؾ��� eureka-client
	// ��֤���ؾ�����Ҫͬʱ������� eureka-client ����
	@GetMapping("/hi01")
    @HystrixCommand(fallbackMethod = "hiError")
	public String hi01() {
		return restTemplate.getForObject("http://eureka-client/hi?name=arvin01", String.class);
	}
	
    //�۶Ϸ���
    public String hiError() {
        return "EurekaribbonApplication hiError";
    }
}
