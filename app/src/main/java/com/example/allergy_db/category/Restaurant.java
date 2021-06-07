package com.example.allergy_db.category;

public class Restaurant {
  private Long id;
  private String name;
  private String image;
  private Long category;

  public Restaurant(Long id, String name, String image, Long category) {
    super();
    this.id = id;
    this.name = name;
    this.image = image;
    this.category = category;
  }

  public Restaurant(String name, String image, Long category) {
    super();
    this.name = name;
    this.image = image;
    this.category = category;
  }

  public Restaurant() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Long getCategory() {
    return category;
  }

  public void setCategory(Long category) {
    this.category = category;
  }
}
