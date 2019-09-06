<!DOCTYPE html>
<html>
<head>
    <title>Simple Map</title>
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 50%;
        }
        /* Optional: Makes the sample page fill the window. */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        .alignCenter {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="alignCenter">
    <h2 style="text-align: center;">UNQ - TPI - Desarrollo de Aplicaciones</h2>
    <h2 style="text-align: center;">Grupo J - 022019</h2>
    <h2 style="text-align: center;">ViandasYa Backend</h2>
    <img src="http://h01.perspectivasur.com/archivos/noticias/fotografias/45866_3.jpg">
    <h2 style="text-align: center;">Eugenio Calcena / Marcelo Garzon</h2>
</div>

<div id="map"></div>
<script>
    var map;
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.916229, lng: -57.968492},
            zoom: 15
        });
    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANpONg-CznLpHdRPClvLm_0NH53mOcEhE&callback=initMap"
        async defer></script>
</body>
</html>