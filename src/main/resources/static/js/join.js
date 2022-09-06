window.addEventListener("load", function(event) {
    let idcheck = document.getElementById("idcheck");

    idcheck.addEventListener("click", () => {
        idCheck()
    })
})

function idCheck(){
    let id = document.getElementById("id")
    console.log(id.value);

    $.ajax({
        type: "GET",
        url: "members/AllfindSeat",
        async: false,
        success : function(data){
            result = data.filter( f => f.seatNumber != 0)
            console.log('통신 성공');
        },
        error: function(){
            console.log('통신 에러');
        }
    })
}