/**
 * Created by dvandeveerdonk on 23-3-17.
 * used for autocomplteting fields in browser
 */
window.onload = function() {
    $('#geneId').autocomplete({
        serviceUrl: '/search/forGene',
        onSelect: function (suggestion) {
            geneToUse = suggestion;
        }
    });
}