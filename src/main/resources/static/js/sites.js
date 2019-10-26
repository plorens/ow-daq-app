
    function confirmDelete(delbutton){

        var txt;
        var r = confirm("Are you sure?");
        if (r == true) {
            document.location="/sites/delete?ID="+delbutton.id;
        }
    }

    function fillFormData( o ){

        var tr=o.parentNode;

        var id=tr.children[0].innerText;
        var desc=tr.children[1].innerText;
        var status=tr.children[2].innerText;

        document.getElementById("fillId").value = id;
        document.getElementById("fillDesc").value = desc;
        document.getElementById("fillStatus").value = status;
    }


    function fillFormToAdd( ){
        var id= "new ID";
        document.getElementById("fillId").value = id;
        document.getElementById("fillDesc").value = null;
        document.getElementById("fillStatus").value = null;
    }