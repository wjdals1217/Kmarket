<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp" %>
             <script>
		        $(function() {
		            $("#tabs").tabs();
		
		            // 팝업 닫기
		            $('.btnClose').click(function(){                
		                $(this).closest('.popup').removeClass('on');                
		            });
		
		            // 배너등록 팝업 띄우기
		            $('.btnRegister').click(function(e){
		                e.preventDefault();
		                $('#bannerRegister').addClass('on');
		            });
		        });
		        
		    </script>
            <section id="admin-config-banner">
                <nav>
                    <h3>디자인관리</h3>
                    <p>
                        HOME > 환경설정 > <strong>디자인관리</strong>
                    </p>
                </nav>
                
                <article id="tabs">
                    <ul>
                        <li><a href="#tabs1">메인 상단 배너</a></li>
                        <li><a href="#tabs2">메인 슬라이더 배너</a></li>
                        <li><a href="#tabs3">상품 상세보기 배너</a></li>
                        <li><a href="#tabs4">회원 로그인 배너</a></li>
                        <li><a href="#tabs5">마이페이지 배너</a></li>
                    </ul>

                    <!-- 메인 상단 배너-->
                    <section id="tabs1">
                        <h4>메인 상단배너</h4>
                        <p>
                            쇼핑몰 메인 상단에 출력되는 배너입니다.
                        </p>                    
                        <table>
                            <tr>
                                <th><input type="checkbox" name="all"/></th>
                                <th>배너번호</th>
                                <th>이미지</th>                            
                                <th>배너정보</th>
                                <th>배너위치</th>
                                <th>시작날짜</th>
                                <th>종료날짜</th>
                                <th>시작시간</th>
                                <th>종료시간</th>
                                <th>조회</th>
                                <th>관리</th>
                            </tr>

                            <tr>
                                <td><input type="checkbox" name="배너번호"/></td>
                                <td>201603292</td>
                                <td><img src="../../img/topBanner1.png" class="thumb"></td>                            
                                <td>
                                    <ul>
                                        <li class="name">배너이름</li>
                                        <li class="size">크기 : 1200 x 80</li>
                                        <li class="size">배경색 : #e4dfdf</li>
                                        <li class="link"><a href="#">배너링크</a></li>
                                    </ul>
                                </td>
                                <td>메인 - 상단</td>
                                <td>2023/01/01</td>
                                <td>2023/01/07</td>
                                <td>13:00</td>
                                <td>14:00</td>
                                <td>12</td>
                                <td>
                                    <a href="#">[비활성]</a>
                                    <a href="#">[활성]</a>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="배너번호"/></td>
                                <td>201603292</td>
                                <td><img src="../../img/topBanner2.png" class="thumb"></td>                            
                                <td>
                                    <ul>
                                        <li class="name">배너이름</li>
                                        <li class="size">크기 : 1200 x 80</li>
                                        <li class="size">배경색 : #e4dfdf</li>
                                        <li class="link"><a href="#">배너링크</a></li>
                                    </ul>
                                </td>
                                <td>메인 - 상단</td>
                                <td>2023/01/01</td>
                                <td>2023/01/07</td>
                                <td>13:00</td>
                                <td>14:00</td>
                                <td>12</td>
                                <td>
                                    <a href="#">[비활성]</a>
                                    <a href="#">[활성]</a>
                                </td>
                            </tr>
                        </table>
                        <input type="button" class="btnNegative" value="선택삭제"/>
                        <input type="button" class="btnPositive btnRegister" value="등록하기"/>
                    
                    </section>

                    <!-- 메인 슬라이더 배너-->
                    <section id="tabs2">
                        <h4>메인 슬라이더배너</h4>
                        <p>
                            쇼핑몰 메인 슬라이더 영역에 노출되는 배너입니다.
                        </p>                    
                        <table>
                            <tr>
                                <th><input type="checkbox" name="all"/></th>
                                <th>배너번호</th>
                                <th>이미지</th>                            
                                <th>배너정보</th>
                                <th>배너위치</th>
                                <th>시작날짜</th>
                                <th>종료날짜</th>
                                <th>시작시간</th>
                                <th>종료시간</th>
                                <th>조회</th>
                                <th>관리</th>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="배너번호"/></td>
                                <td>201603292</td>
                                <td><img src="../../img/slider_item1.jpg" class="thumb"></td>                            
                                <td>
                                    <ul>
                                        <li class="name">배너이름</li>
                                        <li class="size">크기 : 985 x 447</li>
                                        <li class="link"><a href="#">배너링크</a></li>
                                    </ul>                                
                                </td>
                                <td>메인 - 슬라이더</td>
                                <td>2023/01/01</td>
                                <td>2023/01/07</td>
                                <td>13:00</td>
                                <td>14:00</td>
                                <td>12</td>
                                <td>
                                    <a href="#">[비활성]</a>
                                    <a href="#">[활성]</a>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="배너번호"/></td>
                                <td>201603292</td>
                                <td><img src="../../img/slider_item2.jpg" class="thumb"></td>                            
                                <td>
                                    <ul>
                                        <li class="name">배너이름</li>
                                        <li class="size">크기 : 985 x 447</li>
                                        <li class="link"><a href="#">배너링크</a></li>
                                    </ul>                                
                                </td>
                                <td>메인 - 슬라이더</td>
                                <td>2023/01/01</td>
                                <td>2023/01/07</td>
                                <td>13:00</td>
                                <td>14:00</td>
                                <td>12</td>
                                <td>
                                    <a href="#">[비활성]</a>
                                    <a href="#">[활성]</a>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="배너번호"/></td>
                                <td>201603292</td>
                                <td><img src="../../img/slider_item3.jpg" class="thumb"></td>                            
                                <td>
                                    <ul>
                                        <li class="name">배너이름</li>
                                        <li class="size">크기 : 985 x 447</li>
                                        <li class="link"><a href="#">배너링크</a></li>
                                    </ul>                                
                                </td>
                                <td>메인 - 슬라이더</td>
                                <td>2023/01/01</td>
                                <td>2023/01/07</td>
                                <td>13:00</td>
                                <td>14:00</td>
                                <td>12</td>
                                <td>
                                    <a href="#">[비활성]</a>
                                    <a href="#">[활성]</a>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="배너번호"/></td>
                                <td>201603292</td>
                                <td><img src="../../img/slider_item4.jpg" class="thumb"></td>                            
                                <td>
                                    <ul>
                                        <li class="name">배너이름</li>
                                        <li class="size">크기 : 985 x 447</li>
                                        <li class="link"><a href="#">배너링크</a></li>
                                    </ul>                                
                                </td>
                                <td>메인 - 슬라이더</td>
                                <td>2023/01/01</td>
                                <td>2023/01/07</td>
                                <td>13:00</td>
                                <td>14:00</td>
                                <td>12</td>
                                <td>
                                    <a href="#">[비활성]</a>
                                    <a href="#">[활성]</a>
                                </td>
                            </tr>
                        </table>
                        <input type="button" class="btnNegative" value="선택삭제"/>
                        <input type="button" class="btnPositive btnRegister" value="등록하기"/>
                    </section>

                    <!-- 상품 상세보기 배너-->
                    <section id="tabs3">
                        <h4>상품 상세보기 배너</h4>
                        <p>
                            상품 상세보기 상품수량 입력 상단부에 노출되는 배너입니다.
                        </p>                    
                        <table>
                            <tr>
                                <th><input type="checkbox" name="all"/></th>
                                <th>배너번호</th>
                                <th>이미지</th>                            
                                <th>배너정보</th>
                                <th>배너위치</th>
                                <th>시작날짜</th>
                                <th>종료날짜</th>
                                <th>시작시간</th>
                                <th>종료시간</th>
                                <th>조회</th>
                                <th>관리</th>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="배너번호"/></td>
                                <td>201603292</td>
                                <td><img src="../../img/vip_plcc_banner.png" class="thumb"></td>                            
                                <td>
                                    <ul>
                                        <li class="name">배너이름</li>
                                        <li class="size">크기 : 456 x 60</li>
                                        <li class="link"><a href="#">배너링크</a></li>
                                    </ul>                                
                                </td>
                                <td>상품 - 상세보기</td>
                                <td>2023/01/01</td>
                                <td>2023/01/07</td>
                                <td>13:00</td>
                                <td>14:00</td>
                                <td>12</td>
                                <td>
                                    <a href="#">[비활성]</a>
                                    <a href="#">[활성]</a>
                                </td>
                            </tr>
                        </table>
                        <input type="button" class="btnNegative" value="선택삭제"/>
                        <input type="button" class="btnPositive btnRegister" value="등록하기"/>
                    </section>

                    <!-- 회원 로그인 배너-->
                    <section id="tabs4">
                        <h4>회원 로그인 배너</h4>
                        <p>
                            회원 로그인 폼 오른쪽에 노출되는 배너입니다.
                        </p>                    
                        <table>
                            <tr>
                                <th><input type="checkbox" name="all"/></th>
                                <th>배너번호</th>
                                <th>이미지</th>                            
                                <th>배너정보</th>
                                <th>배너위치</th>
                                <th>시작날짜</th>
                                <th>종료날짜</th>
                                <th>시작시간</th>
                                <th>종료시간</th>
                                <th>조회</th>
                                <th>관리</th>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="배너번호"/></td>
                                <td>201603292</td>
                                <td><img src="/member/img/member_login_banner.jpg" class="thumb"></td>                            
                                <td>
                                    <ul>
                                        <li class="name">배너이름</li>
                                        <li class="size">크기 : 425 x 260</li>
                                        <li class="link"><a href="#">배너링크</a></li>
                                    </ul>                                
                                </td>
                                <td>회원 - 로그인</td>
                                <td>2023/01/01</td>
                                <td>2023/01/07</td>
                                <td>13:00</td>
                                <td>14:00</td>
                                <td>12</td>
                                <td>
                                    <a href="#">[비활성]</a>
                                    <a href="#">[활성]</a>
                                </td>
                            </tr>
                        </table>
                        <input type="button" class="btnNegative" value="선택삭제"/>
                        <input type="button" class="btnPositive btnRegister" value="등록하기"/>
                    </section>

                    <!-- 마이페이지 배너-->
                    <section id="tabs5">
                        <h4>마이페이지 배너</h4>
                        <p>
                            마이페이지 하위 모든 페이지에서 노출되는 배너입니다.
                        </p>                    
                        <table>
                            <tr>
                                <th><input type="checkbox" name="all"/></th>
                                <th>배너번호</th>
                                <th>이미지</th>                            
                                <th>배너정보</th>
                                <th>배너위치</th>
                                <th>시작날짜</th>
                                <th>종료날짜</th>
                                <th>시작시간</th>
                                <th>종료시간</th>
                                <th>조회</th>
                                <th>관리</th>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="배너번호"/></td>
                                <td>201603292</td>
                                <td><img src="/my/img/my_banner1.jpg" class="thumb"></td>                            
                                <td>
                                    <ul>
                                        <li class="name">배너이름</li>
                                        <li class="size">크기 : 810 x 86</li>
                                        <li class="link"><a href="#">배너링크</a></li>
                                    </ul>                                
                                </td>
                                <td>마이페이지</td>
                                <td>2023/01/01</td>
                                <td>2023/01/07</td>
                                <td>13:00</td>
                                <td>14:00</td>
                                <td>12</td>
                                <td>
                                    <a href="#">[비활성]</a>
                                    <a href="#">[활성]</a>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="배너번호"/></td>
                                <td>201603292</td>
                                <td><img src="/my/img/my_banner2.png" class="thumb"></td>                            
                                <td>
                                    <ul>
                                        <li class="name">배너이름</li>
                                        <li class="size">크기 : 810 x 86</li>
                                        <li class="link"><a href="#">배너링크</a></li>
                                    </ul>                                
                                </td>
                                <td>마이페이지</td>
                                <td>2023/01/01</td>
                                <td>2023/01/07</td>
                                <td>13:00</td>
                                <td>14:00</td>
                                <td>12</td>
                                <td>
                                    <a href="#">[비활성]</a>
                                    <a href="#">[활성]</a>
                                </td>
                            </tr>
                        </table>
                        <input type="button" class="btnNegative" value="선택삭제"/>
                        <input type="button" class="btnPositive btnRegister" value="등록하기"/>
                    </section>
                </article>
                <p class="ico info">
                    <strong>Tip!</strong>
                    배너 종료 시간을 준수해 주세요.
                </p>
            </section>
        </main>
            <div id="bannerRegister" class="popup">
		        <div>
		            <nav>
		                <h1>배너등록</h1>
		                <button class="btnClose">X</button>
		            </nav>
		            <section>
		                <article>
		                    <h3>배너 정보입력</h3>
		                    <form action="#" method="post">
		                        <table border="0">
		                            <tr>
		                                <td>배너이름</td>
		                                <td>
		                                    <p>배너명을 입력하세요.</p>
		                                    <input type="text" name="name" placeholder="배너명 입력"/>
		                                </td>
		                            </tr>
		                            <tr>
		                                <td>배너파일</td>
		                                <td>
		                                    <p>배너 이미지 파일을 추가하세요.</p>
		                                    <input type="file" name="file" placeholder="배너 이미지 파일 선택"/>
		                                </td>
		                            </tr>
		                            <tr>
		                                <td>배너크기</td>
		                                <td>
		                                    <p>배너의 크기를 입력하세요.</p>
		                                    <input type="text" name="size" placeholder="배너크기"/>                                    
		                                </td>
		                            </tr>
		                            <tr>
		                                <td>배경색</td>
		                                <td>                                    
		                                    <p>메인 상단 배너에 적용되는 배너 배경색입니다. ex) #ffff00</p>
		                                    <input type="text" name="color" placeholder="배너 배경색 입력"/>                                    
		                                </td>
		                            </tr>
		                            <tr>
		                                <td>배너링크</td>
		                                <td>
		                                    <p>배너를 클릭했을 떄 이동할 페이지 링크주소를 입력하세요.</p>
		                                    <input type="text" name="link" placeholder="링크주소 입력"/>                                    
		                                </td>
		                            </tr>
		                            <tr>
		                                <td>배너위치</td>
		                                <td>                                    
		                                    <p>배너가 노출되는 위치를 선택하십시요.</p>
		                                    <select name="position">
		                                        <option value="0">선택</option>
		                                        <option value="MAIN1">메인-상단</option>
		                                        <option value="MAIN2">메인-슬라이더</option>
		                                        <option value="PRODUCT1">상품-상세보기</option>
		                                        <option value="MEMBER1">회원-로그인</option>
		                                        <option value="MY1">마이페이지</option>
		                                    </select>
		                                </td>
		                            </tr>
		                            <tr>
		                                <td>배너 노출날짜</td>
		                                <td>                                    
		                                    <p>배너가 노출되는 기간을 선택하십시요.</p>
		                                    <input type="date" name="begin"/>
		                                    <span>~</span>
		                                    <input type="date" name="end"/>
		                                </td>
		                            </tr>
		                            <tr>
		                                <td>배너 노출시간</td>
		                                <td>                                    
		                                    <p>배너가 노출되는 시간을 선택하십시요.</p>
		                                    <input type="time" name="begin"/>
		                                    <span>~</span>
		                                    <input type="time" name="end"/>
		                                </td>
		                            </tr>
		                        </table>
		
		                        <div>                            
		                            <input type="submit" class="btnPositive" value="등록하기"/>
		                        </div>
		                        
		                    </form>
		                </article>
		            </section>
		        </div>
		    </div>
		<!-- Code injected by live-server -->
		<script>
			// <![CDATA[  <-- For SVG support
			if ('WebSocket' in window) {
				(function () {
					function refreshCSS() {
						var sheets = [].slice.call(document.getElementsByTagName("link"));
						var head = document.getElementsByTagName("head")[0];
						for (var i = 0; i < sheets.length; ++i) {
							var elem = sheets[i];
							var parent = elem.parentElement || head;
							parent.removeChild(elem);
							var rel = elem.rel;
							if (elem.href && typeof rel != "string" || rel.length == 0 || rel.toLowerCase() == "stylesheet") {
								var url = elem.href.replace(/(&|\?)_cacheOverride=\d+/, '');
								elem.href = url + (url.indexOf('?') >= 0 ? '&' : '?') + '_cacheOverride=' + (new Date().valueOf());
							}
							parent.appendChild(elem);
						}
					}
					var protocol = window.location.protocol === 'http:' ? 'ws://' : 'wss://';
					var address = protocol + window.location.host + window.location.pathname + '/ws';
					var socket = new WebSocket(address);
					socket.onmessage = function (msg) {
						if (msg.data == 'reload') window.location.reload();
						else if (msg.data == 'refreshcss') refreshCSS();
					};
					if (sessionStorage && !sessionStorage.getItem('IsThisFirstTime_Log_From_LiveServer')) {
						console.log('Live reload enabled.');
						sessionStorage.setItem('IsThisFirstTime_Log_From_LiveServer', true);
					}
				})();
			}
			else {
				console.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');
			}
			// ]]>
		</script>
<%@ include file="../_footer.jsp" %>