# 프로젝트명 - ArmyDocs(APP version)
- - -

* 핸드폰 구성 / 필수 조건 안내 (Prerequisites)

>안드로이드 3.5.1 버젼을 다운 받는 것을 권장합니다.<br>
>핸드폰의 필수 조건은 없으며 모든 핸드폰의 크기에 맞춰 잘 실행됩니다.

* 설치 안내 (Installation Process)

>깃 허브에서 소스를 다운 받은 다음 <br>
>안드로이드 3.5.1버젼을 다운받습니다.<br>
>1.안드로이드에서 avd매니저를 클릭 후 플랫폼 크기는 아무거나 클릭하고 소프트웨어 버전은 q를 다운 받으세요.<br>
>  그리고 run을 실행하면 사용하실 수 있습니다.<br>
>2.컴퓨터와 핸드폰을 연결한 뒤 running device를 본인의 핸드폰으로 설정 후<br>
> run을 실행하면 사용하실 수 있습니다.<br>

* 사용법 (Getting Started)

>1. 회원가입 창에서 회원가입을 한다. (이미 있다면 로그인하기) <br>
>2. 메인 페이지에 보면 설문 목록들이 올라와 있는데 누르면 그 설문에 참여할 수 있다.<br>
>3. 새 설문 버튼을 누르면 설문을 작성 할 수 있다.<br>
>4. 왼쪽에서 오른쪽으로 드래그하면 개인정보 화면과 각 메뉴로 이동할 수 있는 drawer 화면이 나온다.<br>
>5. drawer 화면의 프로필 버튼을 누르면 개인정보를 확인하는 페이지로 이동하고<br> 그 페이지에서 개인 정보를 수정 할 수 있다.<br>
>6. drawer 화면에서 설문 결과 보기, 개발자 정보 보기 등 기능이 있다.<br>
>7. 로그아웃을 하고 싶다면 아미독스 로고 옆에 문 이미지를 누르면 된다.<br>

* 파일 정보 및 목록 (File Manifest)

> 액티비티 파일
> 클래스 파일
> 유틸(DB등록, ViewPager, 파일 업로드 등)
> 슬라이드 화면 파일
> Drawable (앱에 쓰인 사진 파일들)

* 저작권 및 사용권 정보 (Copyright / End User License)

> 저작권 : Copyright ⓒ TEAM_ArmyDocs

* 배포자 및 개발자의 연락처 정보 (Contact Information)

> 김은진 : 010-3007-7265<br>
> 이지수 : 010-6617-2602<br>
> 정희성 : 010-3275-1025<br>
> 최준영 : 010-6686-6384<br>

* 문제 발생에 대한 해결책 (Troubleshooting)

> 버그 발생이나 제보는 개발자 연락처로 문의 주시면 감사하겠습니다.

* 업데이트 정보 (Change Log)

> 2019-10-24 armydocs_app (ver 1)

---
* 앱 화면<br><br>
<img width="30%" height="30%" src="https://github.com/dldkddkd/armydocs_app/blob/master/app/src/main/res/drawable/slide2.png"></img>
<img width="30%" height="30%" src="https://github.com/dldkddkd/armydocs_app/blob/master/app/src/main/res/drawable/slide1_2.png"></img>
<img width="30%" height="30%" src="https://github.com/dldkddkd/armydocs_app/blob/master/app/src/main/res/drawable/slide3.png"></img>
- - - 

1. 아이템 개요

>아미독스는 군 내에서 수기로 시행되는 각종 설문조사를 전산화하여 구현한 웹 사이트입니다. 주 기능은 설문 작성 및 결과를 분석하는 관리자와 설문에 참여하는 사용자에 따라 나뉩니다.
>관리자는 승인받은 간부들로 구성되며, 사단 및 대대 등의 규모나 익명성 여부를 지정해 설문을 작성할 수 있습니다. 등록된 설문은 해당 부대 소속의 사용자들에게 알림을 보내 참여를 유도하고, 관리자는 실시간 설문 참여 현황을 확인할 수 있습니다.
>사용자는 관리자에게 승인받은 병사들로 구성되며, 지정 기간 내에 등록된 설문을 제출하거나 수정할 수 있습니다. 또한 자신이 속한 부대를 대상으로 설문조사를 시행할 수도 있습니다.
>관리자와 사용자는 간단한 조작으로 객관식, 서술형 등 다양한 형식의 설문을 작성할 수 있으며, 기존의 설문 파일을 업로드해 지난 설문 이력을 추가할 수도 있습니다. 완료된 설문에서는 빅데이터 분석을 통해 그래프화된 다양한 통계적 수치를 확인할 수 있으며, 엑셀 등의 형식으로 저장 가능합니다.
2. 사업전망

>현재 군 내 설문조사는 수기로 작성한 뒤 담당자가 해당 내용을 일일이 타이핑해 취합되고 있습니다. 이러한 단순 업무를 위해 출장 및 야근을 하는 병사들이 많은데, 아미독스는 이 과정을 전산화하여 불필요한 업무와 비용을 줄이는 것을 목표로 합니다.
>단순히 결과만 출력하는 기존 설문조사와 다르게 아미독스는 빅데이터 분석을 통해 여러 통계수치를 그래프화해, 부대에서 특정 사업을 기획할 때 세부사항을 결정하는 데 도움이 됩니다. 추후 아미독스의 이용률이 높아져 커뮤니티화된다면, 병사 개개인의 활용성도 커질 것으로 보입니다.
>그 사례로, 최근 개최된 국방 K-Startup과 같은 군 내 공모전에 참여한 병사들이 실제 기획 단계에서 아이디어에 대한 실수요를 예측 못 해 탈락하는 경우가 많았는데, 개인이 중대 이상의 수요를 조사하는 건 어렵기 때문입니다. 따라서 병사들끼리도 수요조사가 가능하면, 대한민국 국군의 발전에 기여하는 다양한 아이디어 창출과 새로운 소통의 장을 만들 수 있을 것입니다.
3. 개발목표

>아미독스는 웹 기반 서비스로, 웹 서버는 Tomcat과 Spring을 기반으로 Restful 하게 구축하며, POI 라이브러리를 활용해 엑셀 파일을 관리합니다. DB는 MySQL을 사용하고, Hadoop으로 집계된 데이터를 분석합니다. 또한 ELK Stack을 사용해 대규모 요청에 따른 서버 상태를 모니터링합니다.
>웹 클라이언트는 Vue.js를 활용해 SPA로 구축하며, Vuetify와 Webpack, Babel을 사용해 다양한 브라우저를 지원하는 반응형 웹앱을 지원합니다. 추가로 애플리케이션도 추가 개발하여 모바일 편의성을 극대화할 예정입니다.
>아미독스 프로젝트가 국군에서 공식적으로 운영되는 체계로 채택된다면, 수많은 장병들을 대상으로 집계된 데이터들이 통합된 환경에서 관리 및 운용될 수 있으며, 현재 4차 산업혁명 시대를 맞아 진행 중인 여러 빅데이터 및 머신러닝 프로젝트에서 더 유의미한 정보로 가공되어, 대한민국 국군의 발전에 큰 기여를 할 수 있을 것입니다.
<br>

개발 도구 : 안드로이드 스튜디오

데이터 베이스 : https://console.firebase.google.com/project/armydocs-233a3/database/armydocs-233a3/data

웹 버전 깃헙 사이트 : https://github.com/armypago/armydocs 

앱 버전 깃헙 사이트 : https://github.com/dldkddkd/armydocs_app
