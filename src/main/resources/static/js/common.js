window.addEventListener("load", function(event) {
    console.log("hi");
    setInterval(() => {
        seatEndTime();
    },1000)
})

async function seatEndTime() {
    const promise = new Promise((resolve) => {
        setTimeout(() => {
            resolve('success')
         }, 60000);
    });

    promise.then(() => {
        $.ajax({
                    type: "post",
                    url: "/seat/EndTimeCheck",
                    async: true,
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

