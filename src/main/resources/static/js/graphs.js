/**
 * Created by dvandeveerdonk on 3-4-17.
 */
window.onload = function () {
    $.ajax({
        url: "/getPathogenData"
    }).then(function (response) {
        var jsonResponse = jQuery.parseJSON(response);
        var testData = jQuery.parseJSON('{"organism":"Aspergillus_fumigatus","timepoint":"4h","numberOfGenes":876}');
        $('#data').html(Object.keys(jsonResponse));
        // $('#test').html(testData.organism);
        var labels = new Array();
        var values = new Array();
        for (var i in jsonResponse) {
            labels.push(jsonResponse[i].organism);
            values.push(jsonResponse[i].numberOfGenes);
        }
        var chart = new CanvasJS.Chart("chartContainer", {
            title: {
                text: "DE genes per stress factor"
            },
            data: [
                {
                    type: "column",
                    dataPoints: [
                        {label: "eerste", y: 10},
                        {label: "tweede", y: 12},
                        {label: "derde", y: 13}
                        ]
                }
            ]
        });
        chart.render();
    });
};