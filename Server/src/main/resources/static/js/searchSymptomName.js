function searchVal(event) {
    event.preventDefault();
    const searchText = document.getElementById("search");
    const keyword=document.getElementsByName("keyword");
    const symptomName=document.getElementsByName("symptomName");


    if (searchText.length === 0 || searchText.length > 10) {
        alert("입력 길이가 올바르지 않습니다");
    }
    else
    {
        keyword.forEach(keywordList => {

            if(keywordList.value===symptomName.value)
            {
                const heading = document.getElementById('symptom');
                heading.style.color='red';
            }
        })
    }
    return 0;
}