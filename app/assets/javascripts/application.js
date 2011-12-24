// This is a manifest file that'll be compiled into including all the files listed below.
// Add new JavaScript/Coffee code in separate files in this directory and they'll automatically
// be included in the compiled file accessible from http://example.com/assets/application.js
// It's not advisable to add code directly here, but if you do, it'll appear at the bottom of the
// the compiled file.
//
//= require jquery
//= require jquery_ujs
//= require_tree .

$(document).ready(function () {

$('.add-on :checkbox').click(function () {
  if ($(this).attr('checked')) {
    $(this).parents('.add-on').addClass('active')
  } else {
    $(this).parents('.add-on').removeClass('active')
  }
})

$(window).bind('load resize', function () {
  $(".twipsies a").each(function () {
    $(this)
      .twipsy({
        live: false
      , placement: $(this).attr('title')
      , trigger: 'manual'
      , offset: 2
      })
        .twipsy('show')
    })
  })
});

