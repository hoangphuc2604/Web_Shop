
//Slider
document.addEventListener('DOMContentLoaded', () => {
    const sliderItem = document.querySelector('.slider-item');
    if (!sliderItem) return;


    const slides = Array.from(sliderItem.querySelectorAll('img'));
    const dots = Array.from(document.querySelectorAll('.dots i'));
    const total = slides.length;
    let current = 0;
    const delay = 3500;
    let timer = null;

    function updateDots() {
        dots.forEach((dot, idx) => {
            dot.classList.toggle('fa-solid', idx === current);
            dot.classList.toggle('fa-regular', idx !== current);
        });
    }

    function goTo(index) {
        current = ((index % total) + total) % total; // luôn nằm trong [0, total-1]
        sliderItem.style.transform = `translateX(-${current * 100}%)`;
        updateDots();
    }

    function startAutoplay() {
        stopAutoplay();
        timer = setInterval(() => goTo(current + 1), delay);
    }

    function stopAutoplay() {
        if (timer) {
            clearInterval(timer);
            timer = null;
        }
    }

    // Click dot
    dots.forEach((dot, idx) => {
        dot.addEventListener('click', () => {
            goTo(idx);
            startAutoplay();
        });
    });

    // Pause on hover
    const container = document.getElementById('slider-container');
    if (container) {
        container.addEventListener('mouseenter', stopAutoplay);
        container.addEventListener('mouseleave', startAutoplay);
    }

    // Khởi tạo
    goTo(0);
    startAutoplay();

});