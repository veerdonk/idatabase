/**
 * Created by dvandeveerdonk on 23-3-17.
 * used for autocomplteting fields in browser
 * and more basic functionality
 */
window.onload = function(){
    console.log("iDatabase.js loaded");
    $('#geneId').autocomplete({
        serviceUrl: '/search/forGene',
        lookupLimit: 50,
        minChars: 2,
        onSelect: function (suggestion) {
            geneToUse = suggestion;
        }
    });

    function fillForm(){
        var snp = $('#snpName').val();
        $.ajax({
            url: "http://myvariant.info/v1/query?q="+ snp +"\&fields=dbsnp"
        }).then(function (response) {
            var geneName = response.hits[0].dbsnp.gene.symbol;
            $('#geneId').val(geneName);
            $('#geneForm').submit();
        })
    }
    $('#snpForm').submit(function(e){
        e.preventDefault();
        fillForm();
    });
    //
    // $('#submitSNP').click(function () {
    //     fillForm();
    //     // var snp = $('#snpName').val();
    //     // $.ajax({
    //     //     url: "http://myvariant.info/v1/query?q="+ snp +"\&fields=dbsnp"
    //     // }).then(function (response) {
    //     //     var geneName = response.hits[0].dbsnp.gene.symbol;
    //     //     $('#geneId').val(geneName);
    //     //     $('#geneForm').submit();
    //     // })
    // });

    // $('#geneForm').submit(function (e) {
    //     var geneId = $('#geneId').val();
    //     var input = $(this).find('input[name=geneId]');
    //
    //     if(geneId.match('rs\d*')){
    //         $.ajax({
    //             url: "http://myvariant.info/v1/query?q="+ geneId +"\&fields=dbsnp"
    //         }).then(function (response) {
    //             var geneName = response.hits[0].dbsnp.gene.symbol;
    //             $(input).val(geneName);
    //         })
    //     }
    //     return true;
    // });

    $('#snpSearchTab').click(function () {
        console.log("snpsearch clicked");
        $('#snpSearch').show();
        $('#degSearch').hide();
        $('#tab2').removeClass("active");
        $('#tab1').addClass("active");
    });

    $('#degSearchTab').click(function () {
        $('#snpSearch').hide();
        $('#degSearch').show();
        $('#tab1').removeClass("active");
        $('#tab2').addClass("active");
    });

    $('#addRegionButton').click(function () {
        $('.addRegion').toggle();
    });

};