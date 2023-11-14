package kr.co.Kmarket.dao.cs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.db.DBHelper;
import kr.co.Kmarket.dto.FileDTO;
import kr.co.Kmarket.dto.cs.CommentDTO;
import kr.co.Kmarket.dto.cs.CsArticleDTO;
import kr.co.Kmarket.dto.cs.CsCateAsideDTO;
import kr.co.Kmarket.dto.cs.CsCateDetailDTO;
import kr.co.Kmarket.service.FileService;

public class CsArticleDAO extends DBHelper{

	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final CsArticleDTO dto =  new CsArticleDTO();
	private FileService fService = new FileService();
	
	String SQL = "";
	String SQL2 = "";
	String SQL3 = "";
	
	public int insertArticle(CsArticleDTO dto,FileDTO fdto) {
		
		SQL = "INSERT INTO `km_cs_article` SET "
				+ "`group`=?, "
				+ "`cateDetail`=?, "
				+ "`title`=?, "
				+ "`content`=?, "
				+ "`file`=?, "
				+ "`writer`=?, "
				+ "`regip`=?, "
				+ "`type`=?, "
				+ "`uLevel`=?, "
				+ "`rdate`=NOW()";
		
		
		SQL2= 	"SELECT aNo, rdate "
				+ " FROM `km_cs_article` "
				+ " ORDER BY aNo DESC "
				+ " LIMIT 1 ";

		//select LAST_INSERT_ID(aNo) FROM `km_cs_article`;
		
		SQL3= "INSERT INTO `km_file` SET "
				+ " `aNo` = ?, "
				+ " `oriName` = ?, "
				+ " `newName` = ?, "
				+ " `rdate` = ? ";
		
		
		int maxAno = 0;
		String maxRdate = "";
		
		
		try {
			
			conn = getConnection();
			conn.setAutoCommit(false);  //transaction시작
			
			// article insert
			psmt = conn.prepareStatement(SQL);  
			psmt.setString(1, dto.getGroup());
			psmt.setString(2, dto.getCateDetail());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setInt(5, dto.getFile());
			psmt.setString(6, dto.getWriter());
			psmt.setString(7, dto.getRegip());
			psmt.setInt(8, dto.getType());
			psmt.setInt(9, dto.getuLevel());
			psmt.executeUpdate();
	
			psmt.close();
			
			
			// file max(aNo) select
			psmt = conn.prepareStatement(SQL2);
			rs = psmt.executeQuery();
			if(rs.next()) {
				maxAno = rs.getInt(1);
				maxRdate = rs.getString(2);
			}
			
			psmt.close();
			
			
			// file insert
			psmt = conn.prepareStatement(SQL3);
			psmt.setInt(1, maxAno);
			psmt.setString(2, fdto.getOriname());
			psmt.setString(3, fdto.getNewname());
			psmt.setString(4, maxRdate);
			psmt.executeUpdate();
			
			psmt.close();
			
			conn.commit();
		
			close();
			
		}catch(Exception e){
			logger.debug(e.getMessage());
		}
		
		
		return 0;
	}

	
	
	public void insertArticleFAQ(CsArticleDTO dto) {
		
		SQL = "INSERT INTO `km_cs_article` SET "
				+ "`group`=?, "
				+ "`cateDetail`=?, "
				+ "`title`=?, "
				+ "`content`=?, "
				+ "`file`=0, "
				+ "`writer`=?, "
				+ "`regip`=?, "
				+ "`type`=?, "
				+ "`uLevel`=?, "
				+ "`rdate`=NOW()";
		
		

		
		logger.debug("insertArticleFAQ toString: 확인중: "+dto.toString());
		
		
		try {
			
		
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);  
			psmt.setString(1, dto.getGroup());
			psmt.setString(2, dto.getCateDetail());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getWriter());
			psmt.setString(6, dto.getRegip());
			psmt.setInt(7, dto.getType());
			psmt.setInt(8, dto.getuLevel());
			psmt.executeUpdate();
			
