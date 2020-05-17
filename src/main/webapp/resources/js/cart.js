
function updateQuantity(productCode, discount) {
    var price = $('#' + productCode + '-price').text().trim().split(" ")[0].replace(".", "");
    var quantity = $('#' + productCode + '-quantity').val();
    var linePrice = price * quantity;
    if (discount) {
        linePrice -= discount;
    }
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

function updateBonus(productCode, bonus) {
    var point = $('#' + productCode + '-point').val();
    var maxPoint = bonus * $('#' + productCode + '-quantity').val();
    var totalPoint = Number($('.total-point').text());
    var totalUsed = 0;
    $('.product-point input').each(function() {
        totalUsed += Number($(this).val());
    });
    if (totalUsed > totalPoint) {
        point = point - (totalUsed - totalPoint);
    }
    if (point > maxPoint) {
        point = maxPoint;
    }
    $('#' + productCode + '-point').val(point);
    updateQuantity(productCode, point * 1000);
    updatePoinUsed();
}

function updatePoinUsed() {
    var totalUsed = 0;
    $('.product-point input').each(function() {
        totalUsed += Number($(this).val());
    });
    $('#pointUsed').val(totalUsed);
    $('.total-point-used').text(totalUsed);
}
