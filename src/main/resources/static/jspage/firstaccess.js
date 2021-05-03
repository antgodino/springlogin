const ctrlForm = () => {
    let err = checkObblFieldsContent($("#form"));
    if (!err) {
        err = !checkChagePwd($("#newpassword"), $("#newpassword2"));
    }
    return err;
}

$("#submit").click(() => {
    submitForm($("#form"), 'Cambio Password', 'cambio passwqword avvenuto con successo.<br>Ora potrai rieffetture il login',
        !ctrlForm(), false, () => {
            parent.fancyBoxClose()//per chiudere la fancy
        });
});