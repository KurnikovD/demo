package com.example.demo.spark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Source("data/sparkDomains.json")
public class Domain {

    private String hostName;
    private Integer count;
}
