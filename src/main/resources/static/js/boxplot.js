/**
 * Created by dvandeveerdonk on 3-5-17.
 */
window.onload = function() {
    var bpId = $('.boxplot').first().attr('id');

    $.ajax({
        url: "/search/boxplotData?geneName="+bpId
    }).then(function (response) {

    });

    var data = [{
        x: [1, 2, 3, 4, 4, 4, 8, 9, 10],
        type: 'box',
        name: 'test 1'
    }, {
        x: [2, 3, 3, 3, 3, 5, 6, 6, 7],
        type: 'box',
        name: 'Set 2'
    }];

    var layout = {
        title: 'Horizontal Box Plot'
    };

    Plotly.newPlot(bpId, data, layout)

};