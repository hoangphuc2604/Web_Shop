
    const links = document.querySelectorAll(".tab-link");
    const sections = document.querySelectorAll(".section");

    links.forEach(link => {
    link.addEventListener("click", () => {
        links.forEach(l => l.classList.remove("active"));
        link.classList.add("active");

        const target = link.getAttribute("data-target");

        sections.forEach(sec => sec.classList.remove("active"));
        document.getElementById(target).classList.add("active");
    });
});
