package com.example.article.Controller;

import com.example.article.DTO.ArticleDTO;
import com.example.article.DTO.CommentDTO;
import com.example.article.Service.ArticleService;
import com.example.article.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("/article/register")
    public String registerForm(Model model) throws Exception {
        ArticleDTO articleDTO = new ArticleDTO();

        model.addAttribute("articleDTO",articleDTO);
        return "article/register";
    }
    @PostMapping("/article/register")
    public String registerProc(@Valid ArticleDTO articleDTO, BindingResult bindingResult, Model model) throws Exception {
        if(bindingResult.hasErrors()){
            return "article/register";
        }
        articleService.create(articleDTO);
        return "redirect:/article/list";
    }

    @GetMapping("/article/modify")
    public String modifyForm(int id, Model model) throws Exception {

        ArticleDTO articleDTO = articleService.read(id,"M");

        model.addAttribute("articleDTO",articleDTO);
        return "article/modify";
    }
    @PostMapping("article/modify")
    public String modifyProc(@Valid ArticleDTO articleDTO, BindingResult bindingResult, Model model) throws Exception {
        if(bindingResult.hasErrors()){
            return "article/modify";
        }
        articleService.modify(articleDTO);
        return "redirect:/article/list";
    }
    @GetMapping("article/remove")
    public String removeProc(int id, Model model) throws Exception {

        articleService.remove(id);

        return "redirect:/article/list";
    }

    //페이지처리 목록조회
    @GetMapping("/article/list")
    public String 조회(@PageableDefault(page=1) Pageable pageable, Model model) throws Exception {
        Page<ArticleDTO> articleDTOS = articleService.list(pageable);

        //페이지정보
        int blockLimit = 10;
        //시작페이지
        int startPage=(((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit)))-1) * blockLimit+1;
        //끝페이지
        int endPage = Math.min(startPage+blockLimit-1, articleDTOS.getTotalPages());

        //페이지버튼 정보(HTML에서 작성도 가능, [첫,이전, 페이지번호, 다음, 끝]
        int prevPage = articleDTOS.getNumber(); //이전페이지
        int currentPage = articleDTOS.getNumber()+1;//현재페이지
        int nextPage = articleDTOS.getNumber()+2;//다음페이지
        int lastPage = articleDTOS.getTotalPages(); //마지막페이지

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("lastPage", lastPage);

        model.addAttribute("articleDTOS",articleDTOS);

        return "article/list";
    }

    @GetMapping("/article/read")
    public String read(int id, Model model) throws Exception {
        //해당게시글 조회
        ArticleDTO articleDTO = articleService.read(id,"R");
        //댓글 조회
        List<CommentDTO> commentDTOS = commentService.list(id);

        model.addAttribute("articleDTO", articleDTO); //게시글
        model.addAttribute("commentDTOS",commentDTOS); //해당 댓글글
        return "article/read";
    }

}
