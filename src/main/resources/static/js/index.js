window.addEventListener("load", function(event) {
    let MemberId = document.getElementById("MemberId").value;

    let seats = document.querySelectorAll("#seat").forEach( e => {
      e.addEventListener("click", () => {
        useBySeat(e, MemberId)
        })
      })

    })

function useBySeat(e, MemberId){
    let value = $(e).val();
    if(value == '') {
        let message = confirm(e.textContent + "번 자리를 선택하시겠습니가?")
        if(message) choiceSeat(e, MemberId)
    }
    else returnSeat(e, MemberId)
}

function choiceSeat(e, MemberId){
    console.log("선택한 좌석 : " + e.textContent)
    console.log(MemberId)

    if(result){
        if(MemberId.length != 0)
            $.ajax({
                type: "post",
                url: "seat/choice",
                data: {"id" : MemberId, "seatNumber" : e.textContent},
                success : function(data){
                    console.log(data)
                    console.log('통신 성공');
                },
                error: function(){
                    console.log('통신 에러');
                }
            })
        else alert("로그인을 해주세요!")
    }else return
}

function returnSeat(e, MemberId){
}


