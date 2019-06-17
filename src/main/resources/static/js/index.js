console.log('Loaded index.js');

function doSend(url) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            location.reload();
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function remove(abbreviation) {
    console.log('Removing abbreviation', abbreviation);
    doSend("/removeAbbreviation?abbreviation="+abbreviation);
}

function add() {
    console.log('Adding');
    var abbreviation=document.getElementById('abbreviation').value;
    var url=document.getElementById('url').value;
    var restUrl = "/add?url="+url;
    if (abbreviation != null) {
        restUrl = restUrl+"&abbreviation="+abbreviation;
    }
    console.log(restUrl);
    doSend(restUrl);
}

function visit() {
    var abbreviation=document.getElementById('visit').value;
    var xhttp = new XMLHttpRequest();
    /*
    xhttp.onreadystatechange = function() {
        console.log(this);
        if (this.readyState == 4 && this.status == 200) {
            console.log('Sending to new site');
        }
    };
     */
    xhttp.open("GET", "/getUrl?abbreviation="+abbreviation, true);
    xhttp.send();
}
