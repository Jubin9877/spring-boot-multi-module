package io.manco.maxim.sbmm.controller;

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

import io.manco.maxim.sbmm.domain.WatchListDesc;
import io.manco.maxim.sbmm.service.WatchListService;

@Controller
public class WatchListController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WatchListController.class);
	@Autowired
	private WatchListService service;

	@RequestMapping(value = "/signed/view-watchlist", method = RequestMethod.GET)
	public String viewAccount(ModelMap model, @RequestParam int id) {
		model.addAttribute("stockSymbolsList", service.getStockSymbolsList(id));
		model.addAttribute("watchListId", id);
		return "view-watchlist";
	}

	@GetMapping(value = "/signed/add-watchlist")
	public String addWatchList(ModelMap model, @RequestParam int id) {
		LOGGER.info("Add Watchlist request for account with id : {} ", id);
		WatchListDesc watchList = new WatchListDesc();
		model.addAttribute("theWatchListDesc", watchList);
		model.addAttribute("accountId", id);
		return "lazyRowLoad";
	}

	@PostMapping(value = "/signed/lazyRowAdd.web")
	public String lazyRowAdd(@ModelAttribute("theWatchListDesc") WatchListDesc watchListDesc,
			@ModelAttribute("watchListName") String watchlistName,
			@ModelAttribute("watchListDetails") String watchListDetails,
			@ModelAttribute("marketDataFrequency") Integer marketDataFrequency, @RequestParam("id") int accId) {
		LOGGER.info("lazy row add for account : {}", accId);
		watchListDesc.setWatchListName(watchlistName);
		watchListDesc.setStockSymbolsListFromOperationList(watchListDesc.getOperationParameterses());
		watchListDesc.setWatchListDetails(watchListDetails);
		watchListDesc.setMarketDataFrequency(marketDataFrequency);
		service.create(watchListDesc, accId);
		return "redirect:/signed/view-account?id="+accId;
	}
}
