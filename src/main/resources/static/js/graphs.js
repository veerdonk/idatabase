/**
 * Created by dvandeveerdonk on 3-4-17.
 */
window.onload = function () {
    $.ajax({
        url: "/getPathogenData"
    }).then(function (response) {
        // var jsonResponse = jQuery.parseJSON(response);
        // var testData = jQuery.parseJSON('{"organism":"Aspergillus_fumigatus","timepoint":"4h","numberOfGenes":876}');
        // $('#data').html(Object.keys(jsonResponse));
        // // $('#test').html(testData.organism);
        // var labels = new Array();
        // var values = new Array();
        // for (var i in jsonResponse) {
        //     labels.push(jsonResponse[i].organism);
        //     values.push(jsonResponse[i].numberOfGenes);
        // }
        var chart = new CanvasJS.Chart("chartContainer", {
            title: {
                text: "DE genes per stress factor"
            },
            data: [
                {
                    type: "column",
                    dataPoints: [
                        {label: 'Aspergillus_fumigatus4h', y: 876},
                        {label: 'Aspergillus_fumigatus24h', y: 594},
                        {label: 'Candida_albicans4h', y: 850},
                        {label: 'Candida_albicans24h', y: 2511},
                        {label: 'IL-1alpha4h', y: 175},
                        {label: 'IL-1alpha24h', y: 746},
                        {label: 'Mycobacterium_tuberculosis4h', y: 1456},
                        {label: 'Mycobacterium_tuberculosis24h', y: 1921},
                        {label: 'Pseudomonas_aeruginosa4h', y: 2921},
                        {label: 'Pseudomonas_aeruginosa24h', y: 2970},
                        {label: 'Streptococcus_pneumoniae4h', y: 1295},
                        {label: 'Streptococcus_pneumoniae24h', y: 2184}
                    ]
                }
            ]
        });
        chart.render();
    });
};