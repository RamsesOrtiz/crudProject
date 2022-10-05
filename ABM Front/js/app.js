function send() {

    var nombre = document.getElementById('name');
    var apellido = document.getElementById('lastName');
    var telefono = document.getElementById('phone');

    if (nombre.value == "" || apellido.value == "" || telefono.value == "") {
        manageModal('modalError');
    } else {

        var url = 'http://localhost:8080/api/createClient'

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
            response => {
                response.text()
                console.log(this.responseText);
                if (response.status == 200) {
                    get();
                    manageModal('modalSaveOk');
                } else {
                    manageModal('modalErrorServ');
                }
            }
        );
        blankItems('name', 'lastName', 'phone');
    }
}

function get() {

    var url = "http://localhost:8080/api/clients";

    var table = document.getElementById('clientTable');

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

    var id = document.getElementById("editIdClient").value;

    var url = ("http://localhost:8080/api/client/" + id);

    var idClient = document.getElementById('editIdClient');

    var nombre = document.getElementById('editName');

    var apellido = document.getElementById('editLastName')

    var telefono = document.getElementById('editPhone');

    if (idClient.value == "") {
        manageModal('modalErrorLogin');
    } else {
        fetch(url).then(
            res => {
                res.json().then(
                    data => {
                        console.log(data);

                        if (res.status == 200) {
                            nombre.value = data.nombre;
                            apellido.value = data.apellido;
                            telefono.value = data.telefono;
                        } else {

                            manageModal('modalEdit');
                            idClient.value = "";
                            nombre.value = "";
                            apellido.value = "";
                            telefono.value = "";

                        }
                    }
                )
            }
        )
    }
}


function edit() {

    var url = 'http://localhost:8080/api/updateClient'

    var nombre = document.getElementById('editName');
    var apellido = document.getElementById('editLastName');
    var telefono = document.getElementById('editPhone');

    if (nombre.value == "" || apellido.value == "" || telefono.value == "") {
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
            response => {
                response.text()
                console.log(this.responseText);
                if (response.status == 200) {
                    get();
                    manageModal('modalEditOk');
                } else {
                    manageModal('modalErrorServ');
                }
            }
        );

        blankItems('editIdClient', 'editName', 'editLastName', 'editPhone');
    }
}

function deleteClient(id) {

    var url = ("http://localhost:8080/api/deleteClient/" + id);

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
    var span5 = document.getElementsByClassName("close")[5];
    var span6 = document.getElementsByClassName("close")[6];

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

    span5.onclick = function () {
        modal.style.display = "none";
    }

    span6.onclick = function () {
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

    var table = document.getElementById('clientTable')

    closeBtn.onclick = function () {
        table.style.display = "none";
    }
}

