/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function confirmarExclusao(event, id, nome) {
    event.preventDefault();
    swal({
        title: "Você tem certeza ?",
        text: "Deja realmente excluir " + nome + " #" + id + " ?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Sim, exclua isto!",
        closeOnConfirm: false
    },
            function () {
                $('#formExcluir' + id).submit();
            });
}