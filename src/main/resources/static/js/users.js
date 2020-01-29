
    function confirmDelete(delbutton){

        var txt;
        var r = confirm("Are you sure?");
        if (r == true) {
            document.location="/users/delete?ID="+delbutton.id;
        }
    }

    function fillFormData( o ){

        var tr=o.parentNode;

        var id=tr.children[0].innerText;
        var login=tr.children[1].innerText;
        var password=tr.children[2].innerText;

        document.getElementById("fillId").value = id;
        document.getElementById("fillLogin").value = login;
        document.getElementById("fillPassword").value = password;
    }


    function fillFormToAdd( ){
        var id= "new ID";
        document.getElementById("fillId").value = id;
        document.getElementById("fillLogin").value = null;
        document.getElementById("fillPassword").value = null;
    }