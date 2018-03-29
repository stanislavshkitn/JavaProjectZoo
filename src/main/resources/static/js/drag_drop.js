

function init_drag_drop() {
    $(".animal").draggable({
        revert:'invalid',
        start:function () {
            $(this).css({'width':'180px'})
        }
    });
    $(".tbl_animals").droppable({
        drop:function (event,ui) {
            for(i=0;i<LOCATION_ANIMAL.length;i++){
                if(LOCATION_ANIMAL[i].name == ui.draggable.attr("data-animal")){
                    LOCATION_ANIMAL[i].aviary = 0
                }
            }
            /*for(i=0;i<LOCATION_ANIMAL.length;i++){

                console.log(LOCATION_ANIMAL[i].name);
                console.log(LOCATION_ANIMAL[i].aviary);
                console.log("-------------------------------------")

            }*/
        }
    });

    $(".aviary").droppable({
        drop:function (event, ui) {
            for(i=0;i<LOCATION_ANIMAL.length;i++){
                if(LOCATION_ANIMAL[i].name == ui.draggable.attr("data-animal")){
                    LOCATION_ANIMAL[i].aviary = $(this).attr("data-number")
                }
            }
            if(ui.draggable.attr("data-is-predator") == "true"){
                $(this).addClass("aviary-predator")
            }else{
                $(this).addClass("aviary-not-predator")
            }
            /*for(i=0;i<LOCATION_ANIMAL.length;i++){

                console.log(LOCATION_ANIMAL[i].name);
                console.log(LOCATION_ANIMAL[i].aviary);
                console.log(LOCATION_ANIMAL[i].is_predator);
                console.log("-------------------------------------")
            }
            console.log("**************************************************")*/
        }

    });

};
$(function () {
   init_drag_drop();
});
