document.addEventListener('DOMContentLoaded', function() {
    const sections = document.querySelectorAll('.section');

    sections.forEach(section => {
        const title = section.querySelector('h2');
        const options = section.querySelector('.options');

        title.addEventListener('click', function() {
            options.classList.toggle('visible');
        });
    });
});

function calcularImporteTotal() {
    var importe = parseFloat(document.getElementById("importe").value);
    var cuotas = parseInt(document.getElementById("cuotas").value);
    if (!isNaN(importe) && !isNaN(cuotas) && cuotas > 0) {
        var importeTotal = importe * (1 + (0.05 * cuotas)); // Supongamos una tasa de interés del 5% por cuota
        var importeCuota = importeTotal / cuotas;
        document.getElementById("importeTotal").value = importeTotal.toFixed(2);
        document.getElementById("importeCuota").value = importeCuota.toFixed(2);
    }
}