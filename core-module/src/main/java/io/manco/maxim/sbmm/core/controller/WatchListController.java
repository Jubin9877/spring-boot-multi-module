package io.manco.maxim.sbmm.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.manco.maxim.sbmm.core.domain.WatchListDesc;
import io.manco.maxim.sbmm.core.service.WatchListService;

@Controller
public class WatchListController {

  private static final Logger LOGGER = LoggerFactory.getLogger(WatchListController.class);
  @Autowired
  private WatchListService service;

  @RequestMapping(value = "/view-watchlist", method = RequestMethod.GET)
  public String viewAccount(ModelMap model, @RequestParam int id) {
    model.addAttribute("stockSymbolsList", service.getStockSymbolsList(id));
    model.addAttribute("watchListId", id);
    return "view-watchlist";
  }

  @GetMapping(value = "/add-watchlist")
  public String addWatchList(ModelMap model, @RequestParam int id) {
    LOGGER.info("Add request Watchlist with id : {} ", id);
    WatchListDesc watchList = new WatchListDesc();
    watchList.setWatchListId(id);
    model.addAttribute("theWatchListDesc", watchList);
    return "lazyRowLoad";
  }

  @PostMapping(value = "/lazyRowAdd.web")
  public String lazyRowAdd(@ModelAttribute("theWatchListDesc") WatchListDesc watchList,
      @ModelAttribute("username1") String watchlistName, @RequestParam("id") int accId) {
    LOGGER.info("lazy row add for account : {}", accId);
    service.create(watchList, accId, watchlistName);
    return "redirect:/view-account?id=2";
  }
}
