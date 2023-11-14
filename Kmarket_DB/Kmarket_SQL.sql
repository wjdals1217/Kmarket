ALTER TABLE `km_product_cate2` RENAME COLUMN `km_product_cate1_cate1` TO cate1;

ALTER TABLE `km_product` AUTO_INCREMENT=1000000;
ALTER TABLE `km_product_order` AUTO_INCREMENT=1000000;

ALTER TABLE `km_product` MODIFY COLUMN `seller` VARCHAR(20) AFTER `prodNo`;
ALTER TABLE `km_product` MODIFY COLUMN `prodName` VARCHAR(100) AFTER `prodCate2`;
ALTER TABLE `km_product` MODIFY COLUMN `etc1` CHAR(1) AFTER `rdate`;

ALTER TABLE `km_cs_file` RENAME `km_file`;	

ALTER TABLE `km_product` ADD `newThumb1` VARCHAR(255) NOT NULL AFTER `thumb1`;	
ALTER TABLE `km_product` ADD `newThumb2` VARCHAR(255) NOT NULL AFTER `thumb2`;	
ALTER TABLE `km_product` ADD `newThumb3` VARCHAR(255) NOT NULL AFTER `thumb3`;	
ALTER TABLE `km_product` ADD `newDetail` VARCHAR(255) NOT NULL AFTER `detail`;	
ALTER TABLE `km_product_order` ADD `ordStatus` VARCHAR(45) AFTER `recipAddr2`;	
ALTER TABLE `km_product_order` ADD `deliveryStatus` VARCHAR(45) AFTER `ordComplete`;


INSERT INTO `km_product_cate1` VALUES(10, '브랜드패션');
INSERT INTO `km_product_cate1` VALUES(11, '패션의류·잡화·뷰티');
INSERT INTO `km_product_cate1` VALUES(12, '유아동');
INSERT INTO `km_product_cate1` VALUES(13, '식품·생필품');
INSERT INTO `km_product_cate1` VALUES(14, '홈데코·문구·취미·반려');
INSERT INTO `km_product_cate1` VALUES(15, '컴퓨터·디지털·가전');
INSERT INTO `km_product_cate1` VALUES(16, '스포츠·건강·렌탈');
INSERT INTO `km_product_cate1` VALUES(17, '자동차·공구');
INSERT INTO `km_product_cate1` VALUES(18, '여행·km_product_cate1도서·티켓·e쿠폰');

INSERT INTO `km_product_cate2` VALUES(10, '브랜드 여성의류', 10);
INSERT INTO `km_product_cate2` VALUES(11, '브랜드 남성의류', 10);
INSERT INTO `km_product_cate2` VALUES(12, '브랜드 진/캐쥬얼', 10);
INSERT INTO `km_product_cate2` VALUES(13, '브랜드 신발/가방', 10);
INSERT INTO `km_product_cate2` VALUES(14, '브랜드 쥬얼리/시계', 10);
INSERT INTO `km_product_cate2` VALUES(15, '브랜드 아웃도어', 10);

INSERT INTO `km_product_cate2` VALUES(10, '여성의류', 11);
INSERT INTO `km_product_cate2` VALUES(11, '남성의류', 11);
INSERT INTO `km_product_cate2` VALUES(12, '언더웨어', 11);
INSERT INTO `km_product_cate2` VALUES(13, '신발', 11);
INSERT INTO `km_product_cate2` VALUES(14, '가방/잡화', 11);
INSERT INTO `km_product_cate2` VALUES(15, '쥬얼리/시계', 11);

INSERT INTO `km_product_cate2` VALUES(10, '출산/육아', 12);
INSERT INTO `km_product_cate2` VALUES(11, '장난감/완구', 12);
INSERT INTO `km_product_cate2` VALUES(12, '유아동 의류', 12);
INSERT INTO `km_product_cate2` VALUES(13, '유아동 신발/잡화', 12);

INSERT INTO `km_product_cate2` VALUES(10, '신선식품', 13);
INSERT INTO `km_product_cate2` VALUES(11, '가공식품', 13);
INSERT INTO `km_product_cate2` VALUES(12, '건강식품', 13);
INSERT INTO `km_product_cate2` VALUES(13, '커피/음료', 13);
INSERT INTO `km_product_cate2` VALUES(14, '생필품', 13);
INSERT INTO `km_product_cate2` VALUES(15, '바디/헤어', 13);

INSERT INTO `km_product_cate2` VALUES(10, '가구/DIY', 14);
INSERT INTO `km_product_cate2` VALUES(11, '침구/커튼', 14);
INSERT INTO `km_product_cate2` VALUES(12, '조명/인테리어', 14);
INSERT INTO `km_product_cate2` VALUES(13, '생활용품', 14);
INSERT INTO `km_product_cate2` VALUES(14, '주방용품', 14);
INSERT INTO `km_product_cate2` VALUES(15, '문구/사무용품', 14);
INSERT INTO `km_product_cate2` VALUES(16, '사무기기', 14);
INSERT INTO `km_product_cate2` VALUES(17, '악기/취미', 14);
INSERT INTO `km_product_cate2` VALUES(18, '반려동물용품', 14);

