package com.example.stock;

import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, String>{
}
