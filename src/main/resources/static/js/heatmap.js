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
        dom: 'lBfrtip',
        buttons:[
            {
                extend: 'collection',
                text: 'Export <i class="fa-caret-down"></i>',
                background: false,
                buttons: [ 'csv', 'excel', 'pdf', 'copy' ]
            },
            {
                extend: 'collection',
                text: 'Generate Heatmap <i class="fa-caret-down"></i>',
                background: false,
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
                    },
                    {
                        text: 'Platelets',
                        action: function(){
                            drawHeatmap('platelet')
                        }
                    }

                ]
            }
        ],
        "ajax" : {
            url: tableUrl,
            error: function (xhr, error, thrown) {
                $('#noDataError').show();
        }},
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
        }
        else{
            ajaxUrl = '/api/getHeatmapSnpFromDb?qtlId='+snpId+'&qtl='+qtl;
        }
        console.log(ajaxUrl);
        $.ajax({
            url: ajaxUrl
        }).then(function (response) {
            if(response[0]["y"].length >= 1) {
                $('#noHeatmapData').hide();
                console.log(response);
                var data = [response[0]];
                var h = data[0]["y"].length * 17.5;
                var w = data[0]["x"].length * 20;

                if (h < 200) {
                    h = 200;
                }
                if (w < 600) {
                    w = 600;
                }

                var layout = {
                    title: response[0]["qtl"],
                    margin: {
                        l: 100,
                        r: 100,
                        b: 250,
                        t: 70,
                        pad: 4
                    },
                    height: h + 250,
                    width: w

                };
                var pos = 0;
                var max = response.length;
                Plotly.newPlot('heatmap', [response[pos]], layout);
                if (max > 1) {
                    $('.notDisplayed').addClass('btn');
                    $('.notDisplayed').addClass('btn-default');
                }

                window.scrollTo(0, document.body.scrollHeight);

                $('#showAll').click(function () {
                    var allz = [];
                    var ally = [];
                    for (hmd in response) {
                        for (arr in response[hmd]["z"]) {
                            allz.push(response[hmd]["z"][arr]);
                            ally.push(response[hmd]["y"][arr]);
                        }
                    }
                    var allData = [{
                        x: data[0]["x"],
                        y: ally,
                        z: allz,
                        type: 'heatmap',
                        colorscale: data[0]["colorscale"]
                    }];
                    layout.height = ally.length * 17.5;
                    Plotly.newPlot('heatmap', allData, layout);
                });

                $('#next15').click(function () {
                    var r = $.Deferred();
                    pos += 1;
                    if (pos >= max - 1) {
                        pos = max - 1;
                    }
                    layout.height = response[pos]["y"].length * 17.5 + 250;
                    Plotly.newPlot('heatmap', [response[pos]], layout);
                    r.resolve();
                });

                $('#prev15').click(function () {
                    pos -= 1;
                    if (pos <= 0) {
                        pos = 0;
                    }
                    layout.height = response[pos]["y"].length * 17.5 + 250;
                    Plotly.newPlot('heatmap', [response[pos]], layout);
                })
            }
            else{
                $('#noHeatmapData').show();
            }
        });

    }
};

