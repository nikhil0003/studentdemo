
	
var coll = document.getElementsByClassName("collapsible");
var i;
for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    if (content.style.maxHeight){
      content.style.maxHeight = null;
    } else {
      content.style.maxHeight = content.scrollHeight + "px";
    } 
  });
}

let bookSlot = document.getElementById("slotBook");
bookSlot.addEventListener("click", () => {
    let form = document.createElement("form");
    console.log("creating  slootBook Log");
    form.method="post";
    form.action = "/registration";
    document.body.appendChild(form);
    form.submit();
});
