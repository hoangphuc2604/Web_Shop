document.querySelectorAll('.product-qty').forEach(item => {
    const input = item.querySelector('.qty-input');
    const plus = item.querySelector('.increase');
    const minus = item.querySelector('.decrease');

    plus.onclick = () => input.value = Number(input.value) + 1;
    minus.onclick = () => {
        if (input.value > 1) input.value = Number(input.value) - 1;
    };
});
