window.addEventListener("load", function(event) {

    let id = document.getElementById("id");
    let pw = document.getElementById("pw")
    let pw2 = document.getElementById("pw2")

    id.addEventListener("keydown", (e) => {
       idCheck(e)
    })
    id.addEventListener("blur", (e) => {
       idCheck(e)
    })

    emptyCheck()
})

function idCheck(e){

    let idcheck = document.getElementById("idcheck")
    let id = document.getElementById("id")

    idcheck.style.display = "none"

    if(e.key == "Tab" || e.type == "blur" & id.value != ""){
        $.ajax({
            type: "GET",
            url: "/members/idCheck",
            data: { "id" : id.value },
            success : function(data){
                console.log(data)
                console.log('통신 성공');
                if(data != null){
                    idcheck.value = "해당 아이디는 사용중입니다."
                    idcheck.style.color = "red"
                    idcheck.style.display = "block";
                    idcheck.style.border = "none"
                    id.value = ''
                }
                else if(data == null){
                    idcheck.value = "사용 가능한 아이디 입니다."
                    idcheck.style.color = "green"
                    idcheck.style.display = "block";
                    idcheck.style.border = "none"
                }
            },
            error: function(){
                console.log('통신 에러');
            }
        })
    }
    emptyCheck()
}

function pwCheck(){
    let pw = document.getElementById("pw")
    let pw2 = document.getElementById("pw2")
    let pwCheck = document.getElementById("pwcheck")
    let submit = document.getElementById("submt")

    console.log("pw : " + pw.value)
    console.log("pw2 : " + pwCheck.value)
    console.log(pw.value == pw2.value)

    if(pw.value == pw2.value){
        pwCheck.value = "확인되었습니다"
        pwCheck.style.color = "green"
        pwCheck.style.display = "block";
        pwCheck.style.border = "none"
        $("#submit").attr("disabled", false);
    }
    else{
        pwCheck.value = "비밀번호가 일치하지 않습니다."
        pwCheck.style.color = "red"
        pwCheck.style.display = "block";
        pwCheck.style.border = "none"
        $("#submit").attr("disabled", true);
    }

    emptyCheck()
}

function emptyCheck(){
    let id = document.getElementById("id");
    let idcheck = document.getElementById("idcheck")
    let pw = document.getElementById("pw")
    let pw2 = document.getElementById("pw2")
    let pwCheck = document.getElementById("pwcheck")


    if(id.value == "" || pw2.value == ""){
        if(id.value == ""){
            idcheck.value = "아이디를 입력하지 않습니다."
            idcheck.style.color = "red"
            idcheck.style.display = "block";
            idcheck.style.border = "none"
        }
        if(pw.value == "" || pw2.value == ""){
            pwCheck.value = "비밀번호를 입력하지 않습니다."
            pwCheck.style.color = "red"
            pwCheck.style.display = "block";
            pwCheck.style.border = "none"
        }
        $("#submit").attr("disabled", true);
    }
}