			close();
			
			

			
		}catch(Exception e){
			logger.debug(e.getMessage());
		}
		
		
	}
	
	
	/*
	public void fileInsert(FileDTO dto) {
		
		SQL="INSERT INTO `km_file` SET "
		  + " `aNo` =?, "
		  + " `oriName`=?,"
		  + " `newName`=? "
		  + " `rdate`=? ";
		
		conn = getConnection();
		
		try {
			
			psmt = conn.prepareStatement(SQL);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOriname());
			psmt.setString(3, dto.getNewname());
			psmt.setString(4, dto.getRdate());
			
			
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
	}
	*/
	
	
	
//============qna================================================
	
    public List<CsArticleDTO> SelectQnaArticlesAll(String group, int start, String cateDetail, String type) {
    	 
    	logger.debug("SelectQnaArticlesAll 그룹 스타트 체크: "+group,start);
    	logger.debug("SelectQnaArticlesAll 스타트 체크: "+ start);
    	SQL = "SELECT DISTINCT * from `km_cs_article` AS a "
    			+ "JOIN `km_cs_cate_detail` AS c "
    			+ "ON a.`type` = c.`type` "
    			+ "WHERE a.`group` = ? AND  a.`type` >= 20 AND a.`cateDetail` = ? "
    			+ "ORDER BY `aNo` DESC "
    			+ "LIMIT ?, 10 ";
    
    	
    	SQL2 = "SELECT DISTINCT * from `km_cs_article` AS a "
    			+ "JOIN `km_cs_cate_detail` AS c "
    			+ "ON a.`type` = c.`type` "
    			+ "WHERE a.`group` = ? AND  a.`type` <=4 AND a.`cateDetail` = ? "
    			+ "ORDER BY `aNo` DESC "
    			+ "LIMIT ?, 10 ";
    	
 
    	
    	
    	List<CsArticleDTO> articles = new ArrayList<>();
    	
    	try {
    		
    		conn = getConnection();
    		
    		if(Integer.parseInt(type) <= 4) {
    			psmt = conn.prepareStatement(SQL2);
    		}else {
    			psmt = conn.prepareStatement(SQL);
    		}		
    		
    		
			psmt.setString(1, group);
			psmt.setString(2, cateDetail);
			psmt.setInt(3, start);
			rs = psmt.executeQuery();
    		
			while(rs.next()) {
				CsArticleDTO dto = new CsArticleDTO();
				dto.setaNo(rs.getInt("aNo"));
				dto.setGroup(rs.getString("group"));
				dto.setCateDetail(rs.getString("cateDetail"));
				dto.setTitle(rs.getString("title"));
				dto.setMaskingWriter(rs.getString("writer"));
				dto.setRdateYYMMDD(rs.getString("rdate"));
				//dto.setAeName(rs.getString("aeName"));
				dto.setuLevel(rs.getInt("uLevel"));
				dto.setType(rs.getInt("type"));
				dto.setdName(rs.getString("dName"));
				
				articles.add(dto);
			}
			close();
    		
		} catch (Exception e) {
			logger.debug("SelectQna: " + e.getMessage());
		}
    	
    	
    	logger.info("qnaSelects 아티클 찍었따 나와라 ㅡㅡ: "+ articles);
    	
    	return articles;
    }
	
	
	//서비스에서 카테고리 all로 들어올때 분기하기 위해 만든 메서드
    public List<CsArticleDTO> SelectQnaArticlesAllcate(String group, int start, String cateDetail, String type) {
    	 
    	logger.debug("SelectQnaArticlesAll 그룹 스타트 체크: "+group,start);
    	logger.debug("SelectQnaArticlesAll 스타트 체크: "+ start);
    	SQL = "SELECT DISTINCT * from `km_cs_article` AS a " 
    			+" JOIN `km_cs_aside` AS b "
    			+" ON a.`cateDetail` = b.`aeName` "
    			+" JOIN `km_cs_cate_detail` AS c "
    			+" ON a.`type` = c.`type` " 
    			+" WHERE a.`group` = ? AND  a.`type` >= 20 AND  b.`aside_Num` > 1 " 
    			+" ORDER BY `aNo` DESC " 
    			+" LIMIT ?, 10 ";
    	
    	//NoticeController 부분 type <=4이면 이 쿼리문 실행
    	SQL2 ="SELECT DISTINCT * from `km_cs_article` AS a " 
	    			+" JOIN `km_cs_aside` AS b "
	    			+" ON a.`cateDetail` = b.`aeName` "
	    			+" JOIN `km_cs_cate_detail` AS c "
	    			+" ON a.`type` = c.`type` " 
	    			+" WHERE a.`group` = ? AND  a.`type` <=4 AND  b.`aside_Num` > 1 " 
	    			+" ORDER BY `aNo` DESC " 
	    			+" LIMIT ?, 10 ";
    	
    	List<CsArticleDTO> articles = new ArrayList<>();
    	
    	try {
    		conn = getConnection();
    		if(Integer.parseInt(type) <= 4) {
    			psmt = conn.prepareStatement(SQL2);
    		}else {
    			psmt = conn.prepareStatement(SQL);
    		}			
			psmt.setString(1, group);
			psmt.setInt(2, start);
			rs = psmt.executeQuery();
    		
			while(rs.next()) {
				CsArticleDTO dto = new CsArticleDTO();
				dto.setaNo(rs.getInt("aNo"));
				dto.setGroup(rs.getString("group"));
				dto.setCateDetail(rs.getString("cateDetail"));
				dto.setTitle(rs.getString("title"));
				dto.setMaskingWriter(rs.getString("writer"));
				dto.setRdateYYMMDD(rs.getString("rdate"));
				//dto.setAeName(rs.getString("aeName"));
				dto.setuLevel(rs.getInt("uLevel"));
				dto.setType(rs.getInt("type"));
				dto.setdName(rs.getString("dName"));
				
				articles.add(dto);
			}
			close();
    		
		} catch (Exception e) {
			logger.debug("SelectQna: " + e.getMessage());
		}
    	
    	
    	logger.info("qnaSelects 아티클 찍었따 나와라 ㅡㅡ: "+ articles);
    	
    	return articles;
    }
	
	
	
	public CsArticleDTO selectArticle(String no) {
		
		CsArticleDTO dto = new CsArticleDTO();
		
		logger.debug("delete no값~~~!@~~~"+no);
		
		SQL = "SELECT * FROM `km_cs_article` WHERE aNo = ? ";
		
		
		try {
			
			conn = getConnection();
			psmt= conn.prepareStatement(SQL);
			psmt.setInt(1,Integer.parseInt(no));
			rs = psmt.executeQuery();
			
			if(rs.next()) {
			
			dto.setaNo(rs.getInt("aNo"));
			dto.setGroup(rs.getString("group"));
			dto.setCateDetail(rs.getString("cateDetail"));
			dto.setTitle(rs.getString("title"));
			dto.setContent(rs.getString("content"));
			dto.setFile(rs.getInt("file"));
			dto.setMaskingWriter(rs.getString("writer"));
			dto.setRegip(rs.getString("regip"));
			dto.setRdateYYMMDD(rs.getString("rdate"));
			dto.setType(rs.getInt("type"));
			dto.setuLevel(rs.getInt("uLevel"));
			}
			close();
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
		
		logger.debug("cs아티클dao view dto 값 확인 로거~~~"+ dto);
		logger.debug("cs아티클dao view dto 값 확인 로거~~~"+ dto.getaNo());
		logger.debug("cs아티클dao view dto 값 확인 로거~~~"+ dto.getTitle());
		logger.debug("cs아티클dao view dto 값 확인 로거~~~"+ dto.getContent());
		
		return dto;
	}

	
	public List<CsArticleDTO> selectArticlesIndex(String group) {
		SQL = "SELECT * FROM `km_cs_article` AS a JOIN `km_cs_cate_detail` AS b ON a.`type` = b. `type` "
				+ "  WHERE `group` = ? ORDER BY `aNo` DESC LIMIT 5 ";
		
		conn = getConnection();
		
		List<CsArticleDTO> articles = new ArrayList<>();

		try {
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, group);
			rs = psmt.executeQuery();
			
			
		while(rs.next()) {
			
			CsArticleDTO dto = new CsArticleDTO();
			
			dto.setaNo(rs.getInt("aNo"));
			dto.setGroup(rs.getString("group"));
			dto.setCateDetail(rs.getString("cateDetail"));
			dto.setTitle(rs.getString("title"));
			dto.setMaskingWriter(rs.getString("writer"));
			dto.setRdateYYMMDD(rs.getString("rdate"));
			//dto.setAeName(rs.getString("aeName"));
			dto.setuLevel(rs.getInt("uLevel"));
			dto.setType(rs.getInt("type"));
			dto.setdName(rs.getString("dName"));
			
			articles.add(dto);
			
				
			}
			
			close();
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
		
		logger.debug("selectArticlesFAQ 다오 faq 아티클스 데이터:  "+articles.toString());
		
		return articles;
	}
	
	
	
	public List<CsArticleDTO> selectArticles(String cate, int start) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CsArticleDTO> selectArticlesFAQ(String group, String cateDetail) {

		SQL = "SELECT * FROM `km_cs_article` AS a "
				+ " JOIN `km_cs_cate_detail` AS b "
				+ " ON a.type = b.type "
				+ " WHERE a.`group` = ? AND a.`cateDetail` = ? AND a.`type`>= 20 ";
		
		conn = getConnection();
		
		List<CsArticleDTO> articles = new ArrayList<>();

		try {
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, group);
			psmt.setString(2, cateDetail);
			rs = psmt.executeQuery();
			
			
		while(rs.next()) {
			
			CsArticleDTO dto = new CsArticleDTO();
			
			dto.setaNo(rs.getInt("aNo"));
			dto.setGroup(rs.getString("group"));
			dto.setCateDetail(rs.getString("cateDetail"));
			dto.setTitle(rs.getString("title"));
			dto.setMaskingWriter(rs.getString("writer"));
			dto.setRdateYYMMDD(rs.getString("rdate"));
			//dto.setAeName(rs.getString("aeName"));
			dto.setuLevel(rs.getInt("uLevel"));
			dto.setType(rs.getInt("type"));
			dto.setdName(rs.getString("dName"));
			
			articles.add(dto);
			
				
			}
			
			close();
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
		
		logger.debug("selectArticlesFAQ 다오 faq 아티클스 데이터:  "+articles);
		return articles;
	}

	
	
	
	
	public void updateArticle(CsArticleDTO dto) {
		// TODO Auto-generated method stub
		
	}

	public void deleteArticle(String aNo) {

		
		SQL = "DELETE FROM `km_cs_article` WHERE `aNo` = ?";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setInt(1, Integer.parseInt(aNo));
			psmt.executeUpdate();
			
			
			close();
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
		
		
	}

	
	public List<String> deleteFile(String ano) {
		List<String> snames = new ArrayList<String>();
		
		SQL = "SELECT `sfile` FROM `File` WHERE `ano`=?";
		SQL2 = "DELETE FROM `File` WHERE `ano`=? ";
		
		try {
			conn = getConnection(); 
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, ano);
			
			
			psmt1 = conn.prepareStatement(SQL2);
			psmt1.setString(1, ano);
			
			rs = psmt.executeQuery();
			psmt1.executeUpdate();
			psmt1.close();
			while(rs.next()) {
				snames.add(rs.getString(1));
			}
			
			close();
		}catch (Exception e) {
			logger.error("deleteFile - " + e.getMessage());
		}
		
		return snames;
	}
	
	
	
	public int selectCountTotal(String group, String type, String cateDetail) {

		int total = 0;
		
		SQL= "SELECT COUNT(*) FROM `km_cs_article` WHERE `group`=? AND `type` >= ? AND `cateDetail` = ?";
	
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, group);
			psmt.setString(2, type);
			psmt.setString(3, cateDetail);
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("csarticleDAO selectTotalCout = "+total);
		
		return total;
	}


	public int selectCountTotalFAQ(String group, String type) {

		int total = 0;
		
		SQL= "SELECT COUNT(*) FROM `km_cs_article` WHERE `group`=? AND `type` >= ?";
	
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, group);
			psmt.setString(2, type);
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("csarticleDAO selectTotalCout = "+total);
		
		return total;
	}

	public int selectCountTotalCateAll(String group, String type, String cateDetail) {

		int total = 0;
		
		SQL= "SELECT COUNT(*) FROM `km_cs_article` WHERE `group`=? AND `type` >= ? AND `cateDetail` != ?";
	
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, group);
			psmt.setString(2, type);
			psmt.setString(3, cateDetail);
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("csarticleDAO selectTotalCout = "+total);
		
		return total;
	}
	
	
	public CommentDTO selectComments(String ano) {
		
		SQL=" SELECT * FROM `km_admin_comment` "
				+ " WHERE `aNo` = ? ";
		
		conn = getConnection();
		
		CommentDTO dto = null;
		try {
			
			psmt = conn.prepareStatement(SQL);
			psmt.setInt(1, Integer.parseInt(ano));
			rs = psmt.executeQuery();
			
			dto = new CommentDTO();
			
			if(rs.next()) {
				dto.setaNo(rs.getInt("aNo"));
				dto.setrNo(rs.getInt("rNo"));
				dto.setContent(rs.getString("content"));
				dto.setMaskingWriter(rs.getString("writer"));
				dto.setRdateYYMMDD(rs.getString("rdate"));
			}
			
			close();
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
		return dto;
	}

	public CommentDTO insertComment(CommentDTO dto) {

		SQL =" INSERT INTO `km_admin_comment` SET "
				+ " `aNo` = ? "
				+ " `writer` = ? "
				+ " `content` = ? "
				+ " `rdate` = NOW() ";
		
		SQL2=" UPDATE `km_cs_article` SET `aStatus` "
				+ " = 1 WHERE `aNo` = ?";
		
		SQL3=" SELECT * FROM `km_admin_comment` "
				+ " WHERE `aNo` = ? ";
		
		conn = getConnection();
		
		try {
			
			conn.setAutoCommit(false);
			
			psmt= conn.prepareStatement(SQL);
			psmt.setInt(1, dto.getaNo());
			psmt.setString(2, dto.getWriter());
			psmt.setString(3, dto.getContent());
			psmt.executeQuery();
			
			psmt.close();
			
			
			psmt= conn.prepareStatement(SQL2);
			psmt.setInt(1, dto.getaNo());
			psmt.executeQuery();
			psmt.close();
			
			psmt = conn.prepareStatement(SQL3);
			psmt.setInt(1, dto.getaNo());
			psmt.executeQuery(SQL3);
			
			psmt.close();
			
			
			conn.commit();
			
			if(rs.next()) {
				dto.setaNo(rs.getInt(1));
				dto.setrNo(rs.getInt(2));
				dto.setContent(rs.getString(3));
				dto.setMaskingWriter(rs.getString(4));
				dto.setRdateYYMMDD(rs.getString(5));
			}
			
			close();
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
		logger.debug("insertComment dto값 ~~ : " + dto.getaNo());
		logger.debug("insertComment dto값 ~~ : " + dto.getrNo());
		logger.debug("insertComment dto값 ~~ : " + dto.getWriter());
		
		return dto;
	}

	public int updateComment(String no, String content) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteComment(String no) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	public List<CsCateDetailDTO> selectCsWriteCate(String cateDetail){
		
		List<CsCateDetailDTO> cateList = new ArrayList<CsCateDetailDTO>();
		
		
		logger.debug("카테디테일 데이터!!!!!!!##@!#@!#@!   "+cateDetail);
		conn = getConnection();
		
		SQL = "SELECT * FROM `km_cs_cate_detail` WHERE `type`>=20 AND `aeName`=?";
		
		try {
		
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, cateDetail);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CsCateDetailDTO dto = new CsCateDetailDTO();
				dto.setType(rs.getInt(1));
				dto.setdName(rs.getString(2));
				dto.setAeName(rs.getString(3));
				
				cateList.add(dto);
			}
			close();
			
		} catch (Exception e) {
			logger.debug("selectCsWriteCateDAO 에러~~~"+ e.getMessage());
		}
		
		
		
		logger.debug("selectCsWriteCateDAO cateList 정보~~~~~"+ cateList);
		
		
		return cateList; 
	}
	
	
