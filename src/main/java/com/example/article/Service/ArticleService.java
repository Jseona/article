package com.example.article.Service;

import com.example.article.DTO.ArticleDTO;
import com.example.article.Entity.ArticleEntity;
import com.example.article.Repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    //삭제
    public void remove(Integer id) throws Exception {
        articleRepository.deleteById(id);
    }

    //수정
    public void modify(ArticleDTO articleDTO) throws Exception {
        int id = articleDTO.getId();
        Optional<ArticleEntity> read = articleRepository.findById(id);
        ArticleEntity articleEntity = read.orElseThrow(); //오류체크

        ArticleEntity update = modelMapper.map(articleDTO, ArticleEntity.class);
        update.setId(articleEntity.getId());

        articleRepository.save(update);
    }

    //삽입
    public void create(ArticleDTO articleDTO) throws Exception {
        ArticleEntity articleEntity = modelMapper.map(articleDTO, ArticleEntity.class);
        articleRepository.save(articleEntity);
    }

    //개별조회
    public ArticleDTO read(int id, String pan) throws Exception {
        //조회수 증가
        if(pan.equals("R")) { //개별읽기에만 카운터
            articleRepository.viewcnt(id);
           // Optional<ArticleEntity> update = articleRepository.findById(id);
           // ArticleEntity articleEntity = update.orElseThrow(); //오류체크
           // articleEntity.setViewcnt(articleEntity.getViewcnt() + 1);

           // articleRepository.save(articleEntity);
        }

        //articleRepository.viewcnt(id);

        //개별조회
        Optional<ArticleEntity> read = articleRepository.findById(id);
        ArticleDTO articleDTO = modelMapper.map(read, ArticleDTO.class);
        return articleDTO;
    }

    //전체조회(페이지 처리)
    public Page<ArticleDTO> list(Pageable page) throws Exception {
        int curPage = page.getPageNumber()-1;
        int pageLimit = 10;

        Pageable pageable = PageRequest.of(curPage, pageLimit,
                Sort.by(Sort.Direction.DESC,"id"));

        Page<ArticleEntity> articleEntities = articleRepository.findAll(pageable);

        Page<ArticleDTO> articleDTOS = articleEntities.map(data->ArticleDTO.builder()
                .id(data.getId()).title(data.getTitle())
                .content(data.getContent()).viewcnt(data.getViewcnt())
                .modDate(data.getModDate()).build()
        );

        return articleDTOS;
    }
}
