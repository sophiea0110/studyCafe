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
