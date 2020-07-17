package pl.jma.demo.product;

import org.slf4j.Logger;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ProductReactiveService {

  private static final Logger logger = getLogger(ProductReactiveService.class);

  private final ReactiveElasticsearchOperations elasticsearchOperations;

  public ProductReactiveService(ReactiveElasticsearchOperations elasticsearchOperations) {
    this.elasticsearchOperations = elasticsearchOperations;
  }

  public void saveWithMono(final ProductDto productDto) {
    Mono.just(productDto)
        .map(dto -> new Product(dto.getName(), dto.isAvailable()))
        .flatMap(elasticsearchOperations::save)
        .doOnNext(savedProduct -> logger.info("Saved {}", savedProduct.getProductName()))
        .subscribe();
  }
}
