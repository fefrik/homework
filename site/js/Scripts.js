
(function() {

    "use strict";


    var Scripts = {
        TRANSLATOR_ENDPOINT: "http://localhost:8000/translator/",
        initialized: false,

        initialize: function() {
            var context = this;
            if (this.initialized) return;
            this.initialized = true;

            this.initTransaltorSelector();

            // event listeners
            this.listen();

        },

        initTransaltorSelector: function() {
            $.get( Scripts.TRANSLATOR_ENDPOINT, function( data ) {
                var html = "";
                for(var key in data) {
                    html += "<option value=" + data[key]  + ">" + data[key] + "</option>"
                }
                document.getElementById("translator").innerHTML = html;
            });
        },

        listen: function() {
            var context = this;
            $("select#translator").change(function(e) {

                var serviceName = $(this).find("option:selected").attr("value");

                switch (serviceName){
                    case "CAESAR":
                        $(".caesarOption").show();
                        break;
                    default:
                        $(".caesarOption").hide();
                        break;
                }

            });
            $("input#translateButton").click(function(e) {
                e.preventDefault();

                // Select
                var translatorType = $("select#translator").val();
                var translatorDirection = $('input[name=action]:checked').val();

                // Create Json Data Object
                var requestData = {};
                requestData.text = $("textarea#sourceText").val();
                requestData.shift = $("input#shift").val();

                var requestJson = JSON.stringify(requestData);

                $.ajax({
                    url: Scripts.TRANSLATOR_ENDPOINT + translatorType + "/" + translatorDirection,
                    type: "POST",
                    data: requestJson,
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",

                    success: function(data) {
                        document.getElementById("resultText").value = data.result;
                    }
                });
            });
        }
    };

    Scripts.initialize();

})();