package com.paas.runup;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
//@EnableConfigServer
@EnableSwagger2
public class RunUpToolRepoApplication extends SpringBootServletInitializer{
	
//	private static SshTunneling tunnel;
//	
//	 public RunuptoolBackendApplication() {
//	        tunnel = new SshTunneling().init( res->{
//	            if(!res) {
//	                System.out.println("포트포워딩 실패, 프로그램을 종료 합니다.");
//	                System.exit(0);
//	            }
//	        });        
//	    }
	
	public static void main(String[] args) {
		
		SpringApplication.run(RunUpToolRepoApplication.class, args);
		
	}
//	public static void main(String[] args) {
//		new SpringApplicationBuilder(RunuptoolBackendApplication.class).
//			initializers(new SpringApplicationContextInitializer())
//			.application()
//			.run(args);
//	}
	
//	@PreDestroy
//    public void end() {
//        try {
//            tunnel.shutdown();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
	
	@Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RunUpToolRepoApplication.class); 
	}

	
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dsmysql")DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		
		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*Mapper.xml");
		sessionFactory.setMapperLocations(res);
		
		return sessionFactory.getObject();
	}
	
	@Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
	
//	@Bean
//	public SwoomiWebSocketHandler swoomiWebSocketHandler() {
//	  return new SwoomiWebSocketHandler();
//	}
	
}
