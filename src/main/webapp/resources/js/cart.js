$(document).ready(
        function() {

            /* Set rates + misc */
            var taxRate = 0.0;
            var shippingRate = 0.00;
            var fadeTime = 0;

            /* Assign actions */
            $('.product-quantity input').change(function() {
                updateQuantity(this);
            });

            $('.product-removal button').click(function() {
                removeItem(this);
            });
            
            function formatMoney(n) {
                return n.toFixed(0).replace(/./g, function(c, i, a) {
                  return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "," + c : c;
                });
              }

            /* Recalculate cart */
            function recalculateCart() {
                var subtotal = 0;

                /* Sum up row totals */
                $('.product').each(
                        function() {
                            subtotal += parseFloat($(this).children(
                                    '.product-line-price').text().replace(',', ''));
                        });

                /* Calculate totals */
                var tax = subtotal * taxRate;
                var shipping = (subtotal > 0 ? shippingRate : 0);
                var total = subtotal + tax + shipping;
                $('.cart-total').val(total);

                /* Update totals display */
                $('.totals-value').fadeOut(fadeTime, function() {
                    $('#cart-subtotal').html(formatMoney(subtotal));
                    $('#cart-tax').html(formatMoney(tax));
                    $('#cart-shipping').html(formatMoney(shipping));
                    $('#cart-total').html(formatMoney(total));
                    if (total == 0) {
                        $('.checkout').fadeOut(fadeTime);
                    } else {
                        $('.checkout').fadeIn(fadeTime);
                    }
                    $('.totals-value').fadeIn(fadeTime);
                });
            }

            /* Update quantity */
            function updateQuantity(quantityInput) {
                /* Calculate line price */
                var productRow = $(quantityInput).parent().parent();
                var price = parseFloat(productRow.children('.product-price')
                        .text().replace(',', ''));
                var quantity = $(quantityInput).val();
                var linePrice = price * quantity;

                var productCode = productRow.children('.product-code').val();
                productRow.children('.product-order').val(productCode + ' ' + quantity);

                /* Update line price display and recalc cart totals */
                productRow.children('.product-line-price').each(function() {
                    $(this).fadeOut(fadeTime, function() {
                        $(this).text(formatMoney(linePrice));
                        recalculateCart();
                        $(this).fadeIn(fadeTime);
                    });
                });
            }

            /* Remove item from cart */
            function removeItem(removeButton) {
                /* Remove row from DOM and recalc cart total */
                var productRow = $(removeButton).parent().parent();
                productRow.slideUp(fadeTime, function() {
                    productRow.remove();
                    recalculateCart();
                });
            }

        });
