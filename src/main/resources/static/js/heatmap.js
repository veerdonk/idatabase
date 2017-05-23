/**
 * Created by dvandeveerdonk on 3-5-17.
 */
window.onload = function() {
    var snpId = $('.snps').first().attr('id');
    var region = $('.region').first().attr('id');



    if(region!=null){
        ajaxUrl = '/api/getHeatmapSnpFromDb?qtlId='+snpId+'&region='+region;
        tableUrl = '/api/tableData?qtlId='+snpId+'&region='+region;
    }
    else{
        ajaxUrl = '/api/getHeatmapSnpFromDb?qtlId='+snpId;
        tableUrl = '/api/tableData?qtlId='+snpId;
    }

    $('#snpTable').DataTable({
        "ajax" : tableUrl,
        "columns" : [
            {"data": "snp"},
            {"data": "cell_type"},
            {"data": "pval"},
            {"data": "qtl_type"}
        ]
    });



    $.ajax({
        url: ajaxUrl
    }).then(function (response) {
        var data = [response];
        Plotly.newPlot('heatmap', data);
    });



};