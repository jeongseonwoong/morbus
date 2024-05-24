function searchVal(event) {
    event.preventDefault();
    const searchText = document.getElementById("search");
    const keyword=document.getElementsByName("keyword");
    const symptomName=document.getElementById("symptomName");

    if (searchText.length === 0 || searchText.length > 10) {
        alert("입력 길이가 올바르지 않습니다");
    }
    else
    {

        keyword.forEach(key=>{
            if(key.value===searchText.value)
            {
                console.log(symptomName.value);
                console.log(key.value);
                console.log(searchText.value);
                const heading = document.getElementById(symptomName.value.toString());
                heading.style.color='red';
            }
        })
    }
    return 0;
}