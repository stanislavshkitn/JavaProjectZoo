function  add_animal()
{
    var name = $('#form-name').val() || null;
    var is_predator = $('#form-is_predator').is(':checked');
    console.log(name);
    console.log(is_predator);
    if(name != null) {
        console.log('ajax');
        $.ajax({
            url: "/add-animal/",
            type: 'POST',
            data: {'name': name, 'is_predator': is_predator},
            success: function (json) {
                if(json.result == "success") {
                    var animal_name = json.data.name;
                    var animal_is_predator = json.data.is_predator;
                    var animal = {};
                    animal['name'] = animal_name;
                    animal['aviary'] = 0;
                    animal['is_predator'] = animal_is_predator.toString();
                    LOCATION_ANIMAL.push(animal);

                    if (json.data.is_predator) {
                        $(".tbl_animals").append(
                            '<div data-is-predator="' + animal_is_predator + '"  data-animal="' + name + '" class="predator animal">\n' +
                            '                        <div class="name-animal">' + name + '</div>\n' +
                            '                        <a class="delete-animal" id="id_animal_' + name + '" data-name="' + name + '" href="javascript:">Удалить</a>\n' +
                            '                    </div>'
                        )
                    } else {
                        $(".tbl_animals").append(
                            '<div data-is-predator="' + animal_is_predator + '" data-animal="' + name + '" class="not-predator animal">\n' +
                            '                        <div class="name-animal">' + name + '</div>\n' +
                            '                        <a class="delete-animal" id="id_animal_' + name + '" data-name="' + name + '" href="javascript:">Удалить</a>\n' +
                            '                    </div>'
                        )
                    }
                    init_drag_drop();
                }else{
                    alert(json.message);
                }
            }
        });
    }else {
        alert("Введите данные!!!")
    }
    return false;
}

$(function(){
    $(".form-add-animal").on('click', '#button-add-ajax', add_animal);
});