public List<CsCateAsideDTO> selectCsWriteCateFAQ(){
		
		List<CsCateAsideDTO> cateList = new ArrayList<CsCateAsideDTO>();
		
		
		conn = getConnection();
		
		SQL = "SELECT * FROM `km_cs_aside` WHERE `aside_num`> 1";
		
		try {
		
			psmt = conn.prepareStatement(SQL);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CsCateAsideDTO dto = new CsCateAsideDTO();
				dto.setAeName(rs.getString(1));
				dto.setAkName(rs.getString(2));
				dto.setAside_Num(rs.getInt(3));
				
				cateList.add(dto);
			}
			close();
			
		} catch (Exception e) {
			logger.debug("selectCsWriteCateDAO 에러~~~"+ e.getMessage());
		}
		
		
		
		logger.debug("selectCsWriteCateDAO cateList 정보~~~~~"+ cateList);
		
		
		return cateList; 
	}


public List<CsCateAsideDTO> selectCsWriteCateNotice(){
	
	List<CsCateAsideDTO> cateList = new ArrayList<CsCateAsideDTO>();
	
	
	conn = getConnection();
	
	SQL = "SELECT * FROM `km_cs_aside` WHERE `aside_num`> 1";
	
	try {
		
		psmt = conn.prepareStatement(SQL);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			CsCateAsideDTO dto = new CsCateAsideDTO();
			dto.setAeName(rs.getString(1));
			dto.setAkName(rs.getString(2));
			dto.setAside_Num(rs.getInt(3));
			
			cateList.add(dto);
		}
		close();
		
	} catch (Exception e) {
		logger.debug("selectCsWriteCateDAO 에러~~~"+ e.getMessage());
	}
	
	
	
	logger.debug("selectCsWriteCateDAO cateList 정보~~~~~"+ cateList);
	
	
	return cateList; 
}

