package com.example.article.Service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

//파일업로드와 삭제를 처리하는 클래스 (Cotroller에도 만들어서 사용 가능)
@Service
public class FileService {
    //파일 업로드
    //저장위치, 파일명, 파일데이터 필요
    //text는 ASCII 코드로 전달, 그 외 파일은 byte방식(2진수)으로 전달
    //byte를 여러개 받아와야 하기 때문에 배열로 지정,
    //파일의 크기를 알 수 없기 때문에 배열 크기는 지정하지 않음.
    //sample.jpg -> rew34fs324s(난수로 파일명생성) -> .jpg 분리
    //rew34fs324s.jpg(재조립) -> 하드에 저장
    public String uploadFile(String uploadPath, String originalFileName,
                             byte[] fileData) throws Exception {
        //저장 시 동일한 파일이름에 의해서 덮어쓰기 되는 것을 방지
        UUID uuid = UUID.randomUUID();  //임의의 문자열 생성 (rew34fs324s)

        //substring : 지정한 위치까지 문자 추출, lastIndexOf : 뒤에서부터 지정한 문자의 위치
        //sample.jpg=>.jpg
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        //실질적으로 저장할 파일명 만들기
        //rew34fs324s.jpg
        String saveFileName = uuid.toString() + extension;

        //저장할 위치와 파일을 합쳐서 작업파일 만들기
        // c:/image/item/rew34fs324s.jpg
        String fileUploadFullUrl = uploadPath + saveFileName;

        //파일저장
        //읽어오기는 FileInputStream
        //원본 이름으로 저장
        //FileOutputStream fos = new FileOutputStream(uploadPath + originalFileName);

        //지정된 위치에 지정된 이름으로 저장 처리(uuid 사용)
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        //읽어온 바이트를 하드에 쓰기(저장)
        fos.write(fileData);
        //파일은 쓰거나 열거나 하면 무조건 닫아줘야 파일이 안깨짐.
        fos.close();

        //저장한 파일명을 결과값으로 전달
        return saveFileName;
    }

    //파일 삭제
    //이미지를 변경할 경우 기존 ("이미지를 삭제하고") 새로운 파일을 저장하기 위해 필요
     public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath);
        if (deleteFile.exists()) {  //지정한 위치에 파일이 있으면
            deleteFile.delete();  //파일삭제
        }
     }
}

//하드디스크에 저장할 폴더, 이미지 등을 준비
//예전에는 이름 + 시간(날짜,시,분,초) 로 했었음.