INSERT INTO `km_product_cate2` VALUES(10, '노트북/PC', 15);
INSERT INTO `km_product_cate2` VALUES(11, '모니터/프린터', 15);
INSERT INTO `km_product_cate2` VALUES(12, 'PC주변기기', 15);
INSERT INTO `km_product_cate2` VALUES(13, '모바일/태블릿', 15);
INSERT INTO `km_product_cate2` VALUES(14, '카메라', 15);
INSERT INTO `km_product_cate2` VALUES(15, '게임', 15);
INSERT INTO `km_product_cate2` VALUES(16, '영상가전', 15);
INSERT INTO `km_product_cate2` VALUES(17, '주방가전', 15);
INSERT INTO `km_product_cate2` VALUES(18, '계절가전', 15);
INSERT INTO `km_product_cate2` VALUES(19, '생활/미용가전', 15);
INSERT INTO `km_product_cate2` VALUES(20, '음향가전', 15);
INSERT INTO `km_product_cate2` VALUES(21, '건강가전', 15);

INSERT INTO `km_product_cate2` VALUES(10, '스포츠의류/운동화', 16);
INSERT INTO `km_product_cate2` VALUES(11, '휘트니스/수영', 16);
INSERT INTO `km_product_cate2` VALUES(12, '구기/라켓', 16);
INSERT INTO `km_product_cate2` VALUES(13, '골프', 16);
INSERT INTO `km_product_cate2` VALUES(14, '자전거/보드/기타레저', 16);
INSERT INTO `km_product_cate2` VALUES(15, '캠핑/낚시', 16);
INSERT INTO `km_product_cate2` VALUES(16, '등산/아웃도어', 16);
INSERT INTO `km_product_cate2` VALUES(17, '건강/의료용품', 16);
INSERT INTO `km_product_cate2` VALUES(18, '건강식품', 16);
INSERT INTO `km_product_cate2` VALUES(19, '렌탈서비스', 16);

INSERT INTO `km_product_cate2` VALUES(10, '자동차용품', 17);
INSERT INTO `km_product_cate2` VALUES(11, '공구/안전/산업용품', 17);

INSERT INTO `km_product_cate2` VALUES(10, '여행/항공권', 18);
INSERT INTO `km_product_cate2` VALUES(11, '도서/음반/e교육', 18);
INSERT INTO `km_product_cate2` VALUES(12, '공연티켓', 18);
INSERT INTO `km_product_cate2` VALUES(13, 'e쿠폰', 18);
INSERT INTO `km_product_cate2` VALUES(14, '상품권', 18);

# 게시물 채우기 
INSERT INTO `km_product` (`seller`, `prodCate1`, `prodCate2`, `prodName`, `descript`,`company`, `price`, `discount`, `point`, `stock`, `delivery`, `thumb1`, `newThumb1`, `thumb2`, `newThumb2`, `thumb3`, `newThumb3`, `detail`, `newDetail`, `ip`, `rdate`) 
SELECT `seller`, `prodCate1`, `prodCate2`, `prodName`, `descript`,`company`, `price`, `discount`, `point`, `stock`, `delivery`, `thumb1`, `newThumb1`, `thumb2`, `newThumb2`, `thumb3`, `newThumb3`, `detail`, `newDetail`, `ip`, `rdate` FROM `km_product`;

INSERT INTO `km_product_order` (`ordUid`, `ordCount`, `ordPrice`, `ordDiscount`, `ordDelivery`,`savePoint`, `usedPoint`, `ordTotPrice`, `recipName`, `recipHp`, `recipZip`, `recipAddr1`, `recipAddr2`, `ordStatus`, `ordPayment`, `ordComplete`, `deliveryStatus`, `ordDate`) 
SELECT `ordUid`, `ordCount`, `ordPrice`, `ordDiscount`, `ordDelivery`,`savePoint`, `usedPoint`, `ordTotPrice`, `recipName`, `recipHp`, `recipZip`, `recipAddr1`, `recipAddr2`, `ordStatus`, `ordPayment`, `ordComplete`, `deliveryStatus`, `ordDate` FROM `km_product_order`;

INSERT INTO `km_product_order_item` (`ordNo`, `prodNo`, `count`, `price`, `discount`, `point`, `delivery`, `total`) 
SELECT `ordNo`, `prodNo`, `count`, `price`, `discount`, `point`, `delivery`, `total` FROM `km_product_order_item`;

update `km_product` set `sold`=CEILING(RAND()*100);
update `km_product` set `hit`=CEILING(RAND()*100);
update `km_product` set `score`=CEILING(RAND()*5);
update `km_product` set `review`=CEILING(RAND()*100);

SELECT `prodCate1`, `prodCate2`, `newThumb1`, `newThumb2`, `newThumb3`, `newDetail` FROM `km_product` WHERE `prodNo`=1049130;

SELECT DISTINCT a.*, b.`c1Name` , c.`c2Name`  
	FROM `km_product` AS a 
	JOIN `km_product_cate1` AS b 
	ON a.`prodCate1`=b.`cate1` 
	JOIN `km_product_cate2` AS c
	ON b.`cate1`=c.`cate1`
	WHERE `prodNo`=1049129;
	
