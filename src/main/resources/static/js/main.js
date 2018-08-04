/*global $ , alert , console*/

$(function(){
    
    'use strict';
    
   $('html').niceScroll();
    
    
   //$(".header").height( $(window).height());
   $(".wrapper").css("height" ,$(window).height());


});

$(document).ready(function ()
{
    $(" .wrapper header button ").click(function ()
    {

         $(".wrapper nav").toggleClass('effect');

        $(".wrapper .view").toggleClass('expand');

    });


});
