package io.manco.maxim.sbmm.core.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {
  private static String UPLOADED_FOLDER = "C://temp_upload_spring_test//";

  @GetMapping("/upload")
  public String index() {
    return "upload";
  }

  @PostMapping("/signed/upload")
  @ResponseStatus(value = HttpStatus.OK)
  public String singleFileUpload(@RequestBody @RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) {
    if (file.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:uploadStatus";
    }
    try {
      byte[] bytes = file.getBytes();
      Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
      Files.write(path, bytes);
      redirectAttributes.addFlashAttribute("message",
          "You successfully uploaded '" + file.getOriginalFilename() + "'");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "redirect:/signed/uploadStatus";
  }

  @PostMapping("/uploadMulti")
  public String multiFileUpload(@RequestParam("files") MultipartFile[] files,
      RedirectAttributes redirectAttributes) {
    StringJoiner sj = new StringJoiner(" , ");
    for (MultipartFile file : files) {
      if (file.isEmpty()) {
        continue; // next pls
      }
      try {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
        sj.add(file.getOriginalFilename());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    String uploadedFileName = sj.toString();
    if (StringUtils.isEmpty(uploadedFileName)) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
    } else {
      redirectAttributes.addFlashAttribute("message",
          "You successfully uploaded '" + uploadedFileName + "'");
    }
    return "redirect:signed/uploadStatus";
  }

  @GetMapping("/signed/uploadStatus")
  public String uploadStatus() {
    return "uploadStatus";
  }

  @GetMapping("/uploadMultiPage")
  public String uploadMultiPage() {
    return "uploadMulti";
  }
}
