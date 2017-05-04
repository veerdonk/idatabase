/**
 * Created by dvandeveerdonk on 3-5-17.
 */
window.onload = function() {
    var bpId = $('.boxplot').first().attr('id');

    $.ajax({
        url: "/search/boxplotData?geneName="+bpId
    }).then(function (response) {

    });

    // var data = [{
    //     y: [1, 2, 3, 4, 4, 4, 8, 9, 10],
    //     type: 'box',
    //     name: 'test 1'
    // }, {
    //     y: [2, 3, 3, 3, 3, 5, 6, 6, 7],
    //     type: 'box',
    //     name: 'Set 2'
    // }];
    //
    // var layout = {
    //     title: 'Box Plot'
    // };
    //
    // Plotly.newPlot(bpId, data, layout)


    var data = [
        {
            z: [[1.66328471823933, 2.16203038873149], [1.68800016225311,], [2.09209645744887, 1.94890829666134], [2.31512417251552,]],
            x: ['4h', '24h'],
            y: ["Candida<br>albicans", 'Mycobacterium<br>tuberculosis', 'Pseudomonas<br>aeruginosa', 'Streptococcus<br>pneumoniae', 'Aspergillus<br>fumigatus', 'IL-1alpha'],
            type: 'heatmap'
        }
    ];
    var layout ={
            title: 'ENSG00000222179',
            pad: 75,
            l: 200,
            r: 100,
            t: 50,
            b: 50
    };


    Plotly.newPlot(bpId, data, layout);
};