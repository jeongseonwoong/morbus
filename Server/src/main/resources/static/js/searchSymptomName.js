document.addEventListener("DOMContentLoaded",()=>{
    document.getElementById("searchForm").addEventListener("submit",(event)=>{
        event.preventDefault();
        doSearching()
    });

});

function removeExisting(response)
{
    var xhr = new XMLHttpRequest();
    xhr.open("GET","getSymptomList",true);
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.send();
    xhr.onload = () =>{
        if(xhr.status ===200)
        {
            arrayList = JSON.parse(xhr.responseText);
            var array = Array.isArray(arrayList) ? arrayList:[arrayList];
            array.forEach(value => {
                if(document.getElementById(value+"label").style.color === "red" &&response!==value)
                {
                    document.getElementById(value+"label").style.color = "unset";
                }
            })
        }
    }

}

function doSearching()
{
    var searchText = document.getElementById("search").value;
    console.log(searchText);

    var xhr = new XMLHttpRequest();
    xhr.open("POST","selectSymptom",true);
    xhr.setRequestHeader("Content-Type","application/json");

    xhr.send(JSON.stringify({searchText : searchText}));

    xhr.onload = function (){
        if(xhr.status === 200){
            var response = JSON.parse(xhr.responseText);
            removeExisting(response.searchText);
            console.log(xhr.responseText);
            console.log(response);
            document.getElementById(response.searchText+"label").style.color="red";

        }
        else{
            alert("해당되는 증상이 없습니다.");
        }
    }
}