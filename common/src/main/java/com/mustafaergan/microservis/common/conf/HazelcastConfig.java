package com.mustafaergan.microservis.common.conf;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Value("${hazelcast.instance_name}")
    private String instanceName;

    @Value("${hazelcast.map_config_name}")
    private String mapConfigName;

    @Value("${hazelcast.map_config_max_size_config}")
    private int mapConfigMaxSizeConfig;

    @Value("${hazelcast.map_config_time_to_live_seconds}")
    private int mapConfigTimeToLiveSeconds;



    @Bean
    public Config hazelCastConfig(){
        return new Config()
                .setInstanceName(this.instanceName)
                .addMapConfig(
                        new MapConfig()
                                .setName(this.mapConfigName)
                                .setMaxSizeConfig(new MaxSizeConfig(this.mapConfigMaxSizeConfig, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(this.mapConfigTimeToLiveSeconds));
    }

}
