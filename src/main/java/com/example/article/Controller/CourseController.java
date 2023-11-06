package com.example.article.Controller;

import com.example.article.DTO.CourseDTO;
import com.example.article.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/course/list")
    public String courseList(@PageableDefault(page=1) Pageable pageable,
                             @RequestParam(value = "type", defaultValue = "") String type,
                             @RequestParam(value = "keyword", defaultValue = "") String keyword,
                             Model model) throws Exception{
        Page<CourseDTO> courseDTOS = courseService.list(type, pageable, keyword);

        //페이지 정보 만들기
        int blockLimit = 10;  //한 페이지에 출력할 페이지 번호 개수
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit)))-1) * blockLimit+1;
        int endPage = Math.min(startPage+blockLimit-1, courseDTOS.getTotalPages());

        //버튼에 대한 정보 만들기
        int prevPage = courseDTOS.getNumber();
        int curPage = courseDTOS.getNumber()+1;
        int nextPage = courseDTOS.getNumber()+2;

        //값을 저장해서 전달
        //보낼 데이터
        model.addAttribute("courseDTOS", courseDTOS);
        //페이지 정보
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("curPage", curPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);

        return "course/list";
    }

    @GetMapping("/course/register")
    public String courseRegForm(Model model) throws Exception {
        CourseDTO courseDTO = new CourseDTO();

        model.addAttribute("courseDTO", courseDTO);
        return "course/register";
    }
    @PostMapping("/course/register")
    public String courseRegProc(CourseDTO courseDTO, Model model) throws Exception {
        courseService.register(courseDTO.getEvaluationID(), courseDTO);

        return "redirect:/course/list";
    }

    @GetMapping("/course/remove")
    public String courseRemoveProc(int evaluationID) throws Exception {
        courseService.remove(evaluationID);

        return "redirect:/course/list";
    }

    @GetMapping("/course/likecnt")  //추천
    public String likecntProc(int evaluationID, RedirectAttributes redirectAttributes) throws Exception {
        courseService.likecnt(evaluationID);

        redirectAttributes.addAttribute("evaluationID", evaluationID);
        return "redirect:/discussion/read";
    }
}
