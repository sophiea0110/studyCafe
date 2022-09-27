function loginCheck(){
    let MemberId = document.getElementById("MemberId")
    let write = document.getElementById("write")

    if(MemberId.value.length != 0){
        location.href="/board/boardWrite"
    }
    else{
        alert("로그인 상태를 확인하세요")
    }
}

function idCheckModify(){

    let MemberId = document.getElementById("MemberId").value
    let id = document.getElementById("id").textContent
    let no = document.getElementById("no").value

    if(MemberId == id){
        location.href="/board/boardModify?no="+no
    }else{
        alert("권한이 없습니다.")
    }
}
function idCheckDelete(){

    let MemberId = document.getElementById("MemberId").value
    let id = document.getElementById("id").textContent
    let no = document.getElementById("no").value

    if(MemberId == id){
        location.href="/board/boardDelete?no="+no
    }else{
        alert("권한이 없습니다.")
    }
}

function boardEmpty(){
    let form = document.getElementById("form")
    let title = document.getElementById("title").value
    let contents = document.getElementById("contents").value

    if(title.length == 0 | contents.length == 0){
        if(title.length == 0){
            alert("제목을 입력하지않으셨습니다.")
        }
        if(contents.length == 0){
            alert("내용을 입력하지않으셨습니다")
        }
    }
    else{
        form.submit()
    }
}
