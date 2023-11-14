<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp" %>
                <script>
			        $(function(){
			            $('#pointChange').click(function(e){
			                e.preventDefault();
			                $('input[name=savePoint]').show();
			                $('input[name=usedPoint]').show();
			                $('input[name=savePoint]').next().hide();
			                $('input[name=usedPoint]').next().hide();
			            });
			        });
			    </script>
            <section id="admin-user-point">
                <nav>
                    <h3>포인트관리</h3>
                    <p>
                        HOME > 회원관리 > <strong>포인트관리</strong>
                    </p>
                </nav>
                <!-- 포인트 컨텐츠 시작 --> 
                <!-- 번호, 회원명, 아이디, 레벨, 주문확정일자, 지급포인트, 차감포인트 현재포인트 -->  
                <!-- 주문으로만 포인트 변동됨 (다른 이벤트 없음)(구매금액의 1%)-->
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
                    <div class="local_ov">
                         전체 : 
                        <b class="fc_red">1</b>
                         건 조회
                        <strong class="ov_a">포인트 합계 : 800,000원</strong>
                    </div>
                    <table>
                        <tr>
                            
                            <th>번호</th>
                            <th>회원명</th>
                            <th>아이디</th>
                            <th>등급</th>
                            <th>주문번호</th>
                            <th>구매확정일자</th>
                            <th>지급포인트</th>
                            <th>차감포인트</th>
                            <th>현재포인트</th>
                            <th>관리</th>
                            
                        </tr>

                        <tr>
                           
                            <td>1</td>
                            <td>홍길동</td>
                            <td>hong1234</td>
                            <td>gold</td>
                            <td>주문번호112345</td>
                            <td>2023.09.13.</td>
                            <td>
                                <input type="text" name="savePoint" value="8,800"/>
                                <span>8,800</span>
                            </td>
                            <td>
                                <input type="text" name="usedPoint" value="1,100"/>
                                <span>1,100</span>
                            </td>
                            <td>7,700</td>
                            <td>
                                <a href="#" id="pointChange">[포인트변경]</a>
                            </td>
                        </tr>
                    </table>   
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
                    구매확정 시 포인트 지급 및 차감. 포인트변경 클릭시 지급, 차감포인트 수정 가능.
                </p>

                

                <!-- 포인트 컨텐츠 끝 -->
            </section>
        </main>
<%@ include file="../_footer.jsp" %>
