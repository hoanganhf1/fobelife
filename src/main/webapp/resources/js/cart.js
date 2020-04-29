
function updateQuantity(productCode) {
    var price = $('#' + productCode + '-price').text().replace("đ", "").replace(".", "").trim();
    var quantity = $('#' + productCode + '-quantity').val();
    var linePrice = price * quantity;
    $('#' + productCode + '-line-price').text(new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(linePrice));
    $('#' + productCode + '-order').val(productCode + ' ' + quantity + ' ' + linePrice);
    recalculateCart();
}

function recalculateCart() {
    var total = 0;
    $('.product-order').each(function() {
        var res = $(this).val().split(' ');
        total += parseFloat(res[2]);
    });
    $('#cart-total').html(new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(total));
    $('.cart-total').val(total);
    if (total > 0) {
        $('#checkout-cart').prop('disabled', false);
    } else {
        $('#checkout-cart').prop('disabled', true);
    }
}

function reviewCart() {

    $('#cartReviewTable > tbody').empty();
    $('#cartTable > tbody > tr').each(function() {
        var linePrice = $(this).find('.product-line-price').text().replace("đ", "").replace(".", "").trim();
        if (parseFloat(linePrice) > 0) {
            var row = $(this).clone();
            $('#cartReviewTable > tbody').append(row);
        }
    });

    return false;
}