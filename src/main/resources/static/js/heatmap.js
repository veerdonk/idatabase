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
        dom: 'lBfrtip',

        buttons:[
            {
                extend: 'collection',
                text: 'Export',
                buttons: [ 'csv', 'excel', 'pdf', 'copy' ]
            }
        ],
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
        var h = data[0]["y"].length*17.5;
        var w = data[0]["x"].length*20;
        if(h<100){
            h=100;
        }
        if(h>3500){
            $('#tooMuchDataError').toggle();
            h=3500;
        }
        if(w<600){
            w=600;
        }
        console.log(data[0]["x"].length);
        console.log(w);
        var layout = {
            margin: {
                l: 100,
                r: 100,
                b: 250,
                t: 20,
                pad: 4
            },
            height: h+250,
            width: w

        };

        Plotly.newPlot('heatmap', data, layout);
    });

    var scrollInfo = $('.scrollingBar');
    var scrollClass = 'scrollingBar-scrolled';

    $(window).scroll(function(){
        console.log($(this).scrollTop.height());
        if($(this).scrollTop > 217){
            console.log($(this).scrollTop);
            scrollInfo.addClass(scrollClass);
        }else {
            scrollInfo.removeClass(scrollClass);
        }
    });

};

