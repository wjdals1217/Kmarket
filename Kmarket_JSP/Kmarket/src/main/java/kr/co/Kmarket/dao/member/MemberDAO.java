package kr.co.Kmarket.dao.member;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import kr.co.Kmarket.db.DBHelper;
import kr.co.Kmarket.dto.SearchDTO;
import kr.co.Kmarket.dto.member.MemberDTO;


public class MemberDAO extends DBHelper{
	
	private String SQL = "";
	private String SQL2 = "";
	
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private MemberDAO() {}

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//member, seller 회원가입
	public void insertMember(String type, MemberDTO dto) {
		
		SQL = "INSERT INTO `km_member` SET "
				+ "`uid`=?, "
				+ "`pass`=SHA2(?, 256), "
				+ "`name`=?, "
				+ "`gender`=?, "
				+ "`hp`=?, "
				+ "`email`=?, "
				+ "`type`=1, "
				+ "`zip`=?, "
				+ "`addr1`=?, "
				+ "`addr2`=?, "
				+ "`regip`=?, "
				+ "`rdate`=NOW()";
		
		SQL2 = "INSERT INTO `km_member` SET "
				+ "`uid`=?, "
				+ "`level`=5, "
				+ "`pass`=SHA2(?, 256), "
				+ "`email`=?, "
				+ "`type`=2, "
				+ "`zip`=?, "
				+ "`addr1`=?, "
				+ "`addr2`=?, "
				+ "`company`=?, "
				+ "`ceo`=?, "
				+ "`bizRegNum`=?, "
				+ "`comRegNum`=?, "
				+ "`tel`=?, "
				+ "`manager`=?, "
				+ "`managerHp`=?, "
				+ "`fax`=?, "
				+ "`regip`=?, "
				+ "`rdate`=NOW()";
		
		try {
			conn = getConnection();
			if(type.equals("normal")) {
				psmt = conn.prepareStatement(SQL);
				psmt.setString(1, dto.getUid());
				psmt.setString(2, dto.getPass());
				psmt.setString(3, dto.getName());
				psmt.setInt(4, dto.getGender());
				psmt.setString(5, dto.getHp());
				psmt.setString(6, dto.getEmail());
				psmt.setString(7, dto.getZip());
				psmt.setString(8, dto.getAddr1());
				psmt.setString(9, dto.getAddr2());
				psmt.setString(10, dto.getRegip());
				psmt.executeUpdate();
			}else {
				psmt = conn.prepareStatement(SQL2);
				psmt.setString(1, dto.getUid());
				psmt.setString(2, dto.getPass());
				psmt.setString(3, dto.getEmail());
				psmt.setString(4, dto.getZip());
				psmt.setString(5, dto.getAddr1());
				psmt.setString(6, dto.getAddr2());
				psmt.setString(7, dto.getCompany());
				psmt.setString(8, dto.getCeo());
				psmt.setString(9, dto.getBizRegNum());
				psmt.setString(10, dto.getComRegNum());
				psmt.setString(11,dto.getTel());
				psmt.setString(12, dto.getManager());
				psmt.setString(13, dto.getManagerHp());
				psmt.setString(14, dto.getFax());
				psmt.setString(15, dto.getRegip());
				psmt.executeUpdate();
			}
			close();
			
		}catch(Exception e) {
			logger.error("insertMember error : " + e.getMessage());
		}
		
		
			
	}
	
