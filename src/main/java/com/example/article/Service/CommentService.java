package com.example.article.Service;

import com.example.article.DTO.ArticleDTO;
import com.example.article.DTO.CommentDTO;
import com.example.article.Entity.ArticleEntity;
import com.example.article.Entity.CommentEntity;
import com.example.article.Repository.ArticleRepository;
import com.example.article.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    //댓글 삭제
    public void remove(Integer id) throws Exception {
        commentRepository.deleteById(id);
    }

    //댓글 수정(게시글 번호, 댓글내용)
    public void modify(CommentDTO commentDTO) throws Exception {
        Integer id = commentDTO.getId();

        Optional<CommentEntity> read = commentRepository.findById(id); //댓글 조회(확인)
        CommentEntity commentEntity = read.orElseThrow(); //오류체크

        //댓글에 해당하는 부모 게시글 정보(확인)
        Optional<ArticleEntity> data  = articleRepository.findById(commentDTO.getArticleid()); //오류체크
        ArticleEntity articleEntity = data.orElseThrow(); //오류체크

        CommentEntity update = modelMapper.map(commentDTO, CommentEntity.class); //수정할 내용
        update.setId(commentEntity.getId()); //확인된 댓글번호로 재저장
        update.setArticleEntity(articleEntity); //연관관계확인을 위해서 부모 게시글 정보를 저장

        commentRepository.save(update);
    }

    //삽입
    public void create(int id, CommentDTO commentDTO) throws Exception {
        //연관관계 부모 게시글 정보
        Optional<ArticleEntity> data  = articleRepository.findById(id); //해당 게시글을 조회
        ArticleEntity articleEntity = data.orElseThrow(); //문제없으면 저장
        //DTO->Entity변환(댓글을 변환)
        CommentEntity commentEntity = modelMapper.map(commentDTO, CommentEntity.class);
        //ManyToOne에 게시글Entity에 내용을 저장해서 유효성처리
        commentEntity.setArticleEntity(articleEntity);

        commentRepository.save(commentEntity);
    }

    //댓글은 개별조회가 없음, 페이지 정보 없음
    //전체조회
    public List<CommentDTO> list(Integer articleid) throws Exception {

        List<CommentEntity> commentEntities = commentRepository.findByArticleId(articleid);

        List<CommentDTO> commentDTOS = Arrays.asList(modelMapper
                .map(commentEntities, CommentDTO[].class));

        return commentDTOS;
    }


}
//연관관계
//ManyToOne 적용
//삽입, 수정 작업시에 게시글Entity(부모테이블) 값이 필요