<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<aside>
                <!-- Global Navigation Bar -->
                <ul id="gnb">
                    <li>
                        <a href="#"><i class="fa fa-cogs" aria-hidden="true"></i>환경설정</a>
                        <ol>
                            <li><a href="${ctxPath}/admin/config/info.do">기본환경정보</a></li>
                            <li><a href="${ctxPath}/admin/config/userAdmin.do?type=3">관리자</a></li>
                            <li><a href="${ctxPath}/admin/config/banner.do">디자인관리</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-store" aria-hidden="true"></i>상점관리</a>
                        <ol>
                            <li><a href="${ctxPath}/admin/store/userSeller.do?type=2">판매자관리</a></li>
                            <li><a href="${ctxPath}/admin/store/list.do">상품관리</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-users" aria-hidden="true"></i>회원관리</a>
                        <ol>
                            <li><a href="${ctxPath}/admin/user/user.do?type=1&level=0">회원정보관리</a></li>
                            <li><a href="${ctxPath}/admin/user/point.do">포인트관리</a></li>
                            <li><a href="${ctxPath}/admin/user/accessorTotal.do">접속자집계</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>게시판관리</a>
                        <ol>
                            <li><a href="${ctxPath}/admin/board/notice.do?group=notice&cateDetail=all&type=1">공지사항</a></li>
                            <li><a href="${ctxPath}/admin/board/faq.do?group=faq&cateDetail=all&type=20">자주 묻는 질문</a></li>
                            <li><a href="${ctxPath}/admin/board/qna.do?group=qna&cateDetail=all&type=20">고객 문의</a></li>
                            <!-- all즉 모든 cateDEtail type에 구애 받지 않고 전부 다나오게 해야됨 -->
                        </ol>
                    </li>
                </ul>
            </aside>