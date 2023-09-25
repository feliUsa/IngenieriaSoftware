var urlRest = 'https://g052b7fcd819cb6-mivecidb.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/clientes/clientes';

$(document).ready(function () {
    cargarDatosTable();
});

function cargarDatosTable() {
    $.ajax({
        url: urlRest,
        type: 'GET',
        datatype: 'JSON',
        success: function (response) {
            var myItems = response.items;
            var valor = '';
            for (let i = 0; i < myItems.length; i++) {
                valor += '<tr>'+
                    '<td>'+ myItems[i].nombre +'</td >'+
                    '<td>'+ myItems[i].apellido +'</td>'+
                    '<td>'+ myItems[i].email +'</td>'+
                    '<td>'+ myItems[i].barrio +'</td>'+
                    '<td>'+ myItems[i].clave +'</td>'+
                    '<td>'+ myItems[i].rol +'</td>'+
                    '<td>'+ myItems[i].id +'</td>'+
                '</tr >';
            }
            $('#tbodyCliente').html(valor);
        }
    })
}