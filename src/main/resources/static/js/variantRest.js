/**
 * Created by dvandeveerdonk on 3-4-17.
 */
window.onload = function () {
    $('#test').click(function () {
        var form = document.getElementById("geneForm");
        var snp = form.elements[1].value;
        $.ajax({
            url: "http://myvariant.info/v1/query?q="+snp+"\&fields=dbsnp"
        }).then(function (response) {
            $('#responseTest').html(response.hits[0].dbsnp.gene.symbol);
        })
    })
}