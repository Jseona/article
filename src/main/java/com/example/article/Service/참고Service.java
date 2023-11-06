package com.example.article.Service;
/*
import com.example.article.DTO.참고DTO;
import com.example.article.Entity.참고Entity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class 참고Service {
    private final 참고Repository 참고repository;
    private final ModelMapper modelMapper = new ModelMapper();

    //삭제
    public void 사용자메소드(데이터형 기본키) throws Exception {
        참고repository.deleteById(기본키);
    }

    //수정
    public void 사용자메소드(참고DTO 참고dto) throws Exception {
        int id = 참고DTO.getID();
        Optional<참고Entity> read = 참고repository.findById(id);
        참고Entity 참고entity = read.orElseThrow(); //오류체크

        참고Entity update = modelMapper.map(참고dto, 참고Entity.class);
        update.setId(참고Entity.getId());

        참고Repository.save(upate);
    }
    //삽입
    public void 사용자메소드(참고DTO 참고dto) throws Exception {
        참고Entity 참고entity = modelMapper.map(참고dto, 참고Entity.class);
        참고repository.save(참고entity);
    }
    //개별조회
    public 참고DTO read(int id) throws Exception {
        Optional<참고Entity> read = 참고repository.findBYId(id);
        참고DTO 참고dto = modelMapper.amp(read, 참고DTO.class);
        return 참고dto;
    }
    //전체조회(페이지 처리)
    public Page<참고DTO> list(Pageable page) throws Exception {
        int curPage = page.getPageNumber()-1;
        int pageLimit = 화면에 출력할 갯수;

        Pageable pageable = PageRequest.of(curPage, pageLimit,
                Sort.by(Sort.Direction.DESC,"기본키"));

        Page<참고Entity> 참고Entities = 참고repository.findAll(pageable);

        Page<참고DTO> 참고DTOS = 참고Entities.map(data->참고DTO.builder()
                .변수(data.get필드명())
                .build()
        );

        return 참고DTOS;
    }
    //전체조회
    public List<참고DTO> list() throws Exception {
        List<참고Entity> 참고Entities = 참고repository.findAll();

        List<참고DTO> 참고DTOS = Arrays
                   .asList(modelMapper.map(참고Entities, 참고DTO[].class));

        return 참고DTOS;
    }
    //전체조회(페이지, 검색어)
    public Page<참고DTO> list(Pageable page, String type, String keyword) throws Exception {
        int curPage = page.getPageNumber()-1;
        int pageLimit = 화면에 출력할 갯수;

        Pageable pageable = PageRequest.of(curPage, pageLimit,
                Sort.by(Sort.Direction.DESC,"기본키"));

        Page<참고Entity> 참고Entities;

        if(type.equals("분류값") && keyword != null) {
           참고Entities = 참고repository.사용자메소드(pageable, keyword);
        }

        Page<참고DTO> 참고DTOS = 참고Entities.map(data->참고DTO.builder()
                .변수(data.get필드명())
                .build()
        );

        return 참고DTOS;
    }
}
*/