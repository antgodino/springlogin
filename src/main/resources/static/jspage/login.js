function logIn() {
    swal.fire({
        html: '<div class="offset-md-2 col-md-8">' +
            '<form id="login-form">' +
            '<label>Username / Email</label>' +
            '<input class="form-control obbligatory" name="username" type="text">' +
            '<label>Password</label>' +
            '<input class="form-control obbligatory" name="password" type="password">' +
            '</form>' +
            '</div>',
        confirmButtonText: "Submit",
        showLoaderOnConfirm: true,
        customClass: {
            confirmButton: "btn btn-succes",
        },
        preConfirm: function () {
            var err = false;
            err = checkObblFieldsContent($('#login-form')) ? true : err;
            if (!err) {
                return new Promise(function (resolve) {
                    resolve($('#login-form').serializeArray());
                });
            } else {
                return false;
            }
        },
    }).then((result) => {
        if (result.value) {
            showLoad("Attendi ...");
            $.post("/login/rest/signin", result.value).done(function (resp) {
                if (resp != null) {
                    if (resp.tipo == 1) {
                        window.location = "admin";
                    } else if (resp.tipo == 2) {
                        window.location = "user";
                    }
                } else {
                    swalError("errore", "Credenziali errate!");
                }
            });
        } else {
            swal.close();
        }
    });
}

$(document).ready(function () {
    $("#loginsubmit").click(function () {
        logIn()
    });
});