<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp" %>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        // 어떤 그래프를 사용할지 지정 ------ (※2)  
        google.load('visualization', '1.0', {
            'packages': ['corechart']
        });

        // 그래프API로드가 완료되면 실행할 수 있도록 이벤트를 지정 ---- (※3)  
        google.setOnLoadCallback(drawChart);

        // 차트 그리기를 실행 --- (※4)  
        function drawChart() {
            // 데이터오브젝트를 작성 --- (※5)    
            var data = new google.visualization.DataTable();

            // 데이터의 컬럼을 지정 -------- (※6)    
            data.addColumn('string', '인물');
            data.addColumn('number', '취득표');
            
            const data1 = 
            // 실제 데이터를 지정 ------------- (※7)    
            data.addRows([
                ['남성', 50],
                ['여성', 50]
              
            ]);

            // 그래피의 옵션을 지정 ------- (※8)    
            var opt = {
                'title': '접속자 성별',
                'width': 600,
                'height': 400,
                pieSliceText: 'percentage', // label:이름 . vaule:실제값
                legend: { // 범례
                    position: 'right',
                    textStyle: {
                        color: 'blue',
                        fontSize: 25
                    }
                } //'none' 
            };
            // 그래프를 표시 ------------- (※9)    
            var chart = new google.visualization.PieChart( // ***Chart
                document.getElementById('chart_div'));
            chart.draw(data, opt);
        }

    </script>
            <section id="admin-user-user">
                <nav>
                    <h3>접속자집계</h3>
                    <p>
                        HOME > 회원관리 > <strong>접속자집계</strong>
                    </p>
                </nav>
                <!-- 접속자집계 컨텐츠 시작 --> 
                <!-- 그래프 형식으로  -->

                <div id="chart_div" ></div> 
                
                
                <div id="myfirstchart" style="height: 250px; width: 1100px;"></div>

                

                <p class="ico info">
                    <strong>Tip!</strong>
                    전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
                </p>

                

                <!-- 접속자집계 컨텐츠 끝 -->
            </section>
        </main>
        <footer>
            <div>
                <p>Copyright ©kmarket.co.kr All rights reserved. KMARKET ADMINISTRATOR Version 5.4.1.2</p>
            </div>
        </footer>
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
</body>
</html>

<script>
    new Morris.Line({
  // ID of the element in which to draw the chart.
  element: 'myfirstchart',
  // Chart data records -- each entry in this array corresponds to a point on
  // the chart.
  data: [
  /*  { indate: '2023-09-13'  , value: 220 },
    { indate: '2023-09-14'  , value: 108 },
    { indate: '2023-09-15'  , value: 130 },
    { indate: '2023-09-16'  , value: 10 },
    { indate: '2023-09-17'  , value: 56 },
    { indate: '2023-09-18'  , value: 59 },
    { indate: '2023-09-19'  , value: 300 }*/
    { adate:'2023-01', a: 300},
    { adate:'2023-02', a: 440},
    { adate:'2023-03', a: 451},
    { adate:'2023-04', a: 311},
    { adate:'2023-05', a: 100},
    { adate:'2023-06', a: 20},
    { adate:'2023-07', a: 20},
    { adate:'2023-08', a: 30},
    { adate:'2023-09', a: 20},
    { adate:'2023-10', a: 80},
    { adate:'2023-11', a: 50},
    { adate:'2023-12', a: 40}
    
    // 모리스 차트 단점 1. x축 라벨이 무조건 년도 or 년도 + 월 로만
    // 표기 가능하다. 다른식으로 넣으면 간격이 깨지거나 1900년대부터 나옴
    //
  ],
  // The name of the data record attribute that contains x-values.
  xkey: 'adate',
  xLabels:'month',
  //xLabelAngle:45 , 기울이기
  // A list of names of data record attributes that contain y-values.
  ykeys: ['a'],
  // Labels for the ykeys -- will be displayed when you hover over the
  // chart.
  labels: ['a데이터']
});

</script>