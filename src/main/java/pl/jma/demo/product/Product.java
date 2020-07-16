package pl.jma.demo.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "products", shards = 1)
public class Product {

  @Id
  private String id;

  @Field(type = FieldType.Text, index = true)
  private String productName;

  @Field(type = FieldType.Boolean)
  private boolean available;

  public Product() {
  }

  public Product(String productName, boolean available) {
    this.productName = productName;
    this.available = available;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }
}
