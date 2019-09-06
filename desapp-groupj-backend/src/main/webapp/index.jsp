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
    <h2 style="text-align: center;">Eugenio Calcena / Marcelo Garzon</h2>
    <!--
    The URL for an Maps Embed API request is as follows:
    https://www.google.com/maps/embed/v1/MODE?key=YOUR_API_KEY&parameters
    Where:
    {MODE} is one of place, search, view, directions, or streetview.
    Note: Embed API requests using Place mode or View mode remain free with unlimited usage.
    Place MODE displays a map pin at a particular place or address, such as a landmark, business, geographic feature, or town.
    https://www.google.com/maps/embed/v1/place?key=YOUR_API_KEY&q=Eiffel+Tower,Paris+France
    The following URL parameter is required:
    q defines the place to highlight on the map. It accepts a location as either a place name, address, or place ID.
    The string should be URL-escaped, so an address such as
    "City Hall, New York, NY" should be converted to City+Hall,New+York,NY.
    (The Maps Embed API supports both + and %20 when escaping spaces.)
    Place IDs should be prefixed with place_id:
    {YOUR_API_KEY} is your free API key.
    parameters include optional parameters, as well as mode-specific parameters.
    -->
    <iframe width="600" height="450" frameborder="0" style="border:0"
            src="https://www.google.com/maps/embed/v1/place?q=Lebensohn+52%2C+Bernal%2C+Buenos+Aires%2C+Argentina&key=AIzaSyANpONg-CznLpHdRPClvLm_0NH53mOcEhE"
            allowfullscreen>
    </iframe>
</div>
<div id="map"></div>
<script>
    function initMap() {
        var mapOptions = {
            zoom: 15,
            center: new google.maps.LatLng(-34.71, -58.28),
            mapTypeId: 'roadmap'
        };
        var map = new google.maps.Map(document.getElementById('map'), mapOptions);

        var unqPosition = {lat: -34.706513,lng: -58.278518};
        var marker = new google.maps.Marker({
            position: unqPosition,
            map: map,
            title: 'UNQ'
        });

        var rocaBernalPosition = {lat: -34.709475,lng: -58.280314};
        var marker = new google.maps.Marker({
            position: rocaBernalPosition,
            map: map,
            title: 'ROCA'
        });
    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANpONg-CznLpHdRPClvLm_0NH53mOcEhE&callback=initMap"
        async defer>
</script>
</body>
</html>