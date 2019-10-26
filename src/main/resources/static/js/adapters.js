
function confirmDelete(delbutton){

    var r = confirm("Are you sure?");
    if (r == true) {
        document.location="/adapters/delete?ID="+delbutton.id;
    }
}

    function fillFormData( o ){

        var tr=o.parentNode;
        var id=tr.children[0].innerText;
        var name=tr.children[1].innerText;
        var port=tr.children[2].innerText;
        var status=tr.children[3].innerText;


        document.getElementById("fillId").value = id;
        document.getElementById("fillName").value = name;
        document.getElementById("fillPort").value = port;
        document.getElementById("fillStatus").value = status;
    }

    function fillFormToAdd( ){
        var id= "new ID";
        document.getElementById("fillId").value = id;
        document.getElementById("fillName").value = null;
        document.getElementById("fillPort").value = null;
        document.getElementById("fillStatus").value = null;
    }
