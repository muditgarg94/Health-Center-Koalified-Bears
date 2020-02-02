let dropdown = document.getElementById('patient-dropdown');
console.log(dropdown)
dropdown.length = 0;

let defaultOption = document.createElement('option');
defaultOption.text = 'Select Patient';

dropdown.add(defaultOption);
dropdown.selectedIndex = 0;

const url = 'http://localhost:8080/patients/all';

const request = new XMLHttpRequest();
request.open('GET', url, true);

request.onload = function() {
  if (request.status === 200) {
    const data = JSON.parse(request.responseText);
    let option;
    for (let i = 0; i < data.length; i++) {
      option = document.createElement('option');
      option.text = data[i].firstName+' '+data[i].lastName;
      option.value=data[i].patientId;
	  dropdown.add(option);
     
    }
   } else {
    // Reached the server, but it returned an error
  }   
}

request.onerror = function() {
  console.error('An error occurred fetching the JSON from ' + url);
};

request.send(); 