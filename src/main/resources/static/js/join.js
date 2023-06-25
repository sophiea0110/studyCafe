window.addEventListener("load", function(event) {

    let id = document.getElementById("id");

    id.addEventListener("keydown", (e) => {
       idCheck(e)
    })
    id.addEventListener("blur", (e) => {
       idCheck(e)
    })

    emptyCheck()

})


function idCheck(e){

    let idCheck = document.getElementById("idCheck")
    let id = document.getElementById("id")

    idCheck.style.display = "none"

    if(e.key == "Tab" || e.type == "blur" & id.value != ""){
        $.ajax({
            type: "post",
            url: "/members/idCheck",
            data: { "MemberId" : id.value },
            success: function(data){
                console.log(data)
                console.log('통신 성공');
                if(data != null){
                    idCheck.value = "해당 아이디는 사용중입니다."
                    idCheck.style.color = "red"
                    idCheck.style.display = "block";
                    idCheck.style.border = "none"
                    id.value = ''
                }
                else{
                    idCheck.value = "사용 가능한 아이디 입니다."
                    idCheck.style.color = "green"
                    idCheck.style.display = "block";
                    idCheck.style.border = "none"
                }
            },
            error: function(){
                console.log('통신 에러');
            }
        })
    }

    emptyCheck()

}

function pwcheck(){
    let pw = document.getElementById("pw")
    let pw2 = document.getElementById("pw2")
    let pwCheck = document.getElementById("pwCheck")

    let submit = document.getElementById("submit")

    console.log("pw : " + pw.value)
    console.log("pw2 : " + pwCheck.value)
    console.log(pw.value == pw2.value)

    if(pw.value == pw2.value){
        pwCheck.value = "확인되었습니다"
        pwCheck.style.color = "green"
        pwCheck.style.display = "block";
        pwCheck.style.border = "none"
    }
    else{
        pwCheck.value = "비밀번호가 일치하지 않습니다."
        pwCheck.style.color = "red"
        pwCheck.style.display = "block";
        pwCheck.style.border = "none"
    }

    emptyCheck()

}

function emailcheck(){

    let email = document.getElementById("email")
    let emailCheck = document.getElementById("emailCheck")

    if(email.value == ""){
        emailCheck.value = "이메일를 입력하지 않습니다."
        emailCheck.style.color = "red"
        emailCheck.style.display = "block";
        emailCheck.style.border = "none"
    }
    else{
        emailCheck.value = "확인되었습니다"
        emailCheck.style.color = "green"
        emailCheck.style.display = "block";
        emailCheck.style.border = "none"
    }

    emptyCheck()

}

function emptyCheck(){

    let id = document.getElementById("id");
    let idCheck = document.getElementById("idCheck")

    let pw = document.getElementById("pw")
    let pw2 = document.getElementById("pw2")
    let pwCheck = document.getElementById("pwCheck")

    let email = document.getElementById("email")
    let emailCheck = document.getElementById("emailCheck")

    if(id.value != "" && pw.value != "" && pw2.value != "" &&
    pw.value == pw2.value && email.value != ""){
        $("#submit").attr("disabled", false);
    }else {
        if(id.value == ""){
            idCheck.value = "아이디를 입력하지 않습니다."
            idCheck.style.color = "red"
            idCheck.style.display = "block";
            idCheck.style.border = "none"
        }
        if(pw.value == "" || pw2.value == ""){
            pwCheck.value = "비밀번호를 입력하지 않습니다."
            pwCheck.style.color = "red"
            pwCheck.style.display = "block";
            pwCheck.style.border = "none"
        }
        if(email.value == ""){
            emailCheck.value = "이메일를 입력하지 않습니다."
            emailCheck.style.color = "red"
            emailCheck.style.display = "block";
            emailCheck.style.border = "none"
        }
        $("#submit").attr("disabled", true);
    }

}