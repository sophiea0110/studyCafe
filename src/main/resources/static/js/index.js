window.addEventListener("load", function(event) {
    const slides = $('.slides')
    const position = [0, -700, -1400]
    var currentPosition = 0;

    $('.btn-next').click(function(){
        if(currentPosition < position.length -1){
            currentPosition++
        }else {
            currentPosition = 0;
        }
        slides.animate( {left : position[currentPosition]}, 500)
    })

    $('.btn-prev').click(function(){
        if(currentPosition > 0){
            currentPosition--
        }else {
            currentPosition = 0;
        }
        slides.animate( {left : position[currentPosition]}, 500)
    })


})