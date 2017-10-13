package io.manco.maxim.sbmm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.manco.maxim.sbmm.service.BarService;

@RestController
public class AjaxSearchController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AjaxSearchController.class);

  @Autowired
  public BarService service;

  @RequestMapping(value = "/signed/DataControllerSearch", method = RequestMethod.GET)
  public List<String> searchlist(@RequestParam String searchParam) {
    LOGGER.info("Request to Datacontroller for Bar Chart.");
    List<String> tickets = service.searchTickersByChars(searchParam);
    return tickets;
  }

}
