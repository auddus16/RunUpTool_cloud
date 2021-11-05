package com.paas.runup.config.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Profile("cloud")
@Configuration
@ServiceScan
public class CloudConfigData extends AbstractCloudConfig {

	@Value("${db.mysql.servicename}")
	private String mysqlServiceName;

//	private String cubridJdbcUrl;

	@Bean(name = "dsmysql")
	@Primary
	DataSource mysqlDataSource() {
		log.info("cloud mysqlDataSource 메소드 호출");
		CloudFactory cloudFactory = new CloudFactory();
		Cloud cloud = cloudFactory.getCloud();
		ServiceInfo serviceInfo = cloud.getServiceInfo(mysqlServiceName);
		String serviceId = serviceInfo.getId();
		
		
		return cloud.getServiceConnector(serviceId, DataSource.class, null);
	}

	
	@Bean(name = "jdbcMysql")
	// ==> MySql 서비스 Connection설정
	@Autowired
	public JdbcTemplate mysqlJdbcTemplate(@Qualifier("dsmysql") DataSource dsSlave) {
		log.info("jdbcTemplate생성");
		return new JdbcTemplate(dsSlave);
	}

//	@Bean
//	public SqlSessionFactory sqlSessionFactory(@Qualifier("dsmysql")DataSource dataSource) throws Exception {
//		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource);
//		
//		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*Mapper.xml");		
//		sessionFactory.setMapperLocations(res);
//		
//		return sessionFactory.getObject();
//	}
	
//	@Bean(name = "dsCubrid")
//	public DataSource cubridDataSource() {
//	    try {
//	        String vcap_services = System.getenv("VCAP_SERVICES");
//	        JSONObject jsonObj = JSONObject.fromObject(vcap_services);
//	        JSONArray userPro = jsonObj.getJSONArray("CubridDB");
//	        jsonObj = JSONObject.fromObject(userPro.get(0));
//	        jsonObj = jsonObj.getJSONObject("credentials");
//	        cubridJdbcUrl = jsonObj.getString("jdbcurl");
//	        return new SimpleDriverDataSource(cubrid.jdbc.driver.CUBRIDDriver.class.newInstance(), cubridJdbcUrl);
//	    } catch (Exception e) {
//	        throw new RuntimeException(e);
//	    }
//	}

//	@Bean(name = "jdbcCubrid")
//	//==> Cubrid 서비스 Connection설정
//	@Autowired
//	public JdbcTemplate cubridJdbcTemplate(@Qualifier("dsCubrid") DataSource dsSlave) {
//	    return new JdbcTemplate(dsSlave);
//	}
//	/**
//	 * MongoDBFactory
//	 */
//	@Autowired
//	MongoDbFactory mongoDbFactory;
//	@Bean(name = "mongoTemplate")   ==> MongoDB 서비스 Connection설정
//	public MongoTemplate mongoTemplate() throws UnknownHostException {
//	    CloudFactory cloudFactory = new CloudFactory();
//	    Cloud cloud = cloudFactory.getCloud();
//	    MongoServiceInfo serviceInfo = (MongoServiceInfo) cloud.getServiceInfo(mongoServiceName);
//	    // MongoDB 인증 처리
//	    mongoDbFactory.getDb().authenticate(serviceInfo.getUserName(), serviceInfo.getPassword().toCharArray());
//	    MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
//	    return mongoTemplate;
//	}
//	@Bean  ==> Redis 서비스 Connection설정
//	public JedisPool redisTemplate() {
//	    CloudFactory cloudFactory = new CloudFactory();
//	    Cloud cloud = cloudFactory.getCloud();
//	    RedisServiceInfo serviceInfo = (RedisServiceInfo) cloud.getServiceInfo(redisServiceName);
//	    JedisPoolConfig poolConfig = new JedisPoolConfig();
//	    poolConfig.setMaxTotal(128);
//	    return new JedisPool(poolConfig, serviceInfo.getHost(), serviceInfo.getPort(), 5, serviceInfo.getPassword());
//	}
//	@Bean
//	@Primary
//	public CachingConnectionFactory rabbitmqConnectionFactory() throws IOException {
//	    CloudFactory cloudFactory = new CloudFactory();
//	    Cloud cloud = cloudFactory.getCloud();
//	    AmqpServiceInfo serviceInfo = (AmqpServiceInfo) cloud.getServiceInfo(rabbitServiceName);
//	    ConnectionFactory connectionFactory = new ConnectionFactory();
//	    connectionFactory.setHost(serviceInfo.getHost());
//	    connectionFactory.setPort(serviceInfo.getPort());
//	    connectionFactory.setUsername(serviceInfo.getUserName());
//	    connectionFactory.setPassword(serviceInfo.getPassword());
//	    connectionFactory.setVirtualHost(serviceInfo.getVirtualHost());
//	    try {
//	        // SslProtocol 사용 설정
//	        connectionFactory.useSslProtocol("TLS");
//	    } catch (KeyManagementException e) {
//	        e.printStackTrace();
//	    } catch (NoSuchAlgorithmException e) {
//	        e.printStackTrace();
//	    }
//	    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
//	    return cachingConnectionFactory;
//	}
//	@Bean  ==> RabbitMQ 서비스 Connection설정
//	public RabbitTemplate amqpTemplate(
//	        @Qualifier("rabbitmqConnectionFactory") CachingConnectionFactory connectionFactory) {
//	    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//	    rabbitTemplate.setQueue("sampleQueue");
//	    rabbitTemplate.setRoutingKey("sampleQueue");
//	    rabbitTemplate.setConnectionFactory(connectionFactory);
//	    return rabbitTemplate;
//	}
//	@Bean
//	public RabbitAdmin rabbitAdmin(@Qualifier("rabbitmqConnectionFactory") CachingConnectionFactory connectionFactory) {
//	    RabbitAdmin admin = new RabbitAdmin(connectionFactory);
//	    return admin;
//	}
//	@Autowired
//	Gson gson;

//	@Bean
//	public AccountConfig accountConfig() {
//		String vcap_services = System.getenv("VCAP_SERVICES");
//		JsonObject jsonObj = gson.fromJson(vcap_services, JsonElement.class).getAsJsonObject();
//		System.out.println(jsonObj);
//		JsonArray userPro = jsonObj.getAsJsonArray("glusterfs");
//		jsonObj = userPro.get(0).getAsJsonObject().getAsJsonObject("credentials");
/*
		String provider = jsonObj.get("provider").getAsString();
		String tenantName = jsonObj.get("tenantname").getAsString();
		String username = jsonObj.get("username").getAsString();
		String password = jsonObj.get("password").getAsString();
		String authUrl = jsonObj.get("auth_url").getAsString();

		AccountConfig config = new AccountConfig();
		config.setUsername(username);
		config.setTenantName(tenantName);
		config.setPassword(password);
		config.setAuthUrl(authUrl + "/tokens");
		config.setAuthenticationMethod(AuthenticationMethod.KEYSTONE);
		return config;
	}*/

}
