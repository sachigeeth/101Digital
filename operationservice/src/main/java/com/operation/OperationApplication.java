package com.operation;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableJpaRepositories(repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)
@EnableJpaAuditing(dateTimeProviderRef = "dateAuditingProvider", auditorAwareRef = "usernameAuditorAware")
@EnableFeignClients
public class OperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationApplication.class, args);
    }

    @Bean
    public HealthIndicator dbHealthIndicator() {
        return () -> Health.status(Status.UP).withDetail("hello", "hi").build();
    }

}
