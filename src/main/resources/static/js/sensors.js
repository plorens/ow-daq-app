
    function confirmDelete(delbutton){

        var txt;
        var r = confirm("Are you sure?");
        if (r == true) {
            document.location="/sensors/delete?ID="+delbutton.id;
        }
    }

    function fillFormData( o ){

        var tr=o.parentNode;

        var id=tr.children[0].innerText;
        var adapter_id=tr.children[1].innerText;
        var site_id=tr.children[2].innerText;
         var address=tr.children[3].innerText;
        var status=tr.children[4].innerText;


        document.getElementById("fillId").value = id;
        document.getElementById("fillAddress").value = address;
        document.getElementById("fillAdapterId").value = adapter_id;
        document.getElementById("fillSiteId").value = site_id;
        document.getElementById("fillStatus").value = status;

    }


      function fillFormToAdd( ){
        var id= "new ID";
        document.getElementById("fillId").value = id;
        document.getElementById("fillAddress").value = null;
        document.getElementById("fillAdapterId").value = null;
        document.getElementById("fillSiteId").value = null;
        document.getElementById("fillStatus").value = null;
    }