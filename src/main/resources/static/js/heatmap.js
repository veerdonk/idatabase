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

    });

};