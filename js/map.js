// // Obtener el mapa de la clase google_maps.
var map = google_maps.getMap();

// Leer el archivo txt.
var markers = [];
fetch('marcadores.txt')
  .then(response => response.text())
  .then(text => {
    // Dividir el texto en líneas.
    var lines = text.split('\n');

    // Crear un marcador para cada línea.
    for (var i = 0; i < lines.length; i++) {
      var coordinates = lines[i].split(',');
      var marker = new google.maps.Marker({
        position: {lat: parseFloat(coordinates[0]), lng: parseFloat(coordinates[1])},
        map: map
      });
      markers.push(marker);
    }
  });