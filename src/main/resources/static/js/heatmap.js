/**
 * Created by dvandeveerdonk on 3-5-17.
 */
window.onload = function() {
    console.log('Heatmap.js started');
    var snpId = $('.snps').first().attr('id');
    var region = $('.region').first().attr('id');

    if(region!=null){
        tableUrl = '/api/tableData?qtlId='+snpId+'&region='+region;
    }
    else{
        tableUrl = '/api/tableData?qtlId='+snpId;
    }

    $('#snpTable').DataTable({
        dom: 'lB<"toolbar">frtip',
        buttons:[
            {
                extend: 'collection',
                text: 'Export',
                buttons: [ 'csv', 'excel', 'pdf', 'copy' ]
            },
            {
                extend: 'collection',
                text: 'Generate Heatmap',
                buttons: [
                    {
                        text: 'Cytokines',
                        action: function(){
                            drawHeatmap('cytokine')
                        }
                    },
                    {
                        text: 'Cell Counts',
                        action: function(){
                            drawHeatmap('cellCount')
                        }
                    },
                    {
                        text: 'Hormones',
                        action: function(){
                            drawHeatmap('hormones')
                        }
                    },
                    {
                        text: 'Modulators',
                        action: function(){
                            drawHeatmap('immuneModulator')
                        }
                    }
                ]
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

    function drawHeatmap(qtl){
        console.log(qtl + ' clicked');
        console.log(qtl);
        if(region!=null){
            ajaxUrl = '/api/getHeatmapSnpFromDb?qtlId='+snpId+'&qtl='+qtl+'&region='+region;
            console.log(ajaxUrl);
        }
        else{
            ajaxUrl = '/api/getHeatmapSnpFromDb?qtlId='+snpId+'&qtl='+qtl;
        }
        $.ajax({
            url: ajaxUrl
        }).then(function (response) {
            console.log(response);
            var data = [response[0]];
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
            var pos = 0;
            var max = response.length;
            Plotly.newPlot('heatmap', [response[pos]], layout);
            $('.hidden').addClass('pagebutton');
            window.scrollTo(0,document.body.scrollHeight);
            $('#next15').click(function () {
                var r = $.Deferred();
                pos += 1;
                if(pos >= max-1){
                    pos = max-1;
                }
                console.log([response[pos]]);
                Plotly.newPlot('heatmap', [response[pos]], layout);
                r.resolve;
            });

            $('#prev15').click(function () {
                pos -= 1;
                if(pos <= 0){
                    pos = 0;
                }
                Plotly.newPlot('heatmap', [response[pos]], layout);
            })
        });
    }
    // $('.selectedQtl').click(function () {
    //     console.log(this.id + ' clicked');
    //     var qtl = this.id;
    //     console.log(qtl);
    //     if(region!=null){
    //         ajaxUrl = '/api/getHeatmapSnpFromDb?qtlId='+snpId+'&qtl='+qtl+'&region='+region;
    //         console.log(ajaxUrl);
    //     }
    //     else{
    //         ajaxUrl = '/api/getHeatmapSnpFromDb?qtlId='+snpId+'&qtl='+qtl;
    //     }
    //     $.ajax({
    //         url: ajaxUrl
    //     }).then(function (response) {
    //         console.log(response);
    //         var data = [response[0]];
    //         var h = data[0]["y"].length*17.5;
    //         var w = data[0]["x"].length*20;
    //
    //         if(h<100){
    //             h=100;
    //         }
    //
    //         if(h>3500){
    //             $('#tooMuchDataError').toggle();
    //             h=3500;
    //         }
    //         if(w<600){
    //             w=600;
    //         }
    //
    //         var layout = {
    //             margin: {
    //                 l: 100,
    //                 r: 100,
    //                 b: 250,
    //                 t: 20,
    //                 pad: 4
    //             },
    //             height: h+250,
    //             width: w
    //
    //         };
    //         var pos = 0;
    //         var max = response.length;
    //         Plotly.newPlot('heatmap', [response[pos]], layout);
    //         $('.hidden').addClass('pagebutton');
    //         window.scrollTo(0,document.body.scrollHeight);
    //         $('#next15').click(function () {
    //             var r = $.Deferred();
    //             pos += 1;
    //             if(pos >= max-1){
    //                 pos = max-1;
    //             }
    //             console.log([response[pos]]);
    //             Plotly.newPlot('heatmap', [response[pos]], layout);
    //             r.resolve;
    //         });
    //
    //         $('#prev15').click(function () {
    //             pos -= 1;
    //             if(pos <= 0){
    //                 pos = 0;
    //             }
    //             Plotly.newPlot('heatmap', [response[pos]], layout);
    //         })
    //     });
    // });




};

