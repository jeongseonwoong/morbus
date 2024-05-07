function searchVal() {
    const searchText = document.querySelector("#search").value;
    if (searchText.length === 0 || searchText.length > 10) {
        alert("입력 길이가 올바르지 않습니다");
    }
}