package com.bmcapps.graphdailybriefing.model.graphSchema;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class CryptoMarketDataSchema {
    private int fearAndGreedIndexValue;
    private String fearAndGreedIndexValueClassification;
}
