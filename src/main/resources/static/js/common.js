window.addEventListener("load", function(event) {
    setInterval(() => {
        seatEndTime();
    },30000)
})

async function seatEndTime() {
    const promise = new Promise((resolve) => {
            resolve('success')
    });

    promise.then(() => {
        $.ajax({
                    type: "post",
                    url: "/seat/EndTimeCheck",
                    async: false,
                    success : function(data){
                        console.log('통신 성공');
                        location.reload()
                    },
                    error: function(){
                        console.log('통신 에러');
                    }
            })
    })
}

