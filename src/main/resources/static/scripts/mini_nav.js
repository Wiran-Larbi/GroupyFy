const section_btns = {
    contacts : document.getElementById("contacts"),
    groups : document.getElementById("groups")
}
const body = document.body;

const border_btn = `transition-all text-orange-600 border-b-4 border-orange-400 border-solid pb-6`.split(" ");

const set_btn = new Set();
set_btn.add("contacts");
set_btn.add("groups");


// * Adding a Event Listner on All the body
body.addEventListener("click" , (e) => {
        if(e.target.id){
            const target_btn = e.target.id;
            if(set_btn.has(target_btn)){
                if(target_btn === "contacts"){
                    section_btns.contacts.classList.add(...border_btn);
                    section_btns.groups.classList.remove(...border_btn);
                    
                }else if(target_btn === "groups"){
                    section_btns.groups.classList.add(...border_btn);
                    section_btns.contacts.classList.remove(...border_btn);
                }else{
                    console.log("bad id inserted ...");

                }
                //.classList.add(border_btn);
                
                
            }
        }else {
            console.log("Bad Place to Click ...");
        }
})