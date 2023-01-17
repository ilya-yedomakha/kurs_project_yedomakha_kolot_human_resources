document.querySelector('.themetoggle').addEventListener('click', (event) => {
    event.preventDefault();
    if (localStorage.getItem('theme') === 'dark') {
        localStorage.removeItem('theme');
        localStorage.setItem('theme', 'light');
    }
    else {
        localStorage.removeItem('theme');
        localStorage.setItem('theme', 'dark');
    }
    addDarkClassToHTML()
});

function addDarkClassToHTML() {
    try {
        if (localStorage.getItem('theme') === 'dark') {
            document.querySelector('html').classList.remove('light');
            document.querySelector('html').classList.add('dark');
            document.querySelector('.themetoggle span').textContent = 'dark_mode';
        }
        else {
            document.querySelector('html').classList.remove('dark');
            document.querySelector('html').classList.add('light');
            document.querySelector('.themetoggle span').textContent = 'wb_sunny';
        }
    } catch (err) { }
}

addDarkClassToHTML();


function burgerMenu(selector) {
    let menu = $(selector);
    let button = menu.find('.burger-menu__button');
    let links = menu.find('.burger-menu__link');
    let overlay = menu.find('.burger-menu__overlay');

    button.on('click', (e) => {
        e.preventDefault();
        toggleMenu();
    });

    // links.on('click', () => toggleMenu());
    overlay.on('click', () => toggleMenu());

    function toggleMenu() {
        menu.toggleClass('burger-menu_active');
        if (menu.hasClass('burger-menu_active')) {
            $('body').css('overflow', 'hidden');
            var element1 = document.getElementById("tableBlock");
            element1.classList.add("menuOn");
            element1.classList.remove("menuOff");
        } else {
            $('body').css('overflow', 'visible');
            var element = document.getElementById("tableBlock");
            element.classList.add("menuOff");
            element.classList.remove("menuOn");
        }
    }
}

burgerMenu ('.burger-menu');