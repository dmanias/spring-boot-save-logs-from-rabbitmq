package com.jtampakakis.bluemaster.savelogs.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SenderConfig {
	
	@Value("${queue.name}")
    private String message;

    @Bean
    public Queue queue() {
        return new Queue(message, true);
    }
    
    @Bean
    TopicExchange adminExchange() {
        return new TopicExchange(Exchanges.admin.label);
    }
    @Bean
    TopicExchange configExchange() {
        return new TopicExchange(Exchanges.config.label);
    }
    @Bean
    TopicExchange crewpinsExchange() {
        return new TopicExchange(Exchanges.crewpins.label);
    }
    
    @Bean
    Binding customerCreated(Queue testeQueue, TopicExchange adminExchange) {
        return BindingBuilder.bind(testeQueue).to(adminExchange).with(EventName.AdminCustomerCreated.label);
    }    
    @Bean
    Binding configBuilt(Queue testeQueue, TopicExchange configExchange) {
        return BindingBuilder.bind(testeQueue).to(configExchange).with(EventName.RouterConfigBuilt.label);
    }
    @Bean
    Binding sessionStopped(Queue testeQueue, TopicExchange crewpinsExchange) {
        return BindingBuilder.bind(testeQueue).to(crewpinsExchange).with(EventName.PINSessionStoped.label);
    }
}
