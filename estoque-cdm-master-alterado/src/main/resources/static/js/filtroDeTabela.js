/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    $(".filtro-input").on('input', function () {
        // sempre que digitar
        $(".filtro-campo").each(function (index) {
            if (this.innerHTML.toLowerCase().indexOf($(".filtro-input").val().toLowerCase()) >= 0) {
//                            alert('contem');
                $(this).show();
            } else {
                $(this).hide();
//                            alert('nao contem');
            }
        });
    })
});