package com.example.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "product-index")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = -4193301264047593807L;

    @Id
    private String id;

    @Field(type = FieldType.Keyword, analyzer = "edge_ngram_analyzer", searchAnalyzer = "standard")
    private String name;

    @Field(type = FieldType.Keyword, analyzer = "edge_ngram_analyzer", searchAnalyzer = "standard")
    private String description;

    @Field(type = FieldType.Double)
    private Double price;

}
