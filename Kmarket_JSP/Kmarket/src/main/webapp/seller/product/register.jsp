<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp"%>
<!-- 
	날짜 : 2023-09-14
	이름 : 최정민
	내용 : 상품 등록
 -->
<script>
    function changeSelect() {
        const selectValue = $('#selectBox').val();

        const jsonData = {
            "selectValue": selectValue
        };

        $.ajax({
            url: '/Kmarket/seller/product/cate2.do',
            type: 'GET',
            data: jsonData,
            dataType: 'json',
            success: function(data) {
                console.log(data);

                const cate2Select = $('#cate2');
                cate2Select.empty(); // 기존 옵션을 모두 제거합니다.

                // 새로운 옵션 추가
                cate2Select.append($('<option>', {
                    value: '',
                    text: '2차 분류 선택',
                    disabled: 'disabled',
                    selected: 'selected'
                }));

                // 데이터를 이용하여 옵션을 동적으로 생성
                $.each(data, function(index, item) {
                    cate2Select.append($('<option>', {
                        value: item.cate2,
                        text: item.c2Name
                    }));
                });
            }
        });
    }
    
    $(function(){
    	
    	$('select[name=prodCate2]').change(function(){
    		
    		let cate1 = $('select[name=prodCate1]').val();
    		let cate2 = $(this).val();
    		
    		
    		let actionUrl = "/Kmarket/seller/product/register.do?cate1="+cate1+"&cate2="+cate2;
    		$('#formRegister').attr('action', actionUrl);
    		
    		
    	});
    	
    	$('input[name=price]').focusout(function(){
    		const price = $(this).val();
    		const point = $('input[name=point]');
    		
    		point.val(price * 0.01);
    	});
    	
    });
    
  
    
</script>
            <section id="seller-product-register">
                <nav>
                    <h3>상품등록</h3>
                    <p>
                        HOME > 상품관리 > <strong>상품등록</strong>
                    </p>
                </nav>
                <!-- 상품등록 컨텐츠 시작 -->
                <article>
                    <form id="formRegister" action="/Kmarket/seller/product/register.do" method="post" enctype="multipart/form-data">
						<input type="hidden" name="seller" value="${sessUser.uid}"/>
                        <!-- 상품분류 -->
                        <section>
                            <h4>상품분류</h4>
                            <p>
                                기본분류는 반드시 선택하셔야 합니다. 하나의 상품에 1개의 분류를 지정 합니다.
                            </p>
                            <table>
                                <tr>
                                    <td>1차 분류</td>
                                    <td>
                                        <select id="selectBox" required name="prodCate1" onchange="changeSelect()">
                                        	<option selected disabled>1차 분류 선택</option>
	                                        <c:forEach var="cate1" items="${cate1s}">
	                                        	<option value="${cate1.cate1}">${cate1.c1Name}</option>
	                                        </c:forEach>                                                
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2차 분류</td>
                                    <td>
                                        <select required name="prodCate2" id="cate2">
                                        <option selected disabled>2차 분류 선택</option>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </section>

                        <!-- 기본정보 -->
                        <section>
                            <h4>기본정보</h4>
                            <p>
                                기본정보는 반드시 입력해야 합니다.
                            </p>
                            <table>
                                <tr>
                                    <td>상품명</td>
                                    <td><input required type="text" name="prodName"/></td>
                                </tr>
                                <tr>
                                    <td>기본설명</td>
                                    <td>
                                        <span>상품명 하단에 상품에 대한 추가적인 설명이 필요한 경우에 입력</span>
                                        <input required type="text" name="descript"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>제조사</td>
                                    <td><input required type="text" name="company"/></td>
                                </tr>
                                <tr>
                                    <td>판매가격</td>
                                    <td><input type="text" name="price"/>원</td>
                                </tr>                                    
                                <tr>
                                    <td>할인율</td>
                                    <td>
                                        <span>0을 입력하면 할인율 없음</span>
                                        <input type="text" name="discount" value="0"/>%
                                    </td>
                                </tr>
                                <tr>
                                    <td>포인트</td>
                                    <td>
                                        <span>0을 입력하면 포인트 없음</span>
                                        <input type="text" name="point" value="0"/>점
                                    </td>
                                </tr>
                                <tr>
                                    <td>재고수량</td>
                                    <td><input type="text" required name="stock"/>개</td>
                                </tr>
                                <tr>
                                    <td>배송비</td>
                                    <td>
                                        <span>0을 입력하면 배송비 무료</span>
                                        <input type="text" name="delivery" value="0"/>원
                                    </td>
                                </tr>
                                <tr>
                                    <td>상품 썸네일</td>
                                    <td>
                                        <span>크기 190 x 190, 상품 목록에 출력될 이미지 입니다. </span>
                                        <input required type="file" name="thumb1"/>

                                        <span>크기 230 x 230, 상품 메인에 출력될 이미지 입니다. </span>
                                        <input required type="file" name="thumb2"/>

                                        <span>크기 456 x 456, 상품 상세에 출력될 이미지 입니다. </span>
                                        <input required type="file" name="thumb3"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>상세 상품정보</td>
                                    <td>
                                        <span>크기 가로 940px 높이 제약없음, 크기 최대 1MB, 상세페이지 상품정보에 출력될 이미지 입니다.</span>
                                        <input required type="file" name="detail"/>
                                    </td>
                                </tr>
                            </table>                                
                        </section>

                        <!-- 상품정보 제공 고시 -->
                        <section>
                            <h4>상품정보 제공고시</h4>
                            <p>
                                [전자상거래에 관한 상품정보 제공에 관한 고시] 항목에 의거 등록해야 되는 정보입니다.
                            </p>
                            <table>
                                <tr>
                                    <td>상품상태</td>
                                    <td><input type="text" name="status" value="새상품"/></td>
                                </tr>
                                <tr>
                                    <td>부가세 면세여부</td>
                                    <td><input type="text" name="duty" value="과세상품"/></td>
                                </tr>
                                <tr>
                                    <td>영수증발행</td>
                                    <td><input type="text" name="receipt" value="발행가능 - 신용카드 전표, 온라인 현금영수증"/></td>
                                </tr>
                                <tr>
                                    <td>사업자구분</td>
                                    <td><input type="text" name="bizType" value="사업자 판매자"/></td>
                                </tr>                                
                                <tr>
                                    <td>원산지</td>
                                    <td><input type="text" name="origin" value="국내산"/></td>
                                </tr>                                
                            </table>                                
                        </section>
                        
                        <input type="submit" value="등록하기"/>
                    </form>
                </article>

                <p class="ico alert">
                    <strong>Warning!</strong>
                    전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
                </p>
                <!-- 상품등록 컨텐츠 끝 -->
            </section>
        </main>
<%@ include file="../_footer.jsp" %>