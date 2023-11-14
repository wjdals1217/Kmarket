package kr.co.Kmarket.controller.seller.product;
/*
 * 날짜 : 2023-09-14
 * 이름 : 최정민 
 * 내용 : 상품등록 화면구현 및 상품등록 controller
 */

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.Kmarket.dto.FileDTO;
import kr.co.Kmarket.dto.ProductDTO;
import kr.co.Kmarket.dto.seller.Cate1DTO;
import kr.co.Kmarket.service.FileService;
import kr.co.Kmarket.service.ProductService;
import kr.co.Kmarket.service.seller.Cate1Service;

@WebServlet("/seller/product/register.do")
public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = -946511949129250047L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Cate1Service cateService = new Cate1Service();
	private FileService fileService = new FileService();
	private ProductService productService = new ProductService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String success = req.getParameter("success");
		List<Cate1DTO> cate1s = cateService.selectCate1s();
		logger.debug("cates1 : "+cate1s);
		req.setAttribute("success", success);
		req.setAttribute("cate1s", cate1s);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/seller/product/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		logger.debug("cate1 : "+cate1);
		logger.debug("cate2 : "+cate2);
		String fileUrl = "/thumb/"+cate1+"/"+cate2;
		logger.debug("fileUrl : "+fileUrl);
		
		String path = fileService.getPath(req, fileUrl);
		logger.debug("path : "+path);
		
		MultipartRequest mr = fileService.uploadFile(req, path);
		
		String seller = mr.getParameter("seller");
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
		String thumb2 = mr.getOriginalFileName("thumb2");
		String thumb3 = mr.getOriginalFileName("thumb3");
		String detail = mr.getOriginalFileName("detail");
		String status = mr.getParameter("status");
		String duty = mr.getParameter("duty");
		String receipt = mr.getParameter("receipt");
		String bizType = mr.getParameter("bizType");
		String origin = mr.getParameter("origin");
		String ip = req.getRemoteAddr();
		
		logger.debug("seller : "+seller);
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
		logger.debug("thumb2 : "+thumb2);
		logger.debug("thumb3 : "+thumb3);
		logger.debug("detail : "+detail);
		logger.debug("status : "+status);
		logger.debug("duty : "+duty);
		logger.debug("receipt : "+receipt);
		logger.debug("bizType : "+bizType);
		logger.debug("origin : "+origin);
		logger.debug("ip : "+ip);
		
		String newThumb1 = fileService.renameToFile(req, path, thumb1);
		String newThumb2 = fileService.renameToFile(req, path, thumb2);
		String newThumb3 = fileService.renameToFile(req, path, thumb3);
		String newDetail = fileService.renameToFile(req, path, detail);
		
		ProductDTO dto = new ProductDTO();
		dto.setSeller(seller);
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
		dto.setIp(ip);
		logger.debug("dto : "+dto);
		
		productService.insertProduct(dto);
		
		resp.sendRedirect("/Kmarket/seller/product/register.do?success=200");
	}
}
