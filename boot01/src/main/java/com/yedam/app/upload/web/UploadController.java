package com.yedam.app.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

//간단하게 작성하기 위해 서비스 기능을 컨트롤러 파일에 넣어놓음. 추후 기능구현을 할 때는 파일을 분리해야함.


@Slf4j // 스프링 부트 안에 있는 로그를 불러오는 어노테이션-> //메소드를 이용해서 정보를 출력함.
@Controller
public class UploadController {

	@Value("${file.upload.path}") //@Value : 외부 속성(property) 파일이나 환경 변수에 정의된 값을 주입
	private String uploadPath;// file.upload.path라는 이름의 속성(property) 값을 uploadPath라는 변수에 주입

	@GetMapping("formUpload") // 파일 호출
	public void formUploadPage() {
	}

	@PostMapping("uploadForm")
	public String formUploadFile(@RequestPart MultipartFile[] files) { // 화면에서 넘기는 파일명(formUpload.html의 files)
		for (MultipartFile file : files) {
			log.info(file.getContentType());
			log.info(file.getOriginalFilename());
			log.info(String.valueOf(file.getSize()));

			String fileName = file.getOriginalFilename();
			String saveName = uploadPath + File.separator + fileName; //separator : java가 인식하는 경로(java는 / 경로를 인식하지 못함)

			log.debug("saveName : " + saveName);

			Path savePath = Paths.get(saveName); //Path : java내에서 경로를 처리하는 객체
			//Paths.get(saveName) : java 입장에서는 saveName은 일반 String 파일임. 그 String 파일을 Path가 경로로 인식하게 함.

			try {
				file.transferTo(savePath); //transferTo : 매개변수로 넘겨준 경로를 기반으로 파일을 만들고 그 파일의 데이터를 작성하는 작업(실제 업로드 작업을 진행한다고 생각하면 됨)
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:formUpload";
	}
	// 현재 텍스트가 포함되지 않은 이미지 파일 하나만 업로드 했기 때문에 F12화면에서 payload가 확인이 되지 않음
	
	@GetMapping("upload")
	public void uploadPage() {}
	
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) {
	    
		List<String> imageList = new ArrayList<>();
		
		//image 파일만 업로드
	    for(MultipartFile uploadFile : uploadFiles){
	    	if(uploadFile.getContentType().startsWith("image") == false){
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	  
	    	//<중복파일 관리>
	        String fileName = uploadFile.getOriginalFilename();
	        
	        System.out.println("fileName : " + fileName);
	    
	        //날짜 폴더 생성
	        String folderPath = makeFolder();
	        //UUID(고유 Id)
	        String uuid = UUID.randomUUID().toString(); //UUID로 중복을 제한
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        
	        String saveName = uploadPath + File.separator + uploadFileName;
	        
	        Path savePath = Paths.get(saveName);
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath);
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        // DB에 해당 경로 저장
	        // 1) 사용자가 업로드할 때 사용한 파일명
	        // 2) 실제 서버에 업로드할 때 사용한 경로
	        imageList.add(setImagePath(uploadFileName)); // uploadFileNamed으로 이미지 경로를 설정하고, 그 경로를 imageList에 추가 
	     }
	    
	    return imageList;
	}
	
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); 
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}
}
