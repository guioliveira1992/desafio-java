package br.com.gms.desafiojava.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gms.desafiojava.model.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	
	@Query(value = "SELECT p FROM Product p WHERE p.price >= :min_price AND p.price <= :max_price")
	List<Product> findByCriteriaPrice(@Param("min_price") float minPrice, @Param("max_price") float maxPrice);
	
	
	@Query(value = "SELECT p FROM Product p WHERE p.name >= :q OR p.description <= :q")
	List<Product> findByCriteriaNameOrDescription(@Param("q") String q);
	
	
	@Query(value = "SELECT p FROM Product p WHERE (LOWER(p.name) = LOWER(:q) OR LOWER(p.description) = LOWER(:q)) AND (p.price >= :min_price AND p.price <= :max_price)")
	List<Product> findByCriteriaPriceANDNameOrDescription(@Param("q") String q, @Param("min_price") float minPrice, @Param("max_price") float maxPrice);
	
	
	
}
