package pl.jma.demo.product;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class ProductSuggestionService {

  private static final Logger logger = getLogger(ProductService.class);

  private final RestHighLevelClient client;

  public ProductSuggestionService(RestHighLevelClient client) {
    this.client = client;
  }

  public List<String> suggestProducts() {

    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    SuggestionBuilder termSuggestionBuilder =
        SuggestBuilders.termSuggestion("productName").text("Hamer");
    SuggestBuilder suggestBuilder = new SuggestBuilder();
    suggestBuilder.addSuggestion("suggest_product", termSuggestionBuilder);
    searchSourceBuilder.suggest(suggestBuilder);

    SearchRequest searchRequest = new SearchRequest();
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse;

    try {
      searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
      Suggest suggest = searchResponse.getSuggest();
      TermSuggestion termSuggestion = suggest.getSuggestion("suggest_product");
      return termSuggestion.getEntries().stream()
          .flatMap(entry -> entry.getOptions().stream())
          .map(option -> option.getText().string())
          .collect(toList());
    } catch (IOException e) {
      logger.error(e.getMessage());
      return new ArrayList<>();
    }
  }
}
