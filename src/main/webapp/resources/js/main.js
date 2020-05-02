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

function updateStatus(orderId, status) {
    $.ajax({
        method : "PUT",
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify({
            orderId: orderId,
            status: status
        }),
        url : "/api/cart/status"
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

function applyDataTable(tableId, scrollY) {
    $(tableId).DataTable({
        scrollY: scrollY,
        scrollX: true,
        scrollCollapse: true,
        language : {
            "decimal" : "",
            "emptyTable" : "No data available in table",
            "info" : "Hiển thị _START_ đến _END_ của _TOTAL_ sản phẩm",
            "infoEmpty" : "Hiển thị 0 đến 0 của 0 sản phảm",
            "infoFiltered" : "(filtered from _MAX_ total entries)",
            "infoPostFix" : "",
            "thousands" : ",",
            "lengthMenu" : "_MENU_",
            "loadingRecords" : "Loading...",
            "processing" : "Processing...",
            "search" : "",
            "zeroRecords" : "No matching records found",
            "paginate" : {
                "first" : "<<",
                "last" : ">>",
                "next" : ">",
                "previous" : "<"
            },
            "aria" : {
                "sortAscending" : ": activate to sort column ascending",
                "sortDescending" : ": activate to sort column descending"
            }
        },
//        columnDefs: [
//            { width: 200, targets: 0 }
//        ],
//        fixedColumns: true,
        autoWidth: true

    });
}