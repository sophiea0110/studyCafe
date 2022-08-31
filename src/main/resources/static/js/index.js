window.addEventListener("load", function(event) {
    let MemberId = document.getElementById("MemberId");

    let seats = document.querySelectorAll("#seat")

    let addEventSeats = seats.forEach( e => {
      e.addEventListener("click", () => {
        selectMemberSeat(e, MemberId)
      })
    })
})

// user 좌석 선택시 (selectMemberSeat)
// user 로그인 상태 및 user 이미 다른 좌석이 있는지 학인 (findMeberSeat)
// selectMemberSeat -> findMeberSeat -> selectMemberSeat
function findMeberSeat(MemberId){
    let result;
    $.ajax({
        type: "GET",
         url: "seat/findSeat",
         data: { "MemberId" : MemberId.value },
         success : function(data){
            console.log('통신 성공');
         },
         error: function(){
            console.log('통신 에러');
         }
    })
    return result;
    // user의 이미 좌석이 있으면 해당 좌석 번호을 가져온다.
    // 0 아닌 1~10까지 숫자
    // 리턴으로 True or False 반환
}

function selectMemberSeat(e, MemberId){
    console.log("선택한 좌석 : " + e.textContent)
    console.log(MemberId)

    const result = findMeberSeat(MemberId);
    console.log(result)
    if(true){
        if(MemberId.length != 0)
            $.ajax({
                type: "post",
                url: "seat/select",
                data: {"id" : MemberId.value, "seatNumber" : e.textContent},
                success : function(data){
                    console.log(data)
                    console.log('통신 성공');
                },
                error: function(){
                    console.log('통신 에러');
                }
            })
    }
}

function recoverSeat(e, MemberId){
}


