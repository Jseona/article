package com.example.article.Controller;

import com.example.article.Service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;

    @GetMapping("/upload/upload")
    public String uploadForm(Model model) throws Exception {
        return "upload/upload";
    }

    @PostMapping("/upload/upload")
    public String uploadProc(String title, MultipartFile file, Model model) throws Exception {
        System.out.println("받은 파일명 : " + file.getOriginalFilename());
        String newFileName = uploadService.saveImg(file);
        //newFileName에는 새로운 파일명만 존재

        System.out.println("저장 시 사용한 파일명 : " + newFileName);
        // /images/item/aa.jpg => c:/image/item/aa.jpg
        //                                전달 시   폴더명(자원의 대체폴더명) + 파일명으로 전달
        model.addAttribute("img", "/images/item/" + newFileName);
        model.addAttribute("imgfile", newFileName);
        System.out.println("/images/item/" + newFileName);
        return "/upload/list";
    }
}
