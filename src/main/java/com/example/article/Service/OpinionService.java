package com.example.article.Service;

import com.example.article.DTO.OpinionDTO;
import com.example.article.Entity.DiscussionEntity;
import com.example.article.Entity.OpinionEntity;
import com.example.article.Repository.DiscussionRepository;
import com.example.article.Repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OpinionService {
    private final OpinionRepository opinionRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final DiscussionRepository discussionRepository;  //부모테이블

    //삭제
    //번호에 해당하는 자료를 삭제
    public void remove(int id) throws Exception {
        opinionRepository.deleteById(id);
    }

    //등록
    //DTO를 받아서 Entity로 변환한 후 저장처리
    //부모테이블의 레코드 아이디 가져오기
    public void register(int id,OpinionDTO opinionDTO) throws Exception {
        //부모테이블의 해당 레코드를 조회
        Optional<DiscussionEntity> discussionEntity = discussionRepository.findById(id);
        DiscussionEntity data = discussionEntity.orElseThrow();  //Optional이 아닌 Entity로 변환해서 저장

        //변환
        //부모테이블 정보가 없음. => 저장이 안 됨.
        OpinionEntity opinionEntity = modelMapper.map(opinionDTO, OpinionEntity.class);

        //저장
        opinionEntity.setDiscussionEntity(data);  //부모레코드 정보를 추가
        opinionRepository.save(opinionEntity);  //저장
    }

    //토론글에 해당하는 의견목록
    //토론번호에 해당하는 의견 자료들을 읽어서 전달
    public List<OpinionDTO> list(int id) throws Exception {
        //토론번호와 일치하는 자료를 조회
        //@JoinColumn(name = "discussionid")
        List<OpinionEntity> opinionEntities = opinionRepository.findByDiscussionid(id);

        //변환(Entity->DTO)
        List<OpinionDTO> opinionDTOS = Arrays.asList(
                modelMapper.map(opinionEntities, OpinionDTO[].class)
        );

        return opinionDTOS;
    }

    //좋아요
    //해당 의견번호에 좋아요 증가
    public void goodcnt(int id) throws Exception {
        opinionRepository.goodcnt(id);
    }

    //싫어요
    //해당 의견번호에 싫어요 증가
    public void badcnt(int id) throws Exception {
        opinionRepository.badcnt(id);
    }
}
