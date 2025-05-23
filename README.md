# GraphDailyBriefing

📋 **Description**  
GraphDailyBriefing is a Java Spring Boot application that provides a GraphQL interface for accessing weather and cryptocurrency data.

🛠️ **Prerequisites**
- Java 21 installed

▶️ **Running the App**
1. Ensure Java 21 is installed.
2. Open the project in IntelliJ.
3. Run the application.

🔗 **Dependencies**
- Weather MS: [link](https://github.com/brcamp13/graph-daily-briefing-weather-ms) (should be running on port 9090)
- Crypto MS: [link](https://github.com/brcamp13/graph-daily-briefing-crypto-ms) (should be running on port 9091)

🧪 **Testing the Endpoint**  
Visit [http://localhost:8080/graphiql](http://localhost:8080/graphiql) and use the following sample query:

```graphql
{
  weather(city:"Dallas", state:"TX") {
    temperature
    precipitation
    relativeHumidity
    windSpeed
    windDirection
    windGusts
  }
  cryptocurrencies(slugs: ["bitcoin", "ethereum", "chainlink", "pepe"]) {
    name
    symbol
    price
    marketCap
    percentChange24h
    percentChange60d
    percentChange90d
  }
  cryptoMarketData {
    fearAndGreedIndexValue
    fearAndGreedIndexValueClassification
  }
}
```