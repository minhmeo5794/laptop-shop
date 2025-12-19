(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Fixed Navbar
    $(window).scroll(function () {
        if ($(window).width() < 992) {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow');
            } else {
                $('.fixed-top').removeClass('shadow');
            }
        } else {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow').css('top', 0);
            } else {
                $('.fixed-top').removeClass('shadow').css('top', 0);
            }
        }
    });


    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({ scrollTop: 0 }, 1500, 'easeInOutExpo');
        return false;
    });


    // Testimonial carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 1
            },
            992: {
                items: 2
            },
            1200: {
                items: 2
            }
        }
    });


    // vegetable carousel
    $(".vegetable-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 2
            },
            992: {
                items: 3
            },
            1200: {
                items: 4
            }
        }
    });


    // Modal Video
    $(document).ready(function () {
        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });
        console.log($videoSrc);

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })
    });



    // Product Quantity
    // $('.quantity button').on('click', function () {
    //     var button = $(this);
    //     var oldValue = button.parent().parent().find('input').val();
    //     if (button.hasClass('btn-plus')) {
    //         var newVal = parseFloat(oldValue) + 1;
    //     } else {
    //         if (oldValue > 0) {
    //             var newVal = parseFloat(oldValue) - 1;
    //         } else {
    //             newVal = 0;
    //         }
    //     }
    //     button.parent().parent().find('input').val(newVal);
    // });

    // Product Quantity
    $('.quantity button').on('click', function () {
        let change = 0;

        let button = $(this);
        let oldValue = button.parent().parent().find('input').val();
        let newValue;

        if (button.hasClass('btn-plus')) {
            newValue = parseFloat(oldValue) + 1;
            change = 1;
        } else { // button.hasClass('btn-minus')
            if (oldValue > 1) {
                newValue = parseFloat(oldValue) - 1;
                change = -1;
            } else {
                newValue = 1;
            }
        }

        // Update the input value
        const input = button.parent().parent().find('input');
        input.val(newValue);

        // Set form index
        const index = input.attr("data-cart-detail-index")
        const inputElementInCartDetail = document.getElementById(`cartDetails${index}.quantity`);
        $(inputElementInCartDetail).val(newValue);




        // Update the total price
        const orgPrice = input.attr('data-cart-detail-price');
        const cartDetailId = input.attr('data-cart-detail-id');
        const calculatedPriceElement = $(`p[data-cart-detail-id="${cartDetailId}"]`);

        if (calculatedPriceElement) {
            const newPrice = +orgPrice * newValue;
            calculatedPriceElement.text(formatCurrency(newPrice.toFixed(2)) + ' đ');
        }

        // Update the total price in the cart
        const totalPriceElement = $('p[data-cart-total-price]');

        if (totalPriceElement && totalPriceElement.length) {
            const currentTotal = totalPriceElement.first().attr('data-cart-total-price');
            let newTotal;

            if (change === 0) {
                newTotal = +currentTotal;
            } else {
                newTotal = change * (+orgPrice) + (+currentTotal);
            }

            // Reset change
            change = 0;

            // Update the total price
            totalPriceElement?.each(function (index, element) {
                // Update next
                $(totalPriceElement[index]).text(formatCurrency(newTotal.toFixed(2)) + ' đ');
                // Update data
                $(totalPriceElement[index]).attr('data-cart-total-price', newTotal);
            });
        }
    });

    // Format currency
    function formatCurrency(value) {
        const formatter = new Intl.NumberFormat('vi-VN', {
            style: 'decimal',
            minimumFractionDigits: 0,
        });

        let formatted = formatter.format(value);
        formatted = formatted.replace(/\./g, ',');

        return formatted;
    }

})(jQuery);

