
var options = {
  valueNames: ['jSortValue', 'jSortDate'],
  page: 15,
  pagination: {
    innerWindow: 1,
    left: 1,
    right: 1,
    paginationClass: "pagination" } };


var tableList = new List('tableID', options);


$('.jPaginateNext').on('click', function () {
  var list = $('.pagination').find('li');
  $.each(list, function (position, element) {
    if ($(element).is('.active')) {
      $(list[position + 1]).trigger('click');
    }
  });
});


$('.jPaginateBack').on('click', function () {
  var list = $('.pagination').find('li');
  $.each(list, function (position, element) {
    if ($(element).is('.active')) {
      $(list[position - 1]).trigger('click');
    }
  });
});
