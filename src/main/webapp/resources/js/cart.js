
function updateQuantity(productCode) {
    var price = $('#' + productCode + '-price').text().trim().split(" ")[0].replace(".", "");
    var quantity = $('#' + productCode + '-quantity').val();
    var linePrice = price * quantity;
    $('#' + productCode + '-line-price').text(numeral(linePrice).format("0,0") + " VND");
    $('#' + productCode + '-order').val(productCode + ' ' + quantity + ' ' + linePrice);
    recalculateCart();
}

function recalculateCart() {
    var total = 0;
    $('.product-order').each(function() {
        var res = $(this).val().split(' ');
        total += parseFloat(res[2]);
    });
    $('#cart-total').html(numeral(total).format("0,0") + " VND");
    $('.cart-total').val(total);
    if (total > 0) {
        $('#checkout-cart').prop('disabled', false);
    } else {
        $('#checkout-cart').prop('disabled', true);
    }
}