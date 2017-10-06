/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function confirmarRecebimento(event, id, nome,idForm) {
    event.preventDefault();
    swal({
        title: "Você tem certeza ?",
        text: "Você recebeu o " + nome + " #" + id + " ?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Sim",
        closeOnConfirm: false
    },
            function () {
                $('#'+idForm).submit();
            });
}