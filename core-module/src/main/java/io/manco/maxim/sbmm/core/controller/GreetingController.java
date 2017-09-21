package io.manco.maxim.sbmm.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.manco.maxim.sbmm.core.domain.Greeting;

@Controller
public class GreetingController {

  private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

  @GetMapping("/greeting")
  public String greetingForm(Model model) {
    model.addAttribute("greeting", new Greeting());
    return "greeting";
  }

  @RequestMapping(value = "/greeting", method = RequestMethod.POST)
  public String greetingSubmit(@ModelAttribute Greeting greeting) {
    LOGGER.info("Greeting : ", greeting.getContent());
    return "result";
  }

}
