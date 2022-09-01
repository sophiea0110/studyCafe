// load될때 DB를 통해 전체 좌석 상태를 가져온다
// 좌석 상태를 객체로 받아 forEach문으로 좌석 상태 확인후 색 설정
// 사용 O 빨간색
// 사용 X 초록색
// 사용중인 좌석 div태그에 value에 사용자ID를 저장한다.
// 로그인 상태를 확인 하고 사용중인 좌석 div 사용자 ID와 로그인된 ID 상태가 같으면 반납
// 좌석 DIV태그 value가 없고 로그인된 상태이면 좌석 선택
window.addEventListener("load", function(event) {
    let MemberId = document.getElementById("MemberId");

    let seats = document.querySelectorAll("#seat")

    let seatByState = allStateSeat()
    /*
        1 : {
                id : 'aaa',
                pw : '111',
                seatNumber : 1
            }
    */

    seats.forEach( seat => {
        seatByState.forEach( s => {
            if(seat.textContent == s.seatNumber){
               seat.style.backgroundColor="red";
            }else{
               seat.style.backgroundColor="green";
            }
        })
    })

    /*
    seats.forEach( e => {
        if(e.textContent == seatByState){
            e.style.backgroundColor="red";
            e.addEventListener("click", () => {
                    selectMemberSeat(e, MemberId)
                  })
        }else{
            e.style.backgroundColor="green";
            e.addEventListener("click", () => {
                    recoverSeat(e, MemberId)
                  })
        }
    })
    */

    /*
    let addEventSeats = seats.forEach( e => {
      e.addEventListener("click", () => {
        selectMemberSeat(e, MemberId)
      })
    })
    */
})

// user 좌석 선택시 (selectMemberSeat)
// user 로그인 상태 및 user 이미 다른 좌석이 있는지 학인 (findMeberSeat)
// selectMemberSeat -> findMeberSeat -> selectMemberSeat
function selectMemberSeat(e, MemberId){
    console.log("선택한 좌석 : " + e.textContent)

    const aleadyUseSeat = findMeberSeat(MemberId);
    //로그인 상태 if(MemberId.length != 0)
    //이미 다른 좌석이 있는지 확인 if(aleadyUseSeat != 0)
    if(MemberId.length != 0){
        if(aleadyUseSeat == 0){
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
         }  else    alert(MemberId.value + "님은 현재 " + aleadyUseSeat + " 번 좌석 사용중입니다." );
    }else   alert("로그인 상태를 확인하세요!");
 }

// user의 이미 좌석이 있으면 해당 좌석 번호을 가져온다.
// 리턴으로 0 아닌 1~10까지 숫자
function findMeberSeat(MemberId){
    let result;
    $.ajax({
        type: "GET",
         url: "seat/findSeat",
         async: false,
         data: { "MemberId" : MemberId.value },
         success : function(data){
            result = data
            console.log('통신 성공');

         },
         error: function(){
            console.log('통신 에러');
         }
    })
    return result;
}

//
//
//
function recoverSeat(e, MemberId){

    let check = confirm(e.textContent + " 번 좌석을 반납하시겠습니까?")

    if(check){
        $.ajax({
            type: "post",
            url: "seat/recover",
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

// 서버 단에서 객체로 받아 반환
function allStateSeat(){

    let result;

    $.ajax({
            type: "GET",
            url: "seat/AllfindSeat",
            async: false,
            success : function(data){
                console.log(data)
                result = data
                console.log('통신 성공');
            },
            error: function(){
                console.log('통신 에러');
            }
    })

    return result
}



