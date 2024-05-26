document.addEventListener("DOMContentLoaded",()=>{
    document.getElementById("logout").addEventListener("click",()=>{
        console.log("a")
        var xhr = new XMLHttpRequest();
        xhr.open("POST","logout",true);
        xhr.setRequestHeader("Content-Type","application/json");

        xhr.send();
        console.log("b")

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                responseJson = JSON.parse(xhr.responseText);// JSON 응답 파싱
                console.log(responseJson);
                var responseUrl =responseJson.url; // 최종 URL
                console.log(responseUrl);
                window.location.replace(responseUrl);
            }
        };

    });

})