public List<CsCateDetailDTO> selectCsWriteCateDeNotice(){
	
	List<CsCateDetailDTO> cateDetailList = new ArrayList<CsCateDetailDTO>();
	
	
	conn = getConnection();
	
	SQL = "SELECT * FROM `km_cs_cate_detail` WHERE `type`> 20";
	
	try {
		
		psmt = conn.prepareStatement(SQL);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			CsCateDetailDTO dto = new CsCateDetailDTO();
			dto.setAeName(rs.getString(1));
			dto.setType(rs.getInt(2));
			dto.setdName(rs.getString(3));
			
			cateDetailList.add(dto);
		}
		close();
		
	} catch (Exception e) {
		logger.debug("selectCsWriteCateDAO 에러~~~"+ e.getMessage());
	}
	
	
	
	logger.debug("selectCsWriteCateDAO cateList 정보~~~~~"+ cateDetailList);
	
	
	return cateDetailList; 
}


public List<CsCateDetailDTO> selectCsCateDetailFAQ(String cateDetail){
	
	List<CsCateDetailDTO> cateDetailList = new ArrayList<CsCateDetailDTO>();
	
	
	logger.debug("카테디테일 데이터!!!!!!!##@!#@!#@!   "+cateDetail);
	
	conn = getConnection();
	
	SQL = "SELECT * FROM `km_cs_cate_detail` WHERE `type` >= 20  AND `aeName`= ?";
	
	try {
		
		psmt = conn.prepareStatement(SQL);
		psmt.setString(1, cateDetail);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			CsCateDetailDTO dto = new CsCateDetailDTO();
			dto.setType(rs.getInt(1));
			dto.setdName(rs.getString(2));
			dto.setAeName(rs.getString(3));
			
			cateDetailList.add(dto);
		}
		close();
		
	} catch (Exception e) {
		logger.debug("selectCsWriteCateDAO 에러~~~"+ e.getMessage());
	}
	
	
	
	logger.debug("selectCsWriteCateDAO cateList 정보~~~~~"+ cateDetailList);
	
	
	return cateDetailList; 
}



public void deletefile(String aNo) {
	// TODO Auto-generated method stub
	
}
	
}
