function emptyCheck(){;
    let id = document.getElementById("id");
    let pw = document.getElementById("pw");
    let result = ''

    if(id.value.length == 0 && pw.value.length == 0){
        alert("id와 pw가 입력되지 않았습니다.")
        id.focus();

        return false
    }else if(id.value.length == 0){
            alert("id가 입력되지 않았습니다.")
            id.focus();
            return false
    }else if(pw.value.length == 0 ){
            alert("pw가 입력되지 않았습니다.")
            pw.focus();
            return false
        }
    console.log(id)

    $.ajax({
        type: "GET",
        url: "/members/memberCheck",
        async: false,
        data: {"id" : id.value, "pw" : pw.value},
        success : function(data){
            result = data;
            console.log(data)
            console.log(typeof result)
            console.log(result)
            console.log('통신 성공');
        },
        error: function(){
            console.log('통신 에러');
        }
    })
    console.log(result)
    //result가 1이면 로그인 실패 0이면 로그인 성공
    if(result == "true"){
        return true
    }else{
        alert("로그인에 실패 하셨습니다!")
        id.value = '';
        pw.value = '';
        return false
    }
}

