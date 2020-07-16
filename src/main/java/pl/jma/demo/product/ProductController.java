package pl.jma.demo.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;
  private final ProductSuggestionService productSuggestionService;

  public ProductController(ProductService productService,
      ProductSuggestionService productSuggestionService) {
    this.productService = productService;
    this.productSuggestionService = productSuggestionService;
  }

  @PostMapping
  public Product addProduct(@RequestBody ProductDto productDto) {
    return productService.addProduct(productDto.getName(), productDto.isAvailable());
  }

  @GetMapping("/suggest")
  public List<String> getProductSuggestion() {
    return productSuggestionService.suggestProducts();
  }
}
