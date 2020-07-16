package pl.jma.demo.product;

import org.springframework.stereotype.Component;

@Component
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product addProduct(String name, boolean available) {
    Product product = new Product(name, available);
    return productRepository.save(product);
  }
}
