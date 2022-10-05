function login() {

    var user = document.getElementById("user");

    var password = document.getElementById("password");

    var loginPanel = document.getElementById("login");

    var url = ("http://localhost:8080/api/login");

    if (user.value == "" || password.value == "") {
        manageModal('modalError');
    } else {

        var url = 'http://localhost:8080/api/login'

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
                    loginPanel.style.display = "none";
                    window.location.href = "home.html";
                    manageModal('modalSaveOk');
                } else if (response.status == 404) {
                    manageModal('modalErrorLogin');
                } else {
                    manageModal('modalErrorServ');
                }
            }
        );
        blankItems('user', 'password');
    }
}

function manageModal(idModal) {

    var modal = document.getElementById(idModal);

    var span = document.getElementsByClassName("close")[0];
    var span1 = document.getElementsByClassName("close")[1];
    var span2 = document.getElementsByClassName("close")[2];
    var span3 = document.getElementsByClassName("close")[3];

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