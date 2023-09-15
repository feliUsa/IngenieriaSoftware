function initMap() {
    var myLatLng = { lat: -34.397, lng: 150.644 }; // Cambia las coordenadas a las que desees mostrar en el mapa
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 8, // Nivel de zoom inicial
      center: myLatLng // Centro del mapa
    });
    var marker = new google.maps.Marker({
      position: myLatLng,
      map: map,
      title: 'Mi Marcador' // Cambia el título del marcador si lo deseas
    });
  }
  