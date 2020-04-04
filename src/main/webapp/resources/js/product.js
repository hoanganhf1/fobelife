$(document).ready(function() {

    $('.product-removal button').click(function() {
        var proCode = $(this).parent().children('.product-code').val();
        var pStatus = $(this).text();
        
        $.ajax({
            type: 'PUT',
            contentType : 'application/json',
            url: '/api/product',
            data: JSON.stringify({
                'code': proCode,
                'status': pStatus
            }),
            success: function(rep) {
                window.location.href='/product';
            }
        });
    });

});
