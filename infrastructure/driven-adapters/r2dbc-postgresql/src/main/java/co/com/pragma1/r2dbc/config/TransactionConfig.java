package co.com.pragma1.r2dbc.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.TransactionAwareConnectionFactoryProxy;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
public class TransactionConfig {

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(new TransactionAwareConnectionFactoryProxy(connectionFactory));
    }

    @Bean
    public TransactionalOperator transactionalOperator(ReactiveTransactionManager txManager) {
        return TransactionalOperator.create(txManager);
    }
}

