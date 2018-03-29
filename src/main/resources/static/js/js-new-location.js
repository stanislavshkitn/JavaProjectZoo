var LOCATION_ANIMAL = [];
$(function () {
    $(".tbl_animals > div").each(function (index, element) {
        var animal = {};
        animal['name'] = $(element).attr('data-animal');
        animal['aviary'] = 0;
        animal['is_predator'] = $(element).attr('data-is-predator');
        LOCATION_ANIMAL.push(animal)
    });
});

function block_save_location() {
    $("#show-save-location").remove();
    $(".block-save").html(
        'Имя:\n' +
        '                    <input name="name-location" type="text" id="form-name-location">\n' +
        '                    <a class="button" id="button-save-location" href="javascript:">Сохранить</a>'
    );
    return false;
}

function block_add_animal() {
    $("#button-add-show").remove();
    $(".form-add-animal").html(
        '                   <form action="">\n' +
        '                        <div class="field">\n' +
        '                            Название: <input type="text" id="form-name" name="name">\n' +
        '                        </div>\n' +
        '                        <div class="field">\n' +
        '                            Хищник: <input type="checkbox" id="form-is_predator" name="is_predator">\n' +
        '                        </div>\n' +
        '                    </form>\n' +
        '                        <a class="button" id="button-add-ajax" href="javascript:">Добавить</a>'

    );
    return false;
}

function block_alter_count() {
    $(".block-count-aviary").html(
        '<form action="">\n' +
        '                Кол-во вольеров: <input type="text" name="count-aviary" id="form-count-aviary">\n' +
        '                <a class="button" id="button-alter-count-click">Изменить</a>\n' +
        '            </form>'
    )
}

function click_alter_count() {
    var count = $("#form-count-aviary").val() || null;
    if (count != null && Number(count) > 0){
        $(".block-count-aviary").html(
            'Количество вольеров: <div class="count-aviary">'+ count +'</div>\n' +
            '            <a class="button" id="button-alter-count-show">Изменить</a>'
        )
        $(".block_aviary").empty();
        for(i=1;i<Number(count)+1;i++){
            $(".block_aviary").append(
                '<div class="aviary" id="id-aviary-'+ i +'" data-number="'+ i +'">\n' +
                '\n' +
                '                </div>'
            )
        }
        init_drag_drop();
    }else{
        alert("Введите верные данные!!!")
    }

}
$(function(){
    $('#button-add-show').click(block_add_animal);
    $(".block-count-aviary").on('click', '#button-alter-count-show', block_alter_count);
    $(".block-count-aviary").on('click', '#button-alter-count-click', click_alter_count);
    $("#show-save-location").click(block_save_location);
});