"use strict";

// Selectors

// Divs
let resultsDiv = document.querySelector("#results-div")

// Inputs
let bTitleInput = document.querySelector("#bookTitleInput")
let aNameInput = document.querySelector("#authorNameInput")
let dStartedInput = document.querySelector("#dateStartedInput")
let bRatingInput = document.querySelector("#bookRatingInput")
let idInput = document.querySelector("#idInput");

// Buttons
let createBtn = document.querySelector("#createBtn");
let updateBtn = document.querySelector("#updateBtn");
let deleteBtn = document.querySelector("#deleteBtn");


// Functions
let printResults = (result) => {
    let entryDiv = document.createElement("div")
    entryDiv.setAttribute("class", "entry-div")
    entryDiv.textContent = `${result.id} | ${result.bookTitle} | ${result.authorName} | ${result.dateStarted} | ${result.bookRating}`

    resultsDiv.appendChild(entryDiv);
}


let getAll = () => {
    axios.get("http://localhost:8080/book/getAll")
    .then((response) => {

        resultsDiv.innerHTML = "";
        let results = response.data;

        for (let result of results) {
                printResults(result);
        }
        

    }).catch((error) => {console.log(error);});
}

let create =() => {

    let obj = {
        "bookTitle": bTitleInput.value,
        "authorName": aNameInput.value,
        "dateStarted": dStartedInput.value,
        "bookRating": bRatingInput.value,
    }
    axios.post("http://localhost:8080/book/create", obj)
    .then((response) =>{

        getAll();
    }).catch((error) => {console.log(error);});
}

let update = () => {

    let obj = {
        "bookTitle": bTitleInput.value,
        "authorName": aNameInput.value,
        "dateStarted": dStartedInput.value,
        "bookRating": bRatingInput.value,
    }
    axios.put(`http://localhost:8080/book/update/${idInput.value}`, obj)
    .then((response) =>{
        getAll();
    }).catch((error) => {console.error(error);})
}

let del = () => {

    axios.delete(`http://localhost:8080/book/delete/${idInput.value}`)
    .then((response) => {
        getAll();
    }).catch((error) => {console.log(error);})
}


// Listeners
createBtn.addEventListener("click", create);
updateBtn.addEventListener("click", update);
deleteBtn.addEventListener("click", del);