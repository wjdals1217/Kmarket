<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp"%>
<!-- 
	날짜 : 2023-09-18
	이름 : 최정민
	내용 : 상품 수정
 -->
<!-- 
	날짜 : 2023-09-19
	이름 : 최정민
	내용 : 상품 수정
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
    	
    	
    });
</script>
            <section id="seller-product-register">
                <nav>
                    <h3>상품수정</h3>
                    <p>
                        HOME > 상품관리 > <strong>상품수정</strong>
                    </p>
                </nav>
                <!-- 상품등록 컨텐츠 시작 -->
                <article>
                    <form id="formModify" action="/Kmarket/seller/product/modify.do?oriCate1=${oriCate1}&oriCate2=${oriCate2}" method="post" enctype="multipart/form-data">
                    	<input type="hidden" name="prodNo" value="${product.prodNo}"/>
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
                                        	<option value="${product.prodCate1}">${product.c1Name}</option>
	                                        <c:forEach var="cate1" items="${cate1s}">
	                                        	<c:choose>
	                                        		<c:when test="${product.prodCate1 ne cate1.cate1}">
	                                        		<option value="${cate1.cate1}">${cate1.c1Name}</option>
	                                        		</c:when>
	                                        	</c:choose>
	                                        </c:forEach>                                                
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2차 분류</td>
                                    <td>
                                        <select required name="prodCate2" id="cate2">
                                        	<option>${product.c2Name}</option>
                                        <c:forEach var="cate2" items="${cate2s}">
                                        	<c:choose>
	                                        		<c:when test="${product.prodCate2 ne cate2.cate2}">
	                                        		<option value="${cate2.cate2}">${cate2.c2Name}</option>
	                                        		</c:when>
	                                        	</c:choose>
                                        </c:forEach>
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
                                    <td><input required type="text" name="prodName" value="${product.prodName}"/></td>
                                </tr>
                                <tr>
                                    <td>기본설명</td>
                                    <td>
                                        <span>상품명 하단에 상품에 대한 추가적인 설명이 필요한 경우에 입력</span>
                                        <input required type="text" name="descript" value="${product.descript}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>제조사</td>
                                    <td><input required type="text" name="company" value="${product.company}"/></td>
                                </tr>
                                <tr>
                                    <td>판매가격</td>
                                    <td><input required type="text" name="price" value="${product.price}"/>원</td>
                                </tr>                                    
                                <tr>
                                    <td>할인율</td>
                                    <td>
                                        <span>0을 입력하면 할인율 없음</span>
                                        <input required type="text" name="discount" value="${product.discount}"/>원
                                    </td>
                                </tr>
                                <tr>
                                    <td>포인트</td>
                                    <td>
                                        <span>0을 입력하면 포인트 없음</span>
                                        <input required type="text" name="point" value="${product.point}"/>점
                                    </td>
                                </tr>
                                <tr>
                                    <td>재고수량</td>
                                    <td><input type="text" required name="stock" value="${product.stock}"/>개</td>
                                </tr>
                                <tr>
                                    <td>배송비</td>
                                    <td>
                                        <span>0을 입력하면 배송비 무료</span>
                                        <input required type="text" name="delivery" value="${product.delivery}"/>원
                                    </td>
                                </tr>
                                <tr>
                                    <td>상품 썸네일</td>
                                    <td>
                                        <span>크기 190 x 190, 상품 목록에 출력될 이미지 입니다. </span>
                                        <input type="file" name="thumb1"/>
                                        <input type="hidden" name="oName1" value="${product.thumb1}"/>
                                        <input type="hidden" name="oriThumb1" value="${product.newThumb1}"/>

                                        <span>크기 230 x 230, 상품 메인에 출력될 이미지 입니다. </span>
                                        <input type="file" name="thumb2"/>
                                        <input type="hidden" name="oName2" value="${product.thumb2}"/>
                                        <input type="hidden" name="oriThumb2" value="${product.newThumb2}"/>

                                        <span>크기 456 x 456, 상품 상세에 출력될 이미지 입니다. </span>
                                        <input type="file" name="thumb3"/>
                                        <input type="hidden" name="oName3" value="${product.thumb3}"/>
                                        <input type="hidden" name="oriThumb3" value="${product.newThumb3}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>상세 상품정보</td>
                                    <td>
                                        <span>크기 가로 940px 높이 제약없음, 크기 최대 1MB, 상세페이지 상품정보에 출력될 이미지 입니다.</span>
                                        <input type="file" name="detail"/>
                                        <input type="hidden" name="oName4" value="${product.detail}"/>
                                        <input type="hidden" name="oriDetail" value="${product.newDetail}"/>
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
                                    <td><input type="text" name="status" value="${product.status}"/></td>
                                </tr>
                                <tr>
                                    <td>부가세 면세여부</td>
                                    <td><input type="text" name="duty" value="${product.duty}"/></td>
                                </tr>
                                <tr>
                                    <td>영수증발행</td>
                                    <td><input type="text" name="receipt" value="${product.receipt}"/></td>
                                </tr>
                                <tr>
                                    <td>사업자구분</td>
                                    <td><input type="text" name="bizType" value="${product.bizType}"/></td>
                                </tr>                                
                                <tr>
                                    <td>원산지</td>
                                    <td><input type="text" name="origin" value="${product.origin}"/></td>
                                </tr>                                
                            </table>                                
                        </section>
                        
                        <input type="submit" value="수정완료"/>
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