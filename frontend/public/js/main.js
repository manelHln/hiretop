/*
Template Name: Massive
Author: GrayGrids
*/

(function () {

    /*=====================================
    Sticky
    ======================================= */
    window.onscroll = function () {
        var header_navbar = document.querySelector(".navbar-area");
        var sticky = header_navbar.offsetTop;

        if (window.pageYOffset > sticky) {
            header_navbar.classList.add("sticky");
        } else {
            header_navbar.classList.remove("sticky");
        }

    };

    //===== close navbar-collapse when a  clicked
    // let navbarToggler = document.querySelector(".navbar-toggler");
    // var navbarCollapse = document.querySelector(".collapse");

    // document.querySelectorAll(".page-scroll").forEach(e =>
    //     e.addEventListener("click", () => {
    //         navbarToggler.classList.remove("active");
    //         navbarCollapse.classList.remove('show')
    //     })
    // );
    // navbarToggler.addEventListener('click', function () {
    //     navbarToggler.classList.toggle("active");
    // })
    // WOW active
    // new WOW().init();


    
})();