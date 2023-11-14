/**
 * 
 */

 $(document).ready(function () {
    $(".slider > ul").bxSlider({
      easing: "linear",
    });
  });

  $(function () {
    var best = $("aside > .best");

    $(window).scroll(function () {
      var t = $(this).scrollTop();

      if (t > 620) {
        best.css({
          position: "fixed",
          top: "0",
        });
      } else {
        best.css({ position: "static" });
      }
    });
});

function scrollToHitProduct() {
    // 원하는 스크롤 위치 (픽셀)를 설정합니다.
    var yourDesiredScrollPosition = 700; // 예: 500 픽셀
    
    // 스크롤 위치로 부드럽게 스크롤합니다.
    window.scrollTo({
        top: yourDesiredScrollPosition,
        behavior: 'smooth' // 스무스 스크롤 적용
    });
}
function scrollToHitProduct2() {
    // 원하는 스크롤 위치 (픽셀)를 설정합니다.
    var yourDesiredScrollPosition = 1420; // 예: 500 픽셀
    
    // 스크롤 위치로 부드럽게 스크롤합니다.
    window.scrollTo({
        top: yourDesiredScrollPosition,
        behavior: 'smooth' // 스무스 스크롤 적용
    });
}
function scrollToHitProduct3() {
    // 원하는 스크롤 위치 (픽셀)를 설정합니다.
    var yourDesiredScrollPosition = 2150; // 예: 500 픽셀
    
    // 스크롤 위치로 부드럽게 스크롤합니다.
    window.scrollTo({
        top: yourDesiredScrollPosition,
        behavior: 'smooth' // 스무스 스크롤 적용
    });
}
function scrollToHitProduct4() {
    // 원하는 스크롤 위치 (픽셀)를 설정합니다.
    var yourDesiredScrollPosition = 2900; // 예: 500 픽셀
    
    // 스크롤 위치로 부드럽게 스크롤합니다.
    window.scrollTo({
        top: yourDesiredScrollPosition,
        behavior: 'smooth' // 스무스 스크롤 적용
    });
}


