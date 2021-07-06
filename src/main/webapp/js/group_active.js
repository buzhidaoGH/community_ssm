$(function () {
  $(".group_active .group_active_page li a").click(function () {
    var path = $(this).attr("path");
    if (path != undefined){
      // console.log(path);
      $(".content .loading").load(path);
    }
  });
  $("#active_search").click(function () {
    var atitle = $("#active_title").val();
    // console.log(atitle);
    $(".content .loading").load('/pages/group_active?page=1&size=6&atitle='+atitle);
  });
});