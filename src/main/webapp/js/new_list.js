$(function () {
  $(".news_list .news_list_page li a").click(function () {
    var path = $(this).attr("path");
    if (path != undefined){
      // console.log(path);
      $(".content .loading").load(path);
    }
  });
  $("#news_search").click(function () {
    var ntitle = $("#news_title").val();
    // console.log(atitle);
    $(".content .loading").load('/pages/news_list?page=1&size=6&ntitle='+ntitle);
  });
});