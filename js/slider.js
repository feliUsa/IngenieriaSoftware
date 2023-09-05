(function(){
    
    const sliders = [...document.querySelectorAll('.menu__body')];
    const buttonNext = document.querySelector('#next');
    const buttonBefore = document.querySelector('#before');
    let value;   

    buttonNext.addEventListener('click', ()=>{
        changePosition(1);
    });

    buttonBefore.addEventListener('click', ()=>{
        changePosition(-1);
    });

    const changePosition = (add)=>{
        const currentmenu = document.querySelector('.menu__body--show').dataset.id;
        value = Number(currentmenu);
        value+= add;


        sliders[Number(currentmenu)-1].classList.remove('menu__body--show');
        if(value === sliders.length+1 || value === 0){
            value = value === 0 ? sliders.length  : 1;
        }

        sliders[value-1].classList.add('menu__body--show');

    }

})();