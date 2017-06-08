/**
 * Created by dvandeveerdonk on 8-6-17.
 */
window.onload = function () {
    console.log('geneResults.js started');
    var gene = $('.geneId').first().attr('id');
    var ajaxUrl = "/get/genesTable?id="+gene;

    $('#testTable').DataTable({
        dom: 'lBfrtip',
        buttons:[
            {
                extend: 'collection',
                text: 'Export <i class="fa-caret-down"></i>',
                background: false,
                buttons: [ 'csv', 'excel', 'pdf', 'copy' ]
            }],
        "ajax" : {
            url: ajaxUrl,
            error: function (xhr, error, thrown) {
                $('#noDataError').show();
            }},
        "columns" : [
            {"data": "stressFactor"},
            {"data": "timepoint"},
            {"data": "ensId"},
            {"data": "log2fold"},
            {"data": "pval"},
            {"data": "geneName"}
        ]
    })
};
