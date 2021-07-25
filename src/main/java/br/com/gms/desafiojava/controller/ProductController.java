package br.com.gms.desafiojava.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gms.desafiojava.dto.ProductDTO;
import br.com.gms.desafiojava.form.ProductForm;
import br.com.gms.desafiojava.model.Product;
import br.com.gms.desafiojava.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository; 
	
	@GetMapping
	public ResponseEntity<?> list(){
		List<Product> products = productRepository.findAll();
		
		return ResponseEntity.ok(ProductDTO.converter(products));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDTO> created(@RequestBody @Valid ProductForm productForm, UriComponentsBuilder uri){ 
		Product product = productForm.converter();
		productRepository.save(product);
		URI uriPath = uri.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uriPath).body(new ProductDTO(product));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDTO> updated(@PathVariable String id, @RequestBody @Valid ProductForm productForm){
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			Product product = productForm.updated(id, productRepository);
			return ResponseEntity.ok(new ProductDTO(product));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> read(@PathVariable String id){
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			Product product = productRepository.getById(id);
			return ResponseEntity.ok(new ProductDTO(product));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id){
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProductDTO>> search(String q, Float min_price, Float max_price){
		
		if(q == null && max_price == null && min_price ==null) {
			return ResponseEntity.notFound().build();
		}
		List<Product> products = new ArrayList<>();
		
		if(min_price == null) {
			min_price = 0.0f;
		}
		
		if(max_price == null) {
			max_price = 99999.99f;
		}
		
		if(q != null) {
			products = productRepository.findByCriteriaPriceANDNameOrDescription(q, min_price, max_price);
			
		}else {
			
		  products =	productRepository.findByCriteriaPrice(min_price, max_price);
		}
		
		return ResponseEntity.ok(ProductDTO.converter(products));
	}
	
	

}
