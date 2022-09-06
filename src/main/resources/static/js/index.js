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
    let seatByState = allStateSeat().filter( f => f.seatNumber != 0)

    console.log(seatByState)
    /*
        1 : {
                id : 'aaa',
                pw : '111',
                seatNumber : 1
            }
    */
    addEventSeat(seats, seatByState, MemberId)
    seatBoard(seats, seatByState, MemberId)
})

// user 좌석 선택시 (selectMemberSeat)
// user 로그인 상태 및 user 이미 다른 좌석이 있는지 학인 (findMeberSeat)
// selectMemberSeat -> findMeberSeat -> selectMemberSeat
function selectMemberSeat(e, MemberId){
    console.log("선택한 좌석 : " + e.textContent)

    let aleadyUseSeat

    if(MemberId.value.length != 0){
        aleadyUseSeat = findMeberSeat(MemberId);
    }else {
        aleadyUseSeat = 0
    }
    console.log(e.value == undefined)
    //로그인 상태 if(MemberId.length != 0)
    //이미 다른 좌석이 있는지 확인 if(aleadyUseSeat != 0)
    console.log(MemberId)
    console.log(MemberId.value.length == 0)
    if(MemberId.value.length != 0){
        let check = confirm(e.textContent + " 번 좌석을 선택 하시겠습니까?")
        if(aleadyUseSeat == 0 & check != 0){
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
    }   else   alert("로그인 상태를 확인하세요!");
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

    //console.log($(e).attr('value') == MemberId.value)
    //console.log(typeof $(e).attr('value'))
    //console.log(typeof MemberId.value)

    let check

    if($(e).attr('value') == MemberId.value){
        check = confirm(e.textContent + " 번 좌석을 반납하시겠습니까?")
    }else{
        return
    }

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
                result = data.filter( f => f.seatNumber != 0)
                console.log('통신 성공');
            },
            error: function(){
                console.log('통신 에러');
            }
    })

    return result
}

function addEventSeat(seats, seatByState, MemberId){

   let arrSeat = [];
   let arrState = [];

    seats.forEach( seat => {
        arrSeat.push(parseInt(seat.textContent))
    })

    seatByState.forEach( state => {
        arrState.push(state.seatNumber)
    })

    let SeatA = arrSeat.filter( value => arrState.includes(value))
    // 사용중인 좌석
    let SeatB = arrSeat.filter( value => !arrState.includes(value))
    // 사용중이지 않은 좌석
    console.log(SeatA)
    console.log(SeatB)

    //let t = seatByState.filter( f => f.seatNumber == seat[a-1].textContent)

    //console.log(t)

    // 사용중인 좌석
    // [1, 2, 3, 4, 5, 9]
    SeatA.forEach( a => {
        seats[a-1].style.backgroundColor = "red"

        let t = seatByState.filter( f => f.seatNumber == seat[a-1].textContent)

        console.log(t[0].seatNumber)

        $(seats[a-1]).attr("value",t[0].id)
        console.log(seat[a-1])
        seats[a-1].addEventListener("click", () => {
            recoverSeat(seats[a-1], MemberId)
        })
    })

    // 사용중이지 않은 좌석
    // [6, 7, 8, 10]
    SeatB.forEach( b => {
        seats[b-1].style.backgroundColor = "green"
        seats[b-1].addEventListener("click", () => {
                    selectMemberSeat(seats[b-1], MemberId)
        })
    })

}

function seatBoard(seats, seatByState, MemberId){

   let arrSeat = [];
   let arrState = [];
   let userseatNumber;

   if(MemberId.value.length != 0){
        userseatNumber = findMeberSeat(MemberId)
   }else{
        userseatNumber = 0
   }

    seats.forEach( seat => {
        arrSeat.push(parseInt(seat.textContent))
    })

    seatByState.forEach( state => {
        arrState.push(state.seatNumber)
    })

    let SeatA = arrSeat.filter( value => arrState.includes(value))
    // 사용중인 좌석
    let SeatB = arrSeat.filter( value => !arrState.includes(value))
    // 사용중이지 않은 좌석
    console.log(SeatA)
    console.log(SeatB)

    if(MemberId.value.length != 0 & userseatNumber != 0){
        userSeat = document.getElementById("userseat")
        userSeat.textContent = MemberId.value + " 님의 사용중인 좌석은 " + userseatNumber + " 번 입니다."
    }

    useboard = document.getElementById("useseat")
    useboard.textContent = "현재 사용중인 좌석 : " + SeatA.length

    notuseboard = document.getElementById("notuseSeat")
    notuseboard.textContent = "현재 사용 가능한 좌석 : " + SeatB.length
}


