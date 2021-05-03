/*   ----------------    field control   -----------------------   */
 function checkEmail(email) {
     var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
     if (email.val() == '' || !re.test(email.val().toLowerCase())) {
         email.attr("class", "form-control is-invalid");
         return true;
     } else {
         email.attr("class", "form-control is-valid");
         return false;
     }
 }

function checkCF(cf) {
    var codicefiscale = cf.val();
    var esito = true;
    var validi, i, s, set1, set2, setpari, setdisp;
    if (codicefiscale === '')
        esito = false;
    codicefiscale = codicefiscale.toUpperCase();
    if (codicefiscale.length === 16) {
        if (codicefiscale.length !== 16) {
            esito = false;
        } else {
            validi = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            for (i = 0; i < 16; i++) {
                if (validi.indexOf(codicefiscale.charAt(i)) === -1)
                    esito = false;
            }
            set1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            set2 = "ABCDEFGHIJABCDEFGHIJKLMNOPQRSTUVWXYZ";
            setpari = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            setdisp = "BAKPLCQDREVOSFTGUHMINJWZYX";
            s = 0;
            for (i = 1; i <= 13; i += 2)
                s += setpari.indexOf(set2.charAt(set1.indexOf(codicefiscale.charAt(i))));
            for (i = 0; i <= 14; i += 2)
                s += setdisp.indexOf(set2.charAt(set1.indexOf(codicefiscale.charAt(i))));
            if (s % 26 !== codicefiscale.charCodeAt(15) - 'A'.charCodeAt(0)) {
                esito = false;
            }
            if (esito) {
                cf.attr("class", "form-control is-valid");
            } else {
                cf.attr("class", "form-control is-invalid");
            }
            return esito;
        }
    } else {
        cf.attr("class", "form-control is-invalid");
        return false;
    }
}

function checkObblFieldsContent(content) {
    let err = false;
    let elements = [content.find('input.obbligatory'), content.find('textarea.obbligatory'), content.find('select.obbligatory')];
    elements.forEach((elem) => {
        if ($(elem).val() == '') {
            err = true;
            $(elem).removeClass("is-valid").addClass("is-invalid");
        } else {
            $(elem).removeClass("is-invalid").addClass("is-valid");
        }
    });
    return err;
}

function resetInput() {
    $('.form-control').val('');
    $('.form-control').removeClass('is-valid');
    $('.custom-file-input').val('');
    $('.custom-file-label').html('');
    $('.custom-file-input').removeClass('is-valid');
    $('select').val('-');
    $('select').trigger('change');
    $('div.dropdown.bootstrap-select.form-control.kt-').removeClass('is-valid is-valid-select');
}

const checkPassword = (pwd) => new RegExp('(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$').test(pwd.val());

function checkChagePwd(npwd, n2pwd) {//return true se è tutto corretto
    if (checkPassword(npwd)) {
        if (npwd.val() == n2pwd.val()) {
            npwd.removeClass("is-invalid").addClass("is-valid");
            n2pwd.removeClass("is-invalid").addClass("is-valid");
            return true;
        } else {
            n2pwd.removeClass("is-valid").addClass("is-invalid");
            swalError("Errore!", "Le due password non coincidono");
        }
    } else {
        n2pwd.removeClass("is-valid").addClass("is-invalid");
        npwd.removeClass("is-valid").addClass("is-invalid");
        swalError("Errore!",
            "La nuova password deve contenere:" +
            "<div class='col-md-8 center'>" +
            "<ul class='text-start'>" +
            "<li>8 caratteri</li>" +
            "<li>8 una lettera maiuscola</li>" +
            "<li>8 una lettera minuscla</li>" +
            "<li>8 una un carattere speciale (!@#$%^&*)</li>" +
            "</ul></div>");
    }
    return false;
}

/*   ----------------    file control   -----------------------   */
function checkRequiredFileContent(content) {
    var err = false;
    content.find('input:file[tipo=obbligatory]').each(() => {
        if ($(this)[0].files.length == 0) {
            err = true;
            $(this).attr("class", "custom-file-input is-invalid");
        } else {
            $(this).attr("class", "custom-file-input is-valid");
        }
    });
    return err ? false : true;
}

function checkFileExtAndDim(ext) {
    var err = false;
    var extension = '';
    if ($('input[type=file]')[0].files.length > 0) {
        $('input[type=file]').each(() => {
            if (typeof this.files[0] !== 'undefined') {
                extension = this.files[0].name.substring(this.files[0].name.lastIndexOf('.') + 1).toLowerCase();
                if (!ext.includes(extension.toLowerCase())) {
                    $(this).attr("class", "custom-file-input is-invalid");
                    $(this).val('');
                    err = true;
                } else {
                    $(this).attr("class", "custom-file-input is-valid");
                }
            }
        });
        if (!checkFileDim()) {
            err = true;
        }
    } else {
//        $('input[type=file]').attr("class", "custom-file-input");
    }
    return !err;
}
