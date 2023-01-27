const menuToggle = document.querySelector(".menuToggle");
const navigation = document.querySelector(".navigation");
const workPlace = document.querySelector(".workPlace");
function onpenMenu(){
    navigation.classList.toggle('open');
    workPlace.classList.toggle('open');
    if(navigation.classList.contains("open")){
        menuToggle.removeChild(menuToggle.firstChild);
        menuToggle.innerHTML = '<ion-icon name="close-outline"></ion-icon> ';

    }
    else{
        menuToggle.removeChild(menuToggle.firstChild);
        menuToggle.innerHTML = '<ion-icon name="menu-outline"></ion-icon>';
    }
}

let list = document.querySelectorAll(".list");
const title = document.getElementById("title");
function activeLink(){
    list.forEach(item=>item.classList.remove("active"));
    this.classList.add("active");
    let textChild = this.firstChild.nextElementSibling.childNodes[3].innerHTML;
    title.innerHTML = textChild;
}
list.forEach((item)=>{
    item.addEventListener('click', activeLink)
})


