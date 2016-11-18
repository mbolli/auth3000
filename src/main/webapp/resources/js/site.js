$(function() {

    console.log( "ready!" );

    if($("#new_account_modal_link").length > 0) {
        $("#new_account_modal_link").off().on("click", function() {
            $("#new_account_modal").toggle();
        });

    }

    if($(".unhidepw").length > 0) {
        $(".unhidepw").on("click", function(e) {
            pw_encrypted = $(this).parent().find(".hiddenpw").html();
            pw_obj = atob(pw_encrypted); console.log(pw_obj);
            pw_key = $(this).attr("data-hash");
            pw_decrypted = sjcl.json.decrypt(pw_key, pw_obj);
            $show = $(this).parent().find(".cleartext");
            $show.html(pw_decrypted);
        });
    }
});

function encryptPassword(key) {
    var $pw = $("#new_account_form #decrypted_pw");
    var $pw_encrypted = $("#new_account_form").find("[id$=encrypted_pw]");
    var $userid_fill = $("#new_account_form").find("[id$=userid]");
    var $userid_prov = $("#new_account_form #userid_prov");
    var pw = $pw[0].value;
    var encrypted = sjcl.json.encrypt(key, pw);
    console.log(encrypted);
    $pw_encrypted[0].value = btoa(encrypted);
    $userid_fill[0].value = $userid_prov[0].value;
}