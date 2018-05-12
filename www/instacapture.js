var InstaCapture = {
  enableLogging: function (enable) {
    cordova.exec(
            function(){}, // success callback function
            function(){}, // error callback function
            'InstaCaptureModule', // mapped to our native Java class called
            'enableLogging', // with this action name , in this case 'enableLogging'
            [enable]  )// arguments, if needed
  },
  setJpegCompressionQuality: function(JpegCompressionQuality){
  	cordova.exec(
            function(){}, // success callback function
            function(){}, // error callback function
            'InstaCaptureModule', // mapped to our native Java class called
            'setJpegCompressionQuality', // with this action name , in this case 'setJpegCompressionQuality'
            [JpegCompressionQuality]  )// arguments, if needed
  },
  captureScreenshot: function(successCallback, errorCallback){
  	cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'InstaCaptureModule', // mapped to our native Java class called
            'captureScreenshot', // with this action name , in this case 'captureScreenshot'
            []  )// arguments, if needed
  },
  getImages: function(successCallback, errorCallback){
  	cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'InstaCaptureModule', // mapped to our native Java class called
            'getImages', // with this action name , in this case 'getImages'
            []  )// arguments, if needed
  },
}
module.exports = InstaCapture;