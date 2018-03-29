function save_location() {
    var list_name =[];
    var list_aviary =[];
    var list_is_predator =[];
    var count_aviary = $(".count-aviary").text().toString();
    var name_location = $("#form-name-location").val() || null;
    if (name_location!=null) {
        for (i = 0; i < LOCATION_ANIMAL.length; i++) {
            list_name.push(LOCATION_ANIMAL[i].name.toString());
            list_aviary.push(LOCATION_ANIMAL[i].aviary.toString());
            list_is_predator.push(LOCATION_ANIMAL[i].is_predator.toString());
        }
        $.ajax({
            url: "/save-location/",
            type: 'POST',
            data: {
                listName: list_name, listAviary: list_aviary,
                listIsPredator: list_is_predator, "countAviary": count_aviary, "nameLocation":name_location
            },
            success: function (json) {
                if(json.result == "success"){
                    window.location.replace("http://localhost:8080/list-location/");
                }else{
                    $(".block-error").html(
                        '<div class="error-message">'+json.message+'</div>'
                    );
                }
            }
        });
    }else{
        alert("Придумайте и введите имя")
    }
}

$(function(){
    $(".block-save").on('click', '#button-save-location', save_location);
});