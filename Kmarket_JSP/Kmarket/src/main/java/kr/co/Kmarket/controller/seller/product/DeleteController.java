package kr.co.Kmarket.controller.seller.product;
/*
 * 날짜 : 2023/09/18
 * 이름 : 최정민
 * 내용 : 상품삭제
 */
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

import kr.co.Kmarket.dto.ProductDTO;
import kr.co.Kmarket.dto.member.MemberDTO;
import kr.co.Kmarket.service.FileService;
import kr.co.Kmarket.service.ProductService;

@WebServlet("/seller/product/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = -8881308676068023487L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService productService = new ProductService();
	private FileService fileService = new FileService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodNo = req.getParameter("prodNo");
		logger.debug("prodNo"+prodNo);
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO)session.getAttribute("sessUser");
		String seller = sessUser.getCompany();
		
		ProductDTO image = productService.selectImages(prodNo);
		
			String fileUrl = "/thumb/"+image.getProdCate1()+"/"+image.getProdCate2();
			String path = fileService.getPath(req, fileUrl);
			logger.debug("path : "+path);
			File thumb1 = new File(path+"/"+image.getNewThumb1());
			File thumb2 = new File(path+"/"+image.getNewThumb2());
			File thumb3 = new File(path+"/"+image.getNewThumb3());
			File detail = new File(path+"/"+image.getNewDetail());
			
			if(thumb1.exists() && thumb2.exists() && thumb3.exists() && detail.exists()) {
				logger.debug("here2");
				thumb1.delete();
				thumb2.delete();
				thumb3.delete();
				detail.delete();
			}
		productService.deleteProduct(prodNo);
		
		resp.sendRedirect("/Kmarket/seller/product/list.do?seller="+seller);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[]  chks = req.getParameterValues("chk");
		logger.debug("chks : "+chks[0]);
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO)session.getAttribute("sessUser");
		String seller = sessUser.getCompany();
		for (String chk : chks) {
			
			ProductDTO image = productService.selectImages(chk);
			logger.debug("image : "+image.getProdNo());
			
				String fileUrl = "/thumb/"+image.getProdCate1()+"/"+image.getProdCate2();
				String path = fileService.getPath(req, fileUrl);
				logger.debug("path : "+path);
				File thumb1 = new File(path+"/"+image.getNewThumb1());
				File thumb2 = new File(path+"/"+image.getNewThumb2());
				File thumb3 = new File(path+"/"+image.getNewThumb3());
				File detail = new File(path+"/"+image.getNewDetail());
				
				if(thumb1.exists() || thumb2.exists() || thumb3.exists() || detail.exists()) {
					logger.debug("here2");
					thumb1.delete();
					thumb2.delete();
					thumb3.delete();
					detail.delete();
				}
			
			productService.deleteProduct(chk);
			
		}
		resp.sendRedirect("/Kmarket/seller/product/list.do?seller="+seller);
	}
}
