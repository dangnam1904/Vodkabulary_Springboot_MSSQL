$(document).ready(function(){
    let boxList = $(".game__box");
    let trace = [];
    $(".game__character").click(function(){
        if ($(this).text()) {
            for (const box of boxList) {
                if (box.innerHTML === "") {
                    trace.push({key: $(this).attr("id"), value: box.id});
                    box.innerHTML = $(this).text();
                    $(this).text("");
                    break;
                }
            }
            console.log(trace);
        }
    });
    for(const box of boxList) {
        box.onclick = function () {
            if (box.innerHTML !== "") {
                let location = trace.find(item => item.value === box.id);
                $(`#${location.key}`).text(box.innerHTML);
                trace = trace.filter(item => item.key !== location.key);
                box.innerHTML = "";
            }
        }
    }

});


