function generateRandomPassword()
{
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < 16; i++ ) text += possible.charAt(Math.floor(Math.random() * possible.length));
    document.getElementById("generatedPassLabel").value = text;
    alert(text);
    return text;
}