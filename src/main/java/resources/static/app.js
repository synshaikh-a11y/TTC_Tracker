// API Base URL
const API_BASE = '/api';

// Map instance
let map;
let selectedRoute = null;
let vehicleMarkers = [];
let stopMarkers = [];

// Initialize map
function initMap() {
    // Center on Toronto
    map = L.map('map').setView([43.6532, -79.3832], 12);
    
    // Add tile layer
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors',
        maxZoom: 19
    }).addTo(map);
    
    console.log('Map initialized');
}

// Load routes
async function loadRoutes() {
    try {
        const response = await fetch(API_BASE + '/routes');
        const routes = await response.json();
        
        const routesList = document.getElementById('routes-list');
        routesList.innerHTML = '';
        
        routes.forEach(route => {
            const btn = document.createElement('button');
            btn.className = 'route-btn';
            btn.innerHTML = '<strong>' + route.title + '</strong>';
            btn.onclick = function() {
                selectRoute(route);
            };
            routesList.appendChild(btn);
        });
        
        console.log('Loaded ' + routes.length + ' routes');
    } catch (error) {
        console.error('Error loading routes:', error);
        document.getElementById('routes-list').innerHTML = '<p style="color: red;">Error loading routes</p>';
    }
}

// Select route
async function selectRoute(route) {
    selectedRoute = route;
    
    // Update active button
    document.querySelectorAll('.route-btn').forEach(function(btn) {
        btn.classList.remove('active');
    });
    
    // Find and activate the clicked button
    const buttons = document.querySelectorAll('.route-btn');
    buttons.forEach(function(btn) {
        if (btn.textContent.includes(route.title)) {
            btn.classList.add('active');
        }
    });
    
    // Show route info
    document.getElementById('route-info').innerHTML = 
        '<p><strong>Route:</strong> ' + route.title + '</p>' +
        '<p><strong>Route Tag:</strong> ' + route.routeTag + '</p>' +
        '<p><strong>Type:</strong> Streetcar</p>';
    
    // Load stops and vehicles
    await loadStops(route.routeTag);
    await loadVehicles(route.routeTag);
}

// Load stops for route
async function loadStops(routeTag) {
    try {
        const response = await fetch(API_BASE + '/routes/' + routeTag + '/stops');
        const stops = await response.json();
        
        // Clear existing markers
        stopMarkers.forEach(function(marker) {
            map.removeLayer(marker);
        });
        stopMarkers = [];
        
        // Add stop markers
        stops.forEach(function(stop) {
            const marker = L.marker([stop.lat, stop.lon], {
                icon: L.divIcon({
                    className: 'stop-marker',
                    html: '<div style="background: #ED1B2E; width: 10px; height: 10px; border-radius: 50%; border: 2px solid white;"></div>',
                    iconSize: [14, 14]
                })
            }).addTo(map);
            
            marker.bindPopup(
                '<strong>' + stop.title + '</strong><br>' +
                'Stop ID: ' + stop.stopId + '<br>' +
                '<button onclick="loadPredictions(\'' + stop.stopId + '\')">Show Arrivals</button>'
            );
            
            stopMarkers.push(marker);
        });
        
        // Fit map to stops
        if (stops.length > 0) {
            const bounds = L.latLngBounds(stops.map(function(s) {
                return [s.lat, s.lon];
            }));
            map.fitBounds(bounds, { padding: [50, 50] });
        }
        
        console.log('Loaded ' + stops.length + ' stops');
    } catch (error) {
        console.error('Error loading stops:', error);
    }
}

// Load vehicles for route
async function loadVehicles(routeTag) {
    try {
        const response = await fetch(API_BASE + '/routes/' + routeTag + '/vehicles');
        const vehicles = await response.json();
        
        // Clear existing markers
        vehicleMarkers.forEach(function(marker) {
            map.removeLayer(marker);
        });
        vehicleMarkers = [];
        
        // Add vehicle markers
        vehicles.forEach(function(vehicle) {
            const marker = L.marker([vehicle.lat, vehicle.lon], {
                icon: L.divIcon({
                    className: 'vehicle-marker',
                    html: '<div style="font-size: 24px; color: #ED1B2E;">🚌</div>',
                    iconSize: [30, 30]
                })
            }).addTo(map);
            
            marker.bindPopup(
                '<strong>Vehicle ' + vehicle.id + '</strong><br>' +
                'Route: ' + vehicle.routeTag + '<br>' +
                'Direction: ' + vehicle.dirTag
            );
            
            vehicleMarkers.push(marker);
        });
        
        console.log('Loaded ' + vehicles.length + ' vehicles');
    } catch (error) {
        console.error('Error loading vehicles:', error);
    }
}

// Load predictions for stop
async function loadPredictions(stopId) {
    try {
        const response = await fetch(API_BASE + '/stops/' + stopId + '/predictions');
        const predictions = await response.json();
        
        const predictionsDiv = document.getElementById('predictions');
        
        if (predictions.length === 0) {
            predictionsDiv.innerHTML = '<p class="placeholder">No predictions available</p>';
            return;
        }
        
        let html = '';
        predictions.forEach(function(pred) {
            html += '<div class="prediction-item">' +
                '<div class="prediction-time">' + pred.minutes + ' min</div>' +
                '<div class="prediction-direction">' + pred.directionTitle + '</div>' +
                '<div style="font-size: 0.8rem; color: #999; margin-top: 0.2rem;">Vehicle ' + pred.vehicle + '</div>' +
                '</div>';
        });
        
        predictionsDiv.innerHTML = html;
        
    } catch (error) {
        console.error('Error loading predictions:', error);
    }
}

// Refresh data
function refreshData() {
    if (selectedRoute) {
        loadStops(selectedRoute.routeTag);
        loadVehicles(selectedRoute.routeTag);
    }
}

// Center map on Toronto
function centerMap() {
    map.setView([43.6532, -79.3832], 12);
}

// Initialize app
document.addEventListener('DOMContentLoaded', function() {
    initMap();
    loadRoutes();
    
    // Setup button handlers
    document.getElementById('refresh-btn').addEventListener('click', refreshData);
    document.getElementById('center-btn').addEventListener('click', centerMap);
    
    // Auto-refresh every 30 seconds
    setInterval(function() {
        if (selectedRoute) {
            refreshData();
        }
    }, 30000);
});