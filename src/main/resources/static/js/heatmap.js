/**
 * Created by dvandeveerdonk on 3-5-17.
 */
window.onload = function() {
    var snpId = $('.snps').first().attr('id');
    var region = $('.region').first().attr('id');

    if(region!=null){
        ajaxUrl = '/api/getSnpFromDb?qtlId='+snpId+'&region='+region;
    }
    else{
        ajaxUrl = '/api/getSnpFromDb?qtlId='+snpId
    }
    $.ajax({
        url: ajaxUrl
    }).then(function (response) {
        alert(response.snp[0])
    });

    var data = [
        {
            z: [[1, 20, 30, 50, 1], [20, 1, 60, 80, 30], [30, 60, 1, -10, 20]],
            x: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'],
            y: ['Morning', 'Afternoon', 'Evening'],
            type: 'heatmap'
        }
    ];

    Plotly.newPlot('heatmap', data);
};