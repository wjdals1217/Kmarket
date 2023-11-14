package kr.co.Kmarket.controller.seller.product;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.Kmarket.dto.ProductDTO;
import kr.co.Kmarket.dto.member.MemberDTO;
import kr.co.Kmarket.dto.seller.Cate1DTO;
import kr.co.Kmarket.dto.seller.Cate2DTO;
import kr.co.Kmarket.service.FileService;
import kr.co.Kmarket.service.ProductService;
import kr.co.Kmarket.service.seller.Cate1Service;
import kr.co.Kmarket.service.seller.Cate2Service;

@WebServlet("/seller/product/modify.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = 1926866614940162382L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService productService = new ProductService();
	private Cate1Service cate1Service = new Cate1Service();
	private Cate2Service cate2Service = new Cate2Service();
	private FileService fileService = new FileService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodNo = req.getParameter("prodNo");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		logger.debug("prodNo : "+prodNo);
		logger.debug("cate1 : "+cate1);
		logger.debug("cate2 : "+cate2);
		
		List<Cate1DTO> cate1s = cate1Service.selectCate1s();
		logger.debug("cates1 : "+cate1s);
		
		List<Cate2DTO> cate2s = cate2Service.selectCate2s(cate1);
		
		ProductDTO product = productService.selectProduct(prodNo);
		req.setAttribute("product", product);
		req.setAttribute("oriCate1", cate1);
		req.setAttribute("oriCate2", cate2);
		req.setAttribute("cate1s", cate1s);
		req.setAttribute("cate2s", cate2s);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/seller/product/modify.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 수정 전 경로에 수정될 파일 저장을 위해 원래 업로드 폴더 데이터 받아오기
		String oriCate1 = req.getParameter("oriCate1");
		String oriCate2 = req.getParameter("oriCate2");
		logger.debug("oriCate1 : "+oriCate1);
		logger.debug("oriCate2 : "+oriCate2);
		
		// 수정 전 경로
		String fileUrl = "/thumb/"+oriCate1+"/"+oriCate2;
		logger.debug("fileUrl : "+fileUrl);
		String path = fileService.getPath(req, fileUrl);
		logger.debug("path : "+path);
		
		// 수정 전 경로에 파일 업로드
		MultipartRequest mr = fileService.uploadFile(req, path);
		
		String prodNo = mr.getParameter("prodNo");
		String prodCate1 = mr.getParameter("prodCate1");
		String prodCate2 = mr.getParameter("prodCate2");
		String prodName = mr.getParameter("prodName");
		String descript = mr.getParameter("descript");
		String company = mr.getParameter("company");
		String price = mr.getParameter("price");
		String discount = mr.getParameter("discount");
		String point = mr.getParameter("point");  
		String stock = mr.getParameter("stock");
		String delivery = mr.getParameter("delivery");
		String thumb1 = mr.getOriginalFileName("thumb1");
		String oriThumb1 = mr.getParameter("oriThumb1");
		String thumb2 = mr.getOriginalFileName("thumb2");
		String oriThumb2 = mr.getParameter("oriThumb2");
		String thumb3 = mr.getOriginalFileName("thumb3");
		String oriThumb3 = mr.getParameter("oriThumb3");
		String detail = mr.getOriginalFileName("detail");
		String oriDetail = mr.getParameter("oriDetail");
		String status = mr.getParameter("status");
		String duty = mr.getParameter("duty");
		String receipt = mr.getParameter("receipt");
		String bizType = mr.getParameter("bizType");
		String origin = mr.getParameter("origin");
		String oName1 = mr.getParameter("oName1");
		String oName2 = mr.getParameter("oName2");
		String oName3 = mr.getParameter("oName3");
		String oName4 = mr.getParameter("oName4");
		
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO)session.getAttribute("sessUser");
		String seller = sessUser.getCompany();

		logger.debug("prodNo : "+prodNo);
		logger.debug("prodCate1 : "+prodCate1);
		logger.debug("prodCate2 : "+prodCate2);
		logger.debug("prodName : "+prodName);
		logger.debug("descript : "+descript);
		logger.debug("company : "+company);
		logger.debug("price : "+price);
		logger.debug("discount : "+discount);
		logger.debug("point : "+point);
		logger.debug("stock : "+stock);
		logger.debug("delivery : "+delivery);
		logger.debug("thumb1 : "+thumb1);
		logger.debug("oriThumb1 : "+oriThumb1);
		logger.debug("thumb2 : "+thumb2);
		logger.debug("oriThumb2 : "+oriThumb2);
		logger.debug("thumb3 : "+thumb3);
		logger.debug("oriThumb3 : "+oriThumb3);
		logger.debug("detail : "+detail);
		logger.debug("oriDetail : "+oriDetail);
		logger.debug("status : "+status);
		logger.debug("duty : "+duty);
		logger.debug("receipt : "+receipt);
		logger.debug("bizType : "+bizType);
		logger.debug("origin : "+origin);

		// 카테고리 변경 했을 때 newPath
		String newFileUrl = "/thumb/"+prodCate1+"/"+prodCate2;
		logger.debug("newFileUrl : "+newFileUrl);
		String newPath = fileService.getPath(req, newFileUrl);
		logger.debug("newPath : "+newPath);
		
		String newThumb1;
		String newThumb2;
		String newThumb3;
		String newDetail;
		File fileThumb1;
		File fileThumb2;
		File fileThumb3;
		File fileDetail;
		
		// 파일 uuid 생성
		if(thumb1 != null) {
			newThumb1 = fileService.uuidName(thumb1);
			fileThumb1 = new File(path+"/"+thumb1);
			logger.debug("fileThumb1 : "+fileThumb1);
		}else {
			thumb1 = oName1;
			newThumb1 = oriThumb1;
			fileThumb1 = new File(path+"/"+newThumb1);
			logger.debug("fileThumb1 : "+fileThumb1);
		}
		logger.debug("thumb1: "+ thumb1);
		logger.debug("newThumb1: "+ newThumb1);
		
		if(thumb2 != null) {
			newThumb2 = fileService.uuidName(thumb2);
			fileThumb2 = new File(path+"/"+thumb2);
		}else {
			thumb2 = oName2;
			newThumb2 = oriThumb2;
			fileThumb2 = new File(path+"/"+newThumb2);
		}
		logger.debug("newThumb2: "+ newThumb2);
		if(thumb3 != null) {
			newThumb3 = fileService.uuidName(thumb3);
			fileThumb3 = new File(path+"/"+thumb3);
		}else {
			thumb3 = oName3;
			newThumb3 = oriThumb3;
			fileThumb3 = new File(path+"/"+newThumb3);
		}
		logger.debug("newThumb3: "+ newThumb3);
		if(detail != null) {
			newDetail = fileService.uuidName(detail);
			fileDetail = new File(path+"/"+detail);
		}else {
			detail = oName4;
			newDetail = oriDetail;
			fileDetail = new File(path+"/"+newDetail);
		}
		logger.debug("newDetail: "+ newDetail);
		
		// 원본파일
		File oriFileThumb1 = new File(path+"/"+oriThumb1);
		File oriFileThumb2 = new File(path+"/"+oriThumb2);
		File oriFileThumb3 = new File(path+"/"+oriThumb3);
		File oriFileDetail = new File(path+"/"+oriDetail);
		
		// 파일경로 변경		
		
		
		File newFileThumb1 = new File(newPath+"/"+newThumb1);
		logger.debug("newFileThumb1 : "+newFileThumb1);
		fileThumb1.renameTo(newFileThumb1);
		logger.debug("fileThumb1 : "+fileThumb1);
		
		File newFileThumb2 = new File(newPath+"/"+newThumb2);
		fileThumb2.renameTo(newFileThumb2);
		
		File newFileThumb3 = new File(newPath+"/"+newThumb3);
		fileThumb3.renameTo(newFileThumb3);
		
		File newFileDetail = new File(newPath+"/"+newDetail);
		fileDetail.renameTo(newFileDetail);
		
		// 원본파일 uuid와 새로운 파일 uuid 비교 
		if(!newThumb1.equals(oriThumb1) && oriFileThumb1.exists()) {
			oriFileThumb1.delete();
		}
		
		if(!newThumb2.equals(oriThumb2) && oriFileThumb2.exists()) {
			oriFileThumb2.delete();
		}
		
		if(!newThumb3.equals(oriThumb3) && oriFileThumb3.exists()) {
			oriFileThumb3.delete();
		}
		
		if(!newDetail.equals(oriDetail) && oriFileDetail.exists()) {
			oriFileDetail.delete();
		}
		
		ProductDTO dto = new ProductDTO();

		dto.setProdNo(prodNo);
		dto.setProdCate1(prodCate1);
		dto.setProdCate2(prodCate2);
		dto.setProdName(prodName);
		dto.setDescript(descript);
		dto.setCompany(company);
		dto.setPrice(price);
		dto.setDiscount(discount);
		dto.setPoint(point);
		dto.setStock(stock);
		dto.setDelivery(delivery);
		dto.setThumb1(thumb1);
		dto.setNewThumb1(newThumb1); 
		dto.setThumb2(thumb2);
		dto.setNewThumb2(newThumb2); 
		dto.setThumb3(thumb3);
		dto.setNewThumb3(newThumb3); 
		dto.setDetail(detail);
		dto.setNewDetail(newDetail);
		dto.setStatus(status);
		dto.setDuty(duty);
		dto.setReceipt(receipt);
		dto.setBizType(bizType);
		dto.setOrigin(origin);
		logger.debug("dto : "+dto);
		
		productService.updateProduct(dto);
		
		resp.sendRedirect("/Kmarket/seller/product/list.do?seller="+seller);
	}
}
