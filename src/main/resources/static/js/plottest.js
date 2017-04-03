/**
 * Created by dvandeveerdonk on 29-3-17.
 */
window.onload=function () {
    var d1 = [[1,2], [2,3], [3,2]];
    var d2 = [ { label: "Foo", data: [ [10, 1], [17, -14], [30, 5] ] },
        { label: "Bar", data: [ [11, 13], [19, 11], [30, -7] ] }
    ]
    $.plot($("#stressPlot"),[{
        data: d2,
        bars: {
            show: true
        }
    }])
}