	//member,seller 회원가입 중복확인 
	public int selectCountUid(String uid) {
		int result = 0;
		
		SQL = "SELECT COUNT(*) FROM `km_member` WHERE `uid`=?";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		}catch(Exception e) {
			logger.error("selectCountUid error : " + e.getMessage());
		}
		return result;
	}
	
	public int selectCountHp(String hp) {
		int result = 0;
		
		SQL = "SELECT COUNT(*) FROM `km_member` WHERE `hp`=?";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, hp);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		}catch(Exception e) {
			logger.error("selectCountHp : " + e.getMessage());
		}
		return result;
	}

	public int selectCountEmail(String email) {
		int result = 0;
		
		SQL = "SELECT COUNT(*) FROM `km_member` WHERE `email`=?";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		}catch(Exception e) {
			logger.error("selectCountEmail error : " + e.getMessage());
		}
		return result;
		
	}
	public int selectCountbizRegNum(String bizRegNum) {
		int result = 0;
		SQL = "SELECT COUNT(*) FROM `km_member` WHERE `bizRegNum`=?";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, bizRegNum);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		}catch(Exception e) {
			logger.error("bizRegNum error : " + e.getMessage());
		}
		return result;
	}
	public int selectCountcomRegNum(String comRegNum) {
		int result = 0;
		SQL = "SELECT COUNT(*) FROM `km_member` WHERE `comRegNum`=?";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, comRegNum);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		}catch(Exception e) {
			logger.error("comRegNum error : " + e.getMessage());
		}
		return result;
	}
	
	//자동로그인(쿠키 조회)
	public MemberDTO selectCookie(String uid) {
		SQL = "SELECT * FROM `km_member` WHERE `uid`=?";
		MemberDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, uid);
			rs =  psmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setUid(rs.getString(1));
			}
			close();
			
			
		}catch(Exception e) {
			logger.error("selectCookie error : " + e.getMessage());
		}
		return dto;
	}
	
	//로그인
	public MemberDTO selectMember(String uid, String pass) {
		MemberDTO dto = null;
		SQL = "SELECT * FROM `km_member` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setUid(rs.getString(1));
				dto.setLevel(rs.getInt(2));
				dto.setPass(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setGender(rs.getInt(5));
				dto.setHp(rs.getString(6));
				dto.setEmail(rs.getString(7));
				dto.setType(rs.getInt(8));
				dto.setPoint(rs.getInt(9));
				dto.setZip(rs.getString(10));
				dto.setAddr1(rs.getString(11));
				dto.setAddr2(rs.getString(12));
				dto.setCompany(rs.getString(13));
				dto.setCeo(rs.getString(14));
				dto.setBizRegNum(rs.getString(15));
				dto.setComRegNum(rs.getString(16));
				dto.setTel(rs.getString(17));
				dto.setManager(rs.getString(18));
				dto.setManagerHp(rs.getString(19));
				dto.setFax(rs.getString(20));
				dto.setRegip(rs.getString(21));
				dto.setWdate(rs.getString(22));
				dto.setRdate(rs.getString(23));
				dto.setEtc1(rs.getInt(24));
				dto.setEtc2(rs.getInt(25));
				dto.setEtc3(rs.getString(26));
				dto.setEtc4(rs.getString(27));
				dto.setEtc5(rs.getString(28));
			}  
			close();
			
			
		}catch(Exception e) {
			logger.error("selectMember error : " + e.getMessage());
		}
		return dto;
	}
	public MemberDTO selectMember(String uid) {
		MemberDTO dto = null;
		SQL = "SELECT * FROM `km_member` WHERE `uid`=?";
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setUid(rs.getString(1));
				dto.setLevel(rs.getInt(2));
				dto.setPass(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setGender(rs.getInt(5));
				dto.setHp(rs.getString(6));
				dto.setEmail(rs.getString(7));
				dto.setType(rs.getInt(8));
				dto.setPoint(rs.getInt(9));
				dto.setZip(rs.getString(10));
				dto.setAddr1(rs.getString(11));
				dto.setAddr2(rs.getString(12));
				dto.setCompany(rs.getString(13));
				dto.setCeo(rs.getString(14));
				dto.setBizRegNum(rs.getString(15));
				dto.setComRegNum(rs.getString(16));
				dto.setTel(rs.getString(17));
				dto.setManager(rs.getString(18));
				dto.setManagerHp(rs.getString(19));
				dto.setFax(rs.getString(20));
				dto.setRegip(rs.getString(21));
				dto.setWdate(rs.getString(22));
				dto.setRdate(rs.getString(23));
				dto.setEtc1(rs.getInt(24));
				dto.setEtc2(rs.getInt(25));
				dto.setEtc3(rs.getString(26));
				dto.setEtc4(rs.getString(27));
				dto.setEtc5(rs.getString(28));
			}  
			close();
			
			
		}catch(Exception e) {
			logger.error("selectMember error : " + e.getMessage());
		}
		return dto;
	}
	
	//////////////////////////2차 개발////////////////////////////
		//아이디 찾기
		public int selectCountNameAndEmail(String name,String email) {
		
		int result = 0;
		SQL = "SELECT COUNT(*) FROM `km_member` WHERE `name`=? AND `email`=?"; 
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, name);
			psmt.setString(2, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		}catch(Exception e) {
			logger.error("selectCountNameAndEmail error : " + e.getMessage());
		}
		return result;
		
		}
		//비밀번호 찾기
		public int selectCountUidAndEmail(String uid,String email) {
			
			int result = 0;
			SQL = "SELECT COUNT(*) FROM `km_member` WHERE `uid`=? AND `email`=?";
			
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL);
				psmt.setString(1, uid);
				psmt.setString(2, email);
				rs = psmt.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);
				}
				
				close();
			}catch(Exception e) {
				logger.error("selectCountUidAndEmail error : " + e.getMessage());
			}
			return result;
			
		}
		//아이디찾기 전송
		public MemberDTO selectMemberByNameAndEmail(String name, String email) {
			
			MemberDTO dto = null; //회원이 아닐경우 uid, pass없으니까 null로 리턴해주려고 따로 선언, 생성
			SQL = "SELECT * FROM `km_member` WHERE `name`=? AND `email`=?"; 
			
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL);
				psmt.setString(1, name);
				psmt.setString(2, email);
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					dto = new MemberDTO();
					dto.setUid(rs.getString(1));
					dto.setLevel(rs.getInt(2));
					dto.setPass(rs.getString(3));
					dto.setName(rs.getString(4));
					dto.setGender(rs.getInt(5));
					dto.setHp(rs.getString(6));
					dto.setEmail(rs.getString(7));
					dto.setType(rs.getInt(8));
					dto.setPoint(rs.getInt(9));
					dto.setZip(rs.getString(10));
					dto.setAddr1(rs.getString(11));
					dto.setAddr2(rs.getString(12));
					dto.setCompany(rs.getString(13));
					dto.setCeo(rs.getString(14));
					dto.setBizRegNum(rs.getString(15));
					dto.setComRegNum(rs.getString(16));
					dto.setTel(rs.getString(17));
					dto.setManager(rs.getString(18));
					dto.setManagerHp(rs.getString(19));
					dto.setFax(rs.getString(20));
					dto.setRegip(rs.getString(21));
					dto.setWdate(rs.getString(22));
					dto.setRdate(rs.getString(23));
					dto.setEtc1(rs.getInt(24));
					dto.setEtc2(rs.getInt(25));
					dto.setEtc3(rs.getString(26));
					dto.setEtc4(rs.getString(27));
					dto.setEtc5(rs.getString(28));
				}
				close();
				
			}catch(Exception e) {
				logger.error("selectMemberByNameAndEmail error : " + e.getMessage());
			}
			return dto;
		}
		
		//비밀번호 찾기 변경, 회원정보수정 비밀번호 변경
		public int updateMemberPass(String uid, String pass) {
			int result = 0;
			SQL = "UPDATE `km_member` SET `pass`=SHA2(?, 256) WHERE `uid`=?";
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL);
				psmt.setString(1, pass);
				psmt.setString(2, uid);
				result = psmt.executeUpdate();
				
				close();
				
			}catch(Exception e) {
				logger.error("updateMemberPass error : " + e.getMessage());
			}
			return result;
		}
		//회원탈퇴
		public int updateMemberForWithdraw(String uid) {
			
			int result = 0;
			SQL = "UPDATE `km_member` SET"
					+ "`level`=null,"
					+ "`pass`=null,"
					+ "`name`=null,"
					+ "`gender`=null,"
					+ "`hp`=null,"
					+ "`email`=null,"
					+ "`type`=null,"
					+ "`point`=null,"
					+ "`zip`=null,"
					+ "`addr1`=null,"
					+ "`addr2`=null,"
					+ "`company`=null,"
					+ "`ceo`=null,"
					+ "`bizRegNum`=null,"
					+ "`comRegNum`=null,"
					+ "`tel`=null,"
					+ "`manager`=null,"
					+ "`managerHp`=null,"
					+ "`fax`=null,"
					+ "`regip`=null,"
					+ "`wdate`=NOW(),"
					+ "`etc1`=null,"
					+ "`etc2`=null,"
					+ "`etc3`=null,"
					+ "`etc4`=null,"
					+ "`etc5`=null,"
					+ " WHERE `uid`=?"; 	
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL);
				psmt.setString(1, uid);
				result = psmt.executeUpdate();
				
				close();
				
			}catch(Exception e) {
				logger.error("updateMemberForWithdraw error : " + e.getMessage());
			}
			return result;
		}
		//회원정보수정
		public void updateMember(MemberDTO dto) {
			SQL = "UPDATE `km_member` SET"
					+ "`name`=?,"
					+ "`hp`=?,"
					+ "`email`=?,"
					+ "`zip`=?,"
					+ "`addr1`=?,"
					+ "`addr2`=?, "
					+ "`company`=?, "
					+ "`ceo`=?, "
					+ "`tel`=?, "
					+ "`manager`=?, "
					+ "`managerHp`=?, "
					+ "`fax`=?, "
					+ " WHERE `uid`=?";
					
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL);
				psmt.setString(1, dto.getName());
				psmt.setString(2, dto.getHp());
				psmt.setString(3, dto.getEmail());
				psmt.setString(4, dto.getZip());
				psmt.setString(5, dto.getAddr1());
				psmt.setString(6, dto.getAddr2());
				psmt.setString(7, dto.getCompany());
				psmt.setString(8, dto.getCeo());
				psmt.setString(9, dto.getTel());
				psmt.setString(10, dto.getManager());
				psmt.setString(11, dto.getManagerHp());
				psmt.setString(12, dto.getFax());
				psmt.setString(13, dto.getUid());
				psmt.executeUpdate();
				
				close();
				
			}catch(Exception e) {
				logger.error("updateMember error : " + e.getMessage());
			}
		}
		
		// 회원조회
		public List<MemberDTO> selectMembers(int start, SearchDTO searchDTO, String type){
			List<MemberDTO> members = new ArrayList<MemberDTO>();

			MemberDTO dto=null;

			String sql_1 = "SELECT * FROM `km_member` WHERE `type`=1 ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_2 = "SELECT * FROM `km_member` WHERE `type`=2 ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_3 = "SELECT * FROM `km_member` WHERE `type`=3 ORDER BY `rdate` DESC LIMIT ?, 10";

			String sql_level1 = "SELECT * FROM `km_member` WHERE `type`=1 AND `level`=1 ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_level2 = "SELECT * FROM `km_member` WHERE `type`=1 AND `level`=2 ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_level3 = "SELECT * FROM `km_member` WHERE `type`=1 AND `level`=3 ORDER BY `rdate` DESC LIMIT ?, 10";
			
			String sql_search1_1 = "SELECT * FROM `km_member` "
								+ "WHERE `type`=1 AND `uid` LIKE ? "
								+ "ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_search1_2 = "SELECT * FROM `km_member` "
								+ "WHERE `type`=1 AND `name` LIKE ? "
								+ "ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_search2_1 = "SELECT * FROM `km_member` "
								+ "WHERE `type`=2 AND `uid` LIKE ? "
								+ "ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_search2_2 = "SELECT * FROM `km_member` "
								+ "WHERE `type`=2 AND `manager` LIKE ? "
								+ "ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_search2_3 = "SELECT * FROM `km_member` "
								+ "WHERE `type`=2 AND `company` LIKE ? "
								+ "ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_search3_1 = "SELECT * FROM `km_member` "
								+ "WHERE `type`=3 AND `uid` LIKE ? "
								+ "ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_search3_2 = "SELECT * FROM `km_member` "
								+ "WHERE `type`=3 AND `name` LIKE ? "
								+ "ORDER BY `rdate` DESC LIMIT ?, 10";
			conn = getConnection();
			try {
				if(searchDTO.getSearch() == null || searchDTO.getSearch().equals("")) {
					if(type.equals("1")) {
						if(searchDTO.getLevel() == 1) {
							psmt = conn.prepareStatement(sql_level1);
							psmt.setInt(1, start);
						}else if(searchDTO.getLevel() == 2) {
							psmt = conn.prepareStatement(sql_level2);
							psmt.setInt(1, start);
						}else if(searchDTO.getLevel() == 3) {
							psmt = conn.prepareStatement(sql_level3);
							psmt.setInt(1, start);
						}else {
							psmt = conn.prepareStatement(sql_1);
							psmt.setInt(1, start);
						}
					}else if(type.equals("2")) {
						psmt = conn.prepareStatement(sql_2);
						psmt.setInt(1, start);
					}else if(type.equals("3")) {
						logger.debug("search는 null");
						psmt = conn.prepareStatement(sql_3);
						logger.debug("psmt 생성");
						psmt.setInt(1, start);
						logger.debug("start : "+start);
					}
				}else {
					if(type.equals("1")) {
						if(searchDTO.getSearch().equals("search1")) {
							psmt = conn.prepareStatement(sql_search1_1);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
							psmt.setInt(2, start);
						}else if(searchDTO.getSearch().equals("search2")) {
							psmt = conn.prepareStatement(sql_search1_2);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
							psmt.setInt(2, start);
						}
					}else if(type.equals("2")) {
						if(searchDTO.getSearch().equals("search1")) {
							psmt = conn.prepareStatement(sql_search2_1);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
							psmt.setInt(2, start);
						}else if(searchDTO.getSearch().equals("search2")) {
							psmt = conn.prepareStatement(sql_search2_2);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
							psmt.setInt(2, start);
						}else if(searchDTO.getSearch().equals("search3")) {
							psmt = conn.prepareStatement(sql_search2_3);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
							psmt.setInt(2, start);
						}
					}else if(type.equals("3")) {
						if(searchDTO.getSearch().equals("search1")) {
							psmt = conn.prepareStatement(sql_search3_1);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
							psmt.setInt(2, start);
						}else if(searchDTO.getSearch().equals("search2")) {
							psmt = conn.prepareStatement(sql_search3_2);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
							psmt.setInt(2, start);
						}
					}
				}
				rs = psmt.executeQuery();
				logger.debug("rs 생성");
				while(rs.next()) {
					dto = new MemberDTO();
					dto.setUid(rs.getString(1));
					dto.setLevel(rs.getInt(2));
					dto.setPass(rs.getString(3));
					dto.setName(rs.getString(4));
					dto.setGender(rs.getInt(5));
					dto.setHp(rs.getString(6));
					dto.setEmail(rs.getString(7));
					dto.setType(rs.getInt(8));
					dto.setPoint(rs.getInt(9));
					dto.setZip(rs.getString(10));;
					dto.setAddr1(rs.getString(11));
					dto.setAddr2(rs.getString(12));
					dto.setCompany(rs.getString(13));
					dto.setCeo(rs.getString(14));
					dto.setBizRegNum(rs.getString(15));
					dto.setComRegNum(rs.getString(16));
					dto.setTel(rs.getString(17));
					dto.setManager(rs.getString(18));;
					dto.setManagerHp(rs.getString(19));
					dto.setFax(rs.getString(20));
					dto.setRegip(rs.getString(21));
					dto.setWdate(rs.getString(22));
					dto.setRdate(rs.getString(23));
					dto.setEtc1(rs.getInt(24));
					dto.setEtc2(rs.getInt(25));
					dto.setEtc3(rs.getString(26));
					dto.setEtc4(rs.getString(27));
					dto.setEtc5(rs.getString(28));
					members.add(dto);
					logger.debug("dto"+dto);
				}
				logger.debug("dto.getUid() : "+dto.getUid());
				close();
			} catch (Exception e) {
				logger.error("selectMembers error : "+e.getMessage());
			}
			return members;
		}
		public int selectCountTotal(SearchDTO searchDTO, String type) {
			int total = 0;
			String sql_1 = "SELECT COUNT(*) FROM `km_member` WHERE `type`=1";
			String sql_2 = "SELECT COUNT(*) FROM `km_member` WHERE `type`=2";
			String sql_3 = "SELECT COUNT(*) FROM `km_member` WHERE `type`=3";
			
			String sql_level1 = "SELECT COUNT(*) FROM `km_member` WHERE `type`=1 AND `level`=1";
			String sql_level2 = "SELECT COUNT(*) FROM `km_member` WHERE `type`=1 AND `level`=2";
			String sql_level3 = "SELECT COUNT(*) FROM `km_member` WHERE `type`=1 AND `level`=3";
			
			String sql_search1_1 = "SELECT COUNT(*) FROM `km_member` "
								+ "WHERE `type`=1 AND `uid` LIKE ? ";
			String sql_search1_2 = "SELECT COUNT(*) FROM `km_member` "
								+ "WHERE `type`=1 AND `name` LIKE ? ";
			String sql_search2_1 = "SELECT COUNT(*) FROM `km_member` "
								+ "WHERE `type`=2 AND `uid` LIKE ? ";
			String sql_search2_2 = "SELECT COUNT(*) FROM `km_member` "
								+ "WHERE `type`=2 AND `manager` LIKE ? "
								+ "ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_search2_3 = "SELECT COUNT(*) FROM `km_member` "
								+ "WHERE `type`=2 AND `company` LIKE ? "
								+ "ORDER BY `rdate` DESC LIMIT ?, 10";
			String sql_search3_1 = "SELECT COUNT(*) FROM `km_member` "
								+ "WHERE `type`=3 AND `uid` LIKE ? ";
			String sql_search3_2 = "SELECT COUNT(*) FROM `km_member` "
								+ "WHERE `type`=3 AND `name` LIKE ? ";
			conn = getConnection();
			try {
				if(searchDTO.getSearch() == null || searchDTO.getSearch().equals("")) {
					if(type.equals("1")) {
						if(searchDTO.getLevel() == 1) {
							psmt = conn.prepareStatement(sql_level1);
						}else if(searchDTO.getLevel() == 2) {
							psmt = conn.prepareStatement(sql_level2);
						}else if(searchDTO.getLevel() == 3) {
							psmt = conn.prepareStatement(sql_level3);
						}else {
							psmt = conn.prepareStatement(sql_1);
						}
					}else if(type.equals("2")) {
						psmt = conn.prepareStatement(sql_2);
					}else if(type.equals("3")) {
						psmt = conn.prepareStatement(sql_3);
					}
				}else {
					if(type.equals("1")) {
						if(searchDTO.getSearch().equals("search1")) {
							psmt = conn.prepareStatement(sql_search1_1);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
						}else if(searchDTO.getSearch().equals("search2")) {
							psmt = conn.prepareStatement(sql_search1_2);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
						}
					}else if(type.equals("2")) {
						if(searchDTO.getSearch().equals("search1")) {
							psmt = conn.prepareStatement(sql_search2_1);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
						}else if(searchDTO.getSearch().equals("search2")) {
							psmt = conn.prepareStatement(sql_search2_2);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
						}else if(searchDTO.getSearch().equals("search3")) {
							psmt = conn.prepareStatement(sql_search2_3);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
						}
					}else if(type.equals("3")) {
						if(searchDTO.getSearch().equals("search1")) {
							psmt = conn.prepareStatement(sql_search3_1);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
						}else if(searchDTO.getSearch().equals("search2")) {
							psmt = conn.prepareStatement(sql_search3_2);
							psmt.setString(1, "%"+searchDTO.getSearch_text()+"%");
						}
					}
				}
				rs = psmt.executeQuery();
				if(rs.next()) {
					total = rs.getInt(1);
				}
				close();
			} catch (Exception e) {
				logger.error("selectCountTotal : "+e.getMessage());
			}
			return total;
		}
}
