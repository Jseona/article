package com.example.article.Controller;
/*
import com.example.article.DTO.참고DTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RestController  //RestAPI로 처리
public class 참고Controller {
    private final 참고Service 참고service;
    private final ModelMapper modelMapper = new ModelMapper(); //상황에 따라

    @GetMapping({"/등록매핑명", "/맵핑명"})
    public String 등록폼(Model model) throws Exception {
        참고DTO 참고dto = new 참고DTO(); //검증을 사용할 때
        model.addAttribute("참고dto", 참고dto);
        return "이동할 페이지";
    }
    @PostMapping("등록맵핑")
    public String 등록처리(@Valid 참고DTO 참고dto, BindingResult bindingResult, Model model) throws Exception {
        //검증처리시 DTO와 변수명은 동일하게 맞추어 준다.
        if(bindingReuslt.hasError()){
            return "입력페이지";
        }
        참고service.등록메소드(참고dto);
        return "redirect:/이동할 맵핑";
    }
    @GetMapping({"/수정매핑명", "/맵핑명"})
    public String 수정폼(int id, Model model) throws Exception {

        참고DTO 참고dto = 참고service.개별조회메소드(id);

        model.addAttribute("참고dto", 참고dto);
        return "이동할 페이지";
    }
    @PatchMapping("수정매핑/id번호")
    public String 수정처리(@Valid 참고DTO 참고dto, BindingResult bindingResult, Model model) throws Exception {
        //검증처리시 DTO와 변수명은 동일하게 맞추어 준다.
        if(bindingReuslt.hasError()){
            return "입력페이지";
        }
        참고service.수정메소드(참고dto);
        return "redirect:/이동할 맵핑";
    }
    @DeleteMapping("삭제매핑/id번호")
    public String 삭제폼(int id, Model model) throws Exception {

        참고service.삭제메소드(id);

        return "redirect:/이동할 매핑";
    }
    //기본 모든 목록조회
    @GetMapping("/조회맵핑명")
    public String 조회(Model model) throws Exception {
        List<참고DTO> 참고DTOS = 참고service.조회메소드();
        model.addAttribute("참고DTOS", 참고DTOS);

        return "이동할페이지";
    }
    //페이지처리 목록조회
    @GetMapping("/조회맵핑명")
    public String 조회(@PageableDefault(page=1) Pageable pageable, Model model) throws Exception {
        List<참고DTO> 참고DTOS = 참고service.조회메소드(pageable);

        //페이지정보
        int blockLimit = 화면에 출력할 페이지 번호 수(10)
        //시작페이지
        int startPage=(((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit)))-1) * blockLimit+1;
        //끝페이지
        int endPage = Math.min(startPage+blockLimit-1, 참고DTOS.getTotalPages());

        //페이지버튼 정보(HTML에서 작성도 가능, [첫,이전, 페이지번호, 다음, 끝]
        int prevPage = 참고DTOS.getNumber(); //이전페이지
        int currentPage = 참고DTOS.getNumber()+1;//현재페이지
        int nextPage = 참고DTOS.getNumber()+2;//다음페이지
        int lastPage = 참고DTOS.getTotalPages(); //마지막페이지

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("lastPage", lastPage);

        model.addAttribute("참고DTOS", 참고DTOS);

        return "이동할페이지";
    }
    //페이지처리, 검색 목록조회
    @GetMapping("/조회맵핑명")
    public String 조회(@PageableDefault(page=1) Pageable pageable,
                     @RequestParam(value="type", defalutValue="") String type,
                     @RequestParam(value="keyword", defalutValue="")String keyword,
                     Model model) throws Exception {
        List<참고DTO> 참고DTOS = 참고service.조회메소드(pageable, type, keyword);

        //페이지정보
        int blockLimit = 화면에 출력할 페이지 번호 수(10)
        //시작페이지
        int startPage=(((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit)))-1) * blockLimit+1;
        //끝페이지
        int endPage = Math.min(startPage+blockLimit-1, 참고DTOS.getTotalPages());

        //페이지버튼 정보(HTML에서 작성도 가능, [첫,이전, 페이지번호, 다음, 끝]
        int prevPage = 참고DTOS.getNumber(); //이전페이지
        int currentPage = 참고DTOS.getNumber()+1;//현재페이지
        int nextPage = 참고DTOS.getNumber()+2;//다음페이지
        int lastPage = 참고DTOS.getTotalPages(); //마지막페이지

        model.addAttribute("type", type); //이전 검색정보
        model.addAttribute("keyword", keyword);
        model.addAttribute("startPage", startPage); //페이지정보
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("lastPage", lastPage);

        model.addAttribute("참고DTOS", 참고DTOS);

        return "이동할페이지";
    }
}
*/