<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
    const KAKAO_URI = "https://kauth.kakao.com/oauth/authorize";



</script>
<body>

    <div>
        <a id="kakao-login-btn"></a>
<%--        <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${kakaoApiKey}&redirect_uri=${redirectUri}">--%>
<%--            <img src="${pageContext.request.contextPath}/statics/resources/images/kakao_login_medium_narrow.png">--%>
<%--        </a>--%>
        <script type="text/javascript">
            // 사용할 앱의 JavaScript 키를 설정해 주세요.
            Kakao.init('${kakaoApiKey}');
            // 카카오 로그인 버튼을 생성합니다.
            Kakao.Auth.createLoginButton({
                container: '#kakao-login-btn',
                success: function (authObj) {
                    alert(JSON.stringify(authObj));
                },
                fail: function (err) {
                    alert(JSON.stringify(err));
                }
            });
        </script>
    </div>

</body>
</html>