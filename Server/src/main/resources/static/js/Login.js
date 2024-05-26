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
});

function login()
{
    var password = document.getElementById("password").value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET","getMemberRepository",true);
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.send();
    xhr.onreadystatechange =()=>{
        if (xhr.readyState === 4 && xhr.status === 200)
        {
            var a=1;
            responseJson = JSON.parse(xhr.responseText);
            var memberList = Array.isArray(responseJson) ?responseJson:[responseJson];

            memberList.forEach(member=>{
                console.log(member);
                    if(password===member)
                    {
                        a=2;
                    }
            })
            if(a===1)
                alert("입력하신 회원이 존재하지 않습니다.");
        }
    }
}