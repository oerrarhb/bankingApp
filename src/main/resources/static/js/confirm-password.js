var check = function() {
   if (document.getElementById('password').value ==
    document.getElementById('confirm_password').value && document.getElementById('password').value !="") {
    document.getElementById('submit').disabled = false;
    document.getElementById('message').style.color = 'green';
    document.getElementById('message').innerHTML = 'passwords are matching';
  } else {
    document.getElementById('submit').disabled = true;
    document.getElementById('message').style.color = 'red';
    document.getElementById('message').innerHTML = 'passwords are not matching';
  }
}