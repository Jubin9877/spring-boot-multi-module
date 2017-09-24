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
import io.manco.maxim.sbmm.core.service.WatchListService;

@Controller
public class AccountController {
	@Autowired
	private AccountService service;

	@Autowired
	private WatchListService watchListService;

	@GetMapping(value = "/admin/list-accounts")
	public String showAccounts(ModelMap model) {
		model.addAttribute("ACCOUNT_LIST", service.getAccountList());
		return "list-accounts";
	}

	@PostMapping(value = "/edit-account")
	public String putEditedAccountToDb(Account account, RedirectAttributes redirectAttributes) {
		service.updateAccount(account);
		redirectAttributes.addAttribute("success", "true");
		return "redirect:/admin/list-accounts";
	}

	@GetMapping(value = "/add-account")
	public String createNewAccountGet(ModelMap model) {
		model.addAttribute("account", new Account());
		return "add-account";
	}

	@PostMapping(value = "/processAddAccount1")
	public String createAccountPost1(@RequestParam(value = "username1", required = false) String username1) {
		Account account = new Account();
		account.setAccountName(username1);
		service.createAccount(account);
		return "redirect:/admin/list-accounts";
	}

	@PostMapping(value = "/processAddAccount")
	public String createAccountPost2(
			@RequestParam(value = "username1", required = false) String username1,
	    @RequestParam(value = "password1", required = false) String password1,
	    @RequestParam(value = "email1", required = false) String email1,
	    @RequestParam(value = "additionalInfo1", required = false) String additionalInfo1) {
		Account account = new Account();
		account.setEmail(email1);
		account.setAccountName(username1);
		account.setPassword(password1);
		account.setAdditionalInfo(additionalInfo1);
		service.createAccount(account);
		return "redirect:/admin/list-accounts";
	}

	@PostMapping(value = "/add-account")
	public String createAccountPost(Account account,
	    @RequestParam(value = "username1", required = false) String username1) {
		System.out.println(account.getAdditionalInfo());
		service.createAccount(account);
		return "redirect:/admin/list-accounts";
	}

	@GetMapping(value = "/delete-account")
	public String deleteAccount(@RequestParam int id) {
		service.delete(id);
		return "redirect:/admin/list-accounts";
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
	public byte[] showImage(
	    @RequestParam("accId") int itemId/* , HttpServletResponse response */) throws ServletException, IOException {
		byte[] buffer = service.getImage(itemId);
		return buffer;
	}

	@PostMapping("/uploadImage")
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
		return "redirect:/uploadStatus";
	}

	@GetMapping(value = "/view-account")
	public String viewAccount(ModelMap model, @RequestParam int id) {
		Account account = service.retrieveAccount(id);
		Object accountWatchList = watchListService.getWatchListForAccount(id);
		model.addAttribute("watchLists", accountWatchList);
		model.addAttribute("account", account);
		return "view-account";
	}

	@GetMapping(value = "/home")
	public String viewHome() {
		return "redirect:/admin/list-accounts";
	}
}
