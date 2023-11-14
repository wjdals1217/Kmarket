<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp"%>
            <section id="seller-board-qna">
                <nav>
                    <h3>Kmarket사용문의</h3>
                    <p>
                        HOME > 고객문의 > <strong>Kmarket사용문의</strong>
                    </p>
                </nav>
                <!-- kmarket사용문의 컨텐츠 시작 -->        
                <!-- 자기 문의한 글만 볼수있는 list, 글쓰기 버튼도 구현하기 -->                        
                <section>
                    <div>
                        <form id="formSearch" action="/Kmarket/seller/product/list.do" method="get">
			           		<input type="hidden" name="seller" value="${sessUser.company}"/>
				           <select name="search">
				                  <option value="search1">상품명</option>
				                  <option value="search2">상품코드</option>                               
				                  <option value="search3">담당자명</option>                               
				           </select>
				           <input type="text" name="search_text">
				           <button id="btnSearch">검색</button>
			           </form>
                    </div> 
                    
                    <table>
                        <tr>
                            <th>글번호</th>
                            <th>문의내용</th>
                            <th>답변완료</th>
                            <th>작성일자</th>
                        </tr>

                        <tr>
                            <td>1</td>
                            <td>이거 어떡해야하나요</td>
                            <td>완료</td>
                            <td>23-05-25</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>이거 어떡해야하나요</td>
                            <td>미완료</td>
                            <td>23-05-25</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>이거 어떡해야하나요</td>
                            <td>완료</td>
                            <td>23-05-25</td>
                        </tr>
                                               
                    </table>
                    
                    <input class="btnWrite" type="button" value="문의하기" /> 

                    <div class="paging">
                        <span class="prev">
                            <a href="#"><&nbsp;이전</a>
                        </span>
                        <span class="num">
                            <a href="#" class="on">1</a>
                            <a href="#">2</a>
                            <a href="#">3</a>
                            <a href="#">4</a>
                            <a href="#">5</a>
                            <a href="#">6</a>
                            <a href="#">7</a>
                        </span>
                        <span class="next">
                            <a href="#">다음&nbsp;></a>
                        </span>
                        </div>

                </section>                

                
                <p class="ico info">
                    <strong>Tip!</strong>
                    사이트 이용 시 불편한 점이 있다면 문의해주십시오. 
                </p>

                

                <!-- kmarket사용문의 컨텐츠 끝 -->
            </section>
        </main>
<%@ include file="../_footer.jsp" %>
