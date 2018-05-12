document.addEventListener('deviceready', onDeviceReady, false);

function onDeviceReady(){
    console.log("deviceready");

    getImages();
}

function capture(){
    InstaCapture.capture(function(url){
        console.log(url);
        //Do whatever you want with the url
        getImages()
    },function(err){
        console.log(err);
    })
}

function enableLogging(enable){
    InstaCapture.enableLogging(enable);
}

function setJpegCompressionQuality(){
    InstaCapture.setJpegCompressionQuality(document.getElementByID("jpgcom").value);
}

function getImages(){
    InstaCapture.getImages(function(images){
        var text = "";
        for(var i in images){
            text += '<img src="' + images[i] + '" width="50%">';
        }
        var div = document.getElementById('divID').innerHTML = text;

    },function(err){
        console.log(err);
    });
}