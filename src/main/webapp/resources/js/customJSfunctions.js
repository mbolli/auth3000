function generateRandomPassword()
{
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < 16; i++ ) text += possible.charAt(Math.floor(Math.random() * possible.length));
    document.getElementById("pass_gen_form:generatedPassLabel").innerHTML = text;
    alert(text);
    return text;
}