package br.com.gms.desafiojava.dto;

import java.util.List;
import java.util.stream.Collectors;


import br.com.gms.desafiojava.model.Product;

public class ProductDTO {
	
	private String id;
	private String name;
	private String description;
	private float price;
	
	
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public float getPrice() {
		return price;
	}
	
	public static List<ProductDTO> converter(List<Product> products) {
		// TODO Auto-generated method stub
		return products.stream().map(ProductDTO::new).collect(Collectors.toList()); 
	}

}
