package pl.jma.demo.product;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository  extends ElasticsearchRepository<Product, String> {
}
