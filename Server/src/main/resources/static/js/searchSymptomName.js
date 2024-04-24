
    function searchVal() {
    var searching = document.getElementById("search").value; // 사용자 입력을 대문자로 변환
    var symList = document.getElementsByName("Symptom"); // 증상들의 체크박스들을 가져옴

    // 모든 증상들을 돌면서 검색어와 일치하는 항목을 찾음
    for (var i = 0; i < symList.length; i++) {
    if (symList[i].value === searching) { // 검색어와 일치하는 증상을 찾으면
    document.querySelector("label[for='" + symList[i].id + "']").style.fontweight ="bold"; // 해당 증상의 라벨을 가져옴
        }
    }
}
