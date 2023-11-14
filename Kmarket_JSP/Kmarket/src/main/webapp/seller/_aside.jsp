<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<aside>
                <!-- Global Navigation Bar -->
                <ul id="gnb">
                    <li>
                        <a href="#"><i class="fas fa-box-open" aria-hidden="true"></i>상품관리</a>
                        <ol>
                            <li><a href="/Kmarket/seller/product/list.do?seller=${sessUser.company}">상품현황</a></li>
                            <li><a href="/Kmarket/seller/product/register.do">상품등록</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-credit-card" aria-hidden="true"></i>주문관리</a>
                        <ol>
                            <li><a href="/Kmarket/seller/order/order.do?seller=${sessUser.company}&sort=0">주문현황</a></li>
                            <li><a href="/Kmarket/seller/order/delivery.do?seller=${sessUser.company}&sort=0">취소/반품/교환</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>고객문의</a>
                        <ol>
                            <li><a href="/Kmarket/seller/board/qna.do">Kmarket사용문의</a></li>
                        </ol>
                    </li>
                </ul>
            </aside>