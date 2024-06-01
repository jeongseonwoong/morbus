document.addEventListener("DOMContentLoaded", () => {
    var logoutButtons = document.getElementsByName("logout");

    function handleLogout(event) {
        console.log("a");
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "logout", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send();
        console.log("b");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseJson = JSON.parse(xhr.responseText); // JSON 응답 파싱
                console.log(responseJson);
                var responseUrl = responseJson.url; // 최종 URL
                console.log(responseUrl);
                window.location.replace(responseUrl);
            }
        };
    }

    // 각 로그아웃 버튼에 이벤트 리스너 추가
    for (var i = 0; i < logoutButtons.length; i++) {
        logoutButtons[i].addEventListener("click", handleLogout);
    }
});