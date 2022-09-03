//this converts the code into a string
var chartDataStr = decodeHtml(chartData); //chartData inside the parenthesis comes from home.html file in the script tag; decodeHtml comes from this file; the result is stored inside the variable called var chartDataStr from this line which just updates the variable since it was already created before;
var chartJsonArray = JSON.parse(chartDataStr); //turns string to json;

var arrayLength = chartJsonArray.length; //this gets the length of chartJsonArray that was created from above this file;

var numericData = [];
var labelData = [];

for(var i=0; i < arrayLength; i++){
	numericData[i] = chartJsonArray[i].value; //gets value and store it in numericData array in each position; numericData comes from the declaration above;
	labelData[i] = chartJsonArray[i].label; // labelData comes from the declaration above;
}

// For a pie chart;
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
   // The data for our dataset;
    data: {
        labels: labelData, //labelData comes from the for loop above
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            data: numericData //numericData comes from the for loop above;
        }]
    },

    // Configuration options go here;
    options: {
		title: {
			display: true,
			text: "Project Statuses"
		}
	}
});

function decodeHtml(html) { //this method will turn the chartData output into a string and to be used from the variable declared niceChartData from above in this file;
	var txt = document.createElement("textarea");
	txt.innerHTML = html; // basically document.createElement("textarea").innerHTML = html
	return txt.value; //value comes from the chartData result tht was create in the HTML file within the script tags;
}


