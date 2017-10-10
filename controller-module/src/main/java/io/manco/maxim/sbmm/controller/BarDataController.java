package io.manco.maxim.sbmm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.manco.maxim.sbmm.domain.Bar;
import io.manco.maxim.sbmm.domain.Stock;
import io.manco.maxim.sbmm.repository.StockRepository;
import io.manco.maxim.sbmm.service.BarService;

@Controller
public class BarDataController {
  private static final Logger LOGGER = LoggerFactory.getLogger(BarDataController.class);
  @Autowired
  private BarService service;

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return super.toString();
  }

  @Autowired
  public StockRepository stockRepository;

  @RequestMapping(value = "/signed/view-data", method = RequestMethod.GET)
  public String viewAccount(ModelMap model, @RequestParam String stockSymbol) {
    Stock stock = stockRepository.findByName(stockSymbol);
    LOGGER.info("Request to View Data for Bar chart.");
    List<Bar> barList = service.getBarListStock(stock.getId());
    model.addAttribute("barData", barList);
    return "view-data";
  }

  @RequestMapping(value = "/signed/search", method = RequestMethod.GET)
  public String searchGet1() {
    return "search";
  }

  @RequestMapping(value = "/signed/DataController", method = RequestMethod.GET)
  public String searchGet2(ModelMap model, @RequestParam String searchParam) {
    LOGGER.info("Request to Datacontroller for Bar Chart.");
    List<String> tickets = service.searchTickersByChars(searchParam);
    model.addAttribute("THE_SEARCH_RESULT_LIST", tickets);
    return "search";
  }

  // @RequestMapping(value = "/signed/search", method = RequestMethod.POST)
  // public String searchPost(ModelMap model, @RequestParam String stockSymbol) {
  // LOGGER.info("Request to Search for instrument id.");
  // List<Bar> barList = service.getBarList(stockSymbol);
  // model.addAttribute("barData", barList);
  // return "view-data";
  // }
}
