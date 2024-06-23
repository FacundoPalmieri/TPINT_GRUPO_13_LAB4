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