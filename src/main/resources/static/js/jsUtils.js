/*    fortmatting    field    */
const capitalize = (s) => s.charAt(0).toUpperCase() + s.slice(1);
const currencyFormat = (num) => '&euro;' + num.toFixed(0).replace('.', ',').replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
const currencyFormatDecimal = (num) => '&euro;' + num.toFixed(2).replace('.', ',').replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.')

/*   swal function    */
const swalCountDownFunc = (title, message, func) => {
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
const showLoad = (title) => {
    swal.fire({
        title: title,
        text: '',
        onOpen: () => {
            swal.showLoading()
        },
        customClass: {
            container: 'my-swal',
        }
    });
}
const swalSuccess = (title, HTMLmessage) => {
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
const swalSuccessReload = (title, HTMLmessage) => {
    swal.fire({
        "title": '<h2><b>' + title + '</b></h2><br>',
        "html": "<h4>" + HTMLmessage + "</h4><br>",
        "type": "success",
        "confirmButtonColor": '#363a90',
        "confirmButtonClass": "btn btn-success",
        customClass: {
            container: 'my-swal',
        }
    }).then(() => {
        showLoad();
        location.reload();
    });
}
const swalSuccessFunction = (title, HTMLmessage, fun) => {
    swal.fire({
        "title": '<h2><b>' + title + '</b></h2><br>',
        "html": "<h4>" + HTMLmessage + "</h4><br>",
        "type": "success",
        "confirmButtonColor": '#363a90',
        "confirmButtonClass": "btn btn-success",
        customClass: {
            container: 'my-swal',
        }
    }).then(() => {
        showLoad();
        fun();
    });
}
const swalError = (title, message) => {
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
const swalWarning = (title, message) => {
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
const swalConfirm = (title, HTMLmessage, func) => {
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
const closeSwal = () => {
    swal.close()
};

/*    form submit     */
function submitForm(form, success_title_msg, success_msg, ctrl, reload) {
    submitForm(form, success_title_msg, success_msg, ctrl, reload, null);
}

function submitForm(form, success_title_msg, success_msg, ctrl, reload, func) {
    if (ctrl) {
        showLoad();
        form.ajaxSubmit({
            error: () => {
                closeSwal();
                swalError("Errore", "Riprovare, se l'errore persiste contattare l'assistenza");
            },
            success: (resp) => {
                closeSwal();
                if (resp.result) {
                    var message = resp.message != null ? ".<br>" + resp.message : "";
                    if (!!func) {
                        swalSuccessFunction(success_title_msg, success_msg + message, func);
                    } else if (reload) {
                        swalSuccessReload(success_title_msg, success_msg + message);
                    } else {
                        swalSuccess(success_title_msg, success_msg);
                    }
                } else {
                    swalError("Errore!", resp.message);
                }
            }
        });
    }
}