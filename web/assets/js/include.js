$(function () {
    // $.get("header.html",function (data) {
    //     $("#header").html(data);
    // });
    $.get("header1.html",function (data) {
        $("#header").html(data);
    });
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });
});