package com.example.article.Controller;

import com.example.article.DTO.DiscussionDTO;
import com.example.article.DTO.OpinionDTO;
import com.example.article.Service.DiscussionService;
import com.example.article.Service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DiscussionController {
    private final DiscussionService discussionService;
    private final OpinionService opinionService;

    @GetMapping("/discussion/list")  //목록폼
    public String listForm(@PageableDefault(page=1) Pageable pageable, Model model) throws Exception {
        Page<DiscussionDTO> discussionDTOS = discussionService.list(pageable);

        //페이지 정보 만들기
        int blockLimit = 10; //한 페이지에 출력할 페이지 번호 개수
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit)))-1) * blockLimit+1;
        int endPage = Math.min(startPage+blockLimit-1, discussionDTOS.getTotalPages());

        //버튼에 대한 정보 만들기
        int prevPage = discussionDTOS.getNumber();
        int curPage = discussionDTOS.getNumber()+1;
        int nextPage = discussionDTOS.getNumber()+2;
        int lastPage = discussionDTOS.getTotalPages();

        //값을 저장해서 전달
        //보낼 데이터
        model.addAttribute("discussionDTOS",discussionDTOS);
        //페이지 정보
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("curPage", curPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("lastPage", lastPage);

        return "discussion/list";
    }

    @GetMapping("/discussion/read")  //상세보기폼
    public String readForm(int id, Model model) throws Exception {
        //조회수 증가
        discussionService.viewcnt(id);
        //토론글을 읽어오기
        DiscussionDTO discussionDTO = discussionService.read(id);
        //해당 토론글의 의견을 읽어오기
        List<OpinionDTO> opinionDTOS = opinionService.list(id);

        model.addAttribute("discussionDTO", discussionDTO);
        model.addAttribute("opinionDTOS",opinionDTOS);
        return "discussion/read";
    }

    @GetMapping("/discussion/register")  //등록폼
    public String registerForm(Model model) throws Exception {
        //object 사용시 빈 DTO 전달
        DiscussionDTO discussionDTO = new DiscussionDTO();

        model.addAttribute("discussionDTO", discussionDTO);
        return "discussion/register";
    }

    @PostMapping("/discussion/register")  //등록처리
    public String registerProc(@Valid DiscussionDTO discussionDTO,
                               BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {  //유효성검사 시 오류가 발생하면 입력폼으로 돌아가기
            return "discussion/register";
        }
        discussionService.register(discussionDTO);  //저장

        return "redirect:/discussion/list";
    }

    @GetMapping("/discussion/modify")  //수정폼
    public String modifyForm(int id, Model model) throws Exception {
        DiscussionDTO discussionDTO = discussionService.read(id);

        model.addAttribute("discussionDTO", discussionDTO);
        return "discussion/modify";
    }

    @PostMapping("/discussion/modify")  //수정처리
    public String modifyProc (@Valid DiscussionDTO discussionDTO,
                              BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            return "discussion/modify";
        }
        discussionService.modify(discussionDTO);

        return "redirect:/discussion/list";
    }

    @GetMapping("/discussion/remove")  //삭제처리
    public String removeProc (int id, Model model) throws Exception {
        discussionService.remove(id);

        return "redirect:/discussion/list";
    }
}
