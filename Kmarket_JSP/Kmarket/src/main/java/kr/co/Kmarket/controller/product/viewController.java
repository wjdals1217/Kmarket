package kr.co.Kmarket.controller.product;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import kr.co.Kmarket.dto.ProductDTO;
import kr.co.Kmarket.dto.seller.Cate2DTO;
import kr.co.Kmarket.service.ProductService;
import kr.co.Kmarket.service.seller.Cate2Service;
/*
	날짜 : 2023/09/14
	이름 : 김무현
	내용 : Controller 기본셋팅
*/
@WebServlet("/product/view.do")
public class viewController extends HttpServlet{
	private static final long serialVersionUID = -8127962812518063520L;
	
	private Cate2Service Ct2Service = new Cate2Service();
	
	private ProductService pService = new ProductService();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar calendar = Calendar.getInstance();
	
	class DeliveryDate {
	    public String calculateDeliveryDate(String rdate) {
	        String result = "";
	       
	        String dateStr = rdate;
	        // 날짜 문자열을 Date 객체로 변환
	     
	        Date date = null;
	        try {
	            date = dateFormat.parse(dateStr);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        if (date != null) {
	            // 모레의 날짜 계산
	           
	            calendar.setTime(date);
	            calendar.add(Calendar.DAY_OF_MONTH, 1);
	            Date tomorrow = calendar.getTime();
	            // 모레의 날짜에서 요일 계산
	            SimpleDateFormat dayFormat = new SimpleDateFormat("E", Locale.KOREAN);
	            String dayOfWeek = dayFormat.format(tomorrow);
	            // 표시할 날짜 형식 설정
	            SimpleDateFormat displayDateFormat = new SimpleDateFormat("M/d", Locale.KOREAN);
	            String formattedDate = displayDateFormat.format(tomorrow);
	            // 최종 표시 문자열 생성
	            result = "모레 (" + dayOfWeek + ") " + formattedDate + " 도착예정";
	        }
	        return result;
	    }
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String prodNo= req.getParameter("prodNo");
		String prodCate1 = req.getParameter("prodCate1");
		String prodCate2 = req.getParameter("prodCate2");
		logger.debug("prodCate1 : " + prodCate1);
		logger.debug("prodCate2 : " + prodCate2);
		logger.debug("prodNo : " + prodNo);
		
		ProductDTO product = pService.selectProduct(prodNo);
		
		//logger.debug(product.toString());
		
		// DeliveryDate 클래스의 인스턴스 생성
	    DeliveryDate deliveryDateCalculator = new DeliveryDate();
	   
	    Date today = calendar.getTime();
	    // 현재 날짜를 문자열로 변환
	    String currentDate = dateFormat.format(today);
	    // 현재 날짜 출력
	    String deliveryDate = deliveryDateCalculator.calculateDeliveryDate(currentDate);
	   
		
		
		
		//aside 카테고리
		
		List<List<Cate2DTO>> categories = Ct2Service.selectCategories();
				
		List<ProductDTO> productsaside = pService.selectProductBest();
		
		req.setAttribute("prodCate1", prodCate1);
		req.setAttribute("prodCate2", prodCate2);
		req.setAttribute("categories", categories);
		req.setAttribute("productsaside", productsaside);
		req.setAttribute("product", product);
		req.setAttribute("deliveryDate", deliveryDate);
		logger.debug("deliveryDate : " + deliveryDate);
		logger.info("product ------------------- "+product);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/view.jsp");
		dispatcher.forward(req, resp);
	}
}