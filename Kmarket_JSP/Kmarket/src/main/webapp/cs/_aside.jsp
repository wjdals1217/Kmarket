<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       <aside>
       		<c:choose>
       			<c:when test="${dto.group == 'qna'}">
              <h2>문의하기</h2>
              <ul>
            	<li class="${cateDetail eq 'all'?'on':'off'}"><a href="${ctxPath}/cs/qna.do?group=qna&cateDetail=all&type=20">전체 보기</a></li>
            	<li class="${cateDetail eq 'member'?'on':'off'}"><a href="${ctxPath}/cs/qna.do?group=qna&cateDetail=member&type=20">회원</a></li>
                <li class="${cateDetail eq 'event'?'on':'off'}"><a href="${ctxPath}/cs/qna.do?group=qna&cateDetail=event&type=20">쿠폰/이벤트</a></li>
                <li class="${cateDetail eq 'order'?'on':'off'}"><a href="${ctxPath}/cs/qna.do?group=qna&cateDetail=order&type=20">주문/결제</a></li>
                <li class="${cateDetail eq 'delivery'?'on':'off'}"><a href="${ctxPath}/cs/qna.do?group=qna&cateDetail=delivery&type=20">배송</a></li>
                <li class="${cateDetail eq 'cancle'?'on':'off'}"><a href="${ctxPath}/cs/qna.do?group=qna&cateDetail=cancle&type=20">취소/반품/교환</a></li>
                <li class="${cateDetail eq 'travel'?'on':'off'}"><a href="${ctxPath}/cs/qna.do?group=qna&cateDetail=travel&type=20">여행/숙박/항공</a></li>
                <li class="${cateDetail eq 'safe'?'on':'off'}"><a href="${ctxPath}/cs/qna.do?group=qna&cateDetail=safe&type=20">안전거래</a></li>
              </ul>
              </c:when>
       			<c:when test="${dto.group == 'faq'}">
              <h2>자주묻는 질문</h2>
              <ul>
      			<li class="${cateDetail eq 'member'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=member">회원</a></li>
                <li class="${cateDetail eq 'coupon' || cateDetail eq 'event' ?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=coupon">쿠폰/이벤트</a></li>
                <li class="${cateDetail eq 'order'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=order">주문/결제</a></li>
                <li class="${cateDetail eq 'delivery'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=delivery">배송</a></li>
                <li class="${cateDetail eq 'cancle'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=cancle">취소/반품/교환</a></li>
                <li class="${cateDetail eq 'travel'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=travel">여행/숙박/항공</a></li>
                <li class="${cateDetail eq 'safe'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=safe">안전거래</a></li>
              </ul>
              </c:when>
       			<c:when test="${dto.group == 'notice'}">
       			<h2>공지사항</h2>
              	<ul>
                <li class="${cateDetail eq 'all'?'on':'off'}"><a href="${ctxPath}/cs/notice.do?group=notice&cate=all&cateDetail=all&type=1">전체 보기</a></li>
                <li class="${cateDetail eq 'all'?'on':'off'}"><a href="${ctxPath}/cs/notice.do?group=notice&cate=all&cateDetail=service&type=1">고객서비스</a></li>
                <li class="${cateDetail eq 'all'?'on':'off'}"><a href="${ctxPath}/cs/notice.do?group=notice&cate=all&cateDetail=safe&type=1">안전거래</a></li>
                <li class="${cateDetail eq 'all'?'on':'off'}"><a href="${ctxPath}/cs/notice.do?group=notice&cate=all&cateDetail=hazard&type=1">위해상품</a></li>
                <li class="${cateDetail eq 'all'?'on':'off'}"><a href="${ctxPath}/cs/notice.do?group=notice&cate=all&cateDetail=event&type=1">이벤트당첨</a></li>

              </ul>
              </c:when>
              </c:choose>
            </aside>