window.addEventListener("load", function(event) {
    let MemberId = document.getElementById("MemberId").value;

    let seats = document.querySelectorAll("#seat").forEach( e => {
      e.addEventListener("click", () => {
        seatAssign(e, MemberId)
        })
      })

    })

function seatAssign(e, MemberId){
    console.log("선택한 좌석 : " + e.textContent)
    console.log(MemberId)

    var result = confirm(e.textContent + "번 자리를 선택하시겠습니까?")
    console.log(MemberId.length == 0)

    if(result){
        if(MemberId.length != 0)
            $.ajax({
                type: "post",
                url: "seat/choice",
                data: {"id" : MemberId, "seatNumber" : e.textContent},
                success : function(){
                    console.log('통신 성공');
                },
                error: function(){
                    console.log('통신 에러');
                }
            })
        else alert("로그인을 해주세요!")
    }else return
}

