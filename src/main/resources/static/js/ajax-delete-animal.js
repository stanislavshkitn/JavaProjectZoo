function  delete_animal()
{
    var current = $(this);
    var name = current.data('name');

    $.ajax({
        url : "/delete-animal/" + name + "/",
        type : 'POST',
        data : { 'pk' : name },
        success : function (json) {
            $("[data-animal='"+ name +  "']").remove();
            for(i=0;i<LOCATION_ANIMAL.length;i++){
                  if(LOCATION_ANIMAL[i].name == name){
                      LOCATION_ANIMAL.splice(i,1)
                  }
            }
        }
    });
    return false;
}

$(function(){
    $('.tbl_animals').on('click', '.delete-animal', delete_animal);
});