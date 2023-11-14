<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
    const price = ${product.price - (product.price * (product.discount / 100))};
    const delivery = ${product.delivery};
    console.log(price);
    console.log(delivery);
    
    
    window.onload = function () {
    	const inputNum = document.querySelector('input[name="num"]');
    	const inputCountIncrease = document.getElementsByClassName('increase')[0];
    	const inputCount = document.getElementsByName('count')[0];
    	const inputCountDecrease = document.getElementsByClassName('decrease')[0];
    	const inputTotal = document.getElementsByName('total')[0];
    	const inputFinal = document.getElementsByName('final')[0];
    	const formCart = document.getElementById('formCart');
    	const formOrder = document.getElementById('formOrder');

    	// 이벤트 리스너에서 'count' 입력 필드의 값을 업데이트합니다.
    	inputCountIncrease.addEventListener('click', function () {
    	    // 현재 값을 가져옴
    	    let currentValue = parseInt(inputNum.value, 10);

    	    // 값을 증가시킴
    	    currentValue++;

    	    // 변경된 값을 텍스트 필드에 설정
    	    inputNum.value = currentValue;
    	    inputCount.value = currentValue; // 'count' 입력 필드 업데이트

    	    updateTotalAndFinal(currentValue);
    	});

    	inputCountDecrease.addEventListener('click', function () {
    	    // 현재 값을 가져옴
    	    let currentValue = parseInt(inputNum.value, 10);

    	    // 값을 감소시킴 (1보다 작아지지 않도록 확인)
    	    if (currentValue > 1) {
    	        currentValue--;
    	    }

    	    // 변경된 값을 텍스트 필드에 설정
    	    inputNum.value = currentValue;
    	    inputCount.value = currentValue; // 'count' 입력 필드 업데이트

    	    updateTotalAndFinal(currentValue);
    	});
        
        function updateTotalAndFinal(currentValue) {
        	let count = currentValue;
            let total = price * currentValue;
            let finalPrice = total + delivery;
            
            

            formCart.elements['count'].value = currentValue;
            formCart.elements['final'].value = finalPrice;

            formOrder.elements['count'].value = currentValue;
            formOrder.elements['final'].value = finalPrice;
            
            console.log(total);
            console.log(count);
            inputFinal.value =total;

            // id로 <span> 요소를 가져옵니다
            const totalSpan = document.getElementById('totalSpan');

            // <span> 요소의 내용을 finalPrice로 업데이트합니다
            totalSpan.textContent = total.toLocaleString();
          
        }
        
        
     // 장바구니 버튼을 클릭할 때 실행되는 함수
        function addToCart() {
            // 폼 요소를 가져옵니다.
            const form = document.getElementById('formCart');

            // 폼을 서버로 제출합니다.
            form.submit();
        }

        // 장바구니 버튼에 클릭 이벤트 리스너를 추가합니다.
        const cartButton = document.querySelector('.cart');
        cartButton.addEventListener('click', addToCart);

        // 구매하기 버튼 클릭
        function addToOrder() {
            const form = document.getElementById('formOrder');

            form.submit();
        }
        const orderButton = document.querySelector('.order');
        orderButton.addEventListener('click', addToOrder);
        
    };
</script>
<main id="product">
<!-- 
		날짜 : 2023/09/14
		이름 : 김무현
		내용 : include 작업
	 -->
