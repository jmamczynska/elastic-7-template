package pl.jma.demo.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;
  private final ProductSuggestionService productSuggestionService;
  private final ProductReactiveService productReactiveService;

  public ProductController(ProductService productService,
      ProductSuggestionService productSuggestionService,
      ProductReactiveService productReactiveService) {
    this.productService = productService;
    this.productSuggestionService = productSuggestionService;
    this.productReactiveService = productReactiveService;
  }

  @PostMapping
  public Product addProduct(@RequestBody ProductDto productDto) {
    return productService.addProduct(productDto.getName(), productDto.isAvailable());
  }

  @GetMapping("/suggest/{phrase}")
  public List<String> getProductSuggestion(@PathVariable final String phrase) {
    return productSuggestionService.suggestProducts(phrase);
  }

  @PostMapping("/mono")
  public void addProductWithMono(@RequestBody ProductDto productDto) {
    productReactiveService.saveWithMono(productDto);
  }
}
