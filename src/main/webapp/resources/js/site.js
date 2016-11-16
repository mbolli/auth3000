$(function() {

    console.log( "ready!" );

    if($("#new_account_modal_link").length > 0) {
        $("#new_account_modal_link").off().on("click", function() {
            $("#new_account_modal").toggle();
        });

    }
});

function encryptPassword(key) {
    var $pw = $("#new_account_form #decrypted_pw");
    var $pw_encrypted = $("#new_account_form").find("[id$=encrypted_pw]");
    var pw = $pw[0].value;
    console.log("encrypting password..." + pw);
    var encrypted = JSON.parse(sjcl.json.encrypt(key, pw));
    console.log(encrypted);
    $pw_encrypted[0].value = JSON.stringify(encrypted);
    if(encrypted.ct != 'undefined') {
        console.log("encrypted password: " + encrypted.ct);
        console.log(JSON.stringify(encrypted));
        return true;
    }
    return false;
}

function decryptPassword(key, json) {
    var decrypted = sjcl.json.decrypt(key, json);
    console.log("decrypted password: " + decrypted);
    // todo make it work
}