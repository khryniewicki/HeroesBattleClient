var stompClient = null;


window.addEventListener('load', connect);


function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    setInterval(getCoordinates, 50);
}
let connected;
function connect() {
    var contextPath = window.location.protocol + "//" + window.location.host+ '/websocket-example';
    console.log(contextPath);

    var socket = new SockJS(contextPath);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        stompClient.subscribe('/topic/hero', function (coordinates) {
            receiveHeroCoordinates(JSON.parse(coordinates.body));

        });
    });

}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

let X;
let Y;
function getCoordinates() {

    $.get("/checkCoordinate", function onSuccess(HeroDTO) {
        let elementById = document.getElementById('hero');
        elementById.innerText = HeroDTO.character +" [x:" +HeroDTO.positionX+",y:"+HeroDTO.positionY+"]";
            sendHeroCoordinates(HeroDTO);
    });
}


function sendHeroCoordinates(HeroDTO) {
    if(X!=HeroDTO.positionX || Y!=HeroDTO.positionY) {
    X=HeroDTO.positionX;
     Y=HeroDTO.positionY;
    // console.log(X +" "+Y)

    stompClient.send("/app/hero", {}, JSON.stringify({
        'positionX': X,
        'positionY': Y,
    }));}
}

function receiveHeroCoordinates(HeroCoordiantes) {

    console.log(HeroCoordiantes +" delivered")
}