#일반회원 정보	
INSERT INTO `km_member` 
	SET `uid`='user', 
	`pass`=SHA2(1234, 256), 
	`name`='유저', 
	`gender`=1, 
	`hp`='010-1234-1234', 
	`email`='user@gmail.com', 
	`type`=1, 
	`zip`='45521', 
	`addr1`='부산광역시', 
	`addr2`='아무아파트', 
	`regip`='0:0:0:0:0:0:0:1', 
	`rdate`=NOW();
	
#판매자 정보
INSERT INTO `km_member` 
	SET `uid`='seller', 
	`level`=5, 
	`pass`=SHA2(1234, 256), 
	`email`='seller', 
	`type`=2, 
	`zip`='25346', 
	`addr1`='부산광역시', 
	`addr2`='아무회사', 
	`company`='아무회사', 
	`ceo`='셀러', 
	`bizRegNum`='121212121212', 
	`comRegNum`='232145345', 
	`tel`='051-525-2336', 
	`manager`='길동홍', 
	`managerHp`='010-2526-2353', 
	`fax`='010-3453-213', 
	`regip`='0:0:0:0:0:0:0:1', 
	`rdate`=NOW();
	
#상품 삭제
TRUNCATE TABLE `km_product`;

SELECT * FROM `km_product` AS a JOIN `km_member` AS b ON a.`seller`=b.`uid` WHERE b.`company`='아무회사' ORDER BY `prodNo` DESC LIMIT 0, 10;

SELECT COUNT(*) FROM `km_product` AS a JOIN `km_member` AS b ON a.`seller`=b.`uid` WHERE b.`company=? AND `b.`manager` LIKE '길동홍';


#주문목록 확인
SELECT a.`prodNo`, b.*, c.`newThumb1`, c.`prodName` FROM `km_product_order_item` AS a JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` JOIN `km_member` AS d ON c.`seller`=d.uid WHERE d.`company`='아무회사' ORDER BY b.`ordNo` DESC LIMIT 0, 10;

SELECT COUNT(*) FROM `km_product` AS a JOIN `km_member` AS b ON a.`seller`=b.`uid` WHERE b.`company`='아무회사' AND a.`prodName` LIKE %여성%;

UPDATE `km_product_order` SET `ordComplete` = 1 WHERE `ordNo`= 1000038;
UPDATE `km_product_order` SET `deliveryStatus` = 'yet';
UPDATE `km_product_order` SET `ordStatus` = 'success';
UPDATE `km_product_order` SET `ordStatus` = 'cancel' WHERE `ordNo`=1000000;
UPDATE `km_product_order` SET `ordStatus` = 'return' WHERE `ordNo`=1000038;
UPDATE `km_product_order` SET `ordStatus` = 'exchange' WHERE `ordNo`=1000014;

SELECT a.`prodNo`, b.*, c.`newThumb1`, c.`prodName` FROM `km_product_order_item` AS a JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` JOIN `km_member` AS d ON c.`seller`=d.uid WHERE d.`company`='아무회사' ORDER BY b.`ordNo` DESC LIMIT 11, 10;

SELECT a.`prodNo`, b.*, c.`newThumb1`, c.`prodName` FROM `km_product_order_item` AS a JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` JOIN `km_member` AS d ON c.`seller`=d.uid WHERE d.`company`='아무회사' AND c.`prodName` LIKE '%여성%' ORDER BY b.`ordNo` DESC LIMIT 0, 10;

SELECT * FROM `km_member` WHERE `type`=1 ORDER BY `rdate` DESC LIMIT 0, 10;

#주문목록
INSERT INTO `km_product_order` SET `ordUid`='user', 
		`ordCount`=10, 
		`ordPrice`=500000, 
		`ordDiscount`=100000, 
		`ordDelivery`=0, 
		`savePoint`=100, 
		`usedPoint`=1000, 
		`ordTotPrice`=309000, 
		`recipName`='user', 
		`recipHp`='010-1234-3234', 
		`recipZip`='2134', 
		`recipAddr1`='아무광역시', 
		`recipAddr2`='아무아파트', 
		`ordStatus`='success', 
		`ordPayment`=1, 
		`ordComplete`=0, 
		`deliveryStatus`='yet', 
		`ordDate`=NOW();
INSERT INTO `km_product_order_item` VALUES(1000002, 1000000, 50, 500000, 100, 100, 0, 309000);
UPDATE `km_product_order` SET `ordComplete`=1;
UPDATE `km_product_order` SET `ordComplete`=2 WHERE `ordNo`=1000000;
UPDATE `km_product_order` SET `ordStatus`='success' WHERE `ordNo`=1000000;
UPDATE `km_product_order` SET `ordStatus`='cancel' WHERE `ordNo`=1000001;
UPDATE `km_product_order` SET `ordStatus`='return' WHERE `ordNo`=1000002;
UPDATE `km_product_order` SET `ordStatus`='exchange' WHERE `ordNo`=1000000;