package com.example.article.Service;

import com.example.article.DTO.ArticleDTO;
import com.example.article.DTO.CourseDTO;
import com.example.article.Entity.ArticleEntity;
import com.example.article.Entity.CourseEntity;
import com.example.article.Repository.CourseRepository;
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
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    //삭제
    public void remove(int evaluationID) throws Exception {
        courseRepository.deleteById(evaluationID);
    }

    //삽입
    public void register(int evaluationID, CourseDTO courseDTO) throws Exception {
        CourseEntity courseEntity = modelMapper.map(courseDTO, CourseEntity.class);

        courseRepository.save(courseEntity);
    }

    //개별조회
    public CourseDTO read(int evaluationID) throws Exception {
        Optional<CourseEntity> read = courseRepository.findById(evaluationID);
        CourseDTO courseDTO = modelMapper.map(read, CourseDTO.class);

        return courseDTO;
    }

    //전체조회
    public Page<CourseDTO> list(String type, Pageable page, String keyword) throws Exception {
        int curPage = page.getPageNumber()-1;
        int pageLimit = 3;

        Pageable pageable = PageRequest.of(curPage, pageLimit,
                Sort.by(Sort.Direction.DESC,"evaluationID"));

        Page<CourseEntity> courseEntities;

        if (type.equals("t") && keyword != null) {
            courseEntities = courseRepository.searchTitle(keyword, pageable);
        } else if (type.equals("w") && keyword != null) {
            courseEntities = courseRepository.searchWriter(keyword, pageable);
        } else if (type.equals("tw") && keyword != null) {
            courseEntities = courseRepository.searchTitleContent(keyword, pageable);
        } else {
            courseEntities = courseRepository.findAll(pageable);
        }

        Page<CourseDTO> courseDTOS = courseEntities.map(data->CourseDTO.builder()
                .evaluationID(data.getEvaluationID())
                .userID(data.getUserID())
                .lectureName(data.getLectureName())
                .professorName(data.getProfessorName())
                .lectureYear(data.getLecutreYear())
                .semesterDivide(data.getSemesterdivide())
                .lectureDivide(data.getLectureDivide())
                .evaluationTitle(data.getEvaluationTitle())
                .evaluationContent(data.getEvaluationContent())
                .totalScore(data.getTotalScore())
                .creditScore(data.getCreditScore())
                .comfortableScore(data.getComfortableScore())
                .lectureScore(data.getLectureScore())
                .likeCount(data.getLikeCount())
                .build()
        );

        return courseDTOS;
    }

    //추천 갯수 증가
    public void likecnt(int evaluationID) throws Exception {
        courseRepository.likecnt(evaluationID);
    }
}
