package kr.co.Kmarket.controller.admin.store;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.dto.ProductDTO;
import kr.co.Kmarket.service.FileService;
import kr.co.Kmarket.service.ProductService;

@WebServlet("/admin/store/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = -5656850377521938950L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService productService = new ProductService();
	private FileService fileService = new FileService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodNo = req.getParameter("prodNo");
		logger.debug("prodNo"+prodNo);
		
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
		
		resp.sendRedirect("/Kmarket/admin/store/list.do");
	}
}
