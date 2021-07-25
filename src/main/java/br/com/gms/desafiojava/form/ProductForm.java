package br.com.gms.desafiojava.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gms.desafiojava.model.Product;
import br.com.gms.desafiojava.repository.ProductRepository;

public class ProductForm {
	
	@NotNull @NotEmpty
	private String name;
	@NotNull @NotEmpty
	private String description;
	@NotNull @NotEmpty @Min(value = 0)
	private float price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public Product converter() {
		return new Product(name, description, price);
	}
	public Product updated(String id, ProductRepository productRepository) {
		Product product = productRepository.getById(id);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		return product;
	}

}
