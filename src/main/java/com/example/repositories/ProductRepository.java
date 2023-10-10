package com.example.repositories;


import com.example.domains.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"*\"], \"fuzziness\": \"AUTO\"}}")
    Page<Product> findByProductKeyword(String keyword, Pageable pageable);

}
