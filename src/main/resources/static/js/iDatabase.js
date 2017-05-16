/**
 * Created by dvandeveerdonk on 23-3-17.
 * used for autocomplteting fields in browser
 */
window.onload = function() {
    $('#geneId').autocomplete({
        serviceUrl: '/search/forGene',
        lookupLimit: 50,
        minChars: 2,
        onSelect: function (suggestion) {
            geneToUse = suggestion;
        }
    });

    $('#submitSNP').click(function () {
        var snp = $('#snpName').val();
        $.ajax({
            url: "http://myvariant.info/v1/query?q="+ snp +"\&fields=dbsnp"
        }).then(function (response) {
            var geneName = response.hits[0].dbsnp.gene.symbol;
            $('#geneId').val(geneName);
            $('#geneForm').submit();
        })
    });

    $('#switch').click(function () {
        $('.hidden').toggle();
        $('.shown').toggle();
    });

};