/*  text manipulation  */
function capitalize(s) {
    return s.charAt(0).toUpperCase() + s.slice(1);
}

function currencyFormat(num) {
    return '&euro;' + num.toFixed(0).replace('.', ',').replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.')
}

function currencyFormatDecimal(num) {
    return '&euro;' + num.toFixed(2).replace('.', ',').replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.')
}

/*   swal function    */
function swalCountDownFunc(title, message, func) {
    let timerInterval;
    Swal.fire({
        title: '<h2 class="text-danger"><b>' + title + '</b></h2><br>',
        html: "<h4>" + message + ' <b></b> secondi. </h4><br>',
        type: "success",
        timer: 4000,
        timerProgressBar: true,
        onBeforeOpen: () => {
            Swal.showLoading()
            timerInterval = setInterval(() => {
                const content = Swal.getContent()
                if (content) {
                    const b = content.querySelector('b')
                    if (b) {
                        b.textContent = Math.round(Swal.getTimerLeft() / 1000);
                    }
                }
            }, 100)
        },
        onClose: () => {
            clearInterval(timerInterval);
        }
    }).then((result) => {
        func();
    });
}
function showLoad(title) {
    swal.fire({
        title: title,
        text: '',
        onOpen: function () {
            swal.showLoading()
        },
        customClass: {
            container: 'my-swal',
        }
    });
}
function swalSuccessReload(title, HTMLmessage) {
    swal.fire({
        "title": '<h2 class="text-danger"><b>' + title + '</b></h2><br>',
        "html": "<h4>" + HTMLmessage + "</h4><br>",
        "type": "success",
        "confirmButtonColor": '#363a90',
        "confirmButtonClass": "btn btn-success",
        customClass: {
            container: 'my-swal',
        }
    }).then(function () {
        showLoad();
        location.reload();
    });
}
function swalSuccessReload2(title, HTMLmessage) {
    swal.fire({
        "title": '<h2 class="text-danger"><b>' + title + '</b></h2><br>',
        "html": HTMLmessage,
        "type": "success",
        "confirmButtonColor": '#363a90',
        "confirmButtonClass": "btn btn-success",
        customClass: {
            container: 'my-swal',
        }
    }).then(function () {
        showLoad();
        location.reload();
    });
}
function swalSuccess(title, HTMLmessage) {
    swal.fire({
        "title": '<h2 class="text-danger"><b>' + title + '</b></h2><br>',
        "html": "<h4>" + HTMLmessage + "</h4><br>",
        "type": "success",
        "confirmButtonColor": '#363a90',
        "confirmButtonClass": "btn btn-success",
        customClass: {
            container: 'my-swal',
        }
    });
}
function swalError(title, message) {
    swal.fire({
        "title": title,
        "html": message,
        "type": "error",
        cancelButtonClass: "btn btn-danger",
        customClass: {
            container: 'my-swal',
        }
    });
}
function swalWarning(title, message) {
    swal.fire({
        "title": title,
        "html": message,
        "type": "warning",
        cancelButtonClass: "btn btn-danger",
        customClass: {
            container: 'my-swal',
        }
    });
}
function swalConfirm(title, HTMLmessage, func) {
    swal.fire({
        "title": '<h2 class="text-danger"><b>' + title + '</b></h2><br>',
        "html": "<h4>" + HTMLmessage + "</h4><br>",
        animation: false,
        showCancelButton: true,
        confirmButtonText: '&nbsp;<i class="fas fa-check"></i>',
        cancelButtonText: '&nbsp;<i class="fas fa-times"></i>',
        cancelButtonClass: "btn btn-danger",
        confirmButtonClass: "btn btn-success",
        customClass: {
            popup: 'large-swal animated bounceInUp',
        },
    }).then((result) => {
        if (result.value) {
            func();
        } else {
            swal.close();
        }
    });
}