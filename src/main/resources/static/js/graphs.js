/**
 * Created by dvandeveerdonk on 3-4-17.
 */
window.onload = function () {
    $.ajax({
        url: "/getPathogenData"
    }).then(function (response) {

        var chart = new CanvasJS.Chart("chartContainer", {
            title: {
                text: "DE genes per stress factor"
            },
            data: [
                {
                    type: "column",
                    dataPoints: response
                }
            ]
        });
        chart.render();
    });
};