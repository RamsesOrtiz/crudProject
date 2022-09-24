function send() {

    var description = document.getElementById('description');
    var price = document.getElementById('price');
    var category = document.getElementById('category');

    if (description.value == "" || price.value == "" || category.value == "") {
        manageModal('modalError');
    } else {

        var url = 'http://localhost:8080/api/createProduct'

        var elements = document.getElementById("formCreate").elements;
        var data = {};
        for (var i = 0; i < elements.length; i++) {
            var item = elements.item(i);
            data[item.name] = item.value;
        }

        fetch(url, {
            method: "POST",
            body: JSON.stringify(data),
            headers: { "Content-Type": "application/json" }
        }).then(
            function response() {

                response => response.text()
                console.log(this.responseText);
            }
        ).then(
            data => {
                console.log(data);
                get();
                document.querySelector('form').addEventListener('button', manageModal('modalSaveOk'));
            }
        );
        blankItems('description', 'price', 'category');
    }
}

function get() {

    var url = "http://localhost:8080/api/products";

    var table = document.getElementById('productsTable');

    table.style.display = "";


    fetch(url).then(
        res => {
            res.json().then(
                data => {
                    console.log(data);
                    if (data.length > 0) {
                        var temp = "";
                        data.forEach((itemData) => {
                            temp += "<tr id='" + itemData.id_cliente + "'>";
                            temp += "<td>" + itemData.id_cliente + "</td>";
                            temp += "<td>" + itemData.nombre + "</td>";
                            temp += "<td>" + itemData.apellido + "</td>";
                            temp += "<td>" + itemData.telefono + "</td>";
                            temp += "<td>" + "<button class='noselect' id='deleteClient' onclick='deleteClient(" + itemData.id_cliente + ")'><span class='text'>Borrar</span><span class='icon'><svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24'><path d='M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212 8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z'></path></svg></span></button>" + "</td></tr>";
                        });
                        document.getElementById('dataClients').innerHTML = temp;
                    }
                }
            )
        }
    )
}

function getClient() {

    var id = document.getElementById("editIdProduct").value;

    var url = ("http://localhost:8080/api/product/" + id);

    fetch(url).then(
        res => {
            res.json().then(
                data => {
                    console.log(data);

                    if (res.status == 200) {
                        document.getElementById('editDescription').value = data.nombre;
                        document.getElementById('editPrice').value = data.apellido;
                        document.getElementById('editCategory').value = data.telefono;
                    } else {

                        manageModal('modalEdit');
                        document.getElementById('editIdProduct').value = "";

                    }
                }
            )
        }
    )
}


function edit() {

    var url = 'http://localhost:8080/api/updateProduct'

    var description = document.getElementById('editDescription');
    var price = document.getElementById('editPrice');
    var category = document.getElementById('editCategory');

    if (description.value == "" || price.value == "" || category.value == "") {
        manageModal('modalError');
    } else {

        var elements = document.getElementById("formEdit").elements;
        var data = {};
        for (var i = 0; i < elements.length; i++) {
            var item = elements.item(i);
            data[item.name] = item.value;
        }

        fetch(url, {
            method: "POST",
            body: JSON.stringify(data),
            headers: { "Content-Type": "application/json" }
        }).then(
            function response() {

                response => response.text()
                console.log(this.responseText);
            }
        ).then(
            data => {
                console.log(data);
                document.getElementById('formEdit').addEventListener('button', manageModal('modalEditOk'));
            }
        );

        blankItems('editIdProduct', 'editDescription', 'editPrice', 'editCategory');
    }
}

function deleteClient(id) {

    var url = ("http://localhost:8080/api/deleteProduct/" + id);

    fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" }
    }).then(
        function response() {

            response => response.text()
            console.log(this.responseText);
        }
    ).then(
        data => {
            console.log(data);
            get();
            document.querySelector('tr').addEventListener('delete', manageModal('modalBorrar'));
        }
    );
}

function manageModal(idModal) {

    var modal = document.getElementById(idModal);

    var span = document.getElementsByClassName("close")[0];
    var span1 = document.getElementsByClassName("close")[1];
    var span2 = document.getElementsByClassName("close")[2];
    var span3 = document.getElementsByClassName("close")[3];
    var span4 = document.getElementsByClassName("close")[4];

    span.onclick = function () {
        modal.style.display = "none";
    }

    span1.onclick = function () {
        modal.style.display = "none";
    }

    span2.onclick = function () {
        modal.style.display = "none";
    }

    span3.onclick = function () {
        modal.style.display = "none";
    }

    span4.onclick = function () {
        modal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    return modal.style.display = "block";
}

function blankItems(id1, id2, id3, id4) {

    document.getElementById(id1).value = "";
    document.getElementById(id2).value = "";
    document.getElementById(id3).value = "";
    document.getElementById(id4).value = "";
}

function closeTable() {

    var closeBtn = document.getElementById('resetClients');

    var table = document.getElementById('productTable')

    closeBtn.onclick = function () {
        table.style.display = "none";
    }
}