<jsp:include page="../_aside.jsp"/>

    <!-- 상품 상세페이지 시작 -->
    <section class="view">

        <!-- 제목, 페이지 네비게이션 -->
        <jsp:include page="./_nav.jsp"/>

        <!-- 상품 전체 정보 내용 -->                
        <article class="info">
            <div class="image">
                <img src="${ctxPath}/thumb/${prodCate1}/${prodCate2}/${product.newThumb3}" alt="상품이미지"/>
            </div>
            <div class="summary">
                <nav>
                    <h1>${product.seller}</h1>
                    <h2>상품번호&nbsp;:&nbsp;<span>${product.prodNo}</span></h2>
                </nav>                        
                <nav>
                    <h3>${product.prodName}</h3>
                    <p>${product.descript}</p>
                    <h5 class="rating star4"><a href="#">상품평보기</a></h5>
                </nav>
                <nav>
                    <div class="org_price">
                        <del>${product.priceWithComma}</del>
                        <span>${product.discount}%</span>
                    </div>
                    <div class="dis_price">
                        <ins> <c:set var="discountedPrice" value="0" />
					    <c:choose>
					        <c:when test="${product.discount != 0}">
					            <c:set var="discountedPrice" value="${product.price - (product.price * (product.discount / 100))}"/>
					        </c:when>
					        <c:otherwise>
					            <c:set var="discountedPrice" value="${product.price}"/>
					        </c:otherwise>
					    </c:choose>
					    <fmt:formatNumber value="${discountedPrice}"/></ins>
                    </div>
                </nav>
                <nav>
                <c:choose>
                	<c:when test="${product.delivery == 0}">
                	 <span class="delivery">무료배송</span>
                	</c:when>
                	<c:otherwise>
                	<span class="delivery">배송비 ${product.delivery}</span>
                	</c:otherwise>
                </c:choose>
                    <span class="arrival">${deliveryDate}</span>
                    <span class="desc">본 상품은 국내배송만 가능합니다.</span>
                </nav>
                <nav>
                    <span class="card cardfree"><i class="card cardfree"></i>무이자할부</span>&nbsp;&nbsp;
                    <span class="card cardadd"><i class="card cardadd"></i>카드추가혜택</span>
                </nav>
                <nav>
                    <span class="origin">원산지-상세설명 참조</span>
                </nav>
                <img src="../img/vip_plcc_banner.png" alt="100원만 결제해도 1만원 적립!" class="banner" />
                
                <div class="count">
                    <button class="decrease">-</button>
                    <input type="text" name="num" value="1" readonly/>
                    <button class="increase">+</button>
                </div>
                
                <div class="total">
                    <span id="totalSpan"><c:set var="discountedPrice" value="0" />
					    <c:choose>
					        <c:when test="${product.discount != 0}">
					            <c:set var="discountedPrice" value="${product.price - (product.price * (product.discount / 100))}"/>
					        </c:when>
					        <c:otherwise>
					            <c:set var="discountedPrice" value="${product.price}"/>
					        </c:otherwise>
					    </c:choose>
					    <fmt:formatNumber value="${discountedPrice}"/></span>
                    <em>총 상품금액</em>
                </div>
				<form id=formCart action="${ctxPath}/product/cart.do" method="post">
					<input type="hidden" name= "cate1" value="${product.prodCate1}">
					<input type="hidden" name= "cate2" value="${product.prodCate2}">
					<input type="hidden" name= "prodNo" value="${product.prodNo}">
					<input type="hidden" name= "uid" value="${sessUser.uid}">
					<input type="hidden" name= "newThumb1" value="${product.newThumb1}">
					<input type="hidden" name= "pName" value="${product.prodName}">
					<input type="hidden" name= "pDescript" value="${product.descript}">
					<input type="hidden" name= "count" value="1">
					<input type="hidden" name= "price" value="${product.price}">
					<input type="hidden" name= "discount" value="${product.discount}">
					<input type="hidden" name= "point" value="${product.point}">
					<input type="hidden" name= "delivery" value="${product.delivery}">
					<input type="hidden" name= "total" value="${product.price}">
					<input type="hidden" name= "final" value="${product.price + product.delivery}">
				</form>
				<form id=formOrder action="${ctxPath}/product/order.do" method="post">
					<input type="hidden" name= "prodNo" value="${product.prodNo}">
					<input type="hidden" name= "orduid" value="${sessUser.uid}">
					<input type="hidden" name= "recipName" value="${sessUser.name}">
					<input type="hidden" name= "recipHp" value="${sessUser.hp}">
					<input type="hidden" name= "recipZip" value="${sessUser.zip}">
					<input type="hidden" name= "recipAddr1" value="${sessUser.addr1}">
					<input type="hidden" name= "recipAddr2" value="${sessUser.addr2}">
					<input type="hidden" name= "savePoint" value="${sessUser.point}">
					<input type="hidden" name= "newThumb1" value="${product.newThumb1}">
					<input type="hidden" name= "pName" value="${product.prodName}">
					<input type="hidden" name= "pDescript" value="${product.descript}">
					<input type="hidden" name= "count" value="1">
					<input type="hidden" name= "price" value="${product.price}">
					<input type="hidden" name= "discount" value="${product.discount}">
					<input type="hidden" name= "point" value="${product.point}">
					<input type="hidden" name= "delivery" value="${product.delivery}">
					<input type="hidden" name= "total" value="${product.price}">
					<input type="hidden" name= "final" value="${product.price + product.delivery}">
				</form>
                <div class="button">
                    <input type="button" class="cart"  value="장바구니"/>
                    <input type="button" class="order" value="구매하기"/>
                </div>
            </div>
        </article>

        <!-- 상품 정보 내용 -->
        <article class="detail">
            <nav>
                <h1>상품정보</h1>
            </nav>
            <!-- 상품상세페이지 이미지 -->
            <img src="${ctxPath}/thumb/${product.prodCate1}/${product.prodCate2}/${product.newDetail}" alt="상세페이지1">
        </article>

        <!-- 상품 정보 제공 고시 내용 -->
        <article class="notice">
            <nav>
                <h1>상품 정보 제공 고시</h1>
                <p>[전자상거래에 관한 상품정보 제공에 관한 고시] 항목에 의거 등록된 정보입니다.</p>
            </nav>
            <table border="0">
                <tr>
                    <td>상품번호</td>
                    <td>${product.prodNo}</td>
                </tr>
                <tr>
                    <td>상품상태</td>
                    <td>${product.status}</td>
                </tr>
                <tr>
                    <td>부가세 면세여부</td>
                    <td>${product.duty}</td>
                </tr>
                <tr>
                    <td>영수증발행</td>
                    <td>${product.receipt}</td>
                </tr>
                <tr>
                    <td>사업자구분</td>
                    <td>${product.bizType}</td>
                </tr>
                <tr>
                    <td>브랜드</td>
                    <td>${product.company}</td>
                </tr>
                <tr>
                    <td>원산지</td>
                    <td>${product.origin}</td>
                </tr>
            </table>
            <table border="0">
                <tr>
                    <td>제품소재</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>색상</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>치수</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>제조자/수입국</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>제조국</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>취급시 주의사항</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>제조연월</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>품질보증기준</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>A/S 책임자와 전화번호</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>주문후 예상 배송기간</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                <td colspan="2">구매, 교환, 반품, 배송, 설치 등과 관련하여 추가비용, 제한조건 등의 특이사항이 있는 경우</td>
                </tr>
            </table>
            <p class="notice">
                소비자가 전자상거래등에서 소비자 보호에 관한 법률 제 17조 제1항 또는 제3항에 따라 청약철회를 하고
                동법 제 18조 제1항 에 따라 청약철회한 물품을 판매자에게 반환하였음에도 불구 하고 결제 대금의
                환급이 3영업일을 넘게 지연된 경우, 소비자 는 전자상거래등에서 소비자보호에 관한 법률 제18조
                제2항 및 동법 시행령 제21조 2에 따라 지연일수에 대하여 전상법 시행령으로 정하는 이율을 곱하여
                산정한 지연이자(“지연배상금”)를 신청할 수 있습니다. 아울러, 교환∙반품∙보증 및 결제대금의
                환급신청은 [나의쇼핑정보]에서 하실 수 있으며, 자세한 문의는 개별 판매자에게 연락하여 주시기 바랍니다.
            </p>
        </article>
        
        <!-- 상품 리뷰 내용 -->
        <article class="review">
            <nav>
                <h1>상품리뷰</h1>
            </nav>
            <ul>
                <li>
                    <div>
                        <h5 class="rating star4">상품평</h5>
                        <span>seo******	2018-07-10</span>
                    </div>
                    <h3>상품명1/BLUE/L</h3>
                    <p>
                        가격대비 정말 괜찮은 옷이라 생각되네요 핏은 음...제가 입기엔 어깨선이 맞고 루즈핏이라 하기도 좀 힘드네요.
                        아주 약간 루즈한정도...?그래도 이만한 옷은 없다고 봅니다 깨끗하고 포장도 괜찮고 다음에도 여기서 판매하는
                        제품들을 구매하고 싶네요 정말 만족하고 후기 남깁니다 많이 파시길 바래요 ~ ~ ~
                    </p>
                </li>
                <li>
                    <div>
                        <h5 class="rating star4">상품평</h5>
                        <span>seo******	2018-07-10</span>
                    </div>
                    <h3>상품명1/BLUE/L</h3>
                    <p>
                        가격대비 정말 괜찮은 옷이라 생각되네요 핏은 음...제가 입기엔 어깨선이 맞고 루즈핏이라 하기도 좀 힘드네요.
                        아주 약간 루즈한정도...?그래도 이만한 옷은 없다고 봅니다 깨끗하고 포장도 괜찮고 다음에도 여기서 판매하는
                        제품들을 구매하고 싶네요 정말 만족하고 후기 남깁니다 많이 파시길 바래요 ~ ~ ~
                    </p>
                </li>
                <li>
                    <div>
                        <h5 class="rating star4">상품평</h5>
                        <span>seo******	2018-07-10</span>
                    </div>
                    <h3>상품명1/BLUE/L</h3>
                    <p>
                        가격대비 정말 괜찮은 옷이라 생각되네요 핏은 음...제가 입기엔 어깨선이 맞고 루즈핏이라 하기도 좀 힘드네요.
                        아주 약간 루즈한정도...?그래도 이만한 옷은 없다고 봅니다 깨끗하고 포장도 괜찮고 다음에도 여기서 판매하는
                        제품들을 구매하고 싶네요 정말 만족하고 후기 남깁니다 많이 파시길 바래요 ~ ~ ~
                    </p>
                </li>
                <li>
                    <div>
                        <h5 class="rating star4">상품평</h5>
                        <span>seo******	2018-07-10</span>
                    </div>
                    <h3>상품명1/BLUE/L</h3>
                    <p>
                        가격대비 정말 괜찮은 옷이라 생각되네요 핏은 음...제가 입기엔 어깨선이 맞고 루즈핏이라 하기도 좀 힘드네요.
                        아주 약간 루즈한정도...?그래도 이만한 옷은 없다고 봅니다 깨끗하고 포장도 괜찮고 다음에도 여기서 판매하는
                        제품들을 구매하고 싶네요 정말 만족하고 후기 남깁니다 많이 파시길 바래요 ~ ~ ~
                    </p>
                </li>
                <li>
                    <div>
                        <h5 class="rating star4">상품평</h5>
                        <span>seo******	2018-07-10</span>
                    </div>
                    <h3>상품명1/BLUE/L</h3>
                    <p>
                        가격대비 정말 괜찮은 옷이라 생각되네요 핏은 음...제가 입기엔 어깨선이 맞고 루즈핏이라 하기도 좀 힘드네요.
                        아주 약간 루즈한정도...?그래도 이만한 옷은 없다고 봅니다 깨끗하고 포장도 괜찮고 다음에도 여기서 판매하는
                        제품들을 구매하고 싶네요 정말 만족하고 후기 남깁니다 많이 파시길 바래요 ~ ~ ~
                    </p>
                </li>
            </ul>
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

        </article>
        
    </section>
    <!-- 상품 상세페이지 끝 -->
</main>
<%@ include file="../_footer.jsp" %>