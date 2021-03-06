$('.fancybox').fancybox();

function fancyBoxClose() {
    $('div.fancybox-overlay.fancybox-overlay-fixed').css('display', 'none');
}

$("a.fancySmall").fancybox({
    openEffect: 'elastic',
    closeEffect: 'elastic',
    prevEffect: 'fade',
    nextEffect: 'fade',
    fitToView: false, // images won't be scaled to fit to browser's height
    maxWidth: "90%" // images won't exceed the browser's width
});

$("a.fancyDocument").fancybox({
    prevEffect: 'none',
    nextEffect: 'none',
    closeBtn: true,
    type: 'iframe',
    autoSize: false,
    fitToView: false,
    height: '1000',
    width: '1000',
    centerOnScroll: true,
    overlayOpacity: 0,
    overlayShow: true,
});

$("a.fancyProfileNoClose").fancybox({
    prevEffect: 'none',
    nextEffect: 'none',
    closeBtn: false,
    closeClick: false,
    helpers: {
        overlay: {closeClick: false}
    },
    keys: {
        close: null
    },
    type: 'iframe',
    'minHeight': 500,
    centerOnScroll: true,
    // overlayOpacity: 0.5,
    overlayShow: true
});

$("a.fancyBoxFullReloadPage").fancybox({
    prevEffect: 'none',
    nextEffect: 'none',
    openEffect: 'elastic',
    closeEffect: 'elastic',
    closeBtn: true,
    type: 'iframe',
    centerOnScroll: true,
    width: '100%',
    height: '100%',
    overlayOpacity: 0,
    overlayShow: true,
    afterClose: () => {
        location.reload();
    }
});

$("a.fancyBoxReloadTable").fancybox({
    prevEffect: 'none',
    nextEffect: 'none',
    openEffect: 'elastic',
    closeEffect: 'elastic',
    closeBtn: true,
    type: 'iframe',
    centerOnScroll: true,
    width: '100%',
    height: '100%',
    overlayOpacity: 0,
    overlayShow: true,
    afterClose: function () {
        // reload_table($('#kt_table_1'));
    }
});