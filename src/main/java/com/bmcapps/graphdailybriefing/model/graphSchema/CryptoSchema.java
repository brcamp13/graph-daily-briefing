package com.bmcapps.graphdailybriefing.model.graphSchema;

import lombok.Data;

@Data
public class CryptoSchema {
    private String name;
    private String symbol;
    private double price;
    private double marketCap;
    private double percentChange24h;
    private double percentChange60d;
    private double percentChange90d;
}
