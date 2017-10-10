package io.manco.maxim.sbmm.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.manco.maxim.sbmm.domain.Account;
import io.manco.maxim.sbmm.service.AccountService;
import io.manco.maxim.sbmm.service.WatchListService;

@Controller
public class AccountController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  private AccountService service;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private WatchListService watchListService;

  @GetMapping(value = "/signed/list-accounts")
  public String showAccounts(ModelMap model) {
    LOGGER.info("Request to get all account.");
    model.addAttribute("ACCOUNT_LIST", service.getAccountList());
    return "list-accounts";
  }

  @PostMapping(value = "/signed/edit-account")
  public String putEditedAccountToDb(Account account, RedirectAttributes redirectAttributes) {
    LOGGER.info("Request to edit account details.");
    service.updateAccount(account);
    redirectAttributes.addAttribute("success", "true");
    return "redirect:/signed/list-accounts";
  }

  @GetMapping(value = "/signed/add-account")
  public String createNewAccountGet(ModelMap model) {
    LOGGER.info("Request to add  account.");
    model.addAttribute("account", new Account());
    return "add-account";
  }

  @PostMapping(value = "/signed/processAddAccount1")
  public String createAccountPost1(@RequestParam(value = "username1", required = false) String username1) {
    LOGGER.info("Request to Process add account1.");
    Account account = new Account();
    account.setAccountName(username1);
    service.createAccount(account);
    return "redirect:/signed/list-accounts";
  }

  @PostMapping(value = "/signed/processAddAccount")
  public String createAccountPost2(@RequestParam(value = "username1", required = false) String username1,
      @RequestParam(value = "password1", required = false) String password1,
      @RequestParam(value = "email1", required = false) String email1,
      @RequestParam(value = "additionalInfo1", required = false) String additionalInfo1) {
    LOGGER.info("Request to Process add account.");
    Account account = new Account();
    account.setEmail(email1);
    account.setAccountName(username1);
    account.setPassword(passwordEncoder.encode(password1));
    account.setAdditionalInfo(additionalInfo1);
    account.setEnabled(true);
    service.createAccount(account);
    return "redirect:/signed/list-accounts";
  }

  @PostMapping(value = "/signed/add-account")
  public String createAccountPost(Account account,
      @RequestParam(value = "username1", required = false) String username1) {
    LOGGER.info("Request to  add account.");
    service.createAccount(account);
    return "redirect:/signed/list-accounts";
  }

  @GetMapping(value = "signed/delete-account")
  public String deleteAccount(@RequestParam int id) {
    LOGGER.info("Request to  delete account.");
    service.delete(id);
    return "redirect:/signed/list-accounts";
  }

  @GetMapping(value = "/signed/edit-account")
  public String getAccountToEditAndPopulateForm(ModelMap model, @RequestParam int id) {
    LOGGER.debug("Request to Edit account.");
    Account account = service.retrieveAccount(id);
    service.updateAccount(account);
    model.addAttribute("account", account);
    return "edit-account";
  }

  @GetMapping(value = "/signed/getImage")
  @ResponseBody
  public byte[] showImage(@RequestParam("accId") int itemId/* , HttpServletResponse response */)
      throws ServletException, IOException {
    byte[] buffer = service.getImage(itemId);
    return buffer;
  }

  @PostMapping("/signed/uploadImage")
  public String setNewImage(@RequestParam("image") MultipartFile imageFile, @RequestParam("id") Integer accountId,
      RedirectAttributes redirectAttributes) {
    if (imageFile.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:uploadStatus";
    }
    try {
      byte[] bytes = imageFile.getBytes();
      InputStream imageConvertedToInputStream = new ByteArrayInputStream(bytes);
      service.setImage(accountId, imageConvertedToInputStream);
      redirectAttributes.addFlashAttribute("message",
          "You successfully uploaded '" + imageFile.getOriginalFilename() + "'");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "redirect:/signed/uploadStatus";
  }

  @GetMapping(value = "/signed/view-account")
  public String viewAccount(ModelMap model, @RequestParam int id) {
    LOGGER.debug("Request to Process View account.");
    Account account = service.retrieveAccount(id);
    Object accountWatchList = watchListService.getWatchListForAccount(id);
    model.addAttribute("watchLists", accountWatchList);
    model.addAttribute("account", account);
    return "view-account";
  }

  @GetMapping(value = "/home")
  public String viewHome() {
    return "redirect:/signed/list-accounts";
  }
}
