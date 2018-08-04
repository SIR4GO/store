/**
 * Created by Mahmoud on 12/24/2017.
 */



function checkform() {

    var name = document.getElementById('name').value;
    var pass  =  document.getElementById('password').value;
    if(name.length < 6 )
    {
        alert('Username or name  should be greater than 6 characters ')
        return false;
    }
    else if(pass.length < 6)
    {
        alert('Password  should be greater than 6 characters or numbers ')
        return false;

    }
    else
    {
        return true;
    }
}


