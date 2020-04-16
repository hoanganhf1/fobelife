
function updateQuantity(productCode) {
    var price = $('#' + productCode + '-price').text();
    var quantity = $('#' + productCode + '-quantity').val();
    var linePrice = price * quantity;
    $('#' + productCode + '-line-price').text(linePrice);
    $('#' + productCode + '-order').val(productCode + ' ' + quantity + ' ' + linePrice);
    recalculateCart();
}

function recalculateCart() {
    var total = 0;
    $('.product-order').each(function() {
        var res = $(this).val().split(' ');
        total += parseFloat(res[2]);
    });
    $('#cart-total').html(total);
    $('.cart-total').val(total);
}

function formatMoney(n) {
    return n.toFixed(0).replace(/./g, function(c, i, a) {
        return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "," + c : c;
    });
}