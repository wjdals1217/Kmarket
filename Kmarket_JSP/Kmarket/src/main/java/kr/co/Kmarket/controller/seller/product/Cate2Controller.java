package kr.co.Kmarket.controller.seller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.Kmarket.dto.seller.Cate2DTO;
import kr.co.Kmarket.service.seller.Cate2Service;

@WebServlet("/seller/product/cate2.do")
public class Cate2Controller extends HttpServlet{

	private static final long serialVersionUID = 2826097442689005162L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Cate2Service cate2Service = new Cate2Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String selectValue = req.getParameter("selectValue");
		logger.debug("selectValue : "+selectValue);
		
		List<Cate2DTO> cate2s = cate2Service.selectCate2s(selectValue);
		
		resp.setContentType("application/json;charset=UTF-8");
		Gson gson = new Gson();
		String strJsons = gson.toJson(cate2s);
		resp.getWriter().print(strJsons);
	}

}
