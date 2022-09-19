/*
function idCheck(){
    let MemberId = document.getElementById("MemberId")
    let write = document.getElementById("write")

    if(MemberId.value.length != 0){
        location.href="/board/boardWrite"
    }
    else{
        alert("로그인 상태를 확인하세요")
    }
}
*/
/*
function findBySearch(){
    let searchOption = document.getElementById("search-kind")
    let searchKind = searchOption.options[searchOption.selectedIndex].value
    let searchWord = document.getElementById("searchWord").value;

     $.ajax({
        type: "get",
        url: "/board/boardSearch",
        data: {"searchKind" : searchKind, "searchWord" : searchWord},
        success : function(data){
            console.log(data)
            console.log('통신 성공');
        },
        error: function(){
            console.log('통신 에러');
        }
     })
}
*/