package online.qsx.demo.eurekazuul;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * ·��
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class EurekazuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekazuulApplication.class, args);
    }
    
	@Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}
}

/**
 * ·�ɵĹ���
 */
@Component
class MyFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    /**
     * ����һ���ַ�����������������ͣ���zuul�ж��������ֲ�ͬ�������ڵĹ���������
     * pre��·��֮ǰ
     * routing��·��֮ʱ
     * post�� ·��֮��
     * error�����ʹ������
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * ���˵�˳��
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * �������д�߼��жϣ��Ƿ�Ҫ���ˣ�true,��Զ����
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * �������ľ����߼������úܸ��ӣ�������sql��nosqlȥ�жϸ����󵽵���û��Ȩ�޷���
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            logger.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}
            return null;
        }
        logger.info("ok");
        return null;
    }
}
