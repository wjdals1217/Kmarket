package kr.co.Kmarket.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.Kmarket.dao.cs.CsArticleDAO;
import kr.co.Kmarket.dto.FileDTO;
import kr.co.Kmarket.dto.cs.CommentDTO;
import kr.co.Kmarket.dto.cs.CsArticleDTO;
import kr.co.Kmarket.dto.cs.CsCateAsideDTO;
import kr.co.Kmarket.dto.cs.CsCateDetailDTO;


public enum CsArticleService {

	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	private CsArticleDAO dao = new CsArticleDAO();
	
//===================================================================================	
	
	
	
	
	
//===================  qna   ============================================
	
	
	 public List<CsArticleDTO> SelectQnaArticlesAll(String group, int start, String cateDetail, String type) {
		 
		 logger.debug("qnalistController group값: "+ group);
		 logger.debug("qnalistController start값: "+ start);
		 logger.debug("qnalistController start값: "+ cateDetail);
		 
		 
		 if(cateDetail.equals("all")){
			 
			 return dao.SelectQnaArticlesAllcate(group, start, cateDetail, type);
			 
		 }else {
			 
			 return dao.SelectQnaArticlesAll(group, start, cateDetail, type);
		 }
		 
	 }
	
	 public List<CsCateDetailDTO> selectCsWriteCate(String cateDetail){
		 
		 
		 return dao.selectCsWriteCate(cateDetail);
		 
	 }
	public List<CsCateAsideDTO> selectCsWriteCateFAQ(){
		
		
		return dao.selectCsWriteCateFAQ();
	}

	 
	public List<CsCateDetailDTO> selectCsCateDetailFAQ(String cateDetail ){
		
		
		return dao.selectCsCateDetailFAQ(cateDetail);
	}
	 
	
	
	public List<CsCateAsideDTO> selectCsWriteCateNotice(){
		
		
		return dao.selectCsWriteCateNotice();
	}
	
	public List<CsCateDetailDTO> selectCsWriteCateDeNotice(){
		
		
		return dao.selectCsWriteCateDeNotice();
	}
	
	 
	 
	 
	public int insertArticle(CsArticleDTO dto, FileDTO fdto) {

		logger.debug("ArticleService dto: " + dto);
		
		return dao.insertArticle(dto, fdto);
	}
	
	public void insertArticleFAQ(CsArticleDTO dto) {
		
		logger.debug("ArticleService dto: " + dto);
		
		dao.insertArticleFAQ(dto);
		
	}
	
	public CsArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public List<CsArticleDTO> selectArticles(String cate, int start) {
		return dao.selectArticles(cate, start);
	}
	
	public List<CsArticleDTO> selectArticlesIndex(String group) {
		return dao.selectArticlesIndex(group);
	}
	
	public List<CsArticleDTO> selectArticlesFAQ(String group, String cateDetail) {
		return dao.selectArticlesFAQ(group, cateDetail);
	}
	
	public void updateArticle(CsArticleDTO dto) {
		dao.updateArticle(dto);
	}
	
	public void deleteArticle(String aNo) {
		
		logger.debug("delete service no값~@~@~@~~~"+aNo);
		
		dao.deleteArticle(aNo);
	}

	public void deletefile(String aNo) {
		
		logger.debug("delete service no값~@~@~@~~~"+aNo);
		
		dao.deletefile(aNo);
	}

	// 파일삭제
	public List<String> deleteFile(String ano) {
		return dao.deleteFile(ano);
	}
	
	
	
	
	// 추가 
	public int selectCountTotal(String group, String type, String cateDetail) {
		return dao.selectCountTotal(group, type, cateDetail);
	}

	public int selectCountTotalFAQ(String group, String type) {
		return dao.selectCountTotalFAQ(group, type);
	}
	//all 페이징용
	public int selectCountTotalCateAll(String group, String type, String cateDetail) {
		return dao.selectCountTotalCateAll(group, type, cateDetail);
	}
	
	public CommentDTO selectComments(String ano) {
		return dao.selectComments(ano);
	}
	
	public CommentDTO insertComment(CommentDTO dto) {
		return dao.insertComment(dto);
	}
	
	
	public int updateComment(String no, String content) {
		return dao.updateComment(no, content);
	}
	
	public int deleteComment(String no) {
		return dao.deleteComment(no);
	}
	
	
	
	
	
	// 업로드 경로 구하기
	public String getPath(HttpServletRequest req, String dir) {
		// 파일 업로드 경로 구하기 
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath(dir);
		return path;
	}
	
	// 파일명 수정
	public String renameToFile(HttpServletRequest req, String path, String oName) {
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i);
		
		String uuid  = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		File f1 = new File(path+"/"+oName);
		File f2 = new File(path+"/"+sName);
		
		f1.renameTo(f2);
		
		return sName;
	}
	
	
	// 파일 업로드
	public MultipartRequest uploadFile(HttpServletRequest req, String path) {
		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;
		
		// 파일 업로드 및 Multipart 객체 생성
		MultipartRequest mr = null;
		
		try {
			mr = new MultipartRequest(req, 
									  path, 
									  maxSize, 
									  "UTF-8", 
									  new DefaultFileRenamePolicy());
		} catch (IOException e) {
			logger.error("uploadFile : " + e.getMessage());
		}
		
		return mr;
	}
	
	// 파일 다운로드
	public void downloadFile(HttpServletRequest req, HttpServletResponse resp, FileDTO dto) throws IOException {
		// response 파일 다운로드 헤더 수정
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(dto.getNewname(), "utf-8"));
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		// response 파일 스트림 작업
		String path = getPath(req, "/upload");
		
		File file = new File(path+"/"+dto.getNewname());
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
				
		while(true){
			
			int data = bis.read();
			if(data == -1){
				break;
			}
			
			bos.write(data);
		}
		
		bos.close();
		bis.close();
	}

	
	
}
