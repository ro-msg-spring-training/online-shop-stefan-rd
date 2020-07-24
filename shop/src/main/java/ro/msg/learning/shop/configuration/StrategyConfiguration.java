package ro.msg.learning.shop.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;
import ro.msg.learning.shop.service.strategy.Strategy;

@Configuration
public class StrategyConfiguration
{
    @ConditionalOnProperty(prefix = "user", name = "locationStrategy", havingValue = "mostAbundant", matchIfMissing = false)
    @Bean
    public Strategy mostAbundantStrategy()
    {
        return new MostAbundantStrategy();
    }

    @ConditionalOnProperty(prefix = "user", name = "locationStrategy", havingValue = "singleLocation", matchIfMissing = true)
    @Bean
    public Strategy singleLocationStrategy()
    {
        return new SingleLocationStrategy();
    }
}
