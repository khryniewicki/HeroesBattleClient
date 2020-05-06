function checkHeroCoordinate() {
    $.get("/checkCoordinate", function onSuccess(data) {
            let elementById = document.getElementById('hero');
            elementById.innerText = data;
            console.log(data);
        }
    )
};

setInterval(checkHeroCoordinate, 50);
setTimeout(checkHeroCoordinate,2000);