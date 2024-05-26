document.addEventListener("DOMContentLoaded",(event)=>{
    var duplicateDisease = document.querySelectorAll("#duplicateDisease ul button");
    var diseaseList=document.querySelectorAll("#square ul button");
    console.log(diseaseList.length);
    console.log(duplicateDisease.length);
    if(duplicateDisease.length>5 || diseaseList.length>5)
    {
        var hidingList = hideList();
        var button = makeButton();
        button.addEventListener("click",(event)=>{
            event.preventDefault();
            if(hidingList.style.display==="none")
            {
                button.innerText = "증상에 따른 질병 리스트 접기";
                hidingList.style.display="block";
            }
            else
            {
                button.innerText = "증상에 따른 질병 리스트 더보기";
                hidingList.style.display="none"
            }
        })
    }
})

function hideList(event)
{
    var hidingList = document.querySelector("#square");
    hidingList.style.display='none';
    return hidingList;
}

function makeButton()
{
    var showMoreListBttn = document.createElement("button");
    showMoreListBttn.innerText = "증상에 따른 질병 리스트 더보기";
    showMoreListBttn.className="content";
    var insertPosition = document.querySelector("#square");
    document.querySelector("form").insertBefore(showMoreListBttn,insertPosition);
    return showMoreListBttn;
}

