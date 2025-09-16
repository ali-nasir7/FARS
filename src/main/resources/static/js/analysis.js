let barChart, lineChart, pieChart;


async function loadActivities() {
  try {
    const res = await fetch("/api/activities/types");
    const activities = await res.json();
    const select = document.getElementById("faTypeSelect");
    activities.forEach(act => {
      const option = document.createElement("option");
      option.value = act;
      option.text = act;
      select.appendChild(option);
    });
  } catch(err) {
    console.error("Failed to load activities", err);
    document.getElementById("error").innerText = "⚠️ Failed to load activities.";
  }
}

async function loadLeakStats(faType) {
  try {
    let url = "/api/activities/analysis";
    if(faType) url += `?faType=${encodeURIComponent(faType)}`;

    const res = await fetch(url);
    if(!res.ok) throw new Error("Failed to fetch data");

    const data = await res.json();
    const months = data.months || [];
    const counts = data.counts || [];

    if(months.length === 0) {
      document.getElementById("error").innerText = "⚠️ No data available for this selection.";
      if(barChart) barChart.destroy();
      if(lineChart) lineChart.destroy();
      if(pieChart) pieChart.destroy();
      return;
    } else {
      document.getElementById("error").innerText = "";
    }

   
    document.getElementById("prediction").innerText = data.prediction ?? "-";
    document.getElementById("percentage").innerText = data.percentageChange ?? "-";

    
    if(barChart) barChart.destroy();
    if(lineChart) lineChart.destroy();
    if(pieChart) pieChart.destroy();

    // Bar Chart
    barChart = new Chart(document.getElementById("barChart").getContext("2d"), {
      type: 'bar',
      data: {
        labels: months,
        datasets: [{
          label: faType || "Activity",
          data: counts,
          backgroundColor: "rgba(0, 115, 230, 0.7)",
          borderRadius: 6
        }]
      },
      options: { responsive: true, scales:{y:{beginAtZero:true}} }
    });

    // Line Chart
    lineChart = new Chart(document.getElementById("lineChart").getContext("2d"), {
      type: 'line',
      data: {
        labels: months,
        datasets: [{
          label: faType || "Trend",
          data: counts,
          borderColor: "#0073e6",
          tension: 0.3,
          fill: false,
          pointBackgroundColor: "#0073e6"
        }]
      },
      options: { responsive:true, scales:{y:{beginAtZero:true}} }
    });

    // Pie Chart
    pieChart = new Chart(document.getElementById("pieChart").getContext("2d"), {
      type: 'pie',
      data: {
        labels: months,
        datasets: [{
          data: counts,
          backgroundColor: months.map(() => `hsl(${Math.random()*360},70%,60%)`)
        }]
      },
      options: { responsive:true }
    });

  } catch(err){
    console.error(err);
    document.getElementById("error").innerText = "⚠️ Failed to load analysis data.";
  }
}

function viewAnalysis() {
  const faType = document.getElementById("faTypeSelect").value;
  loadLeakStats(faType);
}


loadActivities();
