package com.br.schoolreyfowlogin.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.br.schoolreyfowlogin.repository")
public class DynamodbConfig {

    @Value(value = "${aws.endpoint}")
    private String endpoint;

    @Value(value = "${app.local:false}")
    private boolean isLocal;


    @Bean
    public AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(endpoint, Regions.US_EAST_1.getName());
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }


    @Bean
    @Primary
    public AmazonDynamoDB amazonDynamoDB(AwsClientBuilder.EndpointConfiguration endpointConfiguration) {
        if (isLocal) {
            return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(endpointConfiguration)
                    .build();
        }
        return AmazonDynamoDBClientBuilder.defaultClient();
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }
}

