window.addEventListener("load", function(event) {

    let id = document.getElementById("id");

    id.addEventListener("keydown", (e) => {
       idCheck(e)
    })
    id.addEventListener("blur", (e) => {
       idCheck(e)
    })

})

function idCheck(e){

    let idcheck = document.getElementById("idcheck")
    let id = document.getElementById("id")

    idcheck.style.display = "none"

    if(e.key == "Tab" || e.type == "blur"){
        $.ajax({
            type: "GET",
            url: "/members/idCheck",
            data: { "id" : id.value },
            success : function(data){
                console.log(data)
                console.log('통신 성공');
                if(data != null){
                    idcheck.value = "해당 아이디는 사용중입니다."
                    idcheck.style.backgroundColor = "red"
                    idcheck.style.display = "block";
                    id.value = ''
                }
                else if(data == null){
                    idcheck.value = "사용 가능한 아이디 입니다."
                    idcheck.style.backgroundColor = "green"
                    idcheck.style.display = "block";
                }
            },
            error: function(){
                console.log('통신 에러');
            }
        })
    }
}