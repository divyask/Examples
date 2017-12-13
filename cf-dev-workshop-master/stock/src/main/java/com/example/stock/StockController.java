package com.example.stock;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    private Logger logger = Logger.getLogger(StockController.class);

    @PostMapping("/stock")
    public Stock stock(@RequestParam(value = "ticker") String ticker,
                       @RequestParam(value = "price") Double price){
        Stock stock = new Stock(ticker, price);

        Stock savedStock = stockRepository.save(stock);

        if (savedStock == null) {
            logger.error("SavedStock: " + savedStock);
        }

        return savedStock;
    }


    @GetMapping("/stock")
    public Stock stock(@RequestParam(value = "ticker") String ticker){
        Stock savedStock = stockRepository.findOne(ticker);

        if (savedStock == null) {
            logger.error("SavedStock: " + savedStock);
        }
        return savedStock;
    }
}
