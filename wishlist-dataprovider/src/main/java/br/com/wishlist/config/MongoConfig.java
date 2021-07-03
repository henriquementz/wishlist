package br.com.wishlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(
            Arrays.asList(
                new OffsetDateTimeConverter.OffsetDateTimeReadConverter(),
                new OffsetDateTimeConverter.OffsetDateTimeWriteConverter()));
    }
}
