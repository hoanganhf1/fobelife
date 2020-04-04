$(document).ready(function() {
    $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $(".shopping-cart .product").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
    $("#checkout-cart").on("click", function() {
        var total = $("#cart-total").text();
        if (total <= 0) {
            $("#confirmModal").modal('show');
            return false;
        }
        return true;
    });
});

function deliverOrder(orderId) {
    $.ajax({
        method : "PUT",
        url : "/api/cart/deliver/" + orderId
    }).done(function(rep) {
        window.location.href = "/cart/history";
    });
}

function submitGift(productCode) {
    $.ajax({
        method : "PUT",
        contentType: "application/json",
        dataType: 'json',
        url : "/api/cart/gift/" + productCode
    }).fail(function(error) {
        //var error = JSON.parse(rep.responseText);
        $("#confirmLabel").text("Quà tặng");
        $("#modal-body").text(error.responseJSON.message);
        $("#confirmModal").modal('show');
    })
    .done(function(rep) {
        window.location.reload();
    });
}