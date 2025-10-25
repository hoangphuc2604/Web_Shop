//============== Javascript for slider image ===================
document.addEventListener("DOMContentLoaded", function () {
    const sliderItem = document.querySelector('.slider-item');
    const images = document.querySelectorAll('.slider-item img');
    const btnLeft = document.querySelector('.btn-left');
    const btnRight = document.querySelector('.btn-right');
    const dots = document.querySelectorAll('.dots i');

    let currentIndex = 0;
    const totalSlides = images.length;
    let autoSlideInterval;

    function updateSlider() {
        sliderItem.style.transform = `translateX(-${currentIndex * 100}%)`;
        dots.forEach((dot, i) => {
            dot.classList.toggle('fa-solid', i === currentIndex);
            dot.classList.toggle('fa-regular', i !== currentIndex);
        });
    }

    function nextSlide() {
        currentIndex = (currentIndex + 1) % totalSlides;
        updateSlider();
    }

    function prevSlide() {
        currentIndex = (currentIndex - 1 + totalSlides) % totalSlides;
        updateSlider();
    }

    function resetAutoSlide() {
        clearInterval(autoSlideInterval);
        autoSlideInterval = setInterval(nextSlide, 4000);
    }

    btnRight.addEventListener('click', () => {
        nextSlide();
        resetAutoSlide();
    });

    btnLeft.addEventListener('click', () => {
        prevSlide();
        resetAutoSlide();
    });

    dots.forEach((dot, i) => {
        dot.addEventListener('click', () => {
            currentIndex = i;
            updateSlider();
            resetAutoSlide();
        });
    });

    autoSlideInterval = setInterval(nextSlide, 5000);
    updateSlider();
});
//============================================
