package io.manco.maxim.sbmm.core.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.manco.maxim.sbmm.core.domain.Account;
import io.manco.maxim.sbmm.core.service.AccountService;

@Controller
public class AccountController {
  @Autowired
  private AccountService service;

  @GetMapping(value = "/admin/list-accounts")
  public String showAccounts(ModelMap model) {
    model.addAttribute("ACCOUNT_LIST", service.getAccountList());
    return "list-accounts";
  }

  @PostMapping(value = "/edit-account")
  public String putEditedAccountToDb(Account account, RedirectAttributes redirectAttributes) {
    service.updateAccount(account);
    redirectAttributes.addAttribute("success", "true");
    return "redirect:/list-accounts";
  }

  @GetMapping(value = "/add-account")
  public String createNewAccountGet(ModelMap model) {
    model.addAttribute("account", new Account());
    return "add-account";
  }

  @PostMapping(value = "/processAddAccount1")
  public String createAccountPost1(
      @RequestParam(value = "username1", required = false) String username1) {
    Account account = new Account();
    account.setAccountName(username1);
    service.createAccount(account);
    return "redirect:/list-accounts";
  }

  @PostMapping(value = "/processAddAccount")
  public String createAccountPost2(
      @RequestParam(value = "username1", required = false) String username1) {
    Account account = new Account();
    account.setAccountName(username1);
    service.createAccount(account);
    return "redirect:/list-accounts";
  }

  @PostMapping(value = "/add-account")
  public String createAccountPost(Account account,
      @RequestParam(value = "username1", required = false) String username1) {
    System.out.println(account.getAdditionalInfo());
    service.createAccount(account);
    return "redirect:/list-accounts";
  }

  @GetMapping(value = "/delete-account")
  public String deleteAccount(@RequestParam int id) {
    service.delete(id);
    return "redirect:/list-accounts";
  }

  @GetMapping(value = "/edit-account")
  public String getAccountToEditAndPopulateForm(ModelMap model, @RequestParam int id) {
    Account account = service.retrieveAccount(id);
    service.updateAccount(account);
    model.addAttribute("account", account);
    return "edit-account";
  }

  @GetMapping(value = "/getImage")
  @ResponseBody
  public byte[] showImage(@RequestParam("accId") int itemId/* , HttpServletResponse response */)
      throws ServletException, IOException {
    byte[] buffer = service.getImage(itemId);
    return buffer;
  }

  @PostMapping("/uploadImage") // new annotation since 4.3 todo make this new annotation everywhere
  public String setNewImage(@RequestParam("image") MultipartFile imageFile,
      RedirectAttributes redirectAttributes, Account account) {
    if (imageFile.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:uploadStatus";
    }
    try {
      byte[] bytes = imageFile.getBytes();
      InputStream imageConvertedToInputStream = new ByteArrayInputStream(bytes);
      service.setImage(account.getAccountId(), imageConvertedToInputStream);
      redirectAttributes.addFlashAttribute("message",
          "You successfully uploaded '" + imageFile.getOriginalFilename() + "'");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "redirect:/uploadStatus";
  }

  @GetMapping("/uploadStatus")
  public String uploadStatus() {
    return "uploadStatus";
  }

  // TODO :: This is pending for Watchlist entity.
  // @RequestMapping(value = "/view-account", method = RequestMethod.GET)
  // public String viewAccount(ModelMap model, @RequestParam int id) {
  // Account account = service.retrieveAccount(id);
  // model.addAttribute("watchLists", account.getAttachedWatchedLists());
  // model.addAttribute("account", account);
  // return "view-account";
  